package com.gme.hom.users.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gme.hom.api.config.APIResponseCode;
import com.gme.hom.api.models.APIData;
import com.gme.hom.auth.models.OTP;
import com.gme.hom.auth.service.OTPService;
import com.gme.hom.documents.controllers.DocumentsController;
import com.gme.hom.messaging.config.MessageTypes;
import com.gme.hom.messaging.models.Message;
import com.gme.hom.messaging.services.MessagingService;
import com.gme.hom.users.config.UserContactTypes;
import com.gme.hom.users.config.UserStatusCode;
import com.gme.hom.users.models.UserSignup;
import com.gme.hom.users.models.UserSignupRequest;
import com.gme.hom.users.models.UserSignupResponse;
import com.gme.hom.users.repository.UserSignupRepository;

@Service
public class UserSignupServiceImpl implements UserSignupService {

	@Autowired
	OTPService otpService;

	@Autowired
	UserSignupRepository userSignupRepo;

	@Autowired
	MessagingService messagingService;

	private static final Logger logger = LoggerFactory.getLogger(DocumentsController.class);

	@Override
	public UserSignup saveUserSignup(UserSignup userSignup) {
		return userSignupRepo.save(userSignup);
	}

	@Override
	public Optional<UserSignup> getUserSignupById(Long id) {
		return userSignupRepo.findById(id);
	}

	@Override
	public Optional<UserSignup> findByPhoneNumber(String phoneCode, String phoneNumber) {
		return userSignupRepo.findByPhoneNumber(phoneCode, phoneNumber);
	}

	@Override
	public boolean userSignupAlreadyExixts(Long id) {
		return !userSignupRepo.findById(id).isEmpty();
	}

	@Override
	public String verifyOTP(APIData data, Long id) throws Exception {
		UserSignupRequest usr = data.getUserSignupRequest();
		OTP otp = data.getOtp();
		Optional<UserSignup> userSignup = getUserSignupById(id);
		if (!userSignup.isEmpty()) {
			if (usr.getContactType().equals(UserContactTypes.EMAIL.toString())) {
				if (!userSignup.get().getIsEmailOtpVerified()) {
					if (userSignup.get().getContactInfo().equals(usr.getContactInfo())
							&& otp.getContactInfo().equals(userSignup.get().getContactInfo())) {
						int otpValue = otpService.getOtp(usr.getContactInfo());
						if (otpValue == Integer.parseInt(otp.getOtpCode())) {
							if (otpValue == Integer.parseInt(otp.getOtpCode())) {
								UserSignup us = getUserSignupById(id).get();
								us.setStatus("Email OTP Verified");
								us.setIsEmailOtpVerified(true);
								us.setId(id);
								us = saveUserSignup(us);

							} else {
								throw new Exception("OTP expired!");
							}
						} else {
							throw new Exception("Email OTP verification failed!");
						}
					} else {
						throw new Exception("Credentials did not match!");
					}
				} else {
					throw new Exception("Email already verified!");
				}
			} else if (usr.getContactType().equals(UserContactTypes.PHONE.toString())) {
				if (!userSignup.get().getIsEmailOtpVerified()) {
					if (userSignup.get().getPhoneNumber().equals(usr.getContactInfo())) {
						int otpValue = otpService.getOtp(usr.getContactInfo());
						if (otpValue == Integer.parseInt(otp.getOtpCode())) {
							if (otpValue == Integer.parseInt(otp.getOtpCode())) {
								UserSignup us = getUserSignupById(id).get();
								us.setStatus("Email OTP Verified");
								us.setIsEmailOtpVerified(true);
								us.setId(id);
								us = saveUserSignup(us);

							} else {
								throw new Exception("OTP expired!");
							}
						} else {
							throw new Exception("SMS OTP verification failed!");
						}
					} else {
						throw new Exception("Credentials did not match!");
					}
				} else {
					throw new Exception("Phone number already verified!");
				}
			} else {
				throw new Exception("Contact type not recognized!");
			}
		}
		return APIResponseCode.SUCCESS.toString();

	}

