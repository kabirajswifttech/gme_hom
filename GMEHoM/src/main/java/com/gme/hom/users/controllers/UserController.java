package com.gme.hom.users.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gme.hom.api.config.APIRequestFunctionCode;
import com.gme.hom.api.config.APIRequestScopeCode;
import com.gme.hom.api.config.APIResponseCode;
import com.gme.hom.api.config.APIResponseScopeCode;
import com.gme.hom.api.models.APIRequest;
import com.gme.hom.api.models.APIResponse;
import com.gme.hom.auth.service.PasswordService;
import com.gme.hom.messaging.config.MessageTypes;
import com.gme.hom.messaging.models.Message;
import com.gme.hom.messaging.models.MessageReceiver;
import com.gme.hom.messaging.services.MessagingService;
import com.gme.hom.templates.config.MessageTemplateTypes;
import com.gme.hom.templates.models.MessageTemplateFactory;
import com.gme.hom.templates.models.ResetPassword;
import com.gme.hom.templates.repository.TemplateDTO;
import com.gme.hom.templates.services.TemplateService;
import com.gme.hom.users.config.UserTypeCodes;
import com.gme.hom.users.models.UserInfoRequest;
import com.gme.hom.users.models.UserRequest;
import com.gme.hom.users.models.UserResponse;
import com.gme.hom.users.repository.UserDTO;
import com.gme.hom.users.services.UserService;
import com.gme.hom.usersecurity.services.UserSecurityService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	private final UserService userService;

	private final TemplateService templateService;
	private final MessagingService messagingService;
	private final PasswordService passwordService;
	UserController(UserService userService, TemplateService templateService, MessagingService messagingService,PasswordService passwordService) {
		this.userService = userService;
		this.templateService = templateService;
		this.messagingService = messagingService;
		this.passwordService=passwordService;
	}

	// @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
	@GetMapping("")
	public ResponseEntity<APIResponse> getUsersList(@RequestBody APIRequest apiRequest, HttpServletRequest httpRequest,
			HttpServletResponse httpResponse) {
		APIResponse ar = new APIResponse();

		if (apiRequest.getFunction().equals(APIRequestFunctionCode.SEARCH.toString())
				&& apiRequest.getScope().equals(APIRequestScopeCode.ALL.toString())) {

			String actionUserName = UserSecurityService.getUsername();
			String loggedInUserType = userService.getUserTypeByUsername(actionUserName);
			Long sourceId;

			if (loggedInUserType.equals("ADMIN") || loggedInUserType.equals("ADMIN_USER")) {
				UserInfoRequest userRequest = apiRequest.getData().getUserInfoRequest();
				String userType = ((userRequest.getUser_type() != null) ? userRequest.getUser_type() : "");
				sourceId = (long) ((userRequest.getSourceId() != null) ? userRequest.getSourceId() : 0);

				if (sourceId < 1 && (userType.equals(null) || userType.isBlank() || userType.isEmpty())) {
					List<UserDTO> users = userService.getAllUsers();
					ar.setData(users);
				} else if (sourceId > 0 && !userType.equals(null) && !userType.isBlank() && !userType.isEmpty()) {
					List<UserDTO> users = userService.getUsersByUserTypeAndSourceId(userType, sourceId);
					ar.setData(users);
				} else if (sourceId > 0) {
					List<UserDTO> users = userService.getUsersBySourceId(sourceId);
					ar.setData(users);
				} else {
					List<UserDTO> users = userService.getUsersByUserType(userType);
					ar.setData(users);
				}
				return ResponseEntity.ok(ar);

			} else {
				Long sourceUserId = userService.getIdByUsername(actionUserName);
				sourceId = userService.getSourceIdById(sourceUserId);
				// find source id of loggedin user
				if (sourceId < 1) {
					ar.setStatus(APIResponseCode.FAILURE);
					ar.setDescription("Invalid operation.");
					return ResponseEntity.ok(ar);
				}
				List<UserDTO> users = userService.getUsersBySourceId(sourceId);
				ar.setData(users);
			}
			ar.setStatus(APIResponseCode.SUCCESS);
			ar.setScope(APIResponseScopeCode.MULTIPLE);
			ar.setDataType("Users");
			ar.setDescription("Data retrieved successfully.");
			return ResponseEntity.ok(ar);
		} else if (apiRequest.getFunction().equals(APIRequestFunctionCode.SEARCH.toString())
				&& apiRequest.getScope().equals(APIRequestScopeCode.BYID.toString())) {
			UserRequest userRequest = apiRequest.getData().getUserRequest();
			// TODO: check for scope 'BYID'
			// == id is of the user whose data is going to be updated. users table id column
			// value
			Long id = (long) ((userRequest.getId() != null) ? userRequest.getId() : 0);

			if (id < 1) {
				ar.setStatus(APIResponseCode.FAILURE);
				ar.setDescription("Invalid user.");
				return ResponseEntity.ok(ar);
			}
			UserDTO users = userService.getUserInfoById(id);
			if (users != null) {
				ar.setData(users);
				ar.setStatus(APIResponseCode.SUCCESS);
				ar.setScope(APIResponseScopeCode.SINGLE);
				ar.setDataType("Users");
				ar.setDescription("Data retrieved successfully.");
				return ResponseEntity.ok(ar);
			} else {
				ar.setStatus(APIResponseCode.FAILURE);
				ar.setDescription("No results found for the requested query.");
				return ResponseEntity.ok(ar);
			}

		}

		return ResponseEntity.ok(ar);
	}

	// @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
	@PostMapping("")
	public ResponseEntity<APIResponse> UserServices(@RequestBody APIRequest apiRequest, HttpServletRequest httpRequest,
			HttpServletResponse httpResponse) {
		String RequestedIp=UserSecurityService.getClientAddress(httpRequest);
		APIResponse ar = new APIResponse();
		String actionUserName = UserSecurityService.getUsername();
		String loggedInUserType = userService.getUserTypeByUsername(actionUserName);
		String userType = "";
		String sourceType = "";

		Long sourceId = (long) ((apiRequest.getData().getUserRequest().getSourceId() != null)
				? apiRequest.getData().getUserRequest().getSourceId()
				: 0);

		// == id is of the user whose data is going to be updated. users table id column
		// value
		Long id = (long) ((apiRequest.getData().getUserRequest().getId() != null)
				? apiRequest.getData().getUserRequest().getId()
				: 0);

		Long sourceUserId = (long) ((apiRequest.getData().getUserRequest().getSourceId() != null)
				? apiRequest.getData().getUserRequest().getSourceId()
				: 0);

		if (sourceUserId > 0) {
			// user is created on behaf of others
			sourceId = userService.getSourceIdById(sourceUserId);
			sourceType = userService.getSourceTypeById(sourceUserId);

			if (sourceType == null || sourceType.isEmpty() || sourceType.isBlank()) {
				ar.setStatus(APIResponseCode.FAILURE);
				ar.setDescription("Invalid operation.");
				return ResponseEntity.ok(ar);
			}

		} else {

			// Add Case
			// is_partrner_user true this case should be handled
			if ((loggedInUserType.equals(UserTypeCodes.ADMIN.toString())
					|| loggedInUserType.equals(UserTypeCodes.ADMIN_USER.toString()))
					&& apiRequest.getData().getUserRequest().getIsApiUser()) {
				sourceType = UserTypeCodes.PARTNER.toString();
			} else {
				// logged in user case
				sourceUserId = userService.getIdByUsername(actionUserName);
				sourceId = userService.getSourceIdById(sourceUserId);
				sourceType = userService.getSourceTypeById(sourceUserId);

				if (sourceType == null || sourceType.isEmpty() || sourceType.isBlank()) {
					ar.setStatus(APIResponseCode.FAILURE);
					ar.setDescription("Invalid operation.");
					return ResponseEntity.ok(ar);
				}
			}

		}

		// set new user's user type by business logic

		if (sourceType == null || sourceType.isBlank()) {
			ar.setStatus(APIResponseCode.FAILURE);
			ar.setDescription("Invalid operation.");
			return ResponseEntity.ok(ar);
		} else {
			if (sourceType.equals(UserTypeCodes.ADMIN.toString())) {
				userType = UserTypeCodes.ADMIN_USER.toString();
			} else if (sourceType.equals(UserTypeCodes.ADMIN_USER.toString())) {
				userType = UserTypeCodes.ADMIN.toString();
			} else if (sourceType.equals(UserTypeCodes.MERCHANT.toString())) {
				userType = UserTypeCodes.MERCHANT_USER.toString();
			} else if (sourceType.equals(UserTypeCodes.MERCHANT_USER.toString())) {
				userType = UserTypeCodes.MERCHANT_USER.toString();
			} else if (sourceType.equals(UserTypeCodes.PARTNER.toString())) {
				if (sourceUserId > 0 && sourceId > 0) {
					userType = UserTypeCodes.PARTNER_USER.toString();
				} else {
					userType = UserTypeCodes.PARTNER.toString();
				}

			} else if (sourceType.equals(UserTypeCodes.PARTNER_USER.toString())) {
				userType = UserTypeCodes.PARTNER_USER.toString();
			} else {
				ar.setStatus(APIResponseCode.FAILURE);
				ar.setDescription("Invalid operation.");
				return ResponseEntity.ok(ar);
			}
		}

		if (apiRequest.getFunction().equals(APIRequestFunctionCode.ADD_DATA.toString())) {

			// === for duplicate email id==/////
			Long userIdByEmail = (long) ((userService
					.getIdByEmailId(apiRequest.getData().getUserRequest().getEmailId()) != null)
							? userService.getIdByEmailId(apiRequest.getData().getUserRequest().getEmailId())
							: 0);
			if (userIdByEmail > 0) {
				ar.setStatus(APIResponseCode.FAILURE);
				ar.setDescription("Duplicate email id.");
				return ResponseEntity.ok(ar);
			}

			UserRequest userRequest = apiRequest.getData().getUserRequest();
			if (userRequest != null) {
				try {
					UserResponse userResponse = userService.addUser(userRequest, sourceId, sourceType, userType,
							actionUserName);

					if (userResponse.getId() != null) {
						if (userResponse.getId() > 0) {
							ar.setStatus(APIResponseCode.SUCCESS);
							ar.setDescription("Created successfully.");
							ar.setData(userResponse);
							// reset password email service
							// building message using message template
							passwordService.generateResetPasswordLink(userResponse.getId(), actionUserName,RequestedIp);	
							return ResponseEntity.ok(ar);
						} else {
							ar.setStatus(APIResponseCode.FAILURE);
							ar.setDescription("Creation Failed.");
							return ResponseEntity.ok(ar);
						}
					} else {
						ar.setStatus(APIResponseCode.FAILURE);
						ar.setDescription("Creation Failed.");
						return ResponseEntity.ok(ar);
					}

				} catch (Exception e) {

					ar.setStatus(APIResponseCode.FAILURE);

					ar.setDescription(e.getMessage());
					return ResponseEntity.ok(ar);
				}

			}

		}

		// Update Case
		// is_partrner_user true
		else if (apiRequest.getFunction().equals(APIRequestFunctionCode.UPDATE_DATA.toString())) {
			if (sourceId < 1) {
				ar.setStatus(APIResponseCode.FAILURE);
				ar.setDescription("Invalid operation.");
				return ResponseEntity.ok(ar);
			}

			if (id < 1) {
				ar.setStatus(APIResponseCode.FAILURE);
				ar.setDescription("Invalid user.");
				return ResponseEntity.ok(ar);
			}

			String changingUsersUserType = userService.getUserTypeById(id);
			if (changingUsersUserType == null || changingUsersUserType.isBlank() || changingUsersUserType.isEmpty()
					|| changingUsersUserType.equals("MERCHANT")) {
				ar.setStatus(APIResponseCode.FAILURE);
				ar.setDescription("Merchant's details are not allowed to change.");
				return ResponseEntity.ok(ar);
			}
			String changingUsersEmail = userService.getUserEmailById(id);
			boolean isEmailChanged = false;
			/// ======email id are only allowed to changed by the admin and admin user
			if (!changingUsersEmail.equals(apiRequest.getData().getUserRequest().getEmailId())) {
				if (!loggedInUserType.equals(UserTypeCodes.ADMIN.toString())
						|| !loggedInUserType.equals(UserTypeCodes.ADMIN_USER.toString())) {
					ar.setStatus(APIResponseCode.FAILURE);
					ar.setDescription("You are not allowed to change the email address.");
					return ResponseEntity.ok(ar);
				}
				isEmailChanged = true;// we need to send change reset email

				// === for duplicate email id==/////
				Long userIdByEmail = (long) ((userService
						.getIdByEmailId(apiRequest.getData().getUserRequest().getEmailId()) != null)
								? userService.getIdByEmailId(apiRequest.getData().getUserRequest().getEmailId())
								: 0);
				if (userIdByEmail > 0) {
					ar.setStatus(APIResponseCode.FAILURE);
					ar.setDescription("Duplicate email id.");
					return ResponseEntity.ok(ar);
				}
			}

			UserRequest userRequest = apiRequest.getData().getUserRequest();
			if (userRequest != null) {
				try {
					UserResponse userResponse = userService.updateUser(userRequest, sourceId, sourceType, userType,
							actionUserName, isEmailChanged);

					if (userResponse.getId() != null) {
						if (userResponse.getId() > 0) {
							ar.setStatus(APIResponseCode.SUCCESS);
							ar.setDescription("Updated successfully.");
							ar.setData(userResponse);
						}
					}

					else {
						ar.setStatus(APIResponseCode.FAILURE);
						ar.setDescription("Update Failed.");
					}

					if (isEmailChanged) {
						// reset password email service
						// building message using message template
						passwordService.generateResetPasswordLink(userResponse.getId(), actionUserName,RequestedIp);				
					}

					return ResponseEntity.ok(ar);

				} catch (Exception e) {

					ar.setStatus(APIResponseCode.FAILURE);

					ar.setDescription(e.getMessage());
					return ResponseEntity.ok(ar);
				}

			}

		}

		ar.setData(apiRequest.getData().getUserRequest());
		ar.setStatus(APIResponseCode.FAILURE);
		ar.setDescription("Update Failed.");
		return ResponseEntity.ok(ar);
	}

	@GetMapping("profile")
	public ResponseEntity<APIResponse> getProfile(@RequestBody APIRequest apiRequest, HttpServletRequest httpRequest,
			HttpServletResponse httpResponse) {
		APIResponse ar = new APIResponse();

		if (apiRequest.getFunction().equals(APIRequestFunctionCode.SEARCH.toString())
				&& apiRequest.getScope().equals(APIRequestScopeCode.BYID.toString())) {
			try {
				String actionUserName = UserSecurityService.getUsername();
				Long id = userService.getIdByUsername(actionUserName);
				UserDTO detail = userService.getUserInfoById(id);
				if (detail != null) {
					ar.setData(detail);
					ar.setStatus(APIResponseCode.SUCCESS);
					ar.setScope(APIResponseScopeCode.SINGLE);
					ar.setDataType("Users");
					ar.setDescription("Data retrieved successfully.");
					return ResponseEntity.ok(ar);
				} else {
					ar.setStatus(APIResponseCode.FAILURE);
					ar.setDescription("No results found for the requested query.");
					return ResponseEntity.ok(ar);
				}

			} catch (Exception e) {

				ar.setStatus(APIResponseCode.FAILURE);
				ar.setDescription(e.getMessage());
				return ResponseEntity.ok(ar);
			}
		} else {
			ar.setStatus(APIResponseCode.FAILURE);
			ar.setDescription("Invalid operation");
		}

		return ResponseEntity.ok(ar);

	}

	@PostMapping("profile")
	public ResponseEntity<APIResponse> updateProfile(@RequestBody APIRequest apiRequest, HttpServletRequest httpRequest,
			HttpServletResponse httpResponse) {

		APIResponse ar = new APIResponse();
		if (apiRequest.getFunction().equals(APIRequestFunctionCode.UPDATE_DATA.toString())) {
			UserRequest userRequest = apiRequest.getData().getUserRequest();
			if (userRequest != null) {
				try {
					String actionUserName = UserSecurityService.getUsername();
					Long id = userService.getIdByUsername(actionUserName);
					String loggedInUserType = userService.getUserTypeByUsername(actionUserName);

					UserResponse userResponse = userService.updateProfile(userRequest, id, loggedInUserType,
							actionUserName);
					if (userResponse.getId() != null) {
						if (userResponse.getId() > 0) {
							ar.setScope(APIResponseScopeCode.SINGLE);
							ar.setDataType("Users");
							ar.setStatus(APIResponseCode.SUCCESS);
							ar.setDescription("Updated successfully.");
							ar.setData(userResponse);
						}
					}

					else {
						ar.setStatus(APIResponseCode.FAILURE);
						ar.setDescription("Update Failed.");
					}

				} catch (Exception e) {
					ar.setStatus(APIResponseCode.FAILURE);
					ar.setDescription(e.getMessage());
					return ResponseEntity.ok(ar);
				}
			}
		} else {
			ar.setStatus(APIResponseCode.FAILURE);
			ar.setDescription("Invalid operation");
		}

		return ResponseEntity.ok(ar);
	}

	/*
	 * @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
	 * 
	 * @GetMapping("") public ResponseEntity<APIResponse> getUsers(@RequestBody
	 * APIRequest apiRequest, HttpServletRequest httpRequest, HttpServletResponse
	 * httpResponse) {
	 * 
	 * APIResponse ar = new APIResponse();
	 * 
	 * if (apiRequest.getFunction().equals(APIRequestFunctionCode.SEARCH.toString())
	 * && apiRequest.getScope().equals(APIRequestScopeCode.ALL.toString())) {
	 * 
	 * List<UserDTO> users = userService.getAllUsers();
	 * 
	 * ar.setStatus(APIResponseCode.SUCCESS);
	 * 
	 * ar.setScope(APIResponseScopeCode.MULTIPLE);
	 * 
	 * ar.setDataType("Users");
	 * 
	 * ar.setData(users);
	 * 
	 * ar.setDescription("Users list");
	 * 
	 * return ResponseEntity.ok(ar);
	 * 
	 * } else { ar.setStatus(APIResponseCode.FAILURE);
	 * 
	 * return ResponseEntity.ok(ar); }
	 * 
	 * }
	 * 
	 * // @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
	 * 
	 * @PostMapping("") public User addUser(@RequestBody UserRequest user) { return
	 * userService.addUser(user); }
	 */

}
