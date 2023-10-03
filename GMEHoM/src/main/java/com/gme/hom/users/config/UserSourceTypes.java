package com.gme.hom.users.config;

public enum UserSourceTypes {
	MERCHANT("MERCHANT");
	
	private final String name;

	private UserSourceTypes(String s) {
		name = s;
	}

	public boolean equalsName(String otherName) {
		return name.equals(otherName);
	}

	public String toString() {
		return this.name;
	}

}
