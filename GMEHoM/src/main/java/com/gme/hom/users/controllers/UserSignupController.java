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

import com.gme.hom.api.config.APIResponseCode;
import com.gme.hom.api.models.APIRequest;
import com.gme.hom.api.models.APIResponse;
import com.gme.hom.common.services.ObjectToJson;
import com.gme.hom.documents.controllers.DocumentsController;
import com.gme.hom.users.config.UserContactTypes;
import com.gme.hom.users.config.UserSignupFunctionCodes;
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

	@PostMapping("")
	public ResponseEntity<APIResponse> setContactInfo(@Valid @RequestBody APIRequest apiRequest,
			HttpServletRequest httpRequest, HttpServletResponse httpResponse) {

		// create user signup then send an otp to contact info

		logger.debug(apiRequest.getFunction());

		APIResponse ar = new APIResponse();

		if (apiRequest.getFunction().equals(UserSignupFunctionCodes.SIGNUP_REQUEST.toString())) {

			// generate otp object
			UserSignupRequest usr = apiRequest.getData().getUserSignupRequest();

			if (usr != null && usr.getSourceType().equals(UserTypeCodes.MERCHANT.toString())) {

				UserSignup us = new UserSignup(usr);

				if (us.getContactType() == UserContactTypes.EMAIL) {

					try {

						UserSignupResponse userSignupResponse = userSignupService.emailUserSignup(us);

						ar.setStatus(APIResponseCode.SUCCESS);

						ar.setData(userSignupResponse);

					} catch (Exception e) {

						ar.setStatus(APIResponseCode.FAILURE);

						ar.setDescription(e.getMessage());
					}

					// end of if(us.getContactType() == UserContactTypes.EMAIL)

				} else {
					ar.setStatus(APIResponseCode.FAILURE);
					ar.setDescription("Contact type not recognized!");
				}
			}
			// for non merchant users
//			else if() {
//				
//			}

			else {
				ar.setStatus(APIResponseCode.FAILURE);
				ar.setDescription("User type not recognized!");
			}
		} else {
			ar.setStatus(APIResponseCode.FAILURE);
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
				// ar.setStatus(userSignupService.verifyOTP(apiRequest.getData(), id));
				ar.setDescription("OTP Verified.");
				ar.setData(id);
			} catch (Exception e) {
				ar.setStatus(APIResponseCode.FAILURE);
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
						// ar.setStatus(userSignupService.resendEmailOTP(usr, id));
						ar.setData(id);
						ar.setDescription("Success! Email OTP sent.");
					} catch (Exception e) {
						// ar.setStatus(APIResponseCode.FAILURE.toString());
						ar.setDescription(e.getMessage());
					}
				} else if (usr.getContactType().equals(UserContactTypes.PHONE.toString())) {
					try {
						// ar.setStatus(userSignupService.resendSMSOTP(usr, id));
						ar.setData(id);
						ar.setDescription("Success! SMS OTP sent.");
					} catch (Exception e) {
						// ar.setStatus(APIResponseCode.FAILURE.toString());
						ar.setDescription(e.getMessage());
					}
				}
			} else {
				// ar.setStatus(APIResponseCode.FAILURE.toString());
				ar.setDescription("User does not exixt...");

			}

		} else if (apiRequest.getFunction().equals(UserSignupFunctionCodes.SETTING_PASSWORD.toString())) {
			UserSignupRequest usr = apiRequest.getData().getUserSignupRequest();
			if (usr.getContactType().equals("EMAIL")) {
				try {
					// ar.setStatus(userSignupService.setPasswordByEmail(usr, id));
					ar.setData(id);
					ar.setDescription("User successfully created!");
					// ar.setStatus(APIResponseCode.SUCCESS.toString());
				} catch (Exception e) {
					// ar.setStatus(APIResponseCode.FAILURE.toString());
					ar.setDescription(e.getMessage());
				}
			} else if (usr.getContactType().equals("PHONE_NUMBER")) {
				try {
					// ar.setStatus(userSignupService.setPasswordByEmail(usr, id));
					ar.setData(id);
					ar.setDescription("User successfully created!");
					// ar.setStatus(APIResponseCode.SUCCESS.toString());
				} catch (Exception e) {
					// ar.setStatus(APIResponseCode.FAILURE.toString());
					ar.setDescription(e.getMessage());
				}
			} else if (usr.getContactType().equals("EMAIL_AND_PHONE")) {
				try {
					// ar.setStatus(userSignupService.setPasswordByPhoneAndEmail(usr, id));
					ar.setData(id);
					ar.setDescription("User successfully created!");
					// ar.setStatus(APIResponseCode.SUCCESS.toString());
				} catch (Exception e) {
					// ar.setStatus(APIResponseCode.FAILURE.toString());
					ar.setDescription(e.getMessage());
				}
			} else {
				// ar.setStatus(APIResponseCode.FAILURE.toString());
				ar.setDescription("Contact type not recognized!");
			}
		}

		else {
			// ar.setStatus(APIResponseCode.FAILURE.toString());
			ar.setDescription("Invalid Function Name");
		}
		return ObjectToJson.toJson(ar);
	}

}
