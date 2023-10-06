package com.gme.hom.users.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gme.hom.users.config.UserStatusCodes;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("user_status_code")
	UserStatusCodes userStatusCode;

	@Enumerated(EnumType.STRING)
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("id")
	Long id;
}
