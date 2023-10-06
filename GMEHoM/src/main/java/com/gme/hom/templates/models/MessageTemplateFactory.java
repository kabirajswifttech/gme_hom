package com.gme.hom.templates.models;

import org.springframework.stereotype.Service;

import com.gme.hom.templates.config.MessageTemplateTypes;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MessageTemplateFactory {

	public static MessageTemplateBody newMessageTemplate(MessageTemplateTypes type) {

		switch (type) {

		case SIGNUP_SMS:
			return new SignupSms();
		case SIGNUP_COMPLETED:
			return new SignupCompleted();
		case KYC_REJECTED:
			return new KycRejected();
		case RESET_PASSWORD:
			return new ResetPassword();
		case USER_LOCKED:
			return new UserLocked();
		case USER_ACTIVATED:
			return new UserActivated();
		case PASSWORD_CHANGED:
			return new PasswordChanged();
		case KYC_APPROVED:
			return new KycApproved();
		case KYC_DETAIL_SUBMITTED:
			return new KycDetailSubmitted();
		case ONBOARDING_REMINDER:
			return new OnBoardingReminder();
		case SIGNUP_OTP:
			return new SignupOtpTemplate();
		default:
			throw new IllegalArgumentException("Invalid  type: " + type);
		}
	}

}
