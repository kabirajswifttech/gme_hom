package com.gme.hom.auth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gme.hom.api.config.APIRequestFunctionCode;
import com.gme.hom.api.config.APIRequestScopeCode;
import com.gme.hom.api.config.APIResponseCode;
import com.gme.hom.api.config.APIStatusCode;
import com.gme.hom.api.models.APIRequest;
import com.gme.hom.api.models.APIResponse;
import com.gme.hom.auth.config.AuthFunctionCodes;
import com.gme.hom.auth.config.AuthStatusCodes;
import com.gme.hom.auth.config.OtpStatusCodes;
import com.gme.hom.auth.config.OtpTypes;
import com.gme.hom.auth.config.PasswordStatusCodes;
import com.gme.hom.auth.models.AuthResponse;
import com.gme.hom.auth.models.Otp;
import com.gme.hom.auth.models.OtpRequest;
import com.gme.hom.auth.models.OtpResponse;
import com.gme.hom.auth.models.PasswordRequest;
import com.gme.hom.auth.models.PasswordResponse;
import com.gme.hom.auth.service.AuthService;
import com.gme.hom.auth.service.OtpService;
import com.gme.hom.auth.service.PasswordService;
import com.gme.hom.messaging.config.MessageTypes;
import com.gme.hom.messaging.models.Message;
import com.gme.hom.messaging.models.MessageReceiver;
import com.gme.hom.messaging.services.MessagingService;
import com.gme.hom.templates.config.MessageTemplateTypes;
import com.gme.hom.templates.models.MessageTemplateFactory;
import com.gme.hom.templates.models.PasswordChanged;
import com.gme.hom.templates.models.ResetPassword;
import com.gme.hom.templates.repository.TemplateDTO;
import com.gme.hom.templates.services.TemplateService;
import com.gme.hom.users.services.UserService;
import com.gme.hom.usersecurity.services.UserSecurityService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

	@Autowired
	private AuthService authService;

	@Autowired
	private OtpService otpService;
	
	@Autowired
	private  UserService userService;	
	@Autowired
	private final PasswordService passwordService;

	@PostMapping("")
	public ResponseEntity<APIResponse> authenticate(@RequestBody APIRequest apiRequest, HttpServletRequest httpRequest,
			HttpServletResponse httpResponse) {

		APIResponse ar = new APIResponse();
		ar.setStatus(APIResponseCode.UNKNOWN);

		if (apiRequest.getFunction().equals(AuthFunctionCodes.AUTHENTICATE.toString())) {
			// if the request is to authenticate user

			AuthResponse authResponse = authService.authenticateUser(apiRequest.getData().getUserAuthRequest());

			if (authResponse.getAuthStatus().equals(AuthStatusCodes.AUTHENTICATED)) {

				Cookie cookie = authService.generateCookie(authResponse.getJwt());

				httpResponse.addCookie(cookie);

				ar.setStatus(APIResponseCode.SUCCESS);

				ar.setData(authResponse.getJwt());

				return ResponseEntity.ok(ar);

			} else {

				ar.setStatus(APIResponseCode.FAILURE);

				ar.setData(authResponse);

				return ResponseEntity.ok(ar);

			}
		} // end of AUTHENTICATE
		else if (apiRequest.getFunction().equals(AuthFunctionCodes.VALIDATE_OTP.toString())) {
			// if the request is to validate an OTP

			OtpRequest otpRequest = apiRequest.getData().getOtpRequest();
			ar.setStatus(APIResponseCode.FAILURE);

			// System.out.println(otpRequest.getOtpCode());

			if (otpRequest != null) {

				Otp otpR = new Otp(otpRequest);

				if (otpService.validateOtp(otpR)) {
					ar.setMessage("OTP validation successful.");
					ar.setStatus(APIResponseCode.SUCCESS);
				} else {
					ar.setMessage("OTP validation failure.");

				}
			}
			return ResponseEntity.ok(ar);

		} else if (apiRequest.getFunction().equals(AuthFunctionCodes.REQUEST_OTP.toString())) {
			// if the request is to resend email otp

			// identify the purpose of request
			OtpRequest otpRequest = apiRequest.getData().getOtpRequest();

			// otpRequest.purpose = entity type
			// otpRequest.contact_info = contactInfo

			if (otpRequest.getOtpType() == OtpTypes.EMAIL) {

				if (otpRequest.getContactInfo() != null) {

					OtpResponse otpResponse = otpService.requestOtp(otpRequest.getSourceType(),
							otpRequest.getOtpType(), otpRequest.getContactInfo());

					if (otpResponse.getOtpStatus() == OtpStatusCodes.VERIFIED) {
						ar.setMessage("OTP already verified.");
						ar.setData(otpResponse);
						ar.setStatus(APIResponseCode.SUCCESS);

					} else if (otpResponse.getOtpStatus() == OtpStatusCodes.RESENT) {
						ar.setMessage("OTP resent.");
						ar.setStatus(APIResponseCode.SUCCESS);
						ar.setData(otpResponse.getOtp());
						ar.setStatusCode(APIStatusCode.R);
					}
				} else {
					// INVALID EMAIL
					ar.setStatus(APIResponseCode.INVALID_REQUEST);
				}
			}

			else if (otpRequest.getOtpType() == OtpTypes.SMS) {

				if (otpRequest.getPhoneCode() != null && otpRequest.getPhoneNo() != null) {
					// if phone code and phone no. are present

					OtpResponse otpResponse = otpService.requestOtp(otpRequest.getSourceType(),
							otpRequest.getOtpType(), otpRequest.getPhoneCode() + otpRequest.getPhoneNo());

					if (otpResponse.getOtpStatus() == OtpStatusCodes.VERIFIED) {
						ar.setMessage("OTP already verified.");
						ar.setData(otpResponse);
						ar.setStatus(APIResponseCode.SUCCESS);

					} else if (otpResponse.getOtpStatus() == OtpStatusCodes.RESENT) {
						ar.setMessage("OTP resent.");
						ar.setStatus(APIResponseCode.SUCCESS);
						ar.setData(otpResponse.getOtp());
						ar.setStatusCode(APIStatusCode.R);
					} else {
						ar.setStatus(APIResponseCode.INVALID_REQUEST);
					}
				}
			} else {
				ar.setStatus(APIResponseCode.INVALID_REQUEST);
			}
			return ResponseEntity.ok(ar);

		}

		else {
			ar.setStatus(APIResponseCode.INVALID_REQUEST);
		}

		return ResponseEntity.ok(ar);
	}
	//====================password functions==================================//
	
	@PostMapping("password")
	public ResponseEntity<APIResponse> passowrd(@RequestBody APIRequest apiRequest, HttpServletRequest httpRequest,
			HttpServletResponse httpResponse) {

		APIResponse ar = new APIResponse();
		String RequestedIp=UserSecurityService.getClientAddress(httpRequest);
		
		//reset password for valid link token 
		if (apiRequest.getFunction().equals(APIRequestFunctionCode.UPDATE_DATA.toString())
				&& apiRequest.getScope().equals(APIRequestScopeCode.BYKEYWORD.toString())) {
			try {
				PasswordRequest request = apiRequest.getData().getPasswordRequest();	
				if(!request.getNewPassword().toString().equals(request.getConfirmPassword().toString())) {
					ar.setStatus(APIResponseCode.FAILURE);
					ar.setDescription("Passwords did not match. Please try again.");
					return ResponseEntity.ok(ar);
				}
				if(!(request.getToken()==null) && (!request.getToken().isEmpty())&&(!request.getToken().isEmpty())){
					
					Long userIdFromToken=(long) ((passwordService.getUserIdFromToken(request.getToken())!= null)? passwordService.getUserIdFromToken(request.getToken()): 0);
					if(userIdFromToken<1) {
						ar.setStatus(APIResponseCode.FAILURE);
						ar.setDescription("Invalid token.");
						ar.setMessage("Invalid token.");
						ar.setStatusCode(APIStatusCode.F);
						return ResponseEntity.ok(ar);
					}else {
						
						PasswordResponse response =passwordService.changePasswordByLink(userIdFromToken,request.getNewPassword(),request.getToken(),RequestedIp);		
						ar.setData(response);
						
						if(response.getPasswordStatus().equals(PasswordStatusCodes.SUCCESS)) {
							ar.setStatus(APIResponseCode.SUCCESS);
							ar.setDescription("Password changed successfully.");
							ar.setMessage("Password changed successfully.");

							return ResponseEntity.ok(ar);
						}else {
							ar.setDescription("Request Failed.");
							ar.setMessage("Request Failed.");
							ar.setStatusCode(APIStatusCode.F);
							return ResponseEntity.ok(ar);
						}	
					}
					
					}else {
					ar.setStatus(APIResponseCode.FAILURE);
					ar.setDescription("Invalid token.");
					ar.setMessage("Invalid token.");
					ar.setStatusCode(APIStatusCode.F);
					return ResponseEntity.ok(ar);
				}

			} catch (Exception e) {
				ar.setStatus(APIResponseCode.FAILURE);
				ar.setDescription(e.getMessage());
				return ResponseEntity.ok(ar);
			}
		}
		
		//send reset password link to the user by logged in user 
		else if (apiRequest.getFunction().equals(APIRequestFunctionCode.GENERATE.toString())
				&& apiRequest.getScope().equals(APIRequestScopeCode.BYID.toString())) {
			try {
				String actionUserName = UserSecurityService.getUsername();
				if(actionUserName==null) {
					ar.setDescription("Authorization failed.");
					ar.setMessage("Authorization failed.");
					ar.setStatusCode(APIStatusCode.F);
					return ResponseEntity.ok(ar);
				}
			//	Long id = userService.getIdByUsername(actionUserName);				
				PasswordRequest request = apiRequest.getData().getPasswordRequest();
				//user id whose password is requested for reset 
				Long id = (long) ((request.getId() != null)
						? request.getId()
						: 0);
				if(id<1) {
					ar.setDescription("Invalid user id.");
					ar.setMessage("Invalid user id.");
					ar.setStatusCode(APIStatusCode.F);
					return ResponseEntity.ok(ar);
				}
				String userNameFromId=userService.getUserEmailById(id);
				if(userNameFromId==null) {
					ar.setDescription("Invalid user id.");
					ar.setMessage("Invalid user id.");
					ar.setStatusCode(APIStatusCode.F);
					return ResponseEntity.ok(ar);
				}
				PasswordResponse response =passwordService.generateResetPasswordLink(id, actionUserName,RequestedIp);		
				ar.setData(response);
				
				if(response.getPasswordStatus().equals(PasswordStatusCodes.SUCCESS)) {
					ar.setStatus(APIResponseCode.SUCCESS);
					ar.setDescription("Password reset link sent to the email. Please check the inbox");
					ar.setMessage("Password reset link sent to the email. Please check the inbox");	

					return ResponseEntity.ok(ar);
				}else {
					ar.setDescription("Request Failed.");
					ar.setMessage("Request Failed.");
					ar.setStatusCode(APIStatusCode.F);
					return ResponseEntity.ok(ar);
				}	
			
				

			} catch (Exception e) {

				ar.setStatus(APIResponseCode.FAILURE);
				ar.setDescription(e.getMessage());
				return ResponseEntity.ok(ar);
			}
		}
		
		//forget password and send reset password link in email 
				else if (apiRequest.getFunction().equals(APIRequestFunctionCode.GENERATE.toString())
						&& apiRequest.getScope().equals(APIRequestScopeCode.BYKEYWORD.toString())) {
					try {
						//String actionUserName = UserSecurityService.getUsername();
					   //Long id = userService.getIdByUsername(actionUserName);				
						PasswordRequest request = apiRequest.getData().getPasswordRequest();
						
						if(!(request.getEmailId()==null) && (!request.getEmailId().isEmpty())&&(!request.getEmailId().isEmpty())){
							//user id whose password is requested for reset 
							Long id =(long) ((userService.getIdByEmailId(request.getEmailId())!= null)? userService.getIdByEmailId(request.getEmailId()): 0);					
							if(id<1) {
								ar.setDescription("Invalid email id.");
								ar.setMessage("Invalid email id.");
								ar.setStatusCode(APIStatusCode.F);
								return ResponseEntity.ok(ar);
							}else {
								PasswordResponse response =passwordService.generateResetPasswordLink(id, request.getEmailId(),RequestedIp);		
								ar.setData(response);
								
								if(response.getPasswordStatus().equals(PasswordStatusCodes.SUCCESS)) {
									ar.setStatus(APIResponseCode.SUCCESS);
									ar.setDescription("Password reset link sent to the email. Please check the inbox");
									ar.setMessage("Password reset link sent to the email. Please check the inbox");	

									return ResponseEntity.ok(ar);
								}else {
									ar.setDescription("Request Failed.");
									ar.setMessage("Request Failed.");
									ar.setStatusCode(APIStatusCode.F);
									return ResponseEntity.ok(ar);
								}	
							}
							
							
						}else {
							ar.setDescription("Invalid email id.");
							ar.setMessage("Invalid email id.");
							ar.setStatusCode(APIStatusCode.F);
							return ResponseEntity.ok(ar);
						}

					} catch (Exception e) {

						ar.setStatus(APIResponseCode.FAILURE);
						ar.setDescription(e.getMessage());
						return ResponseEntity.ok(ar);
					}
				}
		// change password for logged in user 
		else if (apiRequest.getFunction().equals(APIRequestFunctionCode.UPDATE_DATA.toString())
				&& apiRequest.getScope().equals(APIRequestScopeCode.BYID.toString())) {
			try {
				
				String actionUserName = UserSecurityService.getUsername();
				if(actionUserName==null) {
					ar.setDescription("Authorization failed.");
					ar.setMessage("Authorization failed.");
					ar.setStatusCode(APIStatusCode.F);
					return ResponseEntity.ok(ar);
				}
				Long id = userService.getIdByUsername(actionUserName);
				
				PasswordRequest request = apiRequest.getData().getPasswordRequest();
				
				if(request.getNewPassword().toString().equals(request.getOldPassword().toString())) {
					ar.setStatus(APIResponseCode.FAILURE);
					ar.setDescription("Invalid new password.");
					return ResponseEntity.ok(ar);
				}
				
				if(!request.getNewPassword().toString().equals(request.getConfirmPassword().toString())) {
					ar.setStatus(APIResponseCode.FAILURE);
					ar.setDescription("Passwords did not match. Please try again.");
					return ResponseEntity.ok(ar);
				}
				boolean isvalidPass=userService.isValidPassword(request.getOldPassword(), id);
				if(isvalidPass) {
					PasswordResponse response =userService.changePassword(request.getNewPassword().toString(), actionUserName, id);					
					ar.setData(response);
					
					if(response.getPasswordStatus().equals(PasswordStatusCodes.SUCCESS)) {
						ar.setStatus(APIResponseCode.SUCCESS);
						ar.setDescription("Updated successfully.");
						ar.setMessage("Updated successfully.");										
						return ResponseEntity.ok(ar);
					}else {
						ar.setDescription("Update Failed.");
						ar.setMessage("Update Failed.");
						ar.setStatusCode(APIStatusCode.F);
						return ResponseEntity.ok(ar);
					}					
					
				}else {			 		
					ar.setStatus(APIResponseCode.FAILURE);
					ar.setDescription("Update Failed.");
					ar.setMessage("Update Failed.");
					ar.setStatusCode(APIStatusCode.F);
					return ResponseEntity.ok(ar);
				}				


			} catch (Exception e) {

				ar.setStatus(APIResponseCode.FAILURE);
				ar.setDescription(e.getMessage());
				ar.setStatusCode(APIStatusCode.F);
				return ResponseEntity.ok(ar);
			}
		}		
		else {
			ar.setStatus(APIResponseCode.FAILURE);
			ar.setDescription("Invalid operation");
			ar.setStatusCode(APIStatusCode.F);
		}

		return ResponseEntity.ok(ar);
	}

}
