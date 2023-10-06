package com.gme.hom.messaging.models;

import com.gme.hom.messaging.config.MessagePurposeCodes;
import com.gme.hom.templates.config.MessageTemplateTypes;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageReceiver {
	
	private String fullname;
	private String contactInfo;
	private String message;
	private MessagePurposeCodes messagePurposeCode;
	private MessageTemplateTypes messageTemplateType;
}
