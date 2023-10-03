package com.example.demo.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class APIRequest {

	@JsonInclude(Include.NON_NULL)
	@JsonProperty(value="function", required=true)
	private String function;

	private String scope;

	@Valid
	@JsonInclude(Include.NON_NULL)
	@JsonProperty(value="data", required=true)
	private APIData data;

	private String description;

}
