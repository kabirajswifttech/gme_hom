package com.gme.hom.auth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gme.hom.api.config.APIResponseCode;
import com.gme.hom.api.models.APIRequest;
import com.gme.hom.api.models.APIResponse;
import com.gme.hom.auth.config.AuthStatusCode;
import com.gme.hom.auth.models.AuthResponse;
import com.gme.hom.auth.service.AuthService;

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

	@PostMapping("")
	public ResponseEntity<APIResponse> authenticate(@RequestBody APIRequest apiRequest, HttpServletRequest httpRequest,
			HttpServletResponse httpResponse) {

		APIResponse ar = new APIResponse();

		AuthResponse authResponse = authService.authenticateUser(apiRequest.getData().getUserAuthRequest());

		if (authResponse.getAuthStatus().equals(AuthStatusCode.AUTHENTICATED)) {

			Cookie cookie = authService.generateCookie(authResponse.getJwt());

			httpResponse.addCookie(cookie);

			ar.setStatus(APIResponseCode.SUCCESS.toString());

			ar.setData(authResponse.getJwt());

			return ResponseEntity.ok(ar);

		} else {

			ar.setStatus(APIResponseCode.FAILURE.toString());

			ar.setData(authResponse);

			return ResponseEntity.ok(ar);

		}

	}

}
