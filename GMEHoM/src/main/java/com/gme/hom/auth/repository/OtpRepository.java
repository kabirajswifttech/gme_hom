package com.gme.hom.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gme.hom.auth.models.Otp;

@Repository
public interface OtpRepository extends JpaRepository<Otp, Long>{
	
	@Query(value = "SELECT otp_type, contact_info, otp_code, source_type, source_id FROM otp "
			+ "WHERE contact_info = ?1 "
			+ "AND otp_code = ?2 "
			+ "AND source_type = ?3 "
			+ "AND source_id = ?4 "
			+ "ORDER BY created_date desc "
			+ "LIMIT 1", nativeQuery = true)
	OtpDTO getOtp(String contactInfo, String otpCode, String sourceType, Long sourceId);

}