	@Override
	public UserSignupResponse emailSignup(UserSignup us) throws Exception {

		UserSignupResponse usr = new UserSignupResponse();

		// check for unique user id
		if (!signupUserAlreadyExisits(us.getContactInfo())) {

			// setting created user
			us.setCreatedBy(us.getContactInfo());

			// setting up the contains of not null
			us.setIsEmailOtpVerified(false);
			us.setIsSmsOtpVerified(false);
			us.setStatus(UserStatusCode.CREATED.toString());
			us = this.saveUserSignup(us);
			
			// extract id of persisted entity
			usr.setId(us.getId());
			usr.setUserStatusCode(UserStatusCode.CREATED);
			
			// generate OTP

			OTP otp = new OTP();
			otp.setContactInfo(us.getContactInfo());
			otp.setPurpose("Email Verification");
			Integer otpCode = otpService.generateOTP(us.getContactInfo());
			otp.setOtpCode(String.valueOf(otpCode));

			logger.debug(otpCode.toString());

			// send opt in email
			Message m = new Message();
			m.setContactInfo(us.getContactInfo());
			m.setContent(otp.getOtpCode());
			m.setMessageType(MessageTypes.EMAILMIME);
			m.setSubject("GME Remit: OTP code for user signup ");
			
			boolean emailSent = messagingService.sendMessage(m);
			if(emailSent) {
				usr.setUserStatusCode(UserStatusCode.OTP_SENT);
			} else {
				usr.setUserStatusCode(UserStatusCode.CREATED);
			}
			
		}

		else {
			usr.setUserStatusCode(UserStatusCode.DUPLICATE);

	//		throw new Exception("User already exists...");
		}

		return usr;

	}

	@Override
	public Long startPhoneSignup(UserSignupRequest usr) throws Exception {
		usr.setPhoneNumber(usr.getContactInfo());
		UserSignup us = new UserSignup(usr);
		if (!usr.getPhoneNumber().isEmpty() && !usr.getSourceType().isEmpty()
				&& !usr.getIncorporationCountry().isEmpty()) {
			// check for unique user id
			/*
			 * if (!merchantService.phoneNumberAlreadyRegistered(usr.getPhoneNumber())) {
			 * OTP otp = new OTP(); otp.setContactInfo(usr.getPhoneCode() +
			 * usr.getPhoneNumber());
			 * otp.setPurpose(SignupTypeCodes.PHONE_NUMBER_VERIFICATION); // generate otp
			 * code Integer otpCode = otpService.generateOTP(usr.getPhoneNumber());
			 * otp.setOtpCode(String.valueOf(otpCode)); System.out.println(otpCode); // save
			 * userSignup object to database us.setStatus(SignupTypeCodes.SMS_OTP_SENT);
			 * us.setCreatedBy(usr.getPhoneNumber()); us = this.saveUserSignup(us);
			 * 
			 * // mail the otp to users email address
			 * 
			 * }
			 * 
			 * else { throw new Exception("User already exists..."); }
			 */
		} else {
			throw new Exception("Insufficient information...");
		}
		return us.getId();
	}

	@Override
	public Long startEmailAndPhoneSignup(UserSignupRequest usr) throws Exception {

		UserSignup us = new UserSignup(usr);

		if (!usr.getEmailId().isEmpty() && !usr.getSourceType().isEmpty() && !usr.getIncorporationCountry().isEmpty()) {
			// check for unique user id
			/*
			 * if (!merchantService.merchantAlreadyExixts(usr.getEmailId()) &&
			 * !merchantService.phoneNumberAlreadyRegistered(usr.getPhoneNumber())) { //
			 * generate email otp OTP otp = new OTP(); otp.setContactInfo(usr.getEmailId());
			 * otp.setPurpose("Email Verification"); Integer otpCode =
			 * otpService.generateOTP(usr.getEmailId());
			 * otp.setOtpCode(String.valueOf(otpCode));
			 * System.out.println("Email OTP: "+otpCode); // mail the otp to users email
			 * address
			 * 
			 * 
			 * // generate SMS otp OTP otp2 = new OTP();
			 * otp2.setContactInfo(usr.getPhoneCode() + usr.getPhoneNumber());
			 * otp2.setPurpose(SignupTypeCodes.PHONE_NUMBER_VERIFICATION); Integer otpCode2
			 * = otpService.generateOTP(usr.getPhoneNumber());
			 * otp2.setOtpCode(String.valueOf(otpCode2));
			 * System.out.println("SMS OTP:"+otpCode2); // save userSignup object to
			 * database us.setStatus("Email and SMS OTP Sent");
			 * us.setCreatedBy(usr.getEmailId()); us = this.saveUserSignup(us); //send SMS
			 * OTP
			 * 
			 * }
			 * 
			 * else { throw new Exception("User already exists..."); }
			 */
		} else {
			throw new Exception("Insufficient information...");
		}
		return us.getId();
	}

