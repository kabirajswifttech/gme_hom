package com.gme.hom.kyc.bulk;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gme.hom.api.config.APIRequestFunctionCode;
import com.gme.hom.api.config.APIResponseCode;
import com.gme.hom.api.models.APIRequest;
import com.gme.hom.api.models.APIResponse;
import com.gme.hom.common.services.ObjectToJson;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/merchant/id")
public class KYCOnboardingController {
	private final MerchantOnboardingService onboardingService;
	
	@PostMapping("")
	public String onBoardMerchant(@RequestBody APIRequest apiReq, @PathVariable("id") Long id) {
		APIResponse ar = new APIResponse();
		if(apiReq.getFunction().equals(APIRequestFunctionCode.ADD_DATA.toString())) {
			try {
				String message = onboardingService.onBoard(apiReq.getData(), id);
				ar.setStatus(APIResponseCode.SUCCESS.toString());
				ar.setDescription(message);
			}catch(Exception e) {
				ar.setStatus(APIResponseCode.FAILURE.toString());
				ar.setDescription(e.getMessage());
			}
		}else {
			ar.setStatus(APIResponseCode.FAILURE.toString());
			ar.setDescription("Invalid function name!");
		}
		return ObjectToJson.toJson(ar);
	}

	
}
