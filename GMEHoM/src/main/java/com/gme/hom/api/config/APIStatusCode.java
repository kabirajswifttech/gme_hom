package com.gme.hom.api.config;

public enum APIStatusCode {
	R("RETRY"), F("FAILURE");

	private final String name;

	private APIStatusCode(String s) {
		name = s;
	}

	public boolean equalsName(String otherName) {
		return name.equals(otherName);
	}

	public String toString() {
		return this.name;
	}

}
