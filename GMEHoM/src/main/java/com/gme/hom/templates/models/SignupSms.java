package com.gme.hom.templates.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class SignupSms implements MessageTemplateBody {
	private String template;
	private String otp;

	@Override
	public String buildMessage() {
		template = template.replace("{{otp}}", otp);
		template = template.replace("\r\n", " ");
		template = template.replace("\\", "");
		return template;
	}

}
