package com.gme.hom.users.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.gme.hom.messaging.config.MessageStatusCode;

public class UserResponse {
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("messaging_status")
	MessageStatusCode messagingStatus;
}
