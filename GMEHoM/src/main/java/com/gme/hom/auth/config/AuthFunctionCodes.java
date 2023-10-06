package com.gme.hom.auth.config;

public enum AuthFunctionCodes {
	
	AUTHENTICATE("AUTHENTICATE"),
	VALIDATE_OTP("VALIDATE_OTP"),
	REQUEST_OTP("REQUEST_OTP");
	
	private final String name;

	private AuthFunctionCodes(String s) {
		name = s;
	}

	public boolean equalsName(String otherName) {
		return name.equals(otherName);
	}

	public String toString() {
		return this.name;
	}

}
