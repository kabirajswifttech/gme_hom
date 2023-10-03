package com.gme.hom.messaging.config;

public enum MessageStatusCode {

	// Status for email sending and verification
	SENT("SENT"), VERIFIED("VERIFIED"), RESENT("RESENT"), UNSUCCESSFUL("UNSUCCESSFUL");

	private final String name;

	private MessageStatusCode(String s) {
		name = s;
	}

	public boolean equalsName(String otherName) {
		return name.equals(otherName);
	}

	public String toString() {
		return this.name;
	}

}
