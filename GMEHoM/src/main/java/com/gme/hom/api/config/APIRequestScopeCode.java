package com.gme.hom.api.config;

public enum APIRequestScopeCode {
	SINGLE("SINGLE"),
	MULTIPLE("MULTIPLE"),
	ALL("ALL"),
	BYKEYWORD("BYKEYWORD"),
	BYSUBTYPE("BYSUBTYPE"),
	EMAILOTP("EMAILOTP"),
	SMSOTP("SMSOTP"),
	BYID("BYID");
	
	private final String name;

	private APIRequestScopeCode(String s) {
		name = s;
	}

	public boolean equalsName(String otherName) {
		return name.equals(otherName);
	}

	public String toString() {
		return this.name;
	}

}
