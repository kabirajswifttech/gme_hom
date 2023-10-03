package com.gme.hom.usersecurity.config;

public enum UserSecurityStatus {
	ACTIVE("ACTIVE");

	private final String name;

	private UserSecurityStatus(String s) {
		name = s;
	}

	public boolean equalsName(String otherName) {
		return name.equals(otherName);
	}

	public String toString() {
		return this.name;
	}

}