	@Override
	public String resendEmailOTP(UserSignupRequest usr, Long id) throws Exception {
		// Optional<UserSignup> userSignup = getUserSignupById(id);
		if (!usr.getContactInfo().isEmpty() && !usr.getAssociationType().isEmpty()
				&& !usr.getIncorporationCountry().isEmpty()) {
			// check for unique user id
			/*
			 * if (!merchantService.merchantAlreadyExixts(usr.getContactInfo())) { if
			 * (userSignupAlreadyExixts(id)) { if
			 * (getUserSignupById(id).get().isEmailOtpVerified() &&
			 * getUserSignupById(id).get().getEmailId().equals(usr.getEmailId())) { throw
			 * new Exception("Email Already verified!"); } else if
			 * (!userSignup.get().getEmailId().equals(usr.getContactInfo())) { throw new
			 * Exception("Credentials did not match!"); } else { OTP otp = new OTP();
			 * otp.setContactInfo(usr.getContactInfo());
			 * otp.setPurpose("Email Verification"); // generate otp code Integer otpCode =
			 * otpService.generateOTP(usr.getContactInfo());
			 * otp.setOtpCode(String.valueOf(otpCode)); System.out.println(otpCode); // save
			 * userSignup object to database UserSignup us = userSignup.get();
			 * us.setStatus("Email OTP sent again."); //us.setUpdatedBy(us.getEmailId()); us
			 * = saveUserSignup(us); // mail the otp to users email address
			 * 
			 * // fill response object
			 * 
			 * } } else { throw new Exception("No such user found..."); } }
			 */
			/*
			 * else { throw new Exception("User already exists..."); }
			 */
		} else {
			throw new Exception("Insufficient information...");
		}
		return APIResponseCode.SUCCESS.toString();
	}

	@Override
	public String resendSMSOTP(UserSignupRequest usr, Long id) throws Exception {
		UserSignup us = getUserSignupById(id).get();
		if (us.getPhoneNumber().equals(usr.getContactInfo())) {
			OTP otp = new OTP();
			otp.setContactInfo(usr.getPhoneNumber());
			otp.setPurpose("Phone Number Verification");
			// generate otp code
			Integer otpCode = otpService.generateOTP(usr.getPhoneCode() + usr.getContactInfo());
			otp.setOtpCode(String.valueOf(otpCode));
			System.out.println(otpCode);
			// save user object to database
			us.setPhoneNumber(usr.getPhoneCode() + usr.getContactInfo());
			us.setStatus("SMS OTP Sent");
			// us.setUpdatedBy(us.getEmailId());
			us = saveUserSignup(us);
			// send the otp to users phone Number

		} else {
			throw new Exception("Phone number did not match");
		}
		return APIResponseCode.SUCCESS.toString();
	}

	@Override
	public String setPasswordByEmail(UserSignupRequest usr, Long id) throws Exception {
		Optional<UserSignup> us = getUserSignupById(id);
		if (!us.isEmpty() && us.get().getContactInfo().equals(usr.getEmailId())) {
			/*
			 * if (us.get().isEmailOtpVerified() && us.get().getPhoneNumber().isEmpty()) {
			 * Merchant merchant = new Merchant(us.get());
			 * merchant.setCreatedBy(usr.getEmailId()); merchant.setStatus("PENDING");
			 * merchant.setActive(true);
			 * 
			 * merchant = merchantService.addMerchant(merchant);
			 * 
			 * UserRequest ur = new UserRequest(); ur.setEmailId(usr.getEmailId());
			 * ur.setUsername(usr.getEmailId()); ur.setPassword(new
			 * BCryptPasswordEncoder().encode(usr.getPassword()));
			 * ur.setMerchantId(merchant.getId()); ur.setStatus("ACTIVE");
			 * ur.setActive(true); userService.addUser(ur); } else { throw new
			 * Exception("Email or Phone number not verified!"); }
			 */
		} else {
			throw new Exception("No Such user found!");
		}
		return APIResponseCode.SUCCESS.toString();
	}

	@Override
	public String setPasswordByPhoneNumber(UserSignupRequest usr, Long id) throws Exception {
		Optional<UserSignup> us = getUserSignupById(id);
		if (!us.isEmpty() && us.get().getPhoneNumber().equals(usr.getPhoneNumber())
				&& us.get().getPhoneCode().equals(usr.getPhoneCode())) {
			/*
			 * if (us.get().isSmsOtpVerified() && us.get().getEmailId().isEmpty()) {
			 * Merchant merchant = new Merchant(us.get());
			 * merchant.setCreatedBy(usr.getEmailId()); merchant.setStatus("PENDING");
			 * merchant.setActive(true);
			 * 
			 * merchant = merchantService.addMerchant(merchant);
			 * 
			 * UserRequest ur = new UserRequest(); ur.setMerchantId(merchant.getId());
			 * ur.setEmailId(usr.getEmailId()); ur.setUsername(usr.getEmailId());
			 * ur.setPassword(new BCryptPasswordEncoder().encode(usr.getPassword()));
			 * ur.setMerchantId(merchant.getId()); ur.setStatus("ACTIVE");
			 * ur.setActive(true); userService.addUser(ur); } else { throw new
			 * Exception("Email or Phone number not verified!"); }
			 */
		} else {
			throw new Exception("No Such user found!");
		}
		return APIResponseCode.SUCCESS.toString();
	}

