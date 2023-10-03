package com.gme.hom.users.config;

public enum UserStatusCode {
	CREATED("CREATED"),
	DUPLICATE("DUPLICATE"),
	OTP_SENT("OTP_SENT");
	
	private final String name;

	private UserStatusCode(String s) {
		name = s;
	}

	public boolean equalsName(String otherName) {
		return name.equals(otherName);
	}

	public String toString() {
		return this.name;
	}

}
