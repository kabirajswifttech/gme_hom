package com.gme.hom.documents.config;

public enum DocumentExtensionCode {
	PNG(".PNG"),
	JPG(".JPG"),
	JPEG(".JPEG"),
	JIFF(".JIFF"),
	DOC(".DOC"),
	DOCX(".DOCX"),
	RTF(".RTF"),
	PDF(".PDF");
	
	private final String name;

	private DocumentExtensionCode(String s) {
		name = s;
	}

	public boolean equalsName(String otherName) {
		return name.equals(otherName);
	}

	public String toString() {
		return this.name;
	}
}
