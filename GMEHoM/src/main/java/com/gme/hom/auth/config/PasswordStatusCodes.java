package com.gme.hom.auth.config;

public enum PasswordStatusCodes {
	// Status for password change
	ACTIVE("ACTIVE"),
	SUCCESS("SUCCESS"),
	FAILURE("FAILURE"),
	EXPIRED("EXPIRED");
	

	private final String name;

	private PasswordStatusCodes(String s) {
		name = s;
	}

	public boolean equalsName(String otherName) {
		return name.equals(otherName);
	}

	public String toString() {
		return this.name;
	}
}
