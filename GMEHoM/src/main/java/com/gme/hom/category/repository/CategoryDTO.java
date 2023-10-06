package com.gme.hom.category.repository;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public interface CategoryDTO {
	@JsonInclude(Include.NON_NULL)
	String getCategory_root_id();
	
	@JsonInclude(Include.NON_NULL)
	String getCategory_type();
	
	@JsonInclude(Include.NON_NULL)
	String getSub_type();
	
	@JsonInclude(Include.NON_NULL)
	String getCategory_code();
	
	@JsonInclude(Include.NON_NULL)
	String getDescription();
	
	@JsonInclude(Include.NON_NULL)
	String getParent_type();
	
	@JsonInclude(Include.NON_NULL)
	String getParent_code();
	
	@JsonInclude(Include.NON_NULL)
	String getRef_col_1();
	
	@JsonInclude(Include.NON_NULL)
	String getRef_col_2();
	
	@JsonInclude(Include.NON_NULL)
	String getRef_col_3();
	
	@JsonInclude(Include.NON_NULL)
	String getRef_col_4();
	
	@JsonInclude(Include.NON_NULL)
	String getRef_col_5();
}
