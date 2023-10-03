package com.gme.hom.users.controllers;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gme.hom.api.config.APIDataTypes;
import com.gme.hom.api.config.APIResponseCode;
import com.gme.hom.api.models.APIRequest;
import com.gme.hom.api.models.APIResponse;
import com.gme.hom.common.services.ObjectToJson;
import com.gme.hom.documents.controllers.DocumentsController;
import com.gme.hom.users.config.UserContactTypes;
import com.gme.hom.users.config.UserSignupFunctionCodes;
import com.gme.hom.users.config.UserStatusCode;
import com.gme.hom.users.config.UserTypeCodes;
import com.gme.hom.users.models.UserSignup;
import com.gme.hom.users.models.UserSignupRequest;
import com.gme.hom.users.models.UserSignupResponse;
import com.gme.hom.users.services.UserSignupService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/signup")
public class UserSignupController {
	@Autowired
	UserSignupService userSignupService;

	private static final Logger logger = LoggerFactory.getLogger(DocumentsController.class);

	// get email otp with email id
	@PostMapping("")
	public ResponseEntity<APIResponse> setContactInfo(@Valid @RequestBody APIRequest apiRequest,
			HttpServletRequest httpRequest, HttpServletResponse httpResponse) {

		logger.debug(apiRequest.getFunction());

		APIResponse ar = new APIResponse();

		if (apiRequest.getFunction().equals(UserSignupFunctionCodes.SIGNUP_REQUEST.toString())) {

			// generate otp object
			UserSignupRequest usr = apiRequest.getData().getUserSignupRequest();

			if (usr != null && usr.getSourceType().equals(UserTypeCodes.MERCHANT.toString())) {

				UserSignup us = new UserSignup(usr);

				if (us.getContactType() == UserContactTypes.EMAIL_AND_PHONE) {

					try {
						
						Long id = userSignupService.startEmailAndPhoneSignup(usr);

						ar.setStatus(APIResponseCode.SUCCESS.toString());

						ar.setData(id);

						ar.setMessage("Signup successful. An OTP are sent to your email address and the phone.");

						ar.setDescription("Success! Email and SMS OTP sent.");

					} catch (Exception e) {

						ar.setStatus(APIResponseCode.FAILURE.toString());

						ar.setDescription(e.getMessage());
					}
				} else if (us.getContactType() == UserContactTypes.EMAIL) {

					try {

						UserSignupResponse userSignupResponse = userSignupService.emailSignup(us);

						ar.setStatus(userSignupResponse.getUserStatusCode().toString());

						if (userSignupResponse.getUserStatusCode() == UserStatusCode.DUPLICATE) {
							
							ar.setMessage("Duplicate data");
							
						} else {
							ar.setData(userSignupResponse.getId());

							ar.setMessage("Signup successful. An OTP is sent to your email address and the phone.");

							ar.setDescription("Success! Email OTP has been sent.");
							
							ar.setDataType(APIDataTypes.LONG);
						}

					} catch (Exception e) {
						
						ar.setStatus(APIResponseCode.FAILURE.toString());
						
						ar.setDescription(e.getMessage());
					}

				} else if (us.getContactType() == UserContactTypes.PHONE) {
					try {
						Long id = userSignupService.startPhoneSignup(usr);
						ar.setStatus(APIResponseCode.SUCCESS.toString());
						ar.setData(id);
						ar.setDescription("Success! SMS OTP has been sent.");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else {
					ar.setStatus(APIResponseCode.FAILURE.toString());
					ar.setDescription("Contact type not recognized!");
				}

			}
			// for non merchant users
//			else if() {
//				
//			}

			else {
				ar.setStatus(APIResponseCode.FAILURE.toString());
				ar.setDescription("User type not recognized!");
			}
		} else {
			ar.setStatus(APIResponseCode.FAILURE.toString());
			ar.setDescription("Invalid Function Name");
		}
		return ResponseEntity.ok(ar);
	}

	// otp verification
	// otp resend
	// set password
	@PostMapping("/{id}")
	public String signupEmail(@RequestBody APIRequest apiRequest, @PathVariable("id") Long id) {
		APIResponse ar = new APIResponse();
		// OTP Verification
		if (apiRequest.getFunction().equals(UserSignupFunctionCodes.OTP_VERIFICATION.toString())) {
			// validate via service
			try {
				ar.setStatus(userSignupService.verifyOTP(apiRequest.getData(), id));
				ar.setDescription("OTP Verified.");
				ar.setData(id);
			} catch (Exception e) {
				ar.setStatus(APIResponseCode.FAILURE.toString());
				ar.setDescription(e.getMessage());
			}
			// RESEND OTP
		} else if (apiRequest.getFunction().equals(UserSignupFunctionCodes.RESEND_OTP.toString())) {
			// generate otp object
			UserSignupRequest usr = apiRequest.getData().getUserSignupRequest();
			Optional<UserSignup> userSignup = userSignupService.getUserSignupById(id);
			if (!userSignup.isEmpty()) {
				if (usr.getContactType().equals(UserContactTypes.EMAIL.toString())) {
					try {
						ar.setStatus(userSignupService.resendEmailOTP(usr, id));
						ar.setData(id);
						ar.setDescription("Success! Email OTP sent.");
					} catch (Exception e) {
						ar.setStatus(APIResponseCode.FAILURE.toString());
						ar.setDescription(e.getMessage());
					}
				} else if (usr.getContactType().equals(UserContactTypes.PHONE.toString())) {
					try {
						ar.setStatus(userSignupService.resendSMSOTP(usr, id));
						ar.setData(id);
						ar.setDescription("Success! SMS OTP sent.");
					} catch (Exception e) {
						ar.setStatus(APIResponseCode.FAILURE.toString());
						ar.setDescription(e.getMessage());
					}
				}
			} else {
				ar.setStatus(APIResponseCode.FAILURE.toString());
				ar.setDescription("User does not exixt...");

			}

		} else if (apiRequest.getFunction().equals(UserSignupFunctionCodes.SETTING_PASSWORD.toString())) {
			UserSignupRequest usr = apiRequest.getData().getUserSignupRequest();
			if (usr.getContactType().equals("EMAIL")) {
				try {
					ar.setStatus(userSignupService.setPasswordByEmail(usr, id));
					ar.setData(id);
					ar.setDescription("User successfully created!");
					ar.setStatus(APIResponseCode.SUCCESS.toString());
				} catch (Exception e) {
					ar.setStatus(APIResponseCode.FAILURE.toString());
					ar.setDescription(e.getMessage());
				}
			} else if (usr.getContactType().equals("PHONE_NUMBER")) {
				try {
					ar.setStatus(userSignupService.setPasswordByEmail(usr, id));
					ar.setData(id);
					ar.setDescription("User successfully created!");
					ar.setStatus(APIResponseCode.SUCCESS.toString());
				} catch (Exception e) {
					ar.setStatus(APIResponseCode.FAILURE.toString());
					ar.setDescription(e.getMessage());
				}
			} else if (usr.getContactType().equals("EMAIL_AND_PHONE")) {
				try {
					ar.setStatus(userSignupService.setPasswordByPhoneAndEmail(usr, id));
					ar.setData(id);
					ar.setDescription("User successfully created!");
					ar.setStatus(APIResponseCode.SUCCESS.toString());
				} catch (Exception e) {
					ar.setStatus(APIResponseCode.FAILURE.toString());
					ar.setDescription(e.getMessage());
				}
			} else {
				ar.setStatus(APIResponseCode.FAILURE.toString());
				ar.setDescription("Contact type not recognized!");
			}

		}

		// and phone number or email
//		else if (apiRequest.getFunction().equals(UserSignupFunctionCodes.SIGNUP_REQUEST)) {
//			// generate otp object
//			UserSignupRequest usr = apiRequest.getData().getUserSignupRequest();
//
//			if (usr != null && usr.getUserType().equals(SignupTypeCodes.MERCHANT)) {
//				Optional<UserSignup> userSignup = userSignupService.getUserSignupById(id);
//				if (userSignup.isEmpty()) {
//					ar.setStatus(APIResponseCodes.FAILURE);
//					ar.setData("User not found!");
//				} else {
//					usr.setMerchantType(usr.getUserSubtype());
//					if (usr.getContactType().equals(SignupTypeCodes.EMAIL)) {
//						try {
//							ar.setStatus(userSignupService.addEmail(usr, id));
//							ar.setData(id);
//							ar.setDescription("Success! Email OTP sent.");
//						} catch (Exception e) {
//							ar.setStatus(APIResponseCodes.FAILURE);
//							ar.setDescription(e.getMessage());
//						}
//					} else if (usr.getContactType().equals(SignupTypeCodes.PHONE_NUMBER)) {
//						try {
//							ar.setStatus(userSignupService.addPhoneNumber(usr, id));
//							ar.setData(id);
//							ar.setDescription("Success! SMS OTP sent.");
//						} catch (Exception e) {
//							ar.setStatus(APIResponseCodes.FAILURE);
//							ar.setDescription(e.getMessage());
//						}
//					} else {
//						ar.setStatus(APIResponseCodes.FAILURE);
//						ar.setDescription("Contact type not recognized!");
//					}
//				}
//
//			}
//			// for non merchant users
////			else if() {
////				
////			}
//
//			else {
//				ar.setStatus(APIResponseCodes.FAILURE);
//				ar.setDescription("User type not recognized!");
//			}
//		} 

		else {
			ar.setStatus(APIResponseCode.FAILURE.toString());
			ar.setDescription("Invalid Function Name");
		}
		return ObjectToJson.toJson(ar);
	}

}

//Resend email otp
//@PostMapping("/resendemailotp/{id}")
//public String resendEmailOTP(@RequestBody APIRequest apiRequest) {
//	APIResponse ar = new APIResponse();
//
//	if (apiRequest.getFunction().equals(UserSignupFunctionCodes.RESEND_EMAIL_OTP)) {
//		// generate otp object
//		UserSignupRequest usr = apiRequest.getData().getUserSignupRequest();
//		if (!usr.getEmailId().isEmpty() && !usr.getMerchantType().isEmpty()
//				&& !usr.getIncorporationCountry().isEmpty()) {
//			// check for unique user id
//			
//			if (!merchantService.merchantAlreadyExixts(usr.getEmailId())) {
//				if (userSignupService.userSignupAlreadyExixts(usr.getEmailId())) {
//					if (userSignupService.getUserSignupByEmailId(usr.getEmailId()).get().isEmailOtpVerified()) {
//						ar.setScope(APIResponseCodes.FAILURE);
//						ar.setDescription("Email Already verified...");
//					} else {
//						OTP otp = new OTP();
//						otp.setContactInfo(usr.getEmailId());
//						otp.setPurpose("Email Verification");
//						// generate otp code
//						Integer otpCode = otpService.generateOTP(usr.getEmailId());
//						otp.setOtpCode(String.valueOf(otpCode));
//
//						// save userSignup object to database
//						UserSignup us = userSignupService.getUserById(id);
//						us.setStatus("Email OTP sent again");
//						us = this.userSignupService.saveUserSignup();
//
//						// mail the otp to users email address
//
//						// fill response object
//						ar.setStatus(APIResponseCodes.SUCCESS);
//						ar.setData(us.getId());
//						ar.setDescription("Success! OTP has been sent to your email." + otpCode);
//					}
//				} else {
//					ar.setStatus(APIResponseCodes.INVALID_USER);
//					ar.setDescription("No such user found...");
//				}
//			} else {
//				ar.setStatus(APIResponseCodes.FAILURE);
//				ar.setDescription("User already exists...");
//			}
//		} else {
//			ar.setStatus(APIResponseCodes.FAILURE);
//			ar.setDescription("Insufficient information...");
//		}
//	} else {
//		ar.setStatus(APIResponseCodes.FAILURE);
//		ar.setDescription("invalid function name...");
//	}
//	return ObjectToJson.toJson(ar);
//
//}
//
//	/* this api verifies phone number with otp sent to the users phone */
//	@PostMapping("/phonesignup/{id}")
//	public String getSmsOTP(@RequestBody APIRequest apiRequest, @PathVariable("id") Long id) {
//		APIResponse ar = new APIResponse();
//
//		if (apiRequest.getFunction().equals(UserSignupFunctionCodes.PHONE_SIGNUP_REQUEST)) {
//
//			// generate otp object
//			UserSignup us = userSignupService.getUserById(id).get();
//			UserSignupRequest usr = apiRequest.getData().getUserSignupRequest();
//			if(us.getEmailId().equals(usr.getEmailId())) {
//				
//				OTP otp = new OTP();
//				otp.setContactInfo(usr.getPhoneNumber());
//				otp.setPurpose("Phone Number Verification");
//				// generate otp code
//				Integer otpCode = otpService.generateOTP(usr.getPhoneNumber());
//				otp.setOtpCode(String.valueOf(otpCode));
//				// save otp object to database
//
//				// save user object to database
//				us.setStatus("SMS OTP Sent");
//				us.setUpdatedBy(us.getEmailId());
//				us = this.userSignupService.saveUserSignup(us);
//
//				// send the otp to users phone Number
//
//				// fill response object
//				ar.setStatus(APIResponseCodes.SUCCESS);
//				ar.setData(us.getId());
//				ar.setDescription("Success! OTP has been sent to your phone number."+otpCode);
//				return ObjectToJson.toJson(ar);
//			}
//			else {
//				ar.setStatus(APIResponseCodes.BAD_CREDENTIALS);
//				ar.setDescription("User credentials did not match(Email)");
//			}
//
//		} else {
//			ar.setStatus(APIResponseCodes.FAILURE);
//		}
//		return ObjectToJson.toJson(ar);
//
//	}
//	
//	
//	/*
//	 * this api generates otp for verifying phone number and sends it back to the
//	 * users phone
//	 */
//	@PostMapping("/phonenumberverify/{id}")
//	public String getSmsOTPVerify(@RequestBody APIRequest apiRequest, @PathVariable("id") Long id) {
//		APIResponse ar = new APIResponse();
//
//		if (apiRequest.getFunction().equals(UserSignupFunctionCodes.SMS_VERIFICATION)) {
//			// validate via service
//			UserSignupRequest usr = apiRequest.getData().getUserSignupRequest();
//			OTP otp = apiRequest.getData().getOtp();
//			int otpValue = otpService.getOtp(otp.getContactInfo());
//			if (otpValue == Integer.parseInt(otp.getOtpCode())) {
//				if (otpValue == Integer.parseInt(otp.getOtpCode())) {
//					usr.setStatus("Phone Number OTP Verified");
//					UserSignup us = new UserSignup(usr);
//					us.setSmsOtpVerified(true);
//					us.setId(id);
//					us = userSignupService.saveUserSignup(us);
//					ar.setStatus(APIResponseCodes.SUCCESS);
//					ar.setDescription("Phone Number OTP Verified");
//					ar.setData(us.getId());
//
//				} 
//				else {
//					ar.setStatus(APIResponseCodes.FAILURE);
//					ar.setDescription("OTP expired.");
//				}
//			} else {
//				ar.setStatus(APIResponseCodes.FAILURE);
//				ar.setDescription("Phone Number Verification failed. OTP did not match!");
//			}
//		} else {
//			ar.setStatus(APIResponseCodes.FAILURE);
//			ar.setDescription("Function code did not match...");
//		}
//		return ObjectToJson.toJson(ar);
//
//	}
//	
//
//	/*
//	 * Process password setup after creating merchant and user from userSignup
//	 * entity
//	 */
//	@PostMapping("/setpassword/{id}")
//	public String AddPassword(@PathVariable("id") Long id, @RequestBody APIRequest apiRequest) {
//
//		APIResponse ar = new APIResponse();
//
//		if (apiRequest.getFunction().equals(UserSignupFunctionCodes.SETTING_PASSWORD)) {
//
//			UserSignupRequest usr = apiRequest.getData().getUserSignupRequest();
//
//			Optional<UserSignup> us = userSignupService.getUserById(id);
//			if (!us.isEmpty() && us.get().getEmailId().equals(usr.getEmailId())) {
//				if (us.get().isEmailOtpVerified() && us.get().isSmsOtpVerified()) {
//					UserRequest ur = new UserRequest();
//					ur.setEmail(usr.getEmailId());
//					ur.setUsername(usr.getEmailId());
//					ur.setPassword(passwordEncoder.encode(usr.getPassword()));
//					ur.getEmail();
//					User user = userService.AddUser(ur);
//
//					Merchant merchant = new Merchant(us.get());
//					merchantService.addMerchant(merchant);
//					ar.setStatus(APIResponseCodes.SUCCESS);
//					ar.setData(user.getId());
//					ar.setDescription("User successfully created!");
//
//				} else {
//					ar.setStatus(APIResponseCodes.FAILURE);
//					ar.setDescription("Email or Phone number not verified!");
//				}
//			} else {
//				ar.setStatus(APIResponseCodes.INVALID_USER);
//				ar.setDescription("No Such user found!");
//			}
//		} else {
//			ar.setStatus(APIResponseCodes.BAD_CREDENTIALS);
//			ar.setDescription("Invalid function name!");
//		}
//		return ObjectToJson.toJson(ar);
//	}
//get email otp with email id
//	@PostMapping("")
//	public String getEmailOTP(@RequestBody APIRequest apiRequest) {
//		APIResponse ar = new APIResponse();
//		if (apiRequest.getFunction().equals(UserSignupFunctionCodes.EMAIL_SIGNUP_REQUEST)) {
//			// generate otp object
//			UserSignupRequest usr = apiRequest.getData().getUserSignupRequest();
//			if (!usr.getEmailId().isEmpty() && !usr.getMerchantType().isEmpty()
//					&& !usr.getIncorporationCountry().isEmpty()) {
//				// check for unique user id
//				if (!merchantService.merchantAlreadyExixts(usr.getEmailId())) {
//					OTP otp = new OTP();
//					otp.setContactInfo(usr.getEmailId());
//					otp.setPurpose("Email Verification");
//					// generate otp code
//					Integer otpCode = otpService.generateOTP(usr.getEmailId());
//					otp.setOtpCode(String.valueOf(otpCode));
//					// save otp object to database
//
//					// save userSignup object to database
//					UserSignup us = new UserSignup(usr);
//					us.setStatus("Email OTP Sent");
//					us.setCreatedBy(usr.getEmailId());
//					us = this.userSignupService.saveUserSignup(us);
//
//					// mail the otp to users email address
//
//					// fill response object
//					ar.setStatus(APIResponseCodes.SUCCESS);
//					ar.setData(us.getId());
//					ar.setDescription("Success! OTP has been sent to your email." + otpCode);
//				}
//
//				else {
//					ar.setStatus(APIResponseCodes.FAILURE);
//					ar.setDescription("User already exists...");
//				}
//			} else {
//				ar.setStatus(APIResponseCodes.FAILURE);
//				ar.setDescription("Insufficient information...");
//			}
//		} else {
//			ar.setScope(APIResponseCodes.FAILURE);
//			ar.setDescription("Invalid Function Name");
//		}
//		return ObjectToJson.toJson(ar);
//	}

//}

/*
 * else if
 * (apiRequest.getFunction().equals(UserSignupFunctionCodes.PHONE_SIGNUP_REQUEST
 * )) { UserSignupRequest usr = apiRequest.getData().getUserSignupRequest(); if
 * (userSignupService.findByPhoneNumber(usr.getPhoneNumber()).isEmpty()) {
 * 
 * // generate otp object UserSignup us =
 * userSignupService.getUserById(id).get(); if
 * (us.getEmailId().equals(usr.getEmailId())) {
 * 
 * OTP otp = new OTP(); otp.setContactInfo(usr.getPhoneNumber());
 * otp.setPurpose("Phone Number Verification"); // generate otp code Integer
 * otpCode = otpService.generateOTP(usr.getPhoneNumber());
 * otp.setOtpCode(String.valueOf(otpCode)); // save otp object to database
 * 
 * // save user object to database us.setPhoneCode(usr.getPhoneCode());
 * us.setPhoneNumber(usr.getPhoneNumber()); us.setStatus("SMS OTP Sent");
 * us.setUpdatedBy(us.getEmailId()); us =
 * this.userSignupService.saveUserSignup(us);
 * 
 * // send the otp to users phone Number
 * 
 * // fill response object ar.setStatus(APIResponseCodes.SUCCESS);
 * ar.setData(us.getId());
 * ar.setDescription("Success! OTP sent to your phone number." + otpCode);
 * return ObjectToJson.toJson(ar); } else {
 * ar.setStatus(APIResponseCodes.FAILURE);
 * ar.setDescription("User credentials did not match(Email)"); } } else {
 * ar.setStatus(APIResponseCodes.FAILURE);
 * ar.setDescription("Phone Number already registered to a user!"); } } else if
 * (apiRequest.getFunction().equals(UserSignupFunctionCodes.RESEND_SMS_OTP)) {
 * UserSignupRequest usr = apiRequest.getData().getUserSignupRequest(); if
 * (!userSignupService.getUserById(id).isEmpty()) {
 * 
 * // generate otp object UserSignup us =
 * userSignupService.getUserById(id).get(); if
 * (us.getEmailId().equals(usr.getEmailId())) {
 * 
 * OTP otp = new OTP(); otp.setContactInfo(usr.getPhoneNumber());
 * otp.setPurpose("Phone Number Verification"); // generate otp code Integer
 * otpCode = otpService.generateOTP(usr.getPhoneNumber());
 * otp.setOtpCode(String.valueOf(otpCode)); // save otp object to database
 * 
 * // save user object to database us.setPhoneNumber(usr.getPhoneNumber());
 * us.setStatus("SMS OTP Sent"); us.setUpdatedBy(us.getEmailId()); us =
 * this.userSignupService.saveUserSignup(us);
 * 
 * // send the otp to users phone Number
 * 
 * // fill response object ar.setStatus(APIResponseCodes.SUCCESS);
 * ar.setData(us.getId());
 * ar.setDescription("Success! OTP has been sent to your phone number." +
 * otpCode); return ObjectToJson.toJson(ar); } else {
 * ar.setStatus(APIResponseCodes.BAD_CREDENTIALS);
 * ar.setDescription("User credentials did not match(Email)"); } } else {
 * ar.setStatus(APIResponseCodes.FAILURE);
 * ar.setDescription("Phone number not registered to a user!"); } }
 */

//		else if (apiRequest.getFunction().equals(UserSignupFunctionCodes.SMS_VERIFICATION)) {
//			// validate via service
//			UserSignupRequest usr = apiRequest.getData().getUserSignupRequest();
//			OTP otp = apiRequest.getData().getOtp();
//			if (otp.getContactInfo().equals(usr.getPhoneNumber())) {
//				int otpValue = otpService.getOtp(otp.getContactInfo());
//				if (otpValue == Integer.parseInt(otp.getOtpCode())) {
//					if (otpValue == Integer.parseInt(otp.getOtpCode())) {
//						UserSignup us = userSignupService.getUserById(id).get();
//						us.setStatus("Phone Number OTP Verified");
//						us.setSmsOtpVerified(true);
//						us.setUpdatedBy(us.getEmailId());
//						us.setId(id);
//						us = userSignupService.saveUserSignup(us);
//						ar.setStatus(APIResponseCodes.SUCCESS);
//						ar.setDescription("Phone Number OTP Verified");
//						ar.setData(us.getId());
//
//					} else {
//						ar.setStatus(APIResponseCodes.FAILURE);
//						ar.setDescription("OTP expired.");
//					}
//				} else {
//					ar.setStatus(APIResponseCodes.FAILURE);
//					ar.setDescription("Phone Number OTP Verification failed!");
//				}
//			} else {
//				ar.setStatus(APIResponseCodes.FAILURE);
//				ar.setDescription("Credentials did not match!");
//			}
//		}