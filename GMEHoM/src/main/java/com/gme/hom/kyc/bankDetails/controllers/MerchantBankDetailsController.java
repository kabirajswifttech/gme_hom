package com.gme.hom.kyc.bankDetails.controllers;

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
import com.gme.hom.kyc.bankDetails.model.MerchantsBankDetails;
import com.gme.hom.kyc.bankDetails.model.MerchantsBankDetailsDTO;
import com.gme.hom.kyc.bankDetails.model.MerchantsBankDetailsRequest;
import com.gme.hom.kyc.bankDetails.services.MerchantsBankDetailsService;
import com.gme.hom.kyc.codes.ResponseMessageCodes;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/v1/merchant_bank_details")
@AllArgsConstructor
public class MerchantBankDetailsController {

	MerchantsBankDetailsService merchantsBankDetailsService;

	private static final Logger logger = LoggerFactory.getLogger(MerchantBankDetailsController.class);
	
	@PostMapping("")
	public ResponseEntity<APIResponse> addMerchantBankDetails(@Valid @RequestBody APIRequest apiReq) {
		APIResponse ar = new APIResponse();
		if (apiReq.getFunction().equals(APIRequestFunctionCode.ADD_DATA.toString())
				&& apiReq.getScope().equals(APIRequestScopeCode.SINGLE.toString())) {
			try {
				MerchantsBankDetailsRequest merchantBankDetailsReq = apiReq.getData().getMerchantsBankDetailsRequest();
				MerchantsBankDetails merchantsBankDetails = new MerchantsBankDetails(merchantBankDetailsReq);
				merchantsBankDetails = merchantsBankDetailsService.save(merchantsBankDetails);
				ar.setData(merchantsBankDetails.getId());
				ar.setStatus(APIResponseCode.SUCCESS.toString());
				ar.setDescription("Data Saved!");
			} catch (Exception e) {
				logger.error(e.getMessage());
				ar.setStatus(APIResponseCode.FAILURE.toString());
				ar.setDescription(ResponseMessageCodes.CREATION_FAILED.toString());
			}
		} else {
			ar.setStatus(APIResponseCode.FAILURE.toString());
			ar.setDescription(ResponseMessageCodes.INVALID_FUNCTION_CODE_OR_SCOPE.toString());
		}
		return ResponseEntity.ok(ar);
	}

	@GetMapping("")
	public ResponseEntity<APIResponse> getMerchantBankDetails(@Valid @RequestBody APIRequest apiReq) {
		APIResponse ar = new APIResponse();
		if (apiReq.getFunction().equals(APIRequestFunctionCode.GET_DATA.toString())
				&& apiReq.getScope().equals(APIRequestScopeCode.ALL.toString())) {
			try {
				List<MerchantsBankDetailsDTO> merchantsBankDetails = merchantsBankDetailsService.getAll();
				ar.setData(merchantsBankDetails);
				ar.setStatus(APIResponseCode.SUCCESS.toString());
				ar.setDescription(ResponseMessageCodes.DATA_RETRIEVED_SUCCESSFULLY.toString());
			} catch (Exception e) {
				logger.error(e.getMessage());
				ar.setStatus(APIResponseCode.FAILURE.toString());
				ar.setDescription(ResponseMessageCodes.NO_RESULTS_FOUND_FOR_YOUR_SEARCH_QUERY.toString());
			}
		} else if (apiReq.getFunction().equals(APIRequestFunctionCode.GET_DATA.toString())
				&& apiReq.getScope().equals(APIRequestScopeCode.SINGLE.toString())) {
			try {
				if (apiReq.getData().getQuery().getBy().equals("MERCHANTS_BANK_DETAIL_ID")
						&& apiReq.getData().getQuery().getValue() != null) {
					Long merchantBankDetailsId = Long.parseLong(apiReq.getData().getQuery().getValue());
					MerchantsBankDetailsDTO merchantsBankDetails = merchantsBankDetailsService
							.getById(merchantBankDetailsId);
					ar.setData(merchantsBankDetails);
					ar.setStatus(APIResponseCode.SUCCESS.toString());
					ar.setDescription("Data Saved!");
				} else if (apiReq.getData().getQuery().getBy().equals("MERCHANT_ID") && apiReq.getData().getQuery().getValue() != null) {
					logger.info("merchant id: "+apiReq.getData().getQuery().getValue());
					Long merchantId = Long.parseLong(apiReq.getData().getQuery().getValue());
					MerchantsBankDetailsDTO merchantsBankDetails = merchantsBankDetailsService.getByMerchantId(merchantId);
					ar.setData(merchantsBankDetails);
					ar.setStatus(APIResponseCode.SUCCESS.toString());
					ar.setDescription(ResponseMessageCodes.DATA_RETRIEVED_SUCCESSFULLY.toString());
				} else {
					ar.setStatus(APIResponseCode.FAILURE.toString());
					ar.setDescription(ResponseMessageCodes.NO_RESULTS_FOUND_FOR_YOUR_SEARCH_QUERY.toString());
				}

			} catch (Exception e) {
				logger.error(e.getMessage());
				ar.setStatus(APIResponseCode.FAILURE.toString());
				ar.setDescription(ResponseMessageCodes.NO_RESULTS_FOUND_FOR_YOUR_SEARCH_QUERY.toString());
			}
		} else {
			ar.setStatus(APIResponseCode.FAILURE.toString());
			ar.setDescription(ResponseMessageCodes.INVALID_FUNCTION_CODE_OR_SCOPE.toString());
		}
		return ResponseEntity.ok(ar);

	}

	@PatchMapping("")
	public ResponseEntity<APIResponse> updateMerchantBankDetails(@Valid @RequestBody APIRequest apiReq) {
		APIResponse ar = new APIResponse();
		if (apiReq.getFunction().equals(APIRequestFunctionCode.UPDATE_DATA.toString())
				&& apiReq.getScope().equals(APIRequestScopeCode.SINGLE.toString())) {
			if (apiReq.getData().getQuery().getBy().equals("MERCHANT_BANK_DETAILS_ID")
					&& apiReq.getData().getQuery().getValue() != null) {
				try {
					MerchantsBankDetailsRequest merchantBankDetailsReq = apiReq.getData()
							.getMerchantsBankDetailsRequest();
					MerchantsBankDetails merchantsBankDetails = new MerchantsBankDetails(merchantBankDetailsReq);
					merchantsBankDetails.setId(Long.parseLong(apiReq.getData().getQuery().getValue()));
					merchantsBankDetails = merchantsBankDetailsService
							.update(merchantBankDetailsReq);
					ar.setData(merchantsBankDetails);
					ar.setStatus(APIResponseCode.SUCCESS.toString());
					ar.setDescription(ResponseMessageCodes.UPDATED_SUCCESSFULLY.toString());
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
