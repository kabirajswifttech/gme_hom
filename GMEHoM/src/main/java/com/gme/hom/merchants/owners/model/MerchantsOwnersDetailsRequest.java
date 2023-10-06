package com.gme.hom.merchants.owners.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MerchantsOwnersDetailsRequest {


	@JsonInclude(Include.NON_NULL)
	@JsonProperty("merchant_id")
	private long merchantId;

	@JsonProperty("full_name")
	private String fullName;

	@JsonProperty("full_name_native")
	private String fullNameNative;

	@JsonProperty("role")
    private String role;

    @JsonProperty("nationality")
    private String nationality;

	@JsonProperty("residence_country")
	private String residenceCountry;

	@JsonProperty("phone_code")
	private String phoneCode;

	@JsonProperty("phone_number")
	private String phoneNumber;

	@JsonProperty("email_id")
	private String emailId;

}
