package com.gme.hom.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gme.hom.users.models.UserSignup;

@Repository
public interface UserSignupRepository extends JpaRepository<UserSignup, Long> {

	@Query(value = "SELECT * FROM users_signup us WHERE us.contact_info = ?1", nativeQuery = true)
	UserSignup findByContactInfo(String contactInfo);

	@Query(value = "SELECT * FROM users_signup us WHERE concat(us.phone_code, us.phone_number) = ?1", nativeQuery = true)
	UserSignup findByPhone(String phone);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value="UPDATE users_signup SET is_email_otp_verified = true "
			+ "WHERE id = ?1 "
			+ "AND contact_info = ?2", nativeQuery=true)
	int setEmailOtpVerifiedTrue(Long sourceId, String contactInfo);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value="UPDATE users_signup SET is_sms_otp_verified = true "
			+ "WHERE id = ?1 "
			+ "AND concat(phone_code, phone_number) = ?2", nativeQuery=true)
	int setSmsOtpVerifiedTrue(Long sourceId, String contactInfo);

}
