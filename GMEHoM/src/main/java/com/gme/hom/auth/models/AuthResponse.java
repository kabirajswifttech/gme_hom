package com.gme.hom.auth.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gme.hom.auth.config.AuthStatusCode;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponse {

	@JsonInclude(Include.NON_NULL)
	@JsonProperty("auth_status")
	AuthStatusCode authStatus;

	@JsonInclude(Include.NON_NULL)
	@JsonProperty("jwt")
	String jwt;

}
