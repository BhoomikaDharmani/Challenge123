package com.synchrony.challenge.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil {

	private static final int STRENGTH = 8;
	
	public static String hashPassword(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(STRENGTH);
		return passwordEncoder.encode(password);
	}
	
	public static boolean isPasswordValid(String password, String hashedPassword) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(STRENGTH);
		return passwordEncoder.matches(password, hashedPassword);

	}
}
