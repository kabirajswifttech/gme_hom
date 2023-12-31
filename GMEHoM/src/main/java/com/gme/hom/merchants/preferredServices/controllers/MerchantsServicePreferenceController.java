package com.gme.hom.merchants.preferredServices.controllers;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gme.hom.api.config.APIRequestFunctionCode;
import com.gme.hom.api.config.APIRequestScopeCode;
import com.gme.hom.api.config.APIResponseCode;
import com.gme.hom.api.models.APIRequest;
import com.gme.hom.api.models.APIResponse;
import com.gme.hom.merchants.config.ResponseMessageCodes;
import com.gme.hom.merchants.preferredServices.model.MerchantsServicePreference;
import com.gme.hom.merchants.preferredServices.model.MerchantsServicePreferenceRequest;
import com.gme.hom.merchants.preferredServices.services.MerchantsServicePreferenceDTO;
import com.gme.hom.merchants.preferredServices.services.MerchantsServicePreferenceService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/merchants_service_preference")
public class MerchantsServicePreferenceController {
	
	@Autowired
	MerchantsServicePreferenceService servicePrefService;
	
	private static final Logger logger = LoggerFactory.getLogger(MerchantsServicePreferenceController.class);
	
	@PostMapping("")
	public ResponseEntity<APIResponse> addMerchantPreferedService(@Valid @RequestBody APIRequest apiReq) {
		APIResponse ar = new APIResponse();
		if(apiReq.getFunction().equals(APIRequestFunctionCode.ADD_DATA.toString()) && apiReq.getScope().equals(APIRequestScopeCode.SINGLE.toString())) {
			MerchantsServicePreferenceRequest servicePrefereReq = apiReq.getData().getMerchantServicePreferenceRequests().get(0);
			try {
				MerchantsServicePreference merchantsServicePreference = servicePrefService.save(new MerchantsServicePreference(servicePrefereReq));
				//ar.setData(merchantsServicePreference.getId());
				ar.setStatus(APIResponseCode.SUCCESS);
				ar.setDescription(ResponseMessageCodes.CREATED_SUCCESSFULLY.toString());
				
			}catch (Exception e) {
				logger.error(e.getMessage());
				ar.setStatus(APIResponseCode.FAILURE);
				ar.setDescription(ResponseMessageCodes.CREATION_FAILED.toString());
			}
		}
		else {
			logger.error("function of scope didnot match!");
			ar.setStatus(APIResponseCode.FAILURE);
			ar.setDescription(ResponseMessageCodes.CREATION_FAILED.toString());
		}
		return ResponseEntity.ok(ar);
	}
	
