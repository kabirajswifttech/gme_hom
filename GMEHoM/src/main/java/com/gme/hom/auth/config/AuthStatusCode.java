package com.gme.hom.auth.config;

public enum AuthStatusCode {
	AUTHENTICATED("AUTHENTICATED"), 
	BAD_CREDENTIALS("BAD_CREDENTIALS");

	private final String name;

	private AuthStatusCode(String s) {
		name = s;
	}

	public boolean equalsName(String otherName) {
		return name.equals(otherName);
	}

	public String toString() {
		return this.name;
	}

}
