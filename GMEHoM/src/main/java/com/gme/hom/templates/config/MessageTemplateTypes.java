package com.gme.hom.templates.config;

public enum MessageTemplateTypes{
	
	
	SIGNUP_SMS("SIGNUP_SMS"),
	SIGNUP_OTP("SIGNUP_OTP"),
	SIGNUP_COMPLETED("SIGNUP_COMPLETED"),
	KYC_REJECTED("KYC_REJECTED"),
	RESET_PASSWORD("RESET_PASSWORD"),
	USER_LOCKED("USER_LOCKED"),
	USER_ACTIVATED("USER_ACTIVATED"),
	PASSWORD_CHANGED("PASSWORD_CHANGED"),
	KYC_APPROVED("KYC_APPROVED"),
	KYC_DETAIL_SUBMITTED("KYC_DETAIL_SUBMITTED"),
	ONBOARDING_REMINDER("ONBOARDING_REMINDER");

	private final String name;

	private MessageTemplateTypes(String s) {
		name = s;
	}

	public boolean equalsName(String otherName) {
		return name.equals(otherName);
	}

	public String toString() {
		return this.name;
	}
}
