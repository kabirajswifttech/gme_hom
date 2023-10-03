package com.gme.hom.users.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gme.hom.users.models.UserSignup;

@Repository
public interface UserSignupRepository extends JpaRepository<UserSignup, Long> {
	
	@Query(value="SELECT * FROM users_signup us WHERE us.contact_info = ?1", nativeQuery = true)
	UserSignup findByEmailId(String username);	
	
	@Query(value="SELECT * FROM users_signup us WHERE us.phone_code = ?1 AND us.phone_number = ?2", nativeQuery = true)
	Optional<UserSignup> findByPhoneNumber(String phoneCode, String phoneNumber);
	
	
	
}
