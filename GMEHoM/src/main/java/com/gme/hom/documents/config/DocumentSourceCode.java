package com.gme.hom.documents.config;

public enum DocumentSourceCode {
	MERCHANT("MERCHANT"),
	TRANSACTION("TRANSACTION");
	
	private final String name;

	private DocumentSourceCode(String s) {
		name = s;
	}

	public boolean equalsName(String otherName) {
		return name.equals(otherName);
	}

	public String toString() {
		return this.name;
	}

}
