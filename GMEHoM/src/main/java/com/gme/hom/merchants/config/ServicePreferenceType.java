package com.gme.hom.merchants.config;

public enum ServicePreferenceType {
	MAKE_PAYMENT("MAKE_PAYMENT"),
	GET_PAYMENT("GET_PAYMENT");
	String val;
	
	private ServicePreferenceType(String val) {
		this.val =val;
	}
}
