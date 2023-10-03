package com.gme.hom.messaging.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageRequest {

	@JsonProperty("message_type")
	private String messageType;

	@JsonInclude(Include.NON_NULL)
	private String priority;

	@JsonProperty("contact_info")
	private String contactInfo;

	@JsonInclude(Include.NON_NULL)
	private String cc;

	@JsonInclude(Include.NON_NULL)
	private String bcc;

	private String content;

	private String subject;

	@JsonProperty("association_id")
	private long associationId;

	@JsonProperty("association_type")
	private String associationType;

	@JsonProperty("purpose_id")
	private long purposeId;

	@JsonProperty("purpose_type")
	private String purposeType;

	@JsonInclude(Include.NON_NULL)
	@JsonProperty("status")
	private String status;

	@JsonInclude(Include.NON_NULL)
	@JsonProperty("schedule_time")
	private java.util.Date scheduleTime;

	@JsonInclude(Include.NON_NULL)
	@JsonProperty("schedule_time_utc")
	private java.util.Date scheduleTimeUtc;

	@JsonInclude(Include.NON_NULL)
	@JsonProperty("reference")
	private String reference;

}
