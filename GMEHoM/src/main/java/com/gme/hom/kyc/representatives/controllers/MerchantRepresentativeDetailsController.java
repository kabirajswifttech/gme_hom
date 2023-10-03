package com.gme.hom.kyc.representatives.controllers;

import java.util.List;

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
import com.gme.hom.kyc.representatives.model.MerchantsRepresentativeDetails;
import com.gme.hom.kyc.representatives.model.MerchantsRepresentativeDetailsRequest;
import com.gme.hom.kyc.representatives.services.MerchantsRepresentativeDetailsService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/merchant_representative_details")
public class MerchantRepresentativeDetailsController {

	private MerchantsRepresentativeDetailsService representativesService;

	@PostMapping("")
	public ResponseEntity<APIResponse> addMerchantRepresentativeDetails(APIRequest apiReq) {
		APIResponse ar = new APIResponse();
		if (apiReq.getFunction().equals(APIRequestFunctionCode.ADD_DATA.toString())
				&& apiReq.getScope().equals(APIRequestScopeCode.SINGLE.toString())) {
			try {
				MerchantsRepresentativeDetailsRequest representativeReq = apiReq.getData()
						.getMerchantsRepresentativeDetailsRequest();
				MerchantsRepresentativeDetails representative = new MerchantsRepresentativeDetails(representativeReq);
				representativesService.save(representative);
				ar.setStatus(APIResponseCode.SUCCESS.toString());
				ar.setDescription(ResponseMessageCodes.CREATED_SUCCESSFULLY.toString());
			} catch (Exception e) {
				ar.setStatus(APIResponseCode.FAILURE.toString());
				ar.setDescription(e.getMessage());
			}
		} else {
			ar.setStatus(APIResponseCode.FAILURE.toString());
			ar.setDescription(ResponseMessageCodes.CREATION_FAILED.toString());
		}
		return ResponseEntity.ok(ar);
	}

	@GetMapping("")
	public ResponseEntity<APIResponse> getMerchantsRepresentativesDetails(@RequestBody APIRequest apiReq) {
		APIResponse ar = new APIResponse();
		try {
			if (apiReq.getFunction().equals(APIRequestFunctionCode.GET_DATA.toString())
					&& apiReq.getScope().equals(APIRequestScopeCode.ALL.toString())) {

				List<MerchantsRepresentativeDetails> merchantsReps = representativesService.getAll();
				ar.setData(merchantsReps);
				ar.setStatus(APIResponseCode.SUCCESS.toString());
				ar.setDescription(ResponseMessageCodes.DATA_RETRIEVED_SUCCESSFULLY.toString());
			} else if (apiReq.getFunction().equals(APIRequestFunctionCode.GET_DATA.toString())
					&& apiReq.getScope().equals(APIRequestScopeCode.SINGLE.toString())) {
				if (apiReq.getData().getQuery().getBy().equals("MERCHANTS_REPRESENTATIVES_DETAILS_ID")
						&& apiReq.getData().getQuery().getValue() != null) {
					MerchantsRepresentativeDetails representative = representativesService
							.getById(Long.parseLong(apiReq.getData().getQuery().getValue()));
					ar.setData(representative);
					ar.setStatus(APIResponseCode.SUCCESS.toString());
					ar.setDescription(ResponseMessageCodes.DATA_RETRIEVED_SUCCESSFULLY.toString());
				} else if (apiReq.getData().getQuery().getBy().equals("MERCHANT_ID")
						&& apiReq.getData().getQuery().getValue() != null) {
					List<MerchantsRepresentativeDetails> representative = representativesService
							.getByMerchantId(Long.parseLong(apiReq.getData().getQuery().getValue()));
					ar.setData(representative);
					ar.setStatus(APIResponseCode.SUCCESS.toString());
					ar.setDescription(ResponseMessageCodes.DATA_RETRIEVED_SUCCESSFULLY.toString());
				} else {
					ar.setStatus(APIResponseCode.FAILURE.toString());
					ar.setDescription(ResponseMessageCodes.NO_RESULTS_FOUND_FOR_YOUR_SEARCH_QUERY.toString());
				}
			} else {
				ar.setStatus(APIResponseCode.FAILURE.toString());
				ar.setDescription(ResponseMessageCodes.NO_RESULTS_FOUND_FOR_YOUR_SEARCH_QUERY.toString());
			}
		} catch (Exception e) {
			ar.setStatus(APIResponseCode.FAILURE.toString());
			ar.setDescription(ResponseMessageCodes.NO_RESULTS_FOUND_FOR_YOUR_SEARCH_QUERY.toString());
		}
		return ResponseEntity.ok(ar);
	}

	@PatchMapping("")
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
					ar.setStatus(APIResponseCode.SUCCESS.toString());
					ar.setDescription(ResponseMessageCodes.DATA_RETRIEVED_SUCCESSFULLY.toString());
				} catch (Exception e) {
					ar.setStatus(APIResponseCode.FAILURE.toString());
					ar.setDescription(ResponseMessageCodes.UPDATE_FAILED.toString());
				}
			} else {
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