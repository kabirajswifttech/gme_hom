package com.gme.hom.messaging.services;

import com.gme.hom.messaging.models.Message;
import com.gme.hom.messaging.models.MessageReceiver;

public interface MessagingService {
	
	public boolean sendMessage(Message m, MessageReceiver mr);

}
