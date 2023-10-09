package com.gme.hom.security.services;

import java.security.SecureRandom;
import java.util.Base64;

public class RandomTokenGenerator {
	 private static final int TOKEN_LENGTH = 16; // You can adjust the length of the token

	    public static String generateToken() {
	        byte[] randomBytes = new byte[TOKEN_LENGTH];
	        new SecureRandom().nextBytes(randomBytes);
	        return Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);
	    }
}
