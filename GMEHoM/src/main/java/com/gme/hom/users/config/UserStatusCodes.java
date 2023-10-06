package com.gme.hom.users.config;

public enum UserStatusCodes {
	
	// user signup codes, if SOURCE_TYPE = MERCHANT
	LOCKED("LOCKED"),
	ACTIVE("ACTIVE"),
	DELETED("DELETED"),
	SUSPENDED("SUSPENDED");
	
	private final String name;

	private UserStatusCodes(String s) {
		name = s;
	}

	public boolean equalsName(String otherName) {
		return name.equals(otherName);
	}

	public String toString() {
		return this.name;
	}

}
