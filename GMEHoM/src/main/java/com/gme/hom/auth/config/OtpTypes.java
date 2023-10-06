package com.gme.hom.auth.config;

public enum OtpTypes {
	EMAIL("EMAIL"),
	SMS("SMS");
	
	private final String name;

	private OtpTypes(String s) {
		name = s;
	}

	public boolean equalsName(String otherName) {
		return name.equals(otherName);
	}

	public String toString() {
		return this.name;
	}

}
