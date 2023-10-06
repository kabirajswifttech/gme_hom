package com.gme.hom.common.models;

public enum EntityTypes {
	MERCHANT("MERCHANT"),
	USER_SIGNUP("USER_SIGNUP"),
	USERS("USERS");
	
	private final String name;

	private EntityTypes(String s) {
		name = s;
	}

	public boolean equalsName(String otherName) {
		return name.equals(otherName);
	}

	public String toString() {
		return this.name;
	}
	

}
