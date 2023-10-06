package com.gme.hom.api.config;

public enum APIResponseCode {
	SUCCESS("SUCCESS"),
	FAILURE("FAILURE"),
	DUPLICATE("DUPLICATE"),
	UNIQUE("UNIQUE"),
	INVALID_REQUEST("INVALID_REQUEST"),
	UNKNOWN("UNKNOWN");
	
	private final String name;

	private APIResponseCode(String s) {
		name = s;
	}

	public boolean equalsName(String otherName) {
		return name.equals(otherName);
	}

	public String toString() {
		return this.name;
	}
}
