package com.gme.hom.messaging.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.gme.hom.GlobalConfig;
import com.gme.hom.messaging.config.MessageStatusCodes;
import com.gme.hom.messaging.config.MessageTypes;
import com.gme.hom.messaging.models.Message;
import com.gme.hom.messaging.models.MessageReceiver;
import com.gme.hom.messaging.repository.MessagingRepository;
import com.gme.hom.security.services.ChecksumService;
import com.gme.hom.templates.services.TemplateService;
import com.gme.hom.usersecurity.services.UserSecurityService;

@Service
public class MessagingServiceImpl implements MessagingService {

	@Autowired
	JavaMailSender mailSender;

	@Autowired
	MessagingRepository messagingRepository;

	@Autowired
	MessagingAsyncService messagingAsyncService;

	@Autowired
	TemplateService templateService;

	public boolean sendMessage(Message m, MessageReceiver mr) {

		if (m.getMessageType() == MessageTypes.EMAIL) {

			messagingAsyncService.sendMimeEmail(m);

			if (saveMessage(m) != null) {
				return true;
			} else {
				return false;
			}

		} else if (m.getMessageType() == MessageTypes.SMS) {

			// System.out.println("Sending SMS to " + mr.getContactInfo() + "...");

			// TODO: HTTPS template

			// TODO: move all to an SWIFT specific service.
			// TODO: make a generic SMS sender that changes the specific service upon
			// different config

			// Preparing HTTP authentication header

			/*
			 * 
			 * String basicAuthStr = SwiftSMSConfig.USERNAME + ":" +
			 * SwiftSMSConfig.PASSWORD; String baseAuth64Str = "Basic" +
			 * Base64.getEncoder().encodeToString(basicAuthStr.getBytes());
			 * 
			 * HttpHeaders headers = new HttpHeaders(); headers.add("Authorization",
			 * baseAuth64Str); headers.add("Content-Type", SwiftSMSConfig.CONTENT_TYPE);
			 * headers.add("OrganisationCode", SwiftSMSConfig.ORG_CODE);
			 * 
			 * // add request body Map<String, String> requestBody = new HashMap<String,
			 * String>(); requestBody.put("IsClientLogin", "N"); requestBody.put("Username",
			 * SwiftSMSConfig.USERNAME); requestBody.put("Password",
			 * SwiftSMSConfig.PASSWORD); requestBody.put("OrganisationCode",
			 * SwiftSMSConfig.ORG_CODE); requestBody.put("ReceiverNo", mr.getContactInfo());
			 * requestBody.put("Message", mr.getContent());
			 * 
			 * 
			 * HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(requestBody,
			 * headers);
			 * 
			 * // ResponseEntity<SwiftSMSResponse> response = //
			 * restTemplate.exchange(SwiftSMSConfig.URL, HttpMethod.POST, request, //
			 * SwiftSMSResponse.class);
			 * 
			 * 
			 * RestTemplate restTemplate = new RestTemplate();
			 * 
			 * ResponseEntity<SwiftSMSResponse> responseEntity =
			 * restTemplate.postForEntity(SwiftSMSConfig.URL + SwiftSMSConfig.SMS_API,
			 * requestEntity, SwiftSMSResponse.class);
			 * 
			 * 
			 * SwiftSMSResponse smsResponse = responseEntity.getBody();
			 * 
			 * System.out.println(smsResponse.toString());
			 * 
			 * if (smsResponse.getResponseCode() == 100) { return true; }
			 */
			return true;

		}
		return false;
	}

	private Message saveMessage(Message m) {
		try {
			m.setEntityHash(ChecksumService.getChecksum(m, GlobalConfig.DATA_ENTITY_HASH));
			m.setMessageQueueId(UUID.randomUUID());
			m.setStatus(MessageStatusCodes.SENT.toString());

			if (UserSecurityService.getUsername() != null) {
				// if the message is being sent by authenticated user
				m.setCreatedBy(UserSecurityService.getUsername());
			} else {
				// else, whatever be the purpose code
				m.setCreatedBy(m.getPurposeCode().toString());
			}

			return messagingRepository.save(m);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
