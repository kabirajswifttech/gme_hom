package com.gme.hom.usersecurity.services;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.servlet.http.HttpServletRequest;

public class UserSecurityService {

	public static String getUsername() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return userDetails.getUsername();
	}
	
	public static String getClientAddress(HttpServletRequest httpRequest) {
		return httpRequest.getRemoteAddr();
	}
}
