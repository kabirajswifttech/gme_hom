package com.gme.hom.kyc.merchants.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.gme.hom.messaging.config.MessageStatusCode;

public class MerchantResponse {
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("status")
	MessageStatusCode status;
}
