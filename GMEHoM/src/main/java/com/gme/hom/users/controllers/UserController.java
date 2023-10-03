package com.gme.hom.users.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.gme.hom.users.models.User;
import com.gme.hom.users.models.UserDTO;
import com.gme.hom.users.models.UserRequest;
import com.gme.hom.users.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	private final UserService userService;

	UserController(UserService userService) {
		this.userService = userService;
	}

	@PreAuthorize(value = "hasRole('ROLE_ADMIN')")
	@GetMapping("")
	public ResponseEntity<APIResponse> getUsers(@RequestBody APIRequest apiRequest, HttpServletRequest httpRequest, HttpServletResponse httpResponse) {

		APIResponse ar = new APIResponse();

		if (apiRequest.getFunction().equals(APIRequestFunctionCode.SEARCH.toString())
				&& apiRequest.getScope().equals(APIRequestScopeCode.ALL.toString())) {

			List<UserDTO> users = userService.getAllUsers();

			ar.setStatus(APIResponseCode.SUCCESS.toString());

			ar.setScope(APIResponseScopeCode.MULTIPLE.toString());

			ar.setDataType("Users");

			ar.setData(users);

			ar.setDescription("Users list");

			return ResponseEntity.ok(ar);

		} else {
			ar.setStatus(APIResponseCode.FAILURE.toString());

			return ResponseEntity.ok(ar);
		}

	}

	// @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
	@PostMapping("")
	public User addUser(@RequestBody UserRequest user) {
		return userService.addUser(user);
	}

}
