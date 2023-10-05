package com.gme.hom.kyc.config;

public enum MerchantStatusCodes {
	APPROVED("APPROVED"),
	PENDING ("PENDING"),
	REJECTED ("REJECTED"),
	UNDER_REVIEW ("UNDER_REVIEW"),
	EXPIRED ("EXPIRED"),
	ENHANCED_DUE_DELIGENCE ("ENHANCED_DUE_DELIGENCE"),
	PARTIAL_APPROVAL ("PARTIAL_APPROVAL"),
	SUSPENDED ("SUSPENDED"),
	CLEARED ("CLEARED"),
	ON_HOLD ("ON_HOLD");
	
	public String val;
	MerchantStatusCodes(String val){
		this.val=val;
	}
	
	@Override
	public String toString(){
		return val;
	}
	
	
}
