package com.gme.hom.messaging.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gme.hom.messaging.config.MessageStatusCode;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageResponse {
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("messaging_status")
	MessageStatusCode messagingStatus;

}
