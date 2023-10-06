package com.gme.hom.users.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gme.hom.auth.config.OtpTypes;
import com.gme.hom.auth.models.Otp;
import com.gme.hom.auth.service.OtpService;
import com.gme.hom.common.models.EntityTypes;
import com.gme.hom.documents.controllers.DocumentsController;
import com.gme.hom.messaging.config.MessagePurposeCodes;
import com.gme.hom.messaging.config.MessageTypes;
import com.gme.hom.messaging.models.Message;
import com.gme.hom.messaging.models.MessageReceiver;
import com.gme.hom.messaging.services.MessagingService;
import com.gme.hom.templates.config.MessageTemplateTypes;
import com.gme.hom.templates.models.MessageTemplateFactory;
import com.gme.hom.templates.models.SignupOtpTemplate;
import com.gme.hom.templates.repository.TemplateDTO;
import com.gme.hom.templates.services.TemplateService;
import com.gme.hom.users.config.UserSignupStatusCodes;
import com.gme.hom.users.models.UserSignup;
import com.gme.hom.users.models.UserSignupResponse;
import com.gme.hom.users.repository.UserSignupRepository;

@Service
public class UserSignupServiceImpl implements UserSignupService {

	@Autowired
	OtpService otpService;

	@Autowired
	UserSignupRepository userSignupRepo;

	@Autowired
	MessagingService messagingService;

	@Autowired
	TemplateService templateService;

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
	public UserSignup findByPhone(String phoneCode, String phoneNumber) {
		return userSignupRepo.findByPhone(phoneCode + phoneNumber);
	}

	@Override
	public boolean userSignupAlreadyExixts(Long id) {
		return !userSignupRepo.findById(id).isEmpty();
	}

	@Override
	public UserSignupResponse emailUserSignup(UserSignup us) {

		UserSignupResponse userSignupResponse = new UserSignupResponse();

		UserSignup existingUs = userSignupRepo.findByContactInfo(us.getContactInfo());

		if (existingUs != null) {
			// if user already exists

			if (!existingUs.getIsEmailOtpVerified()) {
				// if user email is registered but email otp is not verified
				// then send message for resend otp
				userSignupResponse.setUserSignupStatus(UserSignupStatusCodes.REQUEST_EMAIL_OTP);

			} else {
				// if email is verified

				if (existingUs.getPhoneCode() != null && existingUs.getPhoneNumber() != null) {
					// check if phone no. also exists and verified

					if (existingUs.getIsSmsOtpVerified()) {

						userSignupResponse.setUserSignupStatus(UserSignupStatusCodes.PROCEED_LOGIN);

					} else {

						userSignupResponse.setUserSignupStatus(UserSignupStatusCodes.RESEND_PHONE_OTP);
					}

				} else {
					// if phone no. doexn't exist but email was verified
					userSignupResponse.setUserSignupStatus(UserSignupStatusCodes.PROCEED_LOGIN);
				}
			}

		} else {
			// if user doesn't exist
			// then start normal signup process

			// setting created user
			us.setCreatedBy(us.getContactInfo());

			// setting up the contains of not null
			us.setIsEmailOtpVerified(false);
			us.setIsSmsOtpVerified(false);
			us.setStatus(UserSignupStatusCodes.CREATED);
			try {

				us = this.saveUserSignup(us);

			} catch (Exception e) {

				logger.error(e.getMessage());

			}

			// extract id of persisted entity
			userSignupResponse.setId(us.getId());
			userSignupResponse.setUserSignupStatus(UserSignupStatusCodes.CREATED);

			// generate OTP
			Otp otp = otpService.generateOtp(us.getContactInfo(), OtpTypes.EMAIL, EntityTypes.USER_SIGNUP, us.getId());

			if (otp != null) {
				
				// building message using message template

				SignupOtpTemplate sot = (SignupOtpTemplate) MessageTemplateFactory
						.newMessageTemplate(MessageTemplateTypes.SIGNUP_OTP);

				TemplateDTO mt = templateService.getTemplateByTypeAndPurpose(MessageTypes.EMAIL,
						MessageTemplateTypes.SIGNUP_OTP);

				sot.setTemplate(mt.getTemplate());

				sot.setOtp(otp.getOtpCode());

				String message = sot.buildMessage();

				// send otp in email
				Message m = new Message();
				m.setContactInfo(us.getContactInfo());
				m.setContent(message);
				m.setMessageType(MessageTypes.EMAIL);
				m.setSubject(mt.getSubject());
				m.setAssociationId(us.getId());
				m.setAssociationType(EntityTypes.USER_SIGNUP);
				m.setPurposeCode(MessagePurposeCodes.SIGNUP);

				MessageReceiver mr = new MessageReceiver();
				mr.setFullname(us.getFullName());
				// mr.setMessage(message);

				boolean messageSendingSuccess = messagingService.sendMessage(m, mr);

				if (!messageSendingSuccess) {

					userSignupResponse.setUserSignupStatus(UserSignupStatusCodes.REQUEST_EMAIL_OTP);
				}

			}

		}

		return userSignupResponse;
	}

}
