package com.gme.hom.merchants.config;

public enum MerchantType {
	INDIVIDUAL("INDIVIDUAL"),
	BUSINESS("BUSINESS");
	private String val;
	MerchantType(String val) {
		this.val = val;
	}
	public String toString() {
		return val;
	}
}
