package com.gme.hom.kyc.preferredServices.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gme.hom.api.config.APIRequestFunctionCode;
import com.gme.hom.api.config.APIRequestScopeCode;
import com.gme.hom.api.config.APIResponseCode;
import com.gme.hom.api.models.APIRequest;
import com.gme.hom.api.models.APIResponse;
import com.gme.hom.kyc.codes.ResponseMessageCodes;
import com.gme.hom.kyc.preferredServices.model.MerchantsServicePreference;
import com.gme.hom.kyc.preferredServices.model.MerchantsServicePreferenceRequest;
import com.gme.hom.kyc.preferredServices.services.MerchantsServicePreferenceService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/merchants_service_preference")
public class MerchantsServicePreferenceController {
	
	MerchantsServicePreferenceService servicePrefService;
	
	private static final Logger logger = LoggerFactory.getLogger(MerchantsServicePreferenceController.class);
	
	@PostMapping("")
	public ResponseEntity<APIResponse> addMerchantPreferedService(@Valid @RequestBody APIRequest apiReq) {
		APIResponse ar = new APIResponse();
		if(apiReq.getFunction().equals(APIRequestFunctionCode.ADD_DATA.toString()) && apiReq.getScope().equals(APIRequestScopeCode.SINGLE.toString())) {
			MerchantsServicePreferenceRequest servicePrefereReq = apiReq.getData().getMerchantServicePreferenceRequest();
			try {
				MerchantsServicePreference merchantsServicePreference = servicePrefService.save(new MerchantsServicePreference(servicePrefereReq));
				ar.setData(merchantsServicePreference.getId());
				ar.setStatus(APIResponseCode.SUCCESS.toString());
				ar.setDescription(ResponseMessageCodes.CREATED_SUCCESSFULLY.toString());
				
			}catch (Exception e) {
				ar.setStatus(APIResponseCode.FAILURE.toString());
				ar.setDescription(ResponseMessageCodes.CREATION_FAILED.toString());
			}
		}
		return ResponseEntity.ok(ar);
	}
	
	@GetMapping("")
	public ResponseEntity<APIResponse> getMerchantServicePreference(@Valid @RequestBody APIRequest apiReq) {
		APIResponse ar = new APIResponse();
		if(apiReq.getFunction().equals(APIRequestFunctionCode.GET_DATA.toString()) && apiReq.getScope().equals(APIRequestScopeCode.ALL.toString())) {
			if(apiReq.getData().getQuery().getBy()==null) {
				try {
					List<MerchantsServicePreference> merchantsServicePreferences = servicePrefService.getAll();
					ar.setData(merchantsServicePreferences);
					ar.setStatus(APIResponseCode.SUCCESS.toString());
					ar.setDescription(ResponseMessageCodes.DATA_RETRIEVED_SUCCESSFULLY.toString());
				}catch (Exception e) {
					logger.error(e.getMessage());
					ar.setStatus(APIResponseCode.FAILURE.toString());
					ar.setDescription(ResponseMessageCodes.NO_RESULTS_FOUND_FOR_YOUR_SEARCH_QUERY.toString());
				}
			}else if(apiReq.getData().getQuery().getBy().equals("MERCHANT_ID") && apiReq.getData().getQuery().getValue() != null) {
				try {
					Long merchantId = Long.parseLong(apiReq.getData().getQuery().getValue());
					List<MerchantsServicePreference> merchantsServicePreference = servicePrefService.getByIdMerchantId(merchantId);
					ar.setData(merchantsServicePreference);
					ar.setStatus(APIResponseCode.SUCCESS.toString());
					ar.setDescription(ResponseMessageCodes.DATA_RETRIEVED_SUCCESSFULLY.toString());
				}catch (Exception e) {
					logger.error(e.getMessage());
					ar.setStatus(APIResponseCode.FAILURE.toString());
					ar.setDescription(ResponseMessageCodes.NO_RESULTS_FOUND_FOR_YOUR_SEARCH_QUERY.toString());
				}
			}else {
				ar.setStatus(APIResponseCode.FAILURE.toString());
				ar.setDescription(ResponseMessageCodes.NO_RESULTS_FOUND_FOR_YOUR_SEARCH_QUERY.toString());
			}
		}
		else if(apiReq.getFunction().equalsIgnoreCase(APIRequestFunctionCode.GET_DATA.toString()) && apiReq.getScope().equals(APIRequestScopeCode.SINGLE.toString())) {
			try {
				if(apiReq.getData().getQuery().getBy().equals("MERCHANTS_SERVICE_PREFERENCE_ID") && apiReq.getData().getQuery().getValue() != null) {
					Long merchantsServicePreferenceId = Long.parseLong(apiReq.getData().getQuery().getValue());
					MerchantsServicePreference merchantsServicePreference = servicePrefService.getById(merchantsServicePreferenceId);
					ar.setData(merchantsServicePreference);
					ar.setStatus(APIResponseCode.SUCCESS.toString());
					ar.setDescription(ResponseMessageCodes.DATA_RETRIEVED_SUCCESSFULLY.toString());
				}else {
					ar.setStatus(APIResponseCode.FAILURE.toString());
					ar.setDescription(ResponseMessageCodes.NO_RESULTS_FOUND_FOR_YOUR_SEARCH_QUERY.toString());
				}
			}catch (Exception e) {
				ar.setStatus(APIResponseCode.FAILURE.toString());
				ar.setDescription(ResponseMessageCodes.NO_RESULTS_FOUND_FOR_YOUR_SEARCH_QUERY.toString());
			}
		}
		
		return ResponseEntity.ok(ar);
		
	}
	
	@PatchMapping("")
	public ResponseEntity<APIResponse> updateMerchantServicePreferenceDetails(@Valid @RequestBody APIRequest apiReq, HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		APIResponse ar = new APIResponse();
		if(apiReq.getFunction().equals(APIRequestFunctionCode.UPDATE_DATA.toString()) && apiReq.getScope().equals(APIRequestScopeCode.SINGLE.toString())) {
			if (apiReq.getData().getQuery().getBy().equals("MERCHANT_SERVICE_PREFERENCE_ID")
					&& apiReq.getData().getQuery().getValue() != null) {
			try {
				MerchantsServicePreferenceRequest merchantServicePreferenceReq = apiReq.getData().getMerchantServicePreferenceRequest();
				MerchantsServicePreference merchantsServicePreferenceDetails =  new MerchantsServicePreference(merchantServicePreferenceReq);
				merchantsServicePreferenceDetails.setId(Long.parseLong(apiReq.getData().getQuery().getValue()));
				servicePrefService.update(merchantsServicePreferenceDetails);
				ar.setData(merchantsServicePreferenceDetails);
				ar.setStatus(APIResponseCode.SUCCESS.toString());
				ar.setDescription(ResponseMessageCodes.DATA_RETRIEVED_SUCCESSFULLY.toString());	
			}catch (Exception e) {
				ar.setStatus(APIResponseCode.FAILURE.toString());
				ar.setDescription(ResponseMessageCodes.UPDATE_FAILED.toString());
			}
		}else {
			ar.setStatus(APIResponseCode.FAILURE.toString());
			ar.setDescription(ResponseMessageCodes.INVALID_FUNCTION_CODE_OR_SCOPE.toString());
		}
	} else {
		ar.setStatus(APIResponseCode.FAILURE.toString());
		ar.setDescription(ResponseMessageCodes.INVALID_FUNCTION_CODE_OR_SCOPE.toString());
	}
		return ResponseEntity.ok(ar);
	}
	
}

































