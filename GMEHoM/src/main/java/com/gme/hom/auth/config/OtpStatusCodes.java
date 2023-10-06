package com.gme.hom.auth.config;

public enum OtpStatusCodes {

	// Status for email sending and verification
	SENT("SENT"), VERIFIED("VERIFIED"), RESENT("RESENT"), UNSUCCESSFUL("UNSUCCESSFUL"), NOT_VERIFIED("NOT_VERIFIED"),
	EXPIRED("EXPIRED");

	private final String name;

	private OtpStatusCodes(String s) {
		name = s;
	}

	public boolean equalsName(String otherName) {
		return name.equals(otherName);
	}

	public String toString() {
		return this.name;
	}
}
