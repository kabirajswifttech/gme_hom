package com.gme.hom.auth.models;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.gme.hom.auth.config.PasswordStatusCodes;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class PasswordResponse {
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("password_status")
	PasswordStatusCodes passwordStatus;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("id")
	Long id;
	
//	@JsonInclude(Include.NON_NULL)
//	@JsonProperty("full_name")
//	String fullName;
//	
//	@JsonInclude(Include.NON_NULL)
//	@JsonProperty("email_id")
//	String emailId;
}
