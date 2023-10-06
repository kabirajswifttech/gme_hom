package com.gme.hom.usersecurity.services;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.servlet.http.HttpServletRequest;

public class UserSecurityService {

	public static String getUsername() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (!(authentication instanceof AnonymousAuthenticationToken)) {

			UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			return userDetails.getUsername();
		} else {
			return null;
		}
	}

	public static String getClientAddress(HttpServletRequest httpRequest) {
		return httpRequest.getRemoteAddr();
	}
}
