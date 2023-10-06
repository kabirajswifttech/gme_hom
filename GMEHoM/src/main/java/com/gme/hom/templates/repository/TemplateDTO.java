package com.gme.hom.templates.repository;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public interface TemplateDTO {
	@JsonInclude(Include.NON_NULL)
	String getMessage_templates_id();
	
	@JsonInclude(Include.NON_NULL)
	String getTemplate_type();
	
	@JsonInclude(Include.NON_NULL)
	String getPurpose();
	
	@JsonInclude(Include.NON_NULL)
	String getTemplate();
	
	@JsonInclude(Include.NON_NULL)
	String getDescription();
	
	@JsonInclude(Include.NON_NULL)
	java.util.Date getValid_from_date();
	
	@JsonInclude(Include.NON_NULL)
	java.util.Date getValid_to_date();
	
	@JsonInclude(Include.NON_NULL)
	String getRemarks();
	
	@JsonInclude(Include.NON_NULL)
	String getStatus();
	
	@JsonInclude(Include.NON_NULL)
	Boolean getIs_active();
	
	@JsonInclude(Include.NON_NULL)
	String getSubject();	
}
