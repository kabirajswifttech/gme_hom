package com.gme.hom.auth.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gme.hom.auth.config.OtpStatusCodes;
import com.gme.hom.auth.config.OtpTypes;
import com.gme.hom.common.models.EntityTypes;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OtpStatus {
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("otp_status_Code")
	OtpStatusCodes otpStatusCode;

	@JsonInclude(Include.NON_NULL)
	@JsonProperty("entity_type")
	EntityTypes entityType;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("otp_type")
	OtpTypes otpType;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("id")
	Long id;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("contact_info")
	String contactInfo;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("full_name")
	String fullName;
	

}
