package com.gme.hom.auth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gme.hom.api.config.APIResponseCode;
import com.gme.hom.api.config.APIStatusCode;
import com.gme.hom.api.models.APIRequest;
import com.gme.hom.api.models.APIResponse;
import com.gme.hom.auth.config.AuthFunctionCodes;
import com.gme.hom.auth.config.AuthStatusCodes;
import com.gme.hom.auth.config.OtpStatusCodes;
import com.gme.hom.auth.config.OtpTypes;
import com.gme.hom.auth.models.AuthResponse;
import com.gme.hom.auth.models.Otp;
import com.gme.hom.auth.models.OtpRequest;
import com.gme.hom.auth.models.OtpResponse;
import com.gme.hom.auth.service.AuthService;
import com.gme.hom.auth.service.OtpService;

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

}
