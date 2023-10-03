package com.gme.hom.api.internal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gme.hom.api.config.APIRequestFunctionCode;
import com.gme.hom.api.config.APIResponseCode;
import com.gme.hom.api.models.APIRequest;
import com.gme.hom.api.models.APIResponse;
import com.gme.hom.auth.models.OTP;
import com.gme.hom.auth.service.OTPService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/internal/otp")
public class OTPController {

	@Autowired
	private OTPService otpService;

	// INFO: To be used by internal services. MUST BE DISABLED FOR PRODUCTION
	@PostMapping("")
	public ResponseEntity<APIResponse> internalOTP(@RequestBody APIRequest apiRequest, HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		APIResponse ar = new APIResponse();

		if (apiRequest.getFunction().equals(APIRequestFunctionCode.VALIDATE.toString())) {

			OTP otp = apiRequest.getData().getOtp();

			int otpValue = otpService.getOtp(otp.getContactInfo());

			if (otpValue == Integer.parseInt(otp.getOtpCode())) {
				ar.setStatus(APIResponseCode.SUCCESS.toString());

			} else {
				ar.setStatus(APIResponseCode.FAILURE.toString());
				ar.setDescription("OTP expired.");
			}

			ar.setDescription("INFO: To be used by internal services only. MUST BE DISABLED FOR PRODUCTION");

			return ResponseEntity.ok(ar);

		} else if (apiRequest.getFunction().equals(APIRequestFunctionCode.GENERATE.toString())) {

			OTP otp = apiRequest.getData().getOtp();

			int otpCode = otpService.generateOTP(otp.getContactInfo());

			ar.setStatus(APIResponseCode.SUCCESS.toString());

			otp.setOtpCode(String.valueOf(otpCode));

			ar.setDescription("INFO: To be used by internal services only. MUST BE DISABLED FOR PRODUCTION");

			ar.setData(otp);

			return ResponseEntity.ok(ar);

		} else {
			ar.setStatus(APIResponseCode.FAILURE.toString());

			return ResponseEntity.ok(ar);
		}

	}

}
