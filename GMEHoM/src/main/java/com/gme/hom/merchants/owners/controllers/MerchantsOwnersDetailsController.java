package com.gme.hom.merchants.owners.controllers;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
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
import com.gme.hom.merchants.owners.model.MerchantsOwnersDetails;
import com.gme.hom.merchants.owners.model.MerchantsOwnersDetailsRequest;
import com.gme.hom.merchants.owners.services.MerchantsOwnersDetailsDTO;
import com.gme.hom.merchants.owners.services.MerchantsOwnersDetailsService;

import jakarta.persistence.NoResultException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/merchants_owners_details")
@AllArgsConstructor
public class MerchantsOwnersDetailsController {
	
	@Autowired
	MerchantsOwnersDetailsService ownersService;
	
	private static final Logger logger = LoggerFactory.getLogger(MerchantsOwnersDetailsController.class);
	
	
	@PostMapping("")
	public ResponseEntity<APIResponse> addMerchantOwnersDetails(@Valid @RequestBody APIRequest apiReq ) {
		APIResponse ar = new APIResponse();
		if(apiReq.getFunction().equals(APIRequestFunctionCode.ADD_DATA.toString()) && apiReq.getScope().equals(APIRequestScopeCode.SINGLE.toString())) {
			try {
				List<MerchantsOwnersDetailsRequest> ownersDetailsReqs = apiReq.getData().getMerchantsOwnersDetailsRequests();
				ownersDetailsReqs.forEach(req->{
					MerchantsOwnersDetails ownersDetails = new MerchantsOwnersDetails(req);
					try {
						ownersService.save(ownersDetails);
					} catch (NoSuchAlgorithmException | IOException e) {
						logger.error(e.getMessage());
						ar.setStatus(APIResponseCode.FAILURE);
						ar.setDescription(ResponseMessageCodes.CREATION_FAILED.toString());
					} 
				});
				ar.setStatus(APIResponseCode.SUCCESS);
				ar.setDescription(ResponseMessageCodes.CREATED_SUCCESSFULLY.toString());
			} catch (Exception e) {
				logger.error(e.getMessage());
				ar.setStatus(APIResponseCode.FAILURE);
				ar.setDescription(ResponseMessageCodes.CREATION_FAILED.toString());
			}
		}else {
			ar.setStatus(APIResponseCode.FAILURE);
			ar.setDescription(ResponseMessageCodes.CREATION_FAILED.toString());
		}
		
		
		return ResponseEntity.ok(ar);
	}
	
	
	@GetMapping("")
	public ResponseEntity<APIResponse> getMerchantsOwnersDetails(@Valid @RequestBody APIRequest apiReq) {
		APIResponse ar = new APIResponse();
		if(apiReq.getFunction().equals(APIRequestFunctionCode.SEARCH.toString()) && apiReq.getScope().equals(APIRequestScopeCode.ALL.toString())) {
			if(apiReq.getData()==null) {
				try {
					List<MerchantsOwnersDetailsDTO> merchantsOwnersDetails =  ownersService.getAll();
					ar.setData(merchantsOwnersDetails);
					ar.setStatus(APIResponseCode.SUCCESS);
					ar.setDescription(ResponseMessageCodes.DATA_RETRIEVED_SUCCESSFULLY.toString());
				} catch (Exception e) {
					logger.error(e.getMessage());
					ar.setStatus(APIResponseCode.FAILURE);
					ar.setDescription(ResponseMessageCodes.NO_RESULTS_FOUND_FOR_YOUR_SEARCH_QUERY.toString());
				}
			}
			else if(apiReq.getData().getQuery().getBy().equals("MERCHANT_ID") && apiReq.getData().getQuery().getValue() != null) {
				try {
					List<MerchantsOwnersDetailsDTO> merchantsOwnersDetails =  ownersService.getByMerchantId(Long.parseLong(apiReq.getData().getQuery().getValue()));
					if(!merchantsOwnersDetails.isEmpty()) {
						ar.setData(merchantsOwnersDetails);
						ar.setStatus(APIResponseCode.SUCCESS);
						ar.setDescription(ResponseMessageCodes.DATA_RETRIEVED_SUCCESSFULLY.toString());
					}else {
						throw new NoResultException();
					}
				} catch (Exception e) {
					logger.error(e.getMessage());
					ar.setStatus(APIResponseCode.FAILURE);
					ar.setDescription(ResponseMessageCodes.NO_RESULTS_FOUND_FOR_YOUR_SEARCH_QUERY.toString());
				}
			}
			else {
				logger.error("no suitable query params found...");
				ar.setStatus(APIResponseCode.FAILURE);
				ar.setDescription(ResponseMessageCodes.NO_RESULTS_FOUND_FOR_YOUR_SEARCH_QUERY.toString());
			}
		}else if(apiReq.getFunction().equals(APIRequestFunctionCode.SEARCH.toString()) && apiReq.getScope().equals(APIRequestScopeCode.SINGLE.toString())) {
			if(apiReq.getData().getQuery().getBy().equals("MERCHANT_OWNERS_DETAILS_ID") && apiReq.getData().getQuery().getValue() != null) {
				try {
					Optional<MerchantsOwnersDetailsDTO> merchantsOwnersDetails =  ownersService.getById(Long.parseLong(apiReq.getData().getQuery().getValue()));
					if(!merchantsOwnersDetails.isEmpty()) {
						logger.error("here!");
						ar.setData(merchantsOwnersDetails.get());
						ar.setStatus(APIResponseCode.SUCCESS);
						ar.setDescription(ResponseMessageCodes.DATA_RETRIEVED_SUCCESSFULLY.toString());
					}else {
						throw new NoResultException();
					}
				} catch (Exception e) {
					logger.error(e.getMessage());
					ar.setStatus(APIResponseCode.FAILURE);
					ar.setDescription(ResponseMessageCodes.NO_RESULTS_FOUND_FOR_YOUR_SEARCH_QUERY.toString());
				}
			}
			
		}else {
			ar.setStatus(APIResponseCode.FAILURE);
			ar.setDescription(ResponseMessageCodes.NO_RESULTS_FOUND_FOR_YOUR_SEARCH_QUERY.toString());
		}
		return ResponseEntity.ok(ar);
	}
	
	
	@PutMapping("")
	public ResponseEntity<APIResponse> updateOwnersDetails(@Valid @RequestBody APIRequest apiReq) {
		APIResponse ar = new APIResponse();
		if(apiReq.getFunction().equals(APIRequestFunctionCode.UPDATE_DATA.toString()) && apiReq.getScope().equals(APIRequestScopeCode.SINGLE.toString())) {
			if(apiReq.getData().getQuery().getBy().equals("MERCHANT_OWNERS_DETAILS_ID") && apiReq.getData().getQuery().getValue() != null) {
				try {
					MerchantsOwnersDetails owner = new MerchantsOwnersDetails(apiReq.getData().getMerchantsOwnersDetailsRequests().get(0));
					owner.setId(Long.parseLong(apiReq.getData().getQuery().getValue()));
					new MerchantsOwnersDetails();
					owner =  ownersService.update(owner);					
						ar.setData(owner);
						ar.setStatus(APIResponseCode.SUCCESS);
						ar.setDescription(ResponseMessageCodes.DATA_RETRIEVED_SUCCESSFULLY.toString());
					
				} catch (Exception e) {
					ar.setStatus(APIResponseCode.FAILURE);
					ar.setDescription(ResponseMessageCodes.NO_RESULTS_FOUND_FOR_YOUR_SEARCH_QUERY.toString());
				}
			}
		}
		return ResponseEntity.ok(ar);
		
	}
	
	
	
	
	
	
	
	
}
