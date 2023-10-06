package com.gme.hom.messaging.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.gme.hom.GlobalConfig;
import com.gme.hom.messaging.models.Message;

import jakarta.mail.internet.MimeMessage;

@Service
public class MessagingAsyncService {

	@Autowired
	JavaMailSender mailSender;

	@Async
	public void sendMimeEmail(Message m) {
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			mimeMessageHelper.setFrom(GlobalConfig.MAIL_SERVER_FROM.toString());
			mimeMessageHelper.setTo(m.getContactInfo());
			mimeMessageHelper.setText(m.getContent(), true);
			mimeMessageHelper.setSubject(m.getSubject());

			mailSender.send(mimeMessage);

			return;

		} catch (Exception e) {
			e.printStackTrace();

		}
		return;
	}

	public void sendEmail(Message m) {
		try {
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setFrom(GlobalConfig.MAIL_SERVER_FROM.toString());
			mailMessage.setTo(m.getContactInfo());
			mailMessage.setText(m.getContent());
			mailMessage.setSubject(m.getSubject());

			mailSender.send(mailMessage);

			return;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}

	public boolean sendMessageWithAttachment(Message m) {
		return false;
	}

}
