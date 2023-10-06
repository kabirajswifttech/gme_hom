package com.gme.hom.usersecurity.config;

public enum UserSecurityStatusCodes {
	ACTIVE("ACTIVE");

	private final String name;

	private UserSecurityStatusCodes(String s) {
		name = s;
	}

	public boolean equalsName(String otherName) {
		return name.equals(otherName);
	}

	public String toString() {
		return this.name;
	}

}