	@Override
	public String setPasswordByPhoneAndEmail(UserSignupRequest usr, Long id) throws Exception {
		Optional<UserSignup> us = getUserSignupById(id);
		if (!us.isEmpty() && us.get().getPhoneNumber().equals(usr.getPhoneNumber())
				&& us.get().getPhoneCode().equals(usr.getPhoneCode())) {
			/*
			 * if (us.get().isSmsOtpVerified() && us.get().isEmailOtpVerified()) { Merchant
			 * merchant = new Merchant(us.get()); merchant.setCreatedBy(usr.getEmailId());
			 * merchant.setStatus("PENDING"); merchant.setActive(true);
			 * 
			 * merchant = merchantService.addMerchant(merchant);
			 * 
			 * UserRequest ur = new UserRequest(); ur.setMerchantId(merchant.getId());
			 * ur.setEmailId(usr.getEmailId()); ur.setUsername(usr.getEmailId());
			 * ur.setPassword(new BCryptPasswordEncoder().encode(usr.getPassword()));
			 * ur.setMerchantId(merchant.getId()); ur.setStatus("ACTIVE");
			 * ur.setActive(true); userService.addUser(ur); } else { throw new
			 * Exception("Email or Phone number not verified!"); }
			 */
		} else {
			throw new Exception("No Such user found!");
		}
		return APIResponseCode.SUCCESS.toString();
	}

	@Override
	public String addEmail(UserSignupRequest usr, Long id) throws Exception {
		Optional<UserSignup> userSignup = getUserSignupById(id);
		usr.setEmailId(usr.getContactInfo());
		if (usr.getPhoneNumber().equals(userSignup.get().getPhoneNumber())
				&& usr.getAssociationType().equals(userSignup.get().getSourceType())
				&& usr.getIncorporationCountry().equals(userSignup.get().getIncorporationCountry())) {
			// check for unique user id
			/*
			 * if (!merchantService.merchantAlreadyExixts(usr.getEmailId())) { OTP otp = new
			 * OTP(); otp.setContactInfo(usr.getEmailId());
			 * otp.setPurpose("Email Verification"); // generate otp code Integer otpCode =
			 * otpService.generateOTP(usr.getEmailId());
			 * otp.setOtpCode(String.valueOf(otpCode)); System.out.println(otpCode); // save
			 * otp object to database
			 * 
			 * // save userSignup object to database UserSignup us = new UserSignup(usr);
			 * us.setStatus("Email OTP Sent"); us.setCreatedBy(usr.getEmailId()); us =
			 * saveUserSignup(us);
			 * 
			 * // mail the otp to users email address
			 * 
			 * // fill response object }
			 * 
			 * else { throw new Exception("User already exists..."); }
			 */
		} else {
			throw new Exception("Conflicting information...");
		}
		return APIResponseCode.SUCCESS.toString();
	}

	@Override
	public String addPhoneNumber(UserSignupRequest usr, Long id) throws Exception {

		usr.setPhoneNumber(usr.getContactInfo());
		Optional<UserSignup> userSignup = getUserSignupById(id);
		if (usr.getEmailId().equals(userSignup.get().getContactInfo())
				&& usr.getAssociationType().equals(userSignup.get().getSourceType())
				&& usr.getIncorporationCountry().equals(userSignup.get().getIncorporationCountry())) {
			// check for unique user id
			/*
			 * if (!merchantService.phoneNumberAlreadyRegistered(usr.getPhoneNumber())) {
			 * OTP otp = new OTP(); otp.setContactInfo(usr.getPhoneNumber());
			 * otp.setPurpose(SignupTypeCodes.PHONE_NUMBER_VERIFICATION); // generate otp
			 * code Integer otpCode = otpService.generateOTP(usr.getPhoneNumber());
			 * otp.setOtpCode(String.valueOf(otpCode)); System.out.println(otpCode); // save
			 * otp object to database
			 * 
			 * // save userSignup object to database UserSignup us = new UserSignup(usr);
			 * us.setPhoneCode(usr.getPhoneCode()); us.setPhoneNumber(usr.getContactInfo());
			 * us.setStatus(SignupTypeCodes.SMS_OTP_SENT);
			 * us.setCreatedBy(usr.getPhoneNumber()); us = saveUserSignup(us);
			 * id=us.getId(); // mail the otp to users email address
			 * 
			 * } else { throw new Exception("User already exists..."); }
			 */
		} else {
			throw new Exception("Conflicting information...");
		}

		return APIResponseCode.SUCCESS.toString();
	}

	boolean signupUserAlreadyExisits(String username) {

		UserSignup us = userSignupRepo.findByEmailId(username);

		if (us != null) {
			return true;
		}

		return false;
	}

}
