package com.gme.hom.api.external.swifttech.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SwiftSMSResponse {

	@JsonProperty("responseCode")
	private int responseCode;
	
	@JsonProperty("responseDescription")
	private String responseDescription;
	
	@JsonProperty("smsText")
	private String smsText;
	
	@Override
	public String toString() {
		return this.responseCode + this.responseDescription + this.smsText;
	}

}
