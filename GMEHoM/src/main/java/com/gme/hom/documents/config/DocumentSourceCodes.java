package com.gme.hom.documents.config;

public enum DocumentSourceCodes {
	MERCHANT("MERCHANT"),
	TRANSACTION("TRANSACTION");
	
	private final String name;

	private DocumentSourceCodes(String s) {
		name = s;
	}

	public boolean equalsName(String otherName) {
		return name.equals(otherName);
	}

	public String toString() {
		return this.name;
	}

}
