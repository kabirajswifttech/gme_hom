package com.gme.hom.usersecurity.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAuthRequest {

	private String username;
	private String password;

}
