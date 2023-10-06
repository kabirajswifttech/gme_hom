package com.gme.hom.templates.services;

import java.util.List;

import com.gme.hom.messaging.config.MessageTypes;
import com.gme.hom.templates.config.MessageTemplateTypes;
import com.gme.hom.templates.repository.TemplateDTO;

public interface TemplateService {

	List<TemplateDTO> getAllTemplates();

	TemplateDTO getTemplateByTypeAndPurpose(MessageTypes templateType, MessageTemplateTypes purpose);	

}
