package com.gme.hom.users.config;

public enum UserTypeCodes {
	ROLE_ADMIN("ROLE_ADMIN"), 
	ROLE_ADMIN_USER("ROLE_ADMIN_USER"), 
	ROLE_MERCHANT("ROLE_MERCHANT"),
	ROLE_MERCHANT_USER("ROLE_MERCHANT_USER"), 
	
	ADMIN("ADMIN"), 
	ADMIN_USER("ADMIN_USER"),
	MERCHANT("MERCHANT"), 
	MERCHANT_USER("MERCHANT_USER");

	private final String name;

	private UserTypeCodes(String s) {
		name = s;
	}

	public boolean equalsName(String otherName) {
		return name.equals(otherName);
	}

	public String toString() {
		return this.name;
	}
}
