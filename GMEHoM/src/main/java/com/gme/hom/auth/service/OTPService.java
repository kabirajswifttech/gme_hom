package com.gme.hom.auth.service;

public interface OTPService {
	public int generateOTP(String key);
	public int getOtp(String key);

}
