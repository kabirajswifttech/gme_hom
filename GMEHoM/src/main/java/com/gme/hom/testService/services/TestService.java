package com.gme.hom.testService.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.gme.hom.testService.models.Test;

// Service 
@Service
public class TestService {

	Test test;
	
	@Autowired
	Environment env;

	public TestService() {
		test = new Test();
	}

	public int increaseCounter() {
		return test.incCounter();
	}

	public String getInfo() {
		return "{\"counter\":\"" + increaseCounter() + "\",\"java_class_path\":\"" + test.getClasspath() + "\"";
	}

}
