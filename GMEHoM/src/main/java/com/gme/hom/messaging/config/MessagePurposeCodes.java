package com.gme.hom.messaging.config;

public enum MessagePurposeCodes {
	SIGNUP("SIGNUP"),
	OTP("OTP");
	
	private final String name;

	private MessagePurposeCodes(String s) {
		name = s;
	}

	public boolean equalsName(String otherName) {
		return name.equals(otherName);
	}

	public String toString() {
		return this.name;
	}

}
