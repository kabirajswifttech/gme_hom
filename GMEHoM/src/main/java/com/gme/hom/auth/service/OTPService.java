package com.gme.hom.auth.service;

import com.gme.hom.auth.config.OtpTypes;
import com.gme.hom.auth.models.Otp;
import com.gme.hom.auth.models.OtpResponse;
import com.gme.hom.common.models.EntityTypes;

public interface OtpService {
	public Otp generateOtp(String key, OtpTypes otpType, EntityTypes sourceType, Long sourceId);

	public boolean validateOtp(Otp otp);

	public OtpResponse requestOtp(EntityTypes sourceType, OtpTypes otpType, String contactInfo);
}
