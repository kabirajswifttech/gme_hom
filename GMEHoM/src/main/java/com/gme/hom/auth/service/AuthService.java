package com.gme.hom.auth.service;

import com.gme.hom.auth.models.AuthResponse;
import com.gme.hom.usersecurity.models.UserAuthRequest;

import jakarta.servlet.http.Cookie;

public interface AuthService {
	// public Optional<User> AddUser(UserRequest user);

	public AuthResponse authenticateUser(UserAuthRequest userAuthRequest);

	public Cookie generateCookie(String jwt);
	
	//public OtpStatusCodes requestOtp(EntityTypes sourceType, MessageTypes messageType, String key);

}
