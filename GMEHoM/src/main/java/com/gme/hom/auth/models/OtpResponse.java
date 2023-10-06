package com.gme.hom.auth.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gme.hom.auth.config.OtpStatusCodes;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OtpResponse {

	@JsonInclude(Include.NON_NULL)
	@JsonProperty("otp_status")
	OtpStatusCodes otpStatus;

	@JsonInclude(Include.NON_NULL)
	@JsonProperty("otp")
	Otp otp;

}
