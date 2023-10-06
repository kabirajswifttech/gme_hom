package com.gme.hom.merchants.representatives.controllers;

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
import com.gme.hom.merchants.representatives.model.MerchantsRepresentativeDetails;
import com.gme.hom.merchants.representatives.model.MerchantsRepresentativeDetailsRequest;
import com.gme.hom.merchants.representatives.services.MerchantsRepresentativeDetailsDTO;
import com.gme.hom.merchants.representatives.services.MerchantsRepresentativeDetailsService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/merchants_representative_details")
public class MerchantRepresentativeDetailsController {

	@Autowired
	private MerchantsRepresentativeDetailsService representativesService;

	private static final Logger logger = LoggerFactory.getLogger(MerchantRepresentativeDetailsController.class);
	
	@PostMapping("")
	public ResponseEntity<APIResponse> addMerchantRepresentativeDetails(@Valid @RequestBody APIRequest apiReq) {
		APIResponse ar = new APIResponse();
		if (apiReq.getFunction().equals(APIRequestFunctionCode.ADD_DATA.toString())
				&& apiReq.getScope().equals(APIRequestScopeCode.SINGLE.toString())) {
			try {
				MerchantsRepresentativeDetailsRequest representativeReq = apiReq.getData()
						.getMerchantsRepresentativeDetailsRequest();
				MerchantsRepresentativeDetails representative = new MerchantsRepresentativeDetails(representativeReq);
				representative = representativesService.save(representative);
				ar.setData(representative.getId());
				ar.setStatus(APIResponseCode.SUCCESS);
				ar.setDescription(ResponseMessageCodes.CREATED_SUCCESSFULLY.toString());
			} catch (Exception e) {
				ar.setStatus(APIResponseCode.FAILURE);
				ar.setDescription(e.getMessage());
			}
		} else {
			ar.setStatus(APIResponseCode.FAILURE);
			ar.setDescription(ResponseMessageCodes.CREATION_FAILED.toString());
		}
		return ResponseEntity.ok(ar);
	}

	@GetMapping("")
	public ResponseEntity<APIResponse> getMerchantsRepresentativesDetails(@Valid @RequestBody APIRequest apiReq) {
		APIResponse ar = new APIResponse();
		try {
			if ((apiReq.getFunction().equals(APIRequestFunctionCode.SEARCH.toString()))
					&& (apiReq.getScope().equals(APIRequestScopeCode.ALL.toString()))) {
				if(apiReq.getData()==null) {
					List<MerchantsRepresentativeDetailsDTO> merchantsReps = representativesService.getAll();
					ar.setData(merchantsReps);
					ar.setStatus(APIResponseCode.SUCCESS);
					ar.setDescription(ResponseMessageCodes.DATA_RETRIEVED_SUCCESSFULLY.toString());
				}else if (apiReq.getData().getQuery().getBy().equals("MERCHANT_ID")
						&& apiReq.getData().getQuery().getValue() != null) {
					List<MerchantsRepresentativeDetailsDTO> representative = representativesService
							.getByMerchantId(Long.parseLong(apiReq.getData().getQuery().getValue()));
					ar.setData(representative);
					ar.setStatus(APIResponseCode.SUCCESS);
					ar.setDescription(ResponseMessageCodes.DATA_RETRIEVED_SUCCESSFULLY.toString());
				} else {
					ar.setStatus(APIResponseCode.FAILURE);
					ar.setDescription(ResponseMessageCodes.NO_RESULTS_FOUND_FOR_YOUR_SEARCH_QUERY.toString());
				}
			
			} else if ((apiReq.getFunction().equals(APIRequestFunctionCode.SEARCH.toString()))
					&& (apiReq.getScope().equals(APIRequestScopeCode.SINGLE.toString()))) {
				if (apiReq.getData().getQuery().getBy().equals("MERCHANTS_REPRESENTATIVES_DETAILS_ID")
						&& apiReq.getData().getQuery().getValue() != null) {
					Optional<MerchantsRepresentativeDetailsDTO> representative = representativesService
							.getById(Long.parseLong(apiReq.getData().getQuery().getValue()));
					if(!representative.isEmpty()) {
						ar.setData(representative);
						ar.setStatus(APIResponseCode.SUCCESS);
						ar.setDescription(ResponseMessageCodes.DATA_RETRIEVED_SUCCESSFULLY.toString());
					}else {
						throw new EntityNotFoundException();
					}
				} else {
					ar.setStatus(APIResponseCode.FAILURE);
					ar.setDescription(ResponseMessageCodes.NO_RESULTS_FOUND_FOR_YOUR_SEARCH_QUERY.toString());
				}
			} else {
				ar.setStatus(APIResponseCode.FAILURE);
				ar.setDescription(ResponseMessageCodes.NO_RESULTS_FOUND_FOR_YOUR_SEARCH_QUERY.toString());
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			ar.setStatus(APIResponseCode.FAILURE);
			ar.setDescription(ResponseMessageCodes.NO_RESULTS_FOUND_FOR_YOUR_SEARCH_QUERY.toString());
		}
		return ResponseEntity.ok(ar);
	}

	@PutMapping("")
	public ResponseEntity<APIResponse> updateMerchantsRepresentativesDetails(@Valid @RequestBody APIRequest apiReq) {
		APIResponse ar = new APIResponse();
		if (apiReq.getFunction().equals(APIRequestFunctionCode.UPDATE_DATA.toString())
				&& apiReq.getScope().equals(APIRequestScopeCode.SINGLE.toString())) {
			if (apiReq.getData().getQuery().getBy().equals("MERCHANT_REPRESENTATIVE_ID")
					&& apiReq.getData().getQuery().getValue() != null) {
				try {
					MerchantsRepresentativeDetailsRequest merchantBankDetailsReq = apiReq.getData()
							.getMerchantsRepresentativeDetailsRequest();
					MerchantsRepresentativeDetails merchantsRepresentativeDetails = new MerchantsRepresentativeDetails(
							merchantBankDetailsReq);
					merchantsRepresentativeDetails.setId(Long.parseLong(apiReq.getData().getQuery().getValue()));
					merchantsRepresentativeDetails = representativesService.update(merchantsRepresentativeDetails);
					ar.setData(merchantsRepresentativeDetails);
					ar.setStatus(APIResponseCode.SUCCESS);
					ar.setDescription(ResponseMessageCodes.DATA_RETRIEVED_SUCCESSFULLY.toString());
				} catch (Exception e) {
					logger.error(e.getMessage());
					ar.setStatus(APIResponseCode.FAILURE);
					ar.setDescription(ResponseMessageCodes.UPDATE_FAILED.toString());
				}
			} else {
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
