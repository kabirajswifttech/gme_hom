package com.gme.hom.auth.service;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gme.hom.GlobalConfig;
import com.gme.hom.auth.config.OtpStatusCodes;
import com.gme.hom.auth.config.OtpTypes;
import com.gme.hom.auth.models.Otp;
import com.gme.hom.auth.models.OtpResponse;
import com.gme.hom.auth.models.OtpStatus;
import com.gme.hom.auth.repository.OtpDTO;
import com.gme.hom.auth.repository.OtpRepository;
import com.gme.hom.common.models.EntityTables;
import com.gme.hom.common.models.EntityTypes;
import com.gme.hom.documents.controllers.DocumentsController;
import com.gme.hom.messaging.config.MessagePurposeCodes;
import com.gme.hom.messaging.config.MessageTypes;
import com.gme.hom.messaging.models.Message;
import com.gme.hom.messaging.models.MessageReceiver;
import com.gme.hom.messaging.services.MessagingService;
import com.gme.hom.security.services.ChecksumService;
import com.gme.hom.users.models.UserSignup;
import com.gme.hom.users.repository.UserSignupRepository;
import com.gme.hom.usersecurity.services.UserSecurityService;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

@Service
public class OtpServiceImpl implements OtpService {

	@Autowired
	OtpRepository otpRepository;

	@Autowired
	UserSignupRepository userSignupRepository;

	@Autowired
	MessagingService messagingService;

	private LoadingCache<String, Integer> otpCache;

	private static final Logger logger = LoggerFactory.getLogger(DocumentsController.class);

	public OtpServiceImpl() {
		super();

		otpCache = CacheBuilder.newBuilder().expireAfterWrite(GlobalConfig.AUTH_OTP_MAXAGE, TimeUnit.MINUTES)
				.build(new CacheLoader<String, Integer>() {
					public Integer load(String key) {
						return 0;
					}
				});
	}

	private int generateOTP(String key) {
		Random random = new Random();
		int otp = 100000 + random.nextInt(900000);
		otpCache.put(key, otp);

		// TODO: store OTP with meta info in database
		return otp;
	}

