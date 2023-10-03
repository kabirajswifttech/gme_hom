package com.gme.hom.auth.service;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.gme.hom.GlobalConfig;
import com.gme.hom.auth.components.JwtUtils;
import com.gme.hom.auth.config.AuthStatusCode;
import com.gme.hom.auth.models.AuthResponse;
import com.gme.hom.users.repository.UserRepository;
import com.gme.hom.usersecurity.config.UserSecurityStatus;
import com.gme.hom.usersecurity.models.UserAuthRequest;
import com.gme.hom.usersecurity.models.UserSecurityDTO;
import com.gme.hom.usersecurity.services.UserSecurityDetailsService;

import jakarta.servlet.http.Cookie;

@Service
public class AuthServiceImpl implements AuthService {
	@Autowired
	UserRepository userRepository;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserSecurityDetailsService userDetailsService;

	@Autowired
	JwtUtils jwtUtils;

	private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

	public AuthResponse authenticateUser(UserAuthRequest userAuthRequest) {

		AuthResponse authResponse = new AuthResponse();

		authResponse.setAuthStatus(AuthStatusCode.BAD_CREDENTIALS);

		// Obtain user details first
		UserSecurityDTO usDTO = userRepository.getUserSecurityDetails(userAuthRequest.getUsername());

		// If user details are available
		if (usDTO != null) {
			if (usDTO.getStatus().equals(UserSecurityStatus.ACTIVE.toString()) && usDTO.getIs_active()
					&& usDTO.getIs_email_id_verified()) {

				String jwt = authenticateUserJwt(userAuthRequest.getUsername(), userAuthRequest.getPassword());

				if (jwt != null) {

					authResponse.setAuthStatus(AuthStatusCode.AUTHENTICATED);
					authResponse.setJwt(jwt);
				}
			}
		}

		return authResponse;
	}

	// Authenticate user then generate jwt
	private String authenticateUserJwt(String username, String password) {
		try {

			Authentication authResult = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>()));

			if (authResult.isAuthenticated()) {

				final UserDetails userDetails = userDetailsService.loadUserByUsername(username);

				if (userDetails != null) {

					return jwtUtils.generateToken(userDetails);

				} else {

					return null;
				}
			}

		} catch (AuthenticationException e) {

			logger.error(e.toString());

			return null;

		}

		return null;
	}

	// Generate cookie
	public Cookie generateCookie(String jwt) {

		Cookie cookie = new Cookie("jwt", jwt);

		cookie.setMaxAge(GlobalConfig.AUTH_COOKIE_MAXAGE);

		cookie.setHttpOnly(true);

		cookie.setPath("/"); // Global

		return cookie;
	}

	/*
	 * public Optional<User> AddUser(UserRequest user) {
	 * 
	 * User newUser = new User();
	 * 
	 * newUser.setFirstName(user.getFirstName());
	 * 
	 * newUser.setLastName(user.getLastName());
	 * 
	 * newUser.setEmailId(user.getEmailId());
	 * 
	 * newUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
	 * 
	 * newUser.setRoles("ROLE_USER");
	 * 
	 * return Optional.of(userRepository.save(newUser)); }
	 */
}
