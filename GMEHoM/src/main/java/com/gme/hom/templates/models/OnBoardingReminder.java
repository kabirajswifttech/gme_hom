package com.gme.hom.templates.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OnBoardingReminder implements MessageTemplateBody {
	private String template;
	private String full_name;
	private String link;

	@Override
	public String buildMessage() {
		template = template.replace("{{full_name}}", full_name);
		template = template.replace("{{link}}", link);
		template = template.replace("\r\n", " ");
		template = template.replace("\\", "");
		return template;
	}
}
