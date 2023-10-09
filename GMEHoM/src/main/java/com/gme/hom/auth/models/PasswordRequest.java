package com.gme.hom.auth.models;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PasswordRequest {
	@JsonProperty("old_password")
	private String oldPassword;

	@JsonProperty("new_password")
	private String newPassword;

	@JsonProperty("confirm_password")
	private String confirmPassword;

	@JsonProperty("token")
	private String token;

	@JsonProperty("id")
	private Long id;
	@JsonProperty("email_id")
	private String emailId;
}