	private Otp saveOTP(Otp otp) {
		try {
			otp.setEntityHash(ChecksumService.getChecksum(otp, GlobalConfig.DATA_ENTITY_HASH));
			otp.setOtpId(UUID.randomUUID());
			otp.setStatus(OtpStatusCodes.SENT);
			otp.setActive(true);

			if (UserSecurityService.getUsername() != null) {
				// if the message is being sent by authenticated user
				otp.setCreatedBy(UserSecurityService.getUsername());
			} else {
				// else, whatever be the purpose code
				otp.setCreatedBy(otp.getContactInfo());
			}
			return otpRepository.save(otp);

		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	private int getOtp(String key) {
		try {
			return otpCache.get(key);
		} catch (Exception e) {
			return 0;
		}
	}

	private void clearOTP(String key) {
		otpCache.invalidate(key);
	}

	private boolean updateOtpStatusTrue(EntityTypes entityType, OtpTypes otpType, Long sourceId, String contactInfo) {
		
		// updates otp verification status in different entity tables
		// e.g. if sourceType is USER_SIGNUP, then updates the status in USER_SIGNUP table using entity table map

		String tableName = EntityTables.entityTables.get(entityType.toString());

		if (tableName != null) {

			if (entityType == EntityTypes.MERCHANT) {

			} else if (entityType == EntityTypes.USER_SIGNUP) {

				if (otpType == OtpTypes.EMAIL) {

					if (userSignupRepository.setEmailOtpVerifiedTrue(sourceId, contactInfo) != 0) {
						return true;
					}
				}
				else if (otpType == OtpTypes.SMS) {
					if (userSignupRepository.setSmsOtpVerifiedTrue(sourceId, contactInfo) != 0) {
						return true;
					}
				}
			}
		}

		return false;
	}

	private OtpStatus getOtpStatus(EntityTypes entityType, OtpTypes otpType, String contactInfo) {
		// check for email otp status in respective entity type table
		// looks for 'is_email_otp_verified' field in the table
		// using field 'contact_info' with value 'contactInfo'

		OtpStatus otpStatus = new OtpStatus();

		// get table name from entity tables map
		String tableName = EntityTables.entityTables.get(entityType.toString());

		otpStatus.setOtpStatusCode(OtpStatusCodes.UNSUCCESSFUL);

		if (tableName != null) {
			// if table name exists

			if (entityType == EntityTypes.MERCHANT) {
				// if the entity is of type MERCHANT

			} else if (entityType == EntityTypes.USER_SIGNUP) {
				// if the entity is of type USER_SIGNUP

				if (otpType == OtpTypes.EMAIL) {

					UserSignup us = userSignupRepository.findByContactInfo(contactInfo);

					if (us != null) {
						// if user signup data is found
						otpStatus.setContactInfo(contactInfo);
						otpStatus.setEntityType(entityType);
						otpStatus.setId(us.getId());
						otpStatus.setFullName(us.getFullName());
						otpStatus.setOtpType(otpType);

						if (otpType == OtpTypes.EMAIL) {
							// if message type is email
							if (!us.getIsEmailOtpVerified()) {
								otpStatus.setOtpStatusCode(OtpStatusCodes.NOT_VERIFIED);
							} else {
								otpStatus.setOtpStatusCode(OtpStatusCodes.VERIFIED);
							}
						} else if (otpType == OtpTypes.SMS) {
							// if message type is SMS
							if (!us.getIsSmsOtpVerified()) {
								otpStatus.setOtpStatusCode(OtpStatusCodes.NOT_VERIFIED);
							} else {
								otpStatus.setOtpStatusCode(OtpStatusCodes.VERIFIED);
							}

						}
						// add for other types of OTP messages if required
					} // end of if (us != null) {
				} else if (otpType == OtpTypes.SMS) {

					UserSignup us = userSignupRepository.findByPhone(contactInfo);

					if (us != null) {
						// if user signup data is found
						otpStatus.setContactInfo(contactInfo);
						otpStatus.setEntityType(entityType);
						otpStatus.setId(us.getId());
						otpStatus.setFullName(us.getFullName());
						otpStatus.setOtpType(otpType);

						if (otpType == OtpTypes.EMAIL) {
							// if message type is email
							if (!us.getIsEmailOtpVerified()) {
								otpStatus.setOtpStatusCode(OtpStatusCodes.NOT_VERIFIED);
							} else {
								otpStatus.setOtpStatusCode(OtpStatusCodes.VERIFIED);
							}
						} else if (otpType == OtpTypes.SMS) {
							// if message type is SMS
							if (!us.getIsSmsOtpVerified()) {
								otpStatus.setOtpStatusCode(OtpStatusCodes.NOT_VERIFIED);
							} else {
								otpStatus.setOtpStatusCode(OtpStatusCodes.VERIFIED);
							}

						}

					} else {

					}

				} // end of otptype = SMS
			} // end of USER_SIGNUP entity type

		} // add for other entity types
		return otpStatus;
	}

	public Otp generateOtp(String key, OtpTypes otpType, EntityTypes sourceType, Long sourceId) {

		int otpValue = generateOTP(key);

		logger.info(String.valueOf(otpValue));
		// System.out.println("OTP: " + String.valueOf(otpValue));

		Otp otp = new Otp();

		otp.setContactInfo(key);
		otp.setOtpCode(String.valueOf(otpValue));
		otp.setOtpType(otpType);
		otp.setSourceType(sourceType);
		otp.setSourceId(sourceId);

		return saveOTP(otp);
	}

	public boolean validateOtp(Otp otp) {

		int otpVal = getOtp(otp.getContactInfo());

		// System.out.println("OTP: " + otpVal);

		if (otpVal != 0) {

			// obtain most recent otp from db
			OtpDTO otpDto = otpRepository.getOtp(otp.getContactInfo(), otp.getOtpCode(), otp.getSourceType().toString(),
					otp.getSourceId());

			if (otpDto != null) {
				// if the details of otp match

				if (OtpTypes.valueOf(otpDto.getOtp_type()) == OtpTypes.EMAIL) {
					String sourceType = otpDto.getSource_type();
					Long sourceId = otpDto.getSource_id();
					String contactInfo = otpDto.getContact_info();

					if (updateOtpStatusTrue(EntityTypes.valueOf(sourceType), OtpTypes.valueOf(otpDto.getOtp_type()),
							sourceId, contactInfo)) {
						return true;
					}
				} else if (OtpTypes.valueOf(otpDto.getOtp_type()) == OtpTypes.SMS) {

					String sourceType = otpDto.getSource_type();
					Long sourceId = otpDto.getSource_id();
					String contactInfo = otpDto.getContact_info();

					if (updateOtpStatusTrue(EntityTypes.valueOf(sourceType), OtpTypes.valueOf(otpDto.getOtp_type()),
							sourceId, contactInfo)) {
						return true;
					}

				} else {
					return false;
				}
			} else {
				System.out.println("OTP info not found!");
			}
		}

		return false;
	}

	public OtpResponse requestOtp(EntityTypes sourceType, OtpTypes otpType, String contactInfo) {

		OtpResponse otpResponse = new OtpResponse();

		OtpStatus otpStatus = getOtpStatus(sourceType, otpType, contactInfo);

		if (otpStatus.getOtpStatusCode() == OtpStatusCodes.VERIFIED) {
			// otp was already verified
			otpResponse.setOtpStatus(OtpStatusCodes.VERIFIED);

		} else {

			int otpValue = generateOTP(contactInfo);

			logger.info(String.valueOf(otpValue));
			// System.out.println("OTP: " + String.valueOf(otpValue));

			// generate and save OTP
			Otp otp = generateOtp(contactInfo, otpType, sourceType, otpStatus.getId());

			if (otp != null) {

				if (otpType == OtpTypes.EMAIL) {

					// send otp in email
					Message m = new Message();
					m.setContactInfo(contactInfo);
					// m.setContent(otp.getOtpCode());
					m.setMessageType(MessageTypes.EMAIL);
					// m.setSubject("GME Remit: OTP code for user signup ");
					m.setAssociationId(otpStatus.getId());
					m.setAssociationType(sourceType);

					// check source type then put the purpose code
					m.setPurposeCode(MessagePurposeCodes.SIGNUP);

					MessageReceiver mr = new MessageReceiver();
					mr.setFullname(otpStatus.getFullName());
					mr.setMessage(otp.getOtpCode());

					boolean messageSendingSuccess = messagingService.sendMessage(m, mr);

					if (messageSendingSuccess) {

						otpResponse.setOtpStatus(OtpStatusCodes.RESENT);
						otpResponse.setOtp(otp);
					} else {

						otpResponse.setOtpStatus(OtpStatusCodes.UNSUCCESSFUL);

					}
				} // if otpType == OtpTypes.EMAIL
				else if (otpType == OtpTypes.SMS) {
					// send otp in via SMS

					Message m = new Message();
					m.setMessageType(MessageTypes.SMS);

					MessageReceiver mr = new MessageReceiver();

					boolean messageSendingSuccess = messagingService.sendMessage(m, mr);

					if (messageSendingSuccess) {
						otpResponse.setOtpStatus(OtpStatusCodes.RESENT);
						otpResponse.setOtp(otp);
					} else {

						otpResponse.setOtpStatus(OtpStatusCodes.UNSUCCESSFUL);
					}
				}
			}
		}

		return otpResponse;
	}

}
