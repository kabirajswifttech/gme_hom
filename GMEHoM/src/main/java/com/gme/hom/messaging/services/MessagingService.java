package com.gme.hom.messaging.services;

import com.gme.hom.messaging.models.Message;

public interface MessagingService {
	
	public boolean sendMessage(Message m);
	public boolean sendMessageWithAttachment(Message m);

}
