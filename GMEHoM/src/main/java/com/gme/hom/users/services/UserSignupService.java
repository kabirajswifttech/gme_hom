package com.gme.hom.users.services;

import java.util.Optional;

import com.gme.hom.api.models.APIData;
import com.gme.hom.users.models.UserSignup;
import com.gme.hom.users.models.UserSignupRequest;
import com.gme.hom.users.models.UserSignupResponse;

public interface UserSignupService {

	UserSignup saveUserSignup(UserSignup userSignup);

	// Optional<UserSignup> getUserSignupByEmailId(String email);

	boolean userSignupAlreadyExixts(Long id);

	Optional<UserSignup> findByPhoneNumber(String phoneCode, String phoneNumber);

	String verifyOTP(APIData data, Long id) throws Exception;

	UserSignupResponse emailSignup(UserSignup us) throws Exception;

	Long startPhoneSignup(UserSignupRequest usr) throws Exception;

	String resendEmailOTP(UserSignupRequest usr, Long id) throws Exception;

	String resendSMSOTP(UserSignupRequest usr, Long id) throws Exception;

	String setPasswordByEmail(UserSignupRequest usr, Long id)throws Exception;

	String addEmail(UserSignupRequest usr, Long id) throws Exception;

	String addPhoneNumber(UserSignupRequest usr, Long id) throws Exception;

	Optional<UserSignup> getUserSignupById(Long id);

	Long startEmailAndPhoneSignup(UserSignupRequest usr) throws Exception;

	String setPasswordByPhoneNumber(UserSignupRequest usr, Long id) throws Exception;

	String setPasswordByPhoneAndEmail(UserSignupRequest usr, Long id) throws Exception;

}
