package com.gme.hom.merchants.stockholders.controllers;

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
import com.gme.hom.merchants.stockholders.model.MerchantsStockholdersDetails;
import com.gme.hom.merchants.stockholders.model.MerchantsStockholdersDetailsRequest;
import com.gme.hom.merchants.stockholders.services.MerchantsStockholdersDetailsDTO;
import com.gme.hom.merchants.stockholders.services.MerchantsStockholdersDetailsService;

import jakarta.persistence.NoResultException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/merchants_stockholders_details")
@AllArgsConstructor
public class MerchantsStockholdersDetailsController {
	
	@Autowired
	MerchantsStockholdersDetailsService stockholdersService;
	
	private static final Logger logger = LoggerFactory.getLogger(MerchantsStockholdersDetailsController.class);

	@PostMapping("")
	public ResponseEntity<APIResponse> addMerchantStockholderDetails(@Valid @RequestBody APIRequest apiReq) {

		APIResponse ar = new APIResponse();
		if (apiReq.getFunction().equals(APIRequestFunctionCode.ADD_DATA.toString())
				&& apiReq.getScope().equals(APIRequestScopeCode.SINGLE.toString())) {
			try {
				List<MerchantsStockholdersDetailsRequest> stockholdersDetailsReqs = apiReq.getData()
						.getMerchantsStockholdersDetailsRequests();
				MerchantsStockholdersDetails stockholdersDetails = new MerchantsStockholdersDetails(
						stockholdersDetailsReqs.get(0));
				stockholdersDetails = stockholdersService.save(stockholdersDetails);
				ar.setStatus(APIResponseCode.SUCCESS);
				ar.setDescription(ResponseMessageCodes.CREATED_SUCCESSFULLY.toString());
				ar.setData(stockholdersDetails.getId());
			} catch (Exception e) {
				logger.error(e.getMessage());
				ar.setStatus(APIResponseCode.FAILURE);
				ar.setDescription(ResponseMessageCodes.CREATION_FAILED.toString());
			}
		} else {
			ar.setStatus(APIResponseCode.FAILURE);
			ar.setDescription(ResponseMessageCodes.CREATION_FAILED.toString());
		}

		return ResponseEntity.ok(ar);
	}

	@GetMapping("")
	public ResponseEntity<APIResponse> getMerchantStockholdersDetails(@Valid @RequestBody APIRequest apiReq) {
		APIResponse ar = new APIResponse();
		if ((apiReq.getFunction().equals(APIRequestFunctionCode.SEARCH.toString()))
				&& (apiReq.getScope().equals(APIRequestScopeCode.ALL.toString()))) {
			try {
				if (apiReq.getData() == null) {
					List<MerchantsStockholdersDetailsDTO> stockholdersDetails = stockholdersService.getAll();
					ar.setData(stockholdersDetails);
					ar.setStatus(APIResponseCode.SUCCESS);
					ar.setDescription(ResponseMessageCodes.DATA_RETRIEVED_SUCCESSFULLY.toString());
				} else if (apiReq.getData().getQuery().getBy().equals("MERCHANT_ID")
						&& apiReq.getData().getQuery().getValue() != null) {

					List<MerchantsStockholdersDetailsDTO> merchantsStockholdersDetails = stockholdersService
							.getByMerchantId(Long.parseLong(apiReq.getData().getQuery().getValue()));
					if (!merchantsStockholdersDetails.isEmpty()) {
						ar.setData(merchantsStockholdersDetails);
						ar.setStatus(APIResponseCode.SUCCESS);
						ar.setDescription(ResponseMessageCodes.DATA_RETRIEVED_SUCCESSFULLY.toString());
					} else {
						throw new NoResultException();
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
		} else if ((apiReq.getFunction().equals(APIRequestFunctionCode.SEARCH.toString()))
				&& (apiReq.getScope().equals(APIRequestScopeCode.SINGLE.toString()))) {
			if (apiReq.getData().getQuery().getBy().equals("MERCHANTS_STOCKHOLDERS_DETAILS_ID")
					&& apiReq.getData().getQuery().getValue() != null) {
				try {
					Optional<MerchantsStockholdersDetailsDTO> merchantsStockholdersDetails = stockholdersService
							.getById(Long.parseLong(apiReq.getData().getQuery().getValue()));
					if (!merchantsStockholdersDetails.isEmpty()) {
						ar.setData(merchantsStockholdersDetails.get());
						ar.setStatus(APIResponseCode.SUCCESS);
						ar.setDescription(ResponseMessageCodes.DATA_RETRIEVED_SUCCESSFULLY.toString());
					} else {
						throw new NoResultException();
					}
				} catch (Exception e) {
					logger.error(e.getMessage());
					ar.setStatus(APIResponseCode.FAILURE);
					ar.setDescription(ResponseMessageCodes.NO_RESULTS_FOUND_FOR_YOUR_SEARCH_QUERY.toString());
				}
			}

		} else {
			ar.setStatus(APIResponseCode.FAILURE);
			ar.setDescription(ResponseMessageCodes.NO_RESULTS_FOUND_FOR_YOUR_SEARCH_QUERY.toString());
		}
		return ResponseEntity.ok(ar);
	}

	@PutMapping("")
	public ResponseEntity<APIResponse> updateMerchantStockholdersDetails(@Valid @RequestBody APIRequest apiReq) {
		APIResponse ar = new APIResponse();
		if (apiReq.getFunction().equals(APIRequestFunctionCode.UPDATE_DATA.toString())
				&& apiReq.getScope().equals(APIRequestScopeCode.SINGLE.toString())) {
			if (apiReq.getData().getQuery().getBy().equals("MERCHANTS_STOCKHOLDERS_DETAILS_ID")
					&& apiReq.getData().getQuery().getValue() != null) {
				try {
					MerchantsStockholdersDetails merchantsStockholdersDetails = new MerchantsStockholdersDetails(
							apiReq.getData().getMerchantsStockholdersDetailsRequests().get(0));
					merchantsStockholdersDetails.setId(Long.parseLong(apiReq.getData().getQuery().getValue()));
					merchantsStockholdersDetails = stockholdersService.update(merchantsStockholdersDetails);
					ar.setData(merchantsStockholdersDetails);
					ar.setStatus(APIResponseCode.SUCCESS);
					ar.setDescription(ResponseMessageCodes.DATA_RETRIEVED_SUCCESSFULLY.toString());

				} catch (Exception e) {
					logger.error(e.getMessage());
					ar.setStatus(APIResponseCode.FAILURE);
					ar.setDescription(ResponseMessageCodes.NO_RESULTS_FOUND_FOR_YOUR_SEARCH_QUERY.toString());
				}
			}
		}
		return ResponseEntity.ok(ar);
	}

}
