package com.gme.hom.api.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class APIResponse {

	@NotNull
	private String status;

	@JsonInclude(Include.NON_NULL)
	@JsonProperty("status_code")
	private String statusCode;

	@JsonInclude(Include.NON_NULL)
	private String scope;

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
