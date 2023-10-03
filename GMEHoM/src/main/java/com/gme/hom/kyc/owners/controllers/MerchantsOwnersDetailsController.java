package com.gme.hom.kyc.owners.controllers;

import java.util.List;
import java.util.Optional;

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
import com.gme.hom.kyc.owners.model.MerchantsOwnersDetails;
import com.gme.hom.kyc.owners.model.MerchantsOwnersDetailsRequest;
import com.gme.hom.kyc.owners.services.MerchantsOwnersDetailsServiceImpl;

import jakarta.persistence.NoResultException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("merchants_owners_details")
public class MerchantsOwnersDetailsController {
	
	MerchantsOwnersDetailsServiceImpl ownersService;
	
	@PostMapping("")
	public ResponseEntity<APIResponse> addMerchantOwnersDetails(@Valid @RequestBody APIRequest apiReq ) {
		APIResponse ar = new APIResponse();
		if(apiReq.getFunction().equals(APIRequestFunctionCode.ADD_DATA.toString()) && apiReq.getScope().equals(APIRequestScopeCode.SINGLE.toString())) {
			try {
				MerchantsOwnersDetailsRequest ownersDetailsReq = apiReq.getData().getMerchantsOwnersDetailsRequest();
				MerchantsOwnersDetails ownersDetails = new MerchantsOwnersDetails(ownersDetailsReq);
				MerchantsOwnersDetails merchantsDirectorsDetails =  ownersService.save(ownersDetails);
				ar.setStatus(APIResponseCode.SUCCESS.toString());
				ar.setDescription(ResponseMessageCodes.CREATED_SUCCESSFULLY.toString());
				ar.setData(merchantsDirectorsDetails);
			} catch (Exception e) {
				ar.setStatus(APIResponseCode.FAILURE.toString());
				ar.setDescription(ResponseMessageCodes.CREATION_FAILED.toString());
			}
		}else {
			ar.setStatus(APIResponseCode.FAILURE.toString());
			ar.setDescription(ResponseMessageCodes.CREATION_FAILED.toString());
		}
		
		
		return ResponseEntity.ok(ar);
	}
	
	
	@GetMapping("")
	public ResponseEntity<APIResponse> getMerchantsOwnersDetails(@Valid @RequestBody APIRequest apiReq) {
		APIResponse ar = new APIResponse();
		if(apiReq.getFunction().equals(APIRequestFunctionCode.GET_DATA.toString()) && apiReq.getScope().equals(APIRequestScopeCode.ALL.toString())) {
			try {
				List<MerchantsOwnersDetails> merchantsOwnersDetails =  ownersService.getAll();
				ar.setData(merchantsOwnersDetails);
				ar.setStatus(APIResponseCode.SUCCESS.toString());
				ar.setDescription(ResponseMessageCodes.DATA_RETRIEVED_SUCCESSFULLY.toString());
			} catch (Exception e) {
				ar.setStatus(APIResponseCode.FAILURE.toString());
				ar.setDescription(ResponseMessageCodes.NO_RESULTS_FOUND_FOR_YOUR_SEARCH_QUERY.toString());
			}
		}else if(apiReq.getFunction().equals(APIRequestFunctionCode.GET_DATA.toString()) && apiReq.getScope().equals(APIRequestScopeCode.SINGLE.toString())) {
			if(apiReq.getData().getQuery().getBy().equals("MERCHANT_DIRECTORS_DETAILS_ID") && apiReq.getData().getQuery().getValue() != null) {
				try {
					Optional<MerchantsOwnersDetails> merchantsOwnersDetails =  ownersService.getById(Long.parseLong(apiReq.getData().getQuery().getValue()));
					if(!merchantsOwnersDetails.isEmpty()) {
						ar.setData(merchantsOwnersDetails.get());
						ar.setStatus(APIResponseCode.SUCCESS.toString());
						ar.setDescription(ResponseMessageCodes.DATA_RETRIEVED_SUCCESSFULLY.toString());
					}else {
						throw new NoResultException();
					}
				} catch (Exception e) {
					ar.setStatus(APIResponseCode.FAILURE.toString());
					ar.setDescription(ResponseMessageCodes.NO_RESULTS_FOUND_FOR_YOUR_SEARCH_QUERY.toString());
				}
			}
			
		}else if(apiReq.getFunction().equals(APIRequestFunctionCode.GET_DATA.toString()) && apiReq.getScope().equals(APIRequestScopeCode.SINGLE.toString())) {
			if(apiReq.getData().getQuery().getBy().equals("MERCHANT_ID") && apiReq.getData().getQuery().getValue() != null) {
				try {
					List<MerchantsOwnersDetails> merchantsOwnersDetails =  ownersService.getByMerchantId(Long.parseLong(apiReq.getData().getQuery().getValue()));
					if(!merchantsOwnersDetails.isEmpty()) {
						ar.setData(merchantsOwnersDetails);
						ar.setStatus(APIResponseCode.SUCCESS.toString());
						ar.setDescription(ResponseMessageCodes.DATA_RETRIEVED_SUCCESSFULLY.toString());
					}else {
						throw new NoResultException();
					}
				} catch (Exception e) {
					ar.setStatus(APIResponseCode.FAILURE.toString());
					ar.setDescription(ResponseMessageCodes.NO_RESULTS_FOUND_FOR_YOUR_SEARCH_QUERY.toString());
				}
			}
			
		}else {
			ar.setStatus(APIResponseCode.FAILURE.toString());
			ar.setDescription(ResponseMessageCodes.NO_RESULTS_FOUND_FOR_YOUR_SEARCH_QUERY.toString());
		}
		return ResponseEntity.ok(ar);
	}
	
	
	@PatchMapping("")
	public ResponseEntity<APIResponse> updateOwnersDetails(@Valid @RequestBody APIRequest apiReq) {
		APIResponse ar = new APIResponse();
		if(apiReq.getFunction().equals(APIRequestFunctionCode.UPDATE_DATA.toString()) && apiReq.getScope().equals(APIRequestScopeCode.SINGLE.toString())) {
			if(apiReq.getData().getQuery().getBy().equals("MERCHANT_OWNERS_DETAILS_ID") && apiReq.getData().getQuery().getValue() != null) {
				try {
					MerchantsOwnersDetails owner = new MerchantsOwnersDetails(apiReq.getData().getMerchantsOwnersDetailsRequest());
					owner.setId(Long.parseLong(apiReq.getData().getQuery().getValue()));
					owner =  ownersService.update(owner);					
						ar.setData(owner);
						ar.setStatus(APIResponseCode.SUCCESS.toString());
						ar.setDescription(ResponseMessageCodes.DATA_RETRIEVED_SUCCESSFULLY.toString());
					
				} catch (Exception e) {
					ar.setStatus(APIResponseCode.FAILURE.toString());
					ar.setDescription(ResponseMessageCodes.NO_RESULTS_FOUND_FOR_YOUR_SEARCH_QUERY.toString());
				}
			}
		}
		return ResponseEntity.ok(ar);
		
	}
	
	
	
	
	
	
	
	
}