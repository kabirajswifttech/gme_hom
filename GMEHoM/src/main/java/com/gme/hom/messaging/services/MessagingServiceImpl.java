package com.gme.hom.messaging.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.gme.hom.GlobalConfig;
import com.gme.hom.messaging.config.MessageStatusCode;
import com.gme.hom.messaging.config.MessageTypes;
import com.gme.hom.messaging.models.Message;
import com.gme.hom.messaging.repository.MessagingRepository;
import com.gme.hom.security.services.ChecksumService;
import com.gme.hom.usersecurity.services.UserSecurityService;

import jakarta.mail.internet.MimeMessage;

@Service
public class MessagingServiceImpl implements MessagingService {

	@Autowired
	JavaMailSender mailSender;

	@Autowired
	MessagingRepository messagingRepository;

	public boolean sendMessage(Message m) {

		if (m.getMessageType() ==(MessageTypes.EMAIL)) {

			sendEmail(m);

			return true;

		} else if (m.getMessageType() == MessageTypes.EMAILMIME) {

			sendMimeEmail(m);

			return true;

		} else if (m.getMessageType() == MessageTypes.EMAILWATT) {

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
			return false;

		}
		return false;
	}

	private boolean sendEmail(Message m) {
		try {
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setFrom(GlobalConfig.MAIL_SERVER_FROM.toString());
			mailMessage.setTo(m.getContactInfo());
			mailMessage.setText(m.getContent());
			mailMessage.setSubject(m.getSubject());

			mailSender.send(mailMessage);

			if (saveMessage(m) != null) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	private boolean sendMimeEmail(Message m) {
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			mimeMessageHelper.setFrom(GlobalConfig.MAIL_SERVER_FROM.toString());
			mimeMessageHelper.setTo(m.getContactInfo());
			mimeMessageHelper.setText(m.getContent(), true);
			mimeMessageHelper.setSubject(m.getSubject());

			mailSender.send(mimeMessage);

			if (saveMessage(m) != null) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return false;
	}

	public boolean sendMessageWithAttachment(Message m) {
		return false;
	}

	private Message saveMessage(Message m) {
		try {
			m.setEntityHash(ChecksumService.getChecksum(m, GlobalConfig.DATA_ENTITY_HASH));
			m.setMessageQueueId(UUID.randomUUID());
			m.setStatus(MessageStatusCode.SENT.toString());
			m.setCreatedBy(UserSecurityService.getUsername());

			return messagingRepository.save(m);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
