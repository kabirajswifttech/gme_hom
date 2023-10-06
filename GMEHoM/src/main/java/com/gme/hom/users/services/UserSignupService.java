package com.gme.hom.users.services;

import java.util.Optional;

import com.gme.hom.users.models.UserSignup;
import com.gme.hom.users.models.UserSignupResponse;

public interface UserSignupService {

	UserSignup saveUserSignup(UserSignup userSignup);

	boolean userSignupAlreadyExixts(Long id);

	UserSignup findByPhone(String phoneCode, String phoneNo);

	//String verifyOTP(APIData data, Long id) throws Exception;

	UserSignupResponse emailUserSignup(UserSignup us) throws Exception;

	Optional<UserSignup> getUserSignupById(Long id);

	

}
