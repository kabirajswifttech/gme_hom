package com.gme.hom.users.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gme.hom.users.models.User;
import com.gme.hom.users.models.UserDTO;
import com.gme.hom.users.models.UserRequest;
import com.gme.hom.users.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

	// private final UserRepository UserRepository;

	@Autowired
	UserRepository userRepository;

	public List<UserDTO> getAllUsers() {
		return userRepository.getAllUsers();
	}

	public User addUser(UserRequest userRequest) {
		User newUser = new User();
		newUser.setFirstName(userRequest.getFirstName());
		newUser.setLastName(userRequest.getLastName());
		newUser.setEmailId(userRequest.getEmailId());
		newUser.setPassword(new BCryptPasswordEncoder().encode(userRequest.getPassword()));
		newUser.setRoles(userRequest.getRoles());
		return userRepository.save(newUser);
	}

	public long getSourceIdByUsername(String username) {
		return userRepository.getSourceIdByUsername(username);
	}
	
	public String getUserTypeByUsername(String username) {
		return userRepository.getUserTypeByUsername(username);
	}

}
