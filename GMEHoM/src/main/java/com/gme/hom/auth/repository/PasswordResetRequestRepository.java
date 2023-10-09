package com.gme.hom.auth.repository;

import java.sql.Timestamp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.gme.hom.auth.models.PasswordResetRequests;

@Repository
public interface PasswordResetRequestRepository extends JpaRepository<PasswordResetRequests, Long>{

	@Query(value = "select u.user_id from password_reset_requests u where u.token=?1 and u.token_expiration_timestamp<?2 and u.status=?3", nativeQuery = true)
	Long getUserIdFromToken(String token, Timestamp timestamp, String status);
	

	@Query(value = "select u.id from password_reset_requests u where u.token=?1", nativeQuery = true)
	Long getIdFromToken(String token);

}

