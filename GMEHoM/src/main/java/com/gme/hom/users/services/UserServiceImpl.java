package com.gme.hom.users.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gme.hom.GlobalConfig;
import com.gme.hom.security.services.ChecksumService;
import com.gme.hom.security.services.PasswordGenerator;
import com.gme.hom.users.config.UserStatusCodes;
import com.gme.hom.users.config.UserTypeCodes;
import com.gme.hom.users.models.User;
import com.gme.hom.users.models.UserLog;
import com.gme.hom.users.models.UserRequest;
import com.gme.hom.users.models.UserResponse;
import com.gme.hom.users.repository.UserDTO;
import com.gme.hom.users.repository.UserLogRepository;
import com.gme.hom.users.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;

	@Autowired
	UserLogRepository userLogRepo;

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	public Long getSourceIdByUsername(String username) {
		return userRepository.getSourceIdByUsername(username);
	}

	@Override
	public Long getIdByEmailId(String emailId) {
		return userRepository.getIdByEmailId(emailId);
	}

	public String getUserTypeByUsername(String username) {
		return userRepository.getUserTypeByUsername(username);
	}

	@Override
	public Long getIdByUsername(String username) {
		return userRepository.getIdByUsername(username);
	}

	@Override
	public Long getSourceIdById(Long id) {
		return userRepository.getSourceIdById(id);
	}

	public List<UserDTO> getAllUsers() {
		return userRepository.getAllUsers();
	}

	@Override
	public List<UserDTO> getUsersByUserType(String userType) {
		return userRepository.getUsersByUserType(userType);
	}

	@Override
	public List<UserDTO> getUsersBySourceId(Long sourceId) {
		return userRepository.getUsersBySourceId(sourceId);
	}

	@Override
	public List<UserDTO> getUsersByUserTypeAndSourceId(String userType, Long sourceId) {
		return userRepository.getUsersByUserTypeAndSourceId(userType, sourceId);
	}

	@Override
	public UserDTO getUserInfoById(Long id) {
		return userRepository.getUserInfoById(id);
	}

	@Override
	public String getSourceTypeById(Long id) {
		return userRepository.getSourceTypeById(id);
	}

	@Override
	public String getUserTypeById(Long id) {
		return userRepository.getUserTypeById(id);
	}

	@Override
	public String getUserEmailById(Long id) {
		return userRepository.getUserEmailById(id);
	}

	@Override
	public UserResponse addUser(UserRequest userRequest, Long sourceId, String sourceType, String userType,
			String actionUserName) {
		UserResponse response = new UserResponse();
		try {

			// TODO: populate via constructor
			User newUser = new User();

			newUser.setFirstName(userRequest.getFirstName());
			newUser.setMiddleName(userRequest.getMiddleName());
			newUser.setLastName(userRequest.getLastName());
			newUser.setFullName(
					userRequest.getFirstName() + " " + userRequest.getMiddleName() + " " + userRequest.getLastName());
			newUser.setEmailId(userRequest.getEmailId());
			newUser.setPhoneCode(userRequest.getPhoneCode());

			newUser.setPhoneNumber(userRequest.getPhoneNumber());
			newUser.setFullNameNative(userRequest.getFullNameNative());

			newUser.setCountry(userRequest.getCountry());
			// newUser.setNationality(user.getNationality());
			newUser.setSalutation(userRequest.getSalutation());
			newUser.setRoles(userRequest.getRoles());
			newUser.setApiUser(userRequest.getIsApiUser());
			String randomPassword = PasswordGenerator.generateRandomPassword();
			newUser.setPassword(new BCryptPasswordEncoder().encode(randomPassword));
			newUser.setRoles(userRequest.getRoles());
			newUser.setCreatedBy(actionUserName);
			newUser.setStatus("ACTIVE");

			newUser.setSourceId((long) Math.toIntExact(sourceId));
			newUser.setSourceType(sourceType);
			newUser.setUserType(userType);
			newUser.setUsername(userRequest.getEmailId());
			newUser.setForcePwdChange(true);
			newUser.setActive(true);
			newUser.setEmailIdVerified(false);
			newUser.set2faEnabled(false);

			final UUID uuid = UUID.randomUUID();
			newUser.setUserId(uuid);
			newUser.setEntityHash(ChecksumService.getChecksum(newUser, GlobalConfig.DATA_ENTITY_HASH));

			userRepository.save(newUser);

			if (userType.equals("ADMIN") || userType.equals("PARTNER")) {
				newUser.setSourceId((Long) newUser.getId());
				userRepository.save(newUser);
			}

			// logging
			UserLog userLog = new UserLog(newUser);
			userLogRepo.save(userLog);
			response.setId(newUser.getId());
			response.setUserStatusCode(UserStatusCodes.ACTIVE);

		} catch (Exception e) {
			var msg = e.getMessage();
			logger.error(e.getMessage());

		}

		return response;
	}

	@Override
	public UserResponse updateUser(UserRequest userRequest, Long sourceId, String sourceType, String userType,
			String actionUserName, boolean isEmailChanged) {
		UserResponse response = new UserResponse();
		try {

			Optional<User> entity = userRepository.findById((long) userRequest.getId());
			if (entity.isPresent()) {

				long currentTimeMillis = System.currentTimeMillis();
				java.sql.Date sqlDate = new java.sql.Date(currentTimeMillis);

				long utcTimestampMillis = System.currentTimeMillis();
				java.sql.Date utcsqlDate = new java.sql.Date(utcTimestampMillis);

				User newUser = entity.get();

				newUser.setFirstName(userRequest.getFirstName());
				newUser.setMiddleName(userRequest.getMiddleName());
				newUser.setLastName(userRequest.getLastName());
				newUser.setFullName(userRequest.getFirstName() + " " + userRequest.getMiddleName() + " "
						+ userRequest.getLastName());

				if (isEmailChanged) {
					newUser.setEmailId(userRequest.getEmailId());
					String randomPassword = PasswordGenerator.generateRandomPassword();
					newUser.setPassword(new BCryptPasswordEncoder().encode(randomPassword));
					newUser.setUsername(userRequest.getEmailId());
					newUser.setForcePwdChange(true);

				}

				newUser.setPhoneCode(userRequest.getPhoneCode());
				newUser.setPhoneNumber(userRequest.getPhoneNumber());
				newUser.setFullNameNative(userRequest.getFullNameNative());

				newUser.setCountry(userRequest.getCountry());
				// newUser.setNationality(user.getNationality());
				newUser.setSalutation(userRequest.getSalutation());
				newUser.setRoles(userRequest.getRoles());
				newUser.setUpdatedBy(actionUserName);
				newUser.setUpdatedDate(sqlDate);
				newUser.setUpdatedDateUTC(utcsqlDate);
				newUser.setEntityHash(ChecksumService.getChecksum(newUser, GlobalConfig.DATA_ENTITY_HASH));
				userRepository.save(newUser);

				// logging
				UserLog userLog = new UserLog(newUser);
				userLogRepo.save(userLog);

				response.setId((long) userRequest.getId());
				// response.setUserStatusCode(UserStatusCodes.ACTIVE);
				return response;
			} else {
				return response;
			}

		} catch (Exception e) {
			var msg = e.getMessage();
			logger.error(e.getMessage());
		}
		return response;

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

	@Override
	public UserResponse updateProfile(UserRequest userRequest, Long id, String loggedInUserType,
			String actionUserName) {
		UserResponse response = new UserResponse();
		try {

			Optional<User> entity = userRepository.findById(id);
			if (entity.isPresent()) {

				long currentTimeMillis = System.currentTimeMillis();
				java.sql.Date sqlDate = new java.sql.Date(currentTimeMillis);

				long utcTimestampMillis = System.currentTimeMillis();
				java.sql.Date utcsqlDate = new java.sql.Date(utcTimestampMillis);

				User myDetail = entity.get();

				if (!loggedInUserType.equals(UserTypeCodes.MERCHANT.toString())) {

					myDetail.setFirstName(userRequest.getFirstName());
					myDetail.setMiddleName(userRequest.getMiddleName());
					myDetail.setLastName(userRequest.getLastName());
					myDetail.setFullName(userRequest.getFirstName() + " " + userRequest.getMiddleName() + " "
							+ userRequest.getLastName());

					myDetail.setPhoneCode(userRequest.getPhoneCode());
					myDetail.setPhoneNumber(userRequest.getPhoneNumber());
					myDetail.setFullNameNative(userRequest.getFullNameNative());

					myDetail.setCountry(userRequest.getCountry());
					// myDetail.setNationality(user.getNationality());
					myDetail.setSalutation(userRequest.getSalutation());

					myDetail.setCountry(userRequest.getCountry());
					myDetail.setGender(userRequest.getGender());
					myDetail.setAddress1(userRequest.getAddress1());
					myDetail.setAddress2(userRequest.getAddress2());
					myDetail.setDob(userRequest.getDob());
				}

				myDetail.setLanguagePreference(userRequest.getLanguagePreference());
				myDetail.setNotificationPreference(userRequest.getNotificationPreference());
				myDetail.setProfileImage(userRequest.getProfileImage());

				myDetail.setUpdatedBy(actionUserName);
				myDetail.setUpdatedDate(sqlDate);
				myDetail.setUpdatedDateUTC(utcsqlDate);

				myDetail.setEntityHash(ChecksumService.getChecksum(myDetail, GlobalConfig.DATA_ENTITY_HASH));

				userRepository.save(myDetail);

				// logging
				UserLog userLog = new UserLog(myDetail);
				userLogRepo.save(userLog);

				response.setId(id);
				// response.setUserStatusCode(UserStatusCodes.ACTIVE);
				return response;
			} else {
				return response;
			}

		} catch (Exception e) {
			var msg = e.getMessage();
			logger.error(e.getMessage());
		}
		return response;
	}

	// private final UserRepository UserRepository;

	/*
	 * @Autowired UserRepository userRepository;
	 * 
	 * public List<UserDTO> getAllUsers() { return userRepository.getAllUsers(); }
	 * 
	 * 
	 * 
	 * public long getSourceIdByUsername(String username) { return
	 * userRepository.getSourceIdByUsername(username); }
	 * 
	 * public String getUserTypeByUsername(String username) { return
	 * userRepository.getUserTypeByUsername(username); }
	 */

}
