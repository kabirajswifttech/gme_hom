package com.gme.hom.users.services;

import java.util.List;

import com.gme.hom.users.models.User;
import com.gme.hom.users.models.UserRequest;
import com.gme.hom.users.models.UserResponse;
import com.gme.hom.users.repository.UserDTO;

public interface UserService {
	
	//==all user no filters
	public List<UserDTO> getAllUsers();
	
	//==users by user type
	public List<UserDTO> getUsersByUserType(String userType);
	
	//==users by source id
	public List<UserDTO> getUsersBySourceId(Long sourceId);
		
	//==users by user type and source id
	public List<UserDTO> getUsersByUserTypeAndSourceId(String userType, Long sourceId);
		

	public User addUser(UserRequest user);
	
	public Long getSourceIdByUsername(String username);
	public Long getIdByUsername(String username);
	public Long getIdByEmailId(String emailId);
	public Long getSourceIdById(Long id);
	public String getUserTypeByUsername(String username);
	public String getUserTypeById(Long id);
	public String getSourceTypeById(Long id);
	public String getUserEmailById(Long id);
	//UserResponse addUser(UserInfoRequest user);
	//public String getUserTypeBySourceId(long id);
	public UserResponse addUser(UserRequest userRequest, Long sourceId, String sourceType, String userType,String actionUserName);
	public UserResponse updateUser(UserRequest userRequest, Long sourceId, String sourceType, String userType,String actionUserName,boolean isEmailChanged);

	public UserDTO getUserInfoById(Long id);	
	

	public UserResponse updateProfile(UserRequest userRequest, Long id,String loggedInUserType, String actionUserName);		
	
	
	/*
	 * public List<UserDTO> getAllUsers();
	 * 
	 * public User addUser(UserRequest user);
	 * 
	 * public long getSourceIdByUsername(String username);
	 * 
	 * public String getUserTypeByUsername(String username);
	 */

}