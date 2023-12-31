package com.gme.hom.api.config;

public enum APIRequestFunctionCode {
	SEARCH("SEARCH"), 
	ADD_DATA("ADD_DATA"),
	UPDATE_DATA("UPDATE_DATA"),
	VALIDATE("VALIDATE"),
	GENERATE("GENERATE"),
	SIGNUP("SIGNUP"),
	CHECK_DUPLICATE("CHECK_DUPLICATE"),
	CALCULATE_HASH("CALCULATE_HASH"),
	SEND_MESSAGE("SEND_MESSAGE"),
	UPLOAD("UPLOAD");

	private final String name;

	private APIRequestFunctionCode(String s) {
		name = s;
	}

	public boolean equalsName(String otherName) {
		return name.equals(otherName);
	}

	public String toString() {
		return this.name;
	}
}