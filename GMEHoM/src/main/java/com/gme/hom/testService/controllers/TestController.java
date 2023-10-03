package com.gme.hom.testService.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gme.hom.api.config.APIResponseCode;
import com.gme.hom.api.models.APIResponse;
import com.gme.hom.testService.services.TestService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*
 * A test controller with test endpoints
 */
@RestController
@RequestMapping("/test")
public class TestController {
	
	// for dependency injection
	@Autowired
	private TestService testService;
	
	// Logger logger = LoggerFactory.getLogger(TestController.class);
	
	TestController(){
		testService = new TestService();
	}
	
	/*
	 * Default end-point
	 * When running as application: access using http://localhost:8080
	 */
	@GetMapping("")
	public ResponseEntity<APIResponse> index(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		// logger.debug("Test controller");
		APIResponse ar = new APIResponse();
		
		ar.setStatus(APIResponseCode.SUCCESS.toString());

		ar.setDescription("Test service");
		
		ar.setData(testService.getInfo());

		return ResponseEntity.ok(ar);		
		
	}
	
}
