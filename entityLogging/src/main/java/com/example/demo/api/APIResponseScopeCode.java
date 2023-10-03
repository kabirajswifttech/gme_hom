package com.example.demo.api;

public enum APIResponseScopeCode {
	
	SINGLE("SINGLE"),
	MULTIPLE("MULTIPLE");
	
	private final String name;

	private APIResponseScopeCode(String s) {
		name = s;
	}

	public boolean equalsName(String otherName) {
		return name.equals(otherName);
	}

	public String toString() {
		return this.name;
	}

}
