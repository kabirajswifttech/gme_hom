package com.gme.hom.users.services;

import java.util.List;

import com.gme.hom.users.models.User;
import com.gme.hom.users.models.UserDTO;
import com.gme.hom.users.models.UserRequest;

public interface UserService {

	public List<UserDTO> getAllUsers();

	public User addUser(UserRequest user);

	public long getSourceIdByUsername(String username);
	
	public String getUserTypeByUsername(String username);

}