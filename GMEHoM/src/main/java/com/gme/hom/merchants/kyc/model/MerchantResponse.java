package com.gme.hom.merchants.kyc.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.gme.hom.messaging.config.MessageStatusCodes;

public class MerchantResponse {
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("status")
	MessageStatusCodes status;
}
