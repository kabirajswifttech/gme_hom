package com.example.demo.api;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

public class APIRequestQueryParams {

	@JsonInclude(Include.NON_NULL)
	@JsonProperty("for_one")
	private String forOne;

	@JsonInclude(Include.NON_NULL)
	@JsonProperty("for_many")
	private List<String> forMany;

	@JsonInclude(Include.NON_NULL)
	@JsonProperty("subtype")
	private String subtype;

	@JsonProperty("by")
	private String by;

	@JsonProperty("value")
	private String Value;

	public APIRequestQueryParams() {
		super();
	}

	public APIRequestQueryParams(String forOne, List<String> forMany, String subtype, String by, String value) {
		super();
		this.forOne = forOne;
		this.forMany = forMany;
		this.subtype = subtype;
		this.by = by;
		Value = value;
	}

	public String getForOne() {
		return forOne;
	}

	public void setForOne(String forOne) {
		this.forOne = forOne;
	}

	public List<String> getForMany() {
		return forMany;
	}

	public void setForMany(List<String> forMany) {
		this.forMany = forMany;
	}

	public String getSubtype() {
		return subtype;
	}

	public void setSubtype(String subtype) {
		this.subtype = subtype;
	}

	public String getBy() {
		return by;
	}

	public void setBy(String by) {
		this.by = by;
	}

	public String getValue() {
		return Value;
	}

	public void setValue(String value) {
		Value = value;
	}

}
