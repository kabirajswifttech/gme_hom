package com.gme.hom.documents.config;

public enum DocumentTypeCode {
	KYC("KYC"),
	PASSPORT("PASSPORT"),
	COMPANY_REG("COMPANY_REG");
	private final String name;

	private DocumentTypeCode(String s) {
		name = s;
	}

	public boolean equalsName(String otherName) {
		return name.equals(otherName);
	}

	public String toString() {
		return this.name;
	}
}
