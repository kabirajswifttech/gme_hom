package com.gme.hom.templates.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PasswordChanged implements MessageTemplateBody {
	private String template;
	private String full_name;

	@Override
	public String buildMessage() {
		template = template.replace("{{full_name}}", full_name);
		template = template.replace("\r\n", " ");
		template = template.replace("\\", "");
		return template;
	}
}
