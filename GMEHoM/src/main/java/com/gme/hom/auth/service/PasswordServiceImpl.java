package com.gme.hom.auth.service;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gme.hom.GlobalConfig;
import com.gme.hom.auth.config.PasswordStatusCodes;
import com.gme.hom.auth.models.PasswordResetRequests;
import com.gme.hom.auth.models.PasswordResponse;
import com.gme.hom.auth.repository.PasswordResetRequestRepository;
import com.gme.hom.messaging.config.MessageTypes;
import com.gme.hom.messaging.models.Message;
import com.gme.hom.messaging.models.MessageReceiver;
import com.gme.hom.messaging.services.MessagingService;
import com.gme.hom.security.services.ChecksumService;
import com.gme.hom.security.services.RandomTokenGenerator;
import com.gme.hom.templates.config.MessageTemplateTypes;
import com.gme.hom.templates.models.MessageTemplateFactory;
import com.gme.hom.templates.models.PasswordChanged;
import com.gme.hom.templates.models.ResetPassword;
import com.gme.hom.templates.repository.TemplateDTO;
import com.gme.hom.templates.services.TemplateService;
import com.gme.hom.users.models.User;
import com.gme.hom.users.models.UserLog;
import com.gme.hom.users.repository.UserLogRepository;
import com.gme.hom.users.repository.UserRepository;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PasswordServiceImpl implements PasswordService {

	@Autowired
	PasswordResetRequestRepository passwordResetRequestRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	private  MessagingService messagingService;
	@Autowired
	private  TemplateService templateService;
	@Autowired
	UserLogRepository userLogRepo;
	
	private static final Logger logger = LoggerFactory.getLogger(PasswordServiceImpl.class);

	@Override
	public PasswordResponse generateResetPasswordLink(Long id, String actionUserName,String RequestedIp){   

		
		
		PasswordResponse response =new PasswordResponse();
		try {
			
			LocalDateTime localDateTime = LocalDateTime.now();
			Timestamp localtimestamp = Timestamp.valueOf(localDateTime);	
			
			// Convert to UTC
	        ZonedDateTime utcDateTime = ZonedDateTime.of(localDateTime, ZoneId.of("UTC"));
	    	Timestamp localutctimestamp = Timestamp.valueOf(localDateTime);	
			
	        LocalDateTime addedlocalDateTime=localDateTime.plusHours(24);
			
			Timestamp timestamp = Timestamp.valueOf(addedlocalDateTime);			
			PasswordResetRequests entity=new PasswordResetRequests();	
			
			entity.setRequestTimestamp(localtimestamp);			
			entity.setCreatedDate(localtimestamp);
			entity.setCreatedDateUtc(localutctimestamp);
			entity.setTokenExpirationTimestamp(timestamp);	
			
			entity.setUserId(id);
			entity.setToken(RandomTokenGenerator.generateToken());
			entity.setCreatedBy(actionUserName);			
			entity.setRequestIpAddress(RequestedIp);
			
			entity.setStatus(PasswordStatusCodes.ACTIVE.toString());
			entity.setEntityHash(ChecksumService.getChecksum(entity, GlobalConfig.DATA_ENTITY_HASH));
			entity.setActive(true);
			
			passwordResetRequestRepository.save(entity);
		
			Optional<User> userEntity = userRepository.findById(id);
			if (userEntity.isPresent()) {
				User myDetail = userEntity.get();		
				
				response.setPasswordStatus(PasswordStatusCodes.SUCCESS);
				response.setId(id);	
				
				// reset password email service
				// building message using message template

				ResetPassword sot = (ResetPassword) MessageTemplateFactory
						.newMessageTemplate(MessageTemplateTypes.RESET_PASSWORD);

				TemplateDTO mt = templateService.getTemplateByTypeAndPurpose(MessageTypes.EMAIL,
						MessageTemplateTypes.RESET_PASSWORD);

				sot.setTemplate(mt.getTemplate());
				sot.setFull_name(myDetail.getFullName());
				sot.setLink("localhost:8080/resetpass/"+entity.getToken());
				

				String message = sot.buildMessage();

				// send otp in email
				Message m = new Message();
				m.setContactInfo(myDetail.getEmailId());
				m.setContent(message);
				m.setMessageType(MessageTypes.EMAIL);
				m.setSubject(mt.getSubject());
				// m.setAssociationId(us.getId());
				// m.setAssociationType(EntityTypes.USER_SIGNUP);
				// m.setPurposeCode(MessagePurposeCodes.);
				MessageReceiver mr = new MessageReceiver();
				mr.setFullname(myDetail.getFullName());
				// mr.setMessage(message);
				// boolean messageSendingSuccess =
				messagingService.sendMessage(m, mr);						
				
			}else {
				response.setPasswordStatus(PasswordStatusCodes.FAILURE);
				response.setId(id);
			}
					
		}catch(Exception e) {
			var msg = e.getMessage();
			logger.error(e.getMessage());
			response.setPasswordStatus(PasswordStatusCodes.FAILURE);
			response.setId(id);
		}
		return response;
		
	}

	@Override
	public Long getUserIdFromToken(String token) {
		LocalDateTime localDateTime = LocalDateTime.now();
		LocalDateTime addedlocalDateTime=localDateTime.plusHours(24);			
		Timestamp timestamp = Timestamp.valueOf(addedlocalDateTime);
		return passwordResetRequestRepository.getUserIdFromToken(token,timestamp,PasswordStatusCodes.ACTIVE.toString());
	}

	@Override
	public PasswordResponse changePasswordByLink(Long userIdFromToken,String newPassword,String token,String requestedIp) {
		PasswordResponse response =new PasswordResponse();
		LocalDateTime localDateTime = LocalDateTime.now();
		Timestamp timestamp = Timestamp.valueOf(localDateTime);
		
		// Convert to UTC
        ZonedDateTime utcDateTime = ZonedDateTime.of(localDateTime, ZoneId.of("UTC"));
    	Timestamp localutctimestamp = Timestamp.valueOf(localDateTime);	
    	
		try {
			Long requestedId=passwordResetRequestRepository.getIdFromToken(token);
			if(requestedId!=null && requestedId>0) {
				
				Optional<PasswordResetRequests> passwordEntity = passwordResetRequestRepository.findById(requestedId);
				
				Optional<User> userEntity = userRepository.findById(userIdFromToken);
				if (userEntity.isPresent() && passwordEntity.isPresent()) {
					
					////requested token cases////////////		
					
					PasswordResetRequests pwd=passwordEntity.get();					
					pwd.setStatus(PasswordStatusCodes.EXPIRED.toString());
					pwd.setResetIpAddress(requestedIp);
					pwd.setResetTimestamp(timestamp);
					pwd.setEntityHash(ChecksumService.getChecksum(pwd, GlobalConfig.DATA_ENTITY_HASH));
					passwordResetRequestRepository.save(pwd);				

					newPassword=new BCryptPasswordEncoder().encode(newPassword);				
					
					User myDetail = userEntity.get();
					myDetail.setPassword(newPassword);
			
					//myDetail.setUpdatedDate(timestamp);
					//myDetail.setUpdatedDateUTC(localutctimestamp);
					
					myDetail.setEntityHash(ChecksumService.getChecksum(myDetail, GlobalConfig.DATA_ENTITY_HASH));
					//myDetail.isActive()
					myDetail.setEmailIdVerified(true);
					myDetail.setForcePwdChange(false);
					userRepository.save(myDetail);						
					
					UserLog userLog = new UserLog(myDetail);
					userLogRepo.save(userLog);
					
					response.setPasswordStatus(PasswordStatusCodes.SUCCESS);
					response.setId(userIdFromToken);
					
					// building message using message template
					PasswordChanged sot = (PasswordChanged) MessageTemplateFactory
							.newMessageTemplate(MessageTemplateTypes.PASSWORD_CHANGED);

					TemplateDTO mt = templateService.getTemplateByTypeAndPurpose(MessageTypes.EMAIL,
							MessageTemplateTypes.PASSWORD_CHANGED);

					sot.setTemplate(mt.getTemplate());
					sot.setFull_name(myDetail.getFullName());
					

					String message = sot.buildMessage();

					// send otp in email
					Message m = new Message();
					m.setContactInfo(myDetail.getEmailId());
					m.setContent(message);
					m.setMessageType(MessageTypes.EMAIL);
					m.setSubject(mt.getSubject());
					// m.setAssociationId(us.getId());
					// m.setAssociationType(EntityTypes.USER_SIGNUP);
					// m.setPurposeCode(MessagePurposeCodes.);
					MessageReceiver mr = new MessageReceiver();
					mr.setFullname(myDetail.getFullName());
					// mr.setMessage(message);
					// boolean messageSendingSuccess =
					messagingService.sendMessage(m, mr);
					
					return response;									
					
				}
				else {
					response.setPasswordStatus(PasswordStatusCodes.FAILURE);			
					return response;
				}
				
			}else {
				response.setPasswordStatus(PasswordStatusCodes.FAILURE);			
				return response;
			}
		}catch(Exception e) {
			var msg = e.getMessage();
			logger.error(e.getMessage());
			response.setPasswordStatus(PasswordStatusCodes.FAILURE);		
		}
		return response;
	}

}
