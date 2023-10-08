package com.gme.hom.merchants.directors.controllers;

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
import com.gme.hom.merchants.config.FindByCodes;
import com.gme.hom.merchants.config.ResponseMessageCodes;
import com.gme.hom.merchants.directors.model.MerchantsDirectorsDetails;
import com.gme.hom.merchants.directors.model.MerchantsDirectorsDetailsRequest;
import com.gme.hom.merchants.directors.services.MerchantsDirectorsDetailsDTO;
import com.gme.hom.merchants.directors.services.MerchantsDirectorsDetailsService;

import jakarta.persistence.NoResultException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/merchants_directors_details")
@AllArgsConstructor
public class MerchantDirectorsDetailsController {
	
	@Autowired
	MerchantsDirectorsDetailsService merchantsDirectorsDetailsService;
	
	private static final Logger logger = LoggerFactory.getLogger(MerchantDirectorsDetailsController.class);

	@PostMapping("")
	public ResponseEntity<APIResponse> addMerchantDirectorsDetails(@Valid @RequestBody APIRequest apiReq,
			HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		APIResponse ar = new APIResponse();
		if (apiReq.getFunction().equals(APIRequestFunctionCode.ADD_DATA.toString())
				&& apiReq.getScope().equals(APIRequestScopeCode.SINGLE.toString())) {
			try {
				List<MerchantsDirectorsDetailsRequest> directorsDetailsReqs = apiReq.getData()
						.getMerchantsDirectorsDetailsRequests();
				directorsDetailsReqs.forEach(req->{
					
					try {
						merchantsDirectorsDetailsService.save(new MerchantsDirectorsDetails(req));
					} catch (NoSuchAlgorithmException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
					
				});
				ar.setStatus(APIResponseCode.SUCCESS);
				ar.setDescription(ResponseMessageCodes.CREATED_SUCCESSFULLY.toString());
				//ar.setData(directorsDetails.getId());
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
	public ResponseEntity<APIResponse> getMerchantDirectorsDetails(@Valid @RequestBody APIRequest apiReq) {
		APIResponse ar = new APIResponse();
		if (apiReq.getFunction().equals(APIRequestFunctionCode.SEARCH.toString())
				&& apiReq.getScope().equals(APIRequestScopeCode.ALL.toString())) {
			if (apiReq.getData()!= null) {
				if (apiReq.getData().getQuery().getBy().equals(FindByCodes.MERCHANT_ID.toString())
						&& apiReq.getData().getQuery().getValue() != null) {
					try {
						List<MerchantsDirectorsDetailsDTO> merchantsDirectorsDetails = merchantsDirectorsDetailsService
								.getByMerchantId(Long.parseLong(apiReq.getData().getQuery().getValue()));
						if (!merchantsDirectorsDetails.isEmpty()) {
							ar.setData(merchantsDirectorsDetails);
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
				} else {
					logger.error("Query param not found!");
					ar.setStatus(APIResponseCode.FAILURE);
					ar.setDescription(ResponseMessageCodes.NO_RESULTS_FOUND_FOR_YOUR_SEARCH_QUERY.toString());
				}

			} else {
				try {
					List<MerchantsDirectorsDetailsDTO> merchantsDirectorsDetails = merchantsDirectorsDetailsService
							.getAll();
					ar.setData(merchantsDirectorsDetails);
					ar.setStatus(APIResponseCode.SUCCESS);
					ar.setDescription(ResponseMessageCodes.DATA_RETRIEVED_SUCCESSFULLY.toString());
				} catch (Exception e) {
					logger.error(e.getMessage());
					ar.setStatus(APIResponseCode.FAILURE);
					ar.setDescription(ResponseMessageCodes.NO_RESULTS_FOUND_FOR_YOUR_SEARCH_QUERY.toString());
				}
			}
		} else if ((apiReq.getFunction().equals(APIRequestFunctionCode.SEARCH.toString()))
				&& (apiReq.getScope().equals(APIRequestScopeCode.SINGLE.toString()))) {
			logger.error("Hello");
			if (apiReq.getData().getQuery().getBy().equals(FindByCodes.MERCHANTS_DIRECTORS_DETAILS_ID.toString())
					&& apiReq.getData().getQuery().getValue() != null) {
				try {
					Optional<MerchantsDirectorsDetailsDTO> merchantsDirectorsDetails = merchantsDirectorsDetailsService
							.getById(Long.parseLong(apiReq.getData().getQuery().getValue()));
					if (!merchantsDirectorsDetails.isEmpty()) {
						ar.setData(merchantsDirectorsDetails.get());
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
			logger.error("Function or scope didnot match!");
			ar.setStatus(APIResponseCode.FAILURE);
			ar.setDescription(ResponseMessageCodes.NO_RESULTS_FOUND_FOR_YOUR_SEARCH_QUERY.toString());
		}
		return ResponseEntity.ok(ar);
	}

	@PutMapping("")
	public ResponseEntity<APIResponse> updateMerchantDirectorsDetails(@Valid @RequestBody APIRequest apiReq) {
		APIResponse ar = new APIResponse();
		if (apiReq.getFunction().equals(APIRequestFunctionCode.UPDATE_DATA.toString())
				&& apiReq.getScope().equals(APIRequestScopeCode.SINGLE.toString())) {
			if (apiReq.getData().getQuery().getBy().equals(FindByCodes.MERCHANTS_DIRECTORS_DETAILS_ID.toString())
					&& apiReq.getData().getQuery().getValue() != null) {
				try {
					List<MerchantsDirectorsDetailsRequest> merchantsDirectorsDetails = apiReq.getData().getMerchantsDirectorsDetailsRequests();
					MerchantsDirectorsDetails merchantsDirectorsDetail = new MerchantsDirectorsDetails(merchantsDirectorsDetails.get(0));
					merchantsDirectorsDetail.setId(Long.parseLong(apiReq.getData().getQuery().getValue()));
					merchantsDirectorsDetail = merchantsDirectorsDetailsService.update(merchantsDirectorsDetail);
					ar.setData(merchantsDirectorsDetail);
					ar.setStatus(APIResponseCode.SUCCESS);
					ar.setDescription(ResponseMessageCodes.UPDATED_SUCCESSFULLY.toString());

				} catch (Exception e) {
					logger.error(e.getMessage());
					ar.setStatus(APIResponseCode.FAILURE);
					ar.setDescription(ResponseMessageCodes.UPDATE_FAILED.toString());
				}
			}
		}
		return ResponseEntity.ok(ar);

	}
}
