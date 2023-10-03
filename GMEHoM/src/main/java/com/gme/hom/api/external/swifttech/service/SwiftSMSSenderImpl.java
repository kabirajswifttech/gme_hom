package com.gme.hom.api.external.swifttech.service;

import com.gme.hom.api.external.swifttech.models.SwiftSMSRequest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SwiftSMSSenderImpl implements SwiftSMSSender {
	
	private String url;
	private String username;
	private String password;
	private String contentType;
	private String orgCode;
	private String isClientLogin;
	
	public SwiftSMSSenderImpl() {
		super();
	}

	@Override
	public int sendSwiftSMS(SwiftSMSRequest swiftSMSRequest) {
		// TODO Auto-generated method stub
		return 0;
	}

}
