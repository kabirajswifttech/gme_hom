package com.gme.hom.auth.repository;

public interface OtpDTO {
	
	String getOtp_type();	
	String getContact_info();
	String getOtp_code();
	String getSource_type();
	Long getSource_id();
}
