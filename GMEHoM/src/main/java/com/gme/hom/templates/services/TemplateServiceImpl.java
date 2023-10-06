package com.gme.hom.templates.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gme.hom.messaging.config.MessageTypes;
import com.gme.hom.templates.config.MessageTemplateTypes;
import com.gme.hom.templates.repository.TemplateDTO;
import com.gme.hom.templates.repository.TemplateRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TemplateServiceImpl implements TemplateService {
	
	@Autowired
	TemplateRepository templateRepository;	
	
	public List<TemplateDTO> getAllTemplates() {
		return templateRepository.getAllTemplates();
	}

	@Override
	public TemplateDTO getTemplateByTypeAndPurpose(MessageTypes templateType, MessageTemplateTypes purpose) {
		return templateRepository.getTemplateByTypeAndPurpose(templateType.toString(), purpose.toString());
	}
	
	

}
