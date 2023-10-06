package com.gme.hom.users.config;


public enum UserSignupStatusCodes {
	INIT("INIT"),
	CREATED("CREATED"),
	ACTIVE("ACTIVE"),
	COMPLETED("COMPLETED"),
	OTP_SENT("OTP_SENT"),
	REQUEST_EMAIL_OTP("REQUEST_EMAIL_OTP"),
	RESEND_PHONE_OTP("RESEND_PHONE_OTP"),
	PROCEED_LOGIN("PROCEED_LOGIN");

	private final String name;

	private UserSignupStatusCodes(String s) {
		name = s;
	}

	public boolean equalsName(String otherName) {
		return name.equals(otherName);
	}

	public String toString() {
		return this.name;
	}

}
