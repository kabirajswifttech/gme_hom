package com.gme.hom.users.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gme.hom.users.config.UserSignupStatusCodes;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class UserSignupResponse {
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("user_signup_status")
	UserSignupStatusCodes userSignupStatus;
	
	@Enumerated(EnumType.STRING)
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("id")
	Long id;

}
