package com.gme.hom.api.internal.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gme.hom.api.config.APIRequestFunctionCode;
import com.gme.hom.api.config.APIResponseCode;
import com.gme.hom.api.models.APIRequest;
import com.gme.hom.api.models.APIResponse;
import com.gme.hom.messaging.models.Message;
import com.gme.hom.messaging.models.MessageRequest;
import com.gme.hom.messaging.services.MessagingService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/internal/messaging")
public class MessagingController {

	private final MessagingService messagingService;

	MessagingController(MessagingService messagingService) {
		this.messagingService = messagingService;
	}

	// INFO: To be used by internal services. MUST BE DISABLED FOR PRODUCTION
	@PostMapping("")
	public ResponseEntity<APIResponse> sendEmail(@RequestBody APIRequest apiRequest, HttpServletRequest httpRequest,
			HttpServletResponse httpResponse) {

		APIResponse ar = new APIResponse();

		if (apiRequest.getFunction().equals(APIRequestFunctionCode.SEND_MESSAGE.toString())) {

			MessageRequest mr = apiRequest.getData().getMessageRequest();

			if (mr != null) {
				
				Message m = new Message(mr);

				if (messagingService.sendMessage(m)) {

					ar.setStatus(APIResponseCode.SUCCESS.toString());

					ar.setData(mr.getContactInfo());

					ar.setDescription("Message sending successful.");

					ar.setDescription("INFO: To be used by internal services only. MUST BE DISABLED FOR PRODUCTION");

				}
				return ResponseEntity.ok(ar);

			}

		}
		ar.setStatus(APIResponseCode.FAILURE.toString());

		return ResponseEntity.ok(ar);

	}

}
