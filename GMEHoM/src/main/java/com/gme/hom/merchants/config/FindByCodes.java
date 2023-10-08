package com.gme.hom.merchants.config;

public enum FindByCodes {
	MERCHANT_ID("MERCHANT_ID"),
	EMAIL_ID("EMAIL_ID"),
	MERCHANTS_BANK_DETAIL_ID("MERCHANTS_BANK_DETAIL_ID"), 
	MERCHANTS_DIRECTORS_DETAILS_ID("MERCHANTS_DIRECTORS_DETAILS_ID");
	public String var;
	FindByCodes(String var) {
		this.var=var;
	}
	@Override
	public String toString(){
		return var;
	}
}