	@GetMapping("")
	public ResponseEntity<APIResponse> getMerchantServicePreference(@Valid @RequestBody APIRequest apiReq) {
		APIResponse ar = new APIResponse();
		if(apiReq.getFunction().equals(APIRequestFunctionCode.SEARCH.toString()) && apiReq.getScope().equals(APIRequestScopeCode.ALL.toString())) {
			if(apiReq.getData()==null) {
				try {
					List<MerchantsServicePreferenceDTO> merchantsServicePreferences = servicePrefService.getAll();
					ar.setData(merchantsServicePreferences);
					ar.setStatus(APIResponseCode.SUCCESS);
					ar.setDescription(ResponseMessageCodes.DATA_RETRIEVED_SUCCESSFULLY.toString());
				}catch (Exception e) {
					logger.error(e.getMessage());
					ar.setStatus(APIResponseCode.FAILURE);
					ar.setDescription(ResponseMessageCodes.NO_RESULTS_FOUND_FOR_YOUR_SEARCH_QUERY.toString());
				}
			}else if(apiReq.getData().getQuery().getBy().equals("MERCHANT_ID") && apiReq.getData().getQuery().getValue() != null) {
				try {
					Long merchantId = Long.parseLong(apiReq.getData().getQuery().getValue());
					List<MerchantsServicePreferenceDTO> merchantsServicePreference = servicePrefService.getByIdMerchantId(merchantId);
					ar.setData(merchantsServicePreference);
					ar.setStatus(APIResponseCode.SUCCESS);
					ar.setDescription(ResponseMessageCodes.DATA_RETRIEVED_SUCCESSFULLY.toString());
				}catch (Exception e) {
					logger.error(e.getMessage());
					ar.setStatus(APIResponseCode.FAILURE);
					ar.setDescription(ResponseMessageCodes.NO_RESULTS_FOUND_FOR_YOUR_SEARCH_QUERY.toString());
				}
			}else {
				ar.setStatus(APIResponseCode.FAILURE);
				ar.setDescription(ResponseMessageCodes.NO_RESULTS_FOUND_FOR_YOUR_SEARCH_QUERY.toString());
			}
		}
		else if(apiReq.getFunction().equalsIgnoreCase(APIRequestFunctionCode.SEARCH.toString()) && apiReq.getScope().equals(APIRequestScopeCode.SINGLE.toString())) {
			try {
				if(apiReq.getData().getQuery().getBy().equals("MERCHANTS_SERVICE_PREFERENCE_ID") && apiReq.getData().getQuery().getValue() != null) {
					Long merchantsServicePreferenceId = Long.parseLong(apiReq.getData().getQuery().getValue());
					Optional<MerchantsServicePreferenceDTO> merchantsServicePreference = servicePrefService.getById(merchantsServicePreferenceId);
					if(!merchantsServicePreference.isEmpty()) {
						ar.setData(merchantsServicePreference);
						ar.setStatus(APIResponseCode.SUCCESS);
						ar.setDescription(ResponseMessageCodes.DATA_RETRIEVED_SUCCESSFULLY.toString());
					}
					else {
						throw new EntityNotFoundException();
					}
				}else {
					ar.setStatus(APIResponseCode.FAILURE);
					ar.setDescription(ResponseMessageCodes.NO_RESULTS_FOUND_FOR_YOUR_SEARCH_QUERY.toString());
				}
			}catch (Exception e) {
				logger.error(e.getMessage());
				ar.setStatus(APIResponseCode.FAILURE);
				ar.setDescription(ResponseMessageCodes.NO_RESULTS_FOUND_FOR_YOUR_SEARCH_QUERY.toString());
			}
		}
		
		return ResponseEntity.ok(ar);
		
	}
	
	@PutMapping("")
	public ResponseEntity<APIResponse> updateMerchantServicePreferenceDetails(@Valid @RequestBody APIRequest apiReq, HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		APIResponse ar = new APIResponse();
		if(apiReq.getFunction().equals(APIRequestFunctionCode.UPDATE_DATA.toString()) && apiReq.getScope().equals(APIRequestScopeCode.SINGLE.toString())) {
			if (apiReq.getData().getQuery().getBy().equals("MERCHANT_SERVICE_PREFERENCE_ID")
					&& apiReq.getData().getQuery().getValue() != null) {
			try {
				MerchantsServicePreferenceRequest merchantServicePreferenceReq = apiReq.getData().getMerchantServicePreferenceRequests().get(0);
				MerchantsServicePreference merchantsServicePreferenceDetails =  new MerchantsServicePreference(merchantServicePreferenceReq);
				merchantsServicePreferenceDetails.setId(Long.parseLong(apiReq.getData().getQuery().getValue()));
				servicePrefService.update(merchantsServicePreferenceDetails);
				ar.setData(merchantsServicePreferenceDetails);
				ar.setStatus(APIResponseCode.SUCCESS);
				ar.setDescription(ResponseMessageCodes.DATA_RETRIEVED_SUCCESSFULLY.toString());	
			}catch (Exception e) {
				logger.error(e.getMessage());
				ar.setStatus(APIResponseCode.FAILURE);
				ar.setDescription(ResponseMessageCodes.UPDATE_FAILED.toString());
			}
		}else {
			ar.setStatus(APIResponseCode.FAILURE);
			ar.setDescription(ResponseMessageCodes.INVALID_FUNCTION_CODE_OR_SCOPE.toString());
		}
	} else {
		ar.setStatus(APIResponseCode.FAILURE);
		ar.setDescription(ResponseMessageCodes.INVALID_FUNCTION_CODE_OR_SCOPE.toString());
	}
		return ResponseEntity.ok(ar);
	}
	
}

































