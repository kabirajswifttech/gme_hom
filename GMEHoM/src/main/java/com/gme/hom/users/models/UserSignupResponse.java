package com.gme.hom.users.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gme.hom.users.config.UserStatusCode;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class UserSignupResponse {
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("user_status")
	UserStatusCode userStatusCode;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("id")
	Long id;

}
