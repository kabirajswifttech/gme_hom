package com.gme.hom.users.config;

public enum UserContactTypes {
	EMAIL("EMAIL"), 
	PHONE("PHONE"), 
	EMAIL_AND_PHONE("EMAIL_AND_PHONE");

	private final String name;

	private UserContactTypes(String s) {
		name = s;
	}

	public boolean equalsName(String otherName) {
		return name.equals(otherName);
	}

	public String toString() {
		return this.name;
	}

}
