package com.gme.hom.usersecurity.repository;

public interface UserSecurityDTO {
	
		String getEmail_id();

		String getUsername();

		String getRoles();
		
		String getStatus();
		
		boolean getIs_active();
		
		boolean getIs_email_id_verified();
}
