package com.gme.hom.users.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gme.hom.users.models.User;
import com.gme.hom.users.models.UserDTO;
import com.gme.hom.usersecurity.models.UserSecurityDTO;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query(value = "select u.first_name, u.last_name, u.email, u.username, u.roles from users u", nativeQuery = true)
	List<UserDTO> getAllUsers();

	Optional<User> findByUsername(String username);
	
	@Query(value = "select u.id from users u where u.username=?1", nativeQuery = true )
	long getSourceIdByUsername(String username);
	
	@Query(value = "select u.user_type from users u where u.username=?1", nativeQuery = true )
	String getUserTypeByUsername(String username);
	
	@Query(value = "select u.status, u.is_active, u.status, u.is_email_id_verified from users u where u.username=?1", nativeQuery = true)
	UserSecurityDTO getUserSecurityDetails(String username);
	
}
