package com.gme.hom.users.config;

public class UserSignupFunctionCodes {
	public static final String EMAIL_SIGNUP_REQUEST="EMAIL_SIGNUP_REQUEST";
	public static final String SIGNUP_REQUEST="SIGNUP_REQUEST";
	public static final String PHONE_SIGNUP_REQUEST="PHONE_SIGNUP_REQUEST";
	public static final String OTP_VERIFICATION="OTP_VERIFICATION";
	public static final String SETTING_PASSWORD="SETTING_PASSWORD";
	public static final String RESEND_OTP = "RESEND_OTP";
	
	private final String name;

	private UserSignupFunctionCodes(String s) {
		name = s;
	}

	public boolean equalsName(String otherName) {
		return name.equals(otherName);
	}

	public String toString() {
		return this.name;
	}
	
}
