package com.gme.hom.templates.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gme.hom.templates.models.MessageTemplate;

@Repository
	public interface TemplateRepository extends JpaRepository<MessageTemplate, Long> {
	
	@Query(value = "select id,template_type,purpose,template,description"
			+ ",valid_from_date,valid_to_date,remarks,status,is_active,subject  from message_templates", nativeQuery = true)
	List<TemplateDTO> getAllTemplates();


	
	@Query(value = "select id,template_type,purpose,template,description"
			+ ",valid_from_date,valid_to_date,remarks,status,is_active,subject  from message_templates  "
			+ "WHERE  template_type = ?1 " + "AND purpose = ?2 "
			+ "AND is_active = TRUE AND status='ACTIVE'", nativeQuery = true)
	TemplateDTO getTemplateByTypeAndPurpose(String templateType, String purpose);

}
