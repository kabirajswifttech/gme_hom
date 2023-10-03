package com.gme.hom.usersecurity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.gme.hom.users.repository.UserRepository;
import com.gme.hom.usersecurity.models.UserSecurity;

@Repository
public class UserSecurityDetailsService implements UserDetailsService {

    /*
     private final UsersRepository usersRepository;
          */
    
	@Autowired
	private UserRepository userRepository;
	
	UserSecurityDetailsService(UserRepository userRepository){
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return userRepository.findByUsername(username).map(UserSecurity::new)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found!"));
	}

}
