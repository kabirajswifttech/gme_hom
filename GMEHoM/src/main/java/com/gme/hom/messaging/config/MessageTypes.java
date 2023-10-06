package com.gme.hom.messaging.config;

public enum MessageTypes {
	
	EMAIL("EMAIL"),
	SMS("SMS");
	
	private final String name;

	private MessageTypes(String s) {
		name = s;
	}

	public boolean equalsName(String otherName) {
		return name.equals(otherName);
	}

	public String toString() {
		return this.name;
	}
}
