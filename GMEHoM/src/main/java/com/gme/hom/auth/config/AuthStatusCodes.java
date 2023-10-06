package com.gme.hom.auth.config;

public enum AuthStatusCodes {
	AUTHENTICATED("AUTHENTICATED"), 
	BAD_CREDENTIALS("BAD_CREDENTIALS");

	private final String name;

	private AuthStatusCodes(String s) {
		name = s;
	}

	public boolean equalsName(String otherName) {
		return name.equals(otherName);
	}

	public String toString() {
		return this.name;
	}

}
