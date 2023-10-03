package com.gme.hom.api.external.swifttech.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SwiftSMSRequest {
	
	@JsonProperty("IsClientLogin")
	private String IsClientLogin;
	
	@JsonProperty("Username")
	private String Username;
	
	@JsonProperty("Password")
	private String Password;
	
	@JsonProperty("OrganisationCode")
	private String OrganisationCode;
	
	@JsonProperty("ReceiveNo")
	private String ReceiveNo;
	
	@JsonProperty("Message")
	private String Message;
}
