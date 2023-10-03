package com.example.demo.api;

public enum APIResponseCode {
	SUCCESS("SUCCESS"),
	FAILURE("FAILURE"),
	DUPLICATE("DUPLICATE"),
	UNIQUE("UNIQUE");
	
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
