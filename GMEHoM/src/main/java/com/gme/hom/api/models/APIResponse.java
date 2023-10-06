package com.gme.hom.api.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gme.hom.api.config.APIResponseCode;
import com.gme.hom.api.config.APIResponseScopeCode;
import com.gme.hom.api.config.APIStatusCode;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class APIResponse {

	@NotNull
	@Enumerated(EnumType.STRING)
	@JsonInclude(Include.NON_NULL)
	private APIResponseCode status;
	
	@JsonProperty("status_code")
	@JsonInclude(Include.NON_NULL)
	private APIStatusCode statusCode;


	@Enumerated(EnumType.STRING)
	@JsonInclude(Include.NON_NULL)
	private APIResponseScopeCode scope;

	@JsonInclude(Include.NON_NULL)
	private String description;

	@JsonInclude(Include.NON_NULL)
	private String message;

	@JsonInclude(Include.NON_NULL)
	@JsonProperty("data_type")
	private String dataType;

	@JsonInclude(Include.NON_NULL)
	private Object data;

}
