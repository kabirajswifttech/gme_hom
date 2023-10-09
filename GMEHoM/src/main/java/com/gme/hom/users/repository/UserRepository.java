package com.gme.hom.users.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gme.hom.users.models.User;
import com.gme.hom.usersecurity.repository.UserSecurityDTO;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String username);

	@Query(value = "select u.id from users u where u.username=?1", nativeQuery = true)
	long getSourceIdByUsername(String username);

	@Query(value = "select u.user_type from users u where u.username=?1", nativeQuery = true)
	String getUserTypeByUsername(String username);

	@Query(value = "select u.status, u.is_active, u.is_email_id_verified from users u where u.username=?1", nativeQuery = true)
	UserSecurityDTO getUserSecurityDetails(String username);

	@Query(value = "select u.source_type from users u where u.id=?1", nativeQuery = true)
	String getSourceTypeById(Long id);

	@Query(value = "select u.user_type from users u where u.id=?1", nativeQuery = true)
	String getUserTypeById(Long id);

	@Query(value = "select u.email_id from users u where u.id=?1", nativeQuery = true)
	String getUserEmailById(Long id);

	@Query(value = "select u.id from users u where u.username=?1", nativeQuery = true)
	Long getIdByUsername(String username);

	@Query(value = "select u.source_id from users u where u.id=?1", nativeQuery = true)
	Long getSourceIdById(Long id);

	@Query(value = "select u.id from users u where u.email_id=?1", nativeQuery = true)
	Long getIdByEmailId(String emailId);

	@Query(value = "select id,full_name ,full_name_native ,email_id ,phone_number ,country ,status "
			+ ",roles,is_email_id_verified,user_type,source_id ,source_type ,created_by,created_date from users ", nativeQuery = true)
	List<UserDTO> getAllUsers();

	@Query(value = "select id,full_name ,full_name_native ,email_id ,phone_number ,country ,status"
			+ " ,roles,is_email_id_verified,user_type ,source_id ,source_type,created_by,created_date from users "
			+ " where user_type=?1", nativeQuery = true)
	List<UserDTO> getUsersByUserType(String userType);

	@Query(value = "select id,full_name ,full_name_native ,email_id ,phone_number ,country ,status "
			+ ",roles,is_email_id_verified,user_type,source_id ,source_type,created_by,created_date  from users"
			+ "  where source_id=?1", nativeQuery = true)

	List<UserDTO> getUsersBySourceId(Long sourceId);

	@Query(value = "select id,full_name ,full_name_native ,email_id ,phone_number ,country ,status "
			+ ",roles,is_email_id_verified,user_type,source_id ,source_type ,created_by,created_date from users "
			+ " where user_type=?1 and source_id=?2", nativeQuery = true)
	List<UserDTO> getUsersByUserTypeAndSourceId(String userType, Long sourceId);

	@Query(value = "select id,full_name ,full_name_native ,email_id ,phone_number ,country ,status"
			+ " ,roles,is_email_id_verified,user_type,source_id ,source_type ,created_by,created_date from users"
			+ "  where id=?1", nativeQuery = true)
	UserDTO getUserInfoById(Long id);
	
	@Query(value = "select u.password from users u where u.id=?1", nativeQuery = true)
	String getUserPasswordFormId(Long id);
	
	
}
