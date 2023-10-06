package com.gme.hom.auth.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gme.hom.auth.config.OtpTypes;
import com.gme.hom.common.models.EntityTypes;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OtpRequest {
	
	@JsonProperty("contact_info")
	@JsonInclude(Include.NON_NULL)
	private String contactInfo;
	
	@JsonProperty("phone_code")
	@JsonInclude(Include.NON_NULL)
	private String phoneCode;
	
	@JsonProperty("phone_no")
	@JsonInclude(Include.NON_NULL)
	private String phoneNo;

	@JsonProperty("otp_code")
	@JsonInclude(Include.NON_NULL)
	private String otpCode;
	
	@Enumerated(EnumType.STRING)
	@JsonProperty("source_type")
	@JsonInclude(Include.NON_NULL)
	private EntityTypes sourceType;
	
	@JsonProperty("id")
	@JsonInclude(Include.NON_NULL)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@JsonProperty("otp_type")
	@JsonInclude(Include.NON_NULL)
	private OtpTypes OtpType;
	
	
	
}
