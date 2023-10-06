package com.gme.hom.security.services;

import java.util.Random;

public class PasswordGenerator {
	public static String generateRandomPassword() {
		int passwordLength = 8;
		StringBuilder password = new StringBuilder();

		Random random = new Random();
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+";

		for (int i = 0; i < passwordLength; i++) {
			int index = random.nextInt(characters.length());
			password.append(characters.charAt(index));
		}

		return password.toString();
	}
}
