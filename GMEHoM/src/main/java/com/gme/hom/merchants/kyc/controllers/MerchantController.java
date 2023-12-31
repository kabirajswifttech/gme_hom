package com.gme.hom.merchants.kyc.controllers;

import java.util.List;

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
import com.gme.hom.documents.models.DocumentRequest;
import com.gme.hom.documents.services.DocumentService;
import com.gme.hom.merchants.config.FindByCodes;
import com.gme.hom.merchants.config.ResponseMessageCodes;
import com.gme.hom.merchants.kyc.model.Merchant;
import com.gme.hom.merchants.kyc.model.MerchantRequest;
import com.gme.hom.merchants.kyc.services.MerchantDTO;
import com.gme.hom.merchants.kyc.services.MerchantService;

import jakarta.persistence.NoResultException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/merchant")
@RequiredArgsConstructor
public class MerchantController {

	@Autowired
	private MerchantService merchantService;
	
	@Autowired
	private DocumentService documentService;
	

	private static final Logger logger = LoggerFactory.getLogger(MerchantController.class);

	@PostMapping("")
	public ResponseEntity<APIResponse> addMerchantDetails(@Valid @RequestBody APIRequest apiReq, HttpServletRequest req) {
		APIResponse ar = new APIResponse();
		if (apiReq.getFunction().equals(APIRequestFunctionCode.ADD_DATA.toString())
				&& apiReq.getScope().equals(APIRequestScopeCode.SINGLE.toString())) {
			try {
				MerchantRequest merchantReq = apiReq.getData().getMerchantRequest();
				Merchant merchant = new Merchant(merchantReq);
				merchant = merchantService.save(merchant);
				Long id = merchant.getId();
				//logger.debug("Merchant with email id '" + merchant.getEmailId() + "' created.");
				ar.setStatus(APIResponseCode.SUCCESS);
				ar.setDescription(ResponseMessageCodes.CREATED_SUCCESSFULLY.toString());
				ar.setData(merchant.getId());
				if(apiReq.getData().getDocumentRequests()!=null) {
					List<DocumentRequest> docReqs = apiReq.getData().getDocumentRequests();
					docReqs.forEach(doc->doc.setAssociationId(id));
					documentService.saveDocuments(docReqs, req);
				}
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
	public ResponseEntity<APIResponse> getMerchants(@Valid @RequestBody APIRequest apiReq) {
		APIResponse ar = new APIResponse();
		if (apiReq.getFunction().equals(APIRequestFunctionCode.SEARCH.toString())
				&& apiReq.getScope().equals(APIRequestScopeCode.ALL.toString())) {
			try {
				List<MerchantDTO> merchants = merchantService.getAllMerchants();
				ar.setData(merchants);
				ar.setStatus(APIResponseCode.SUCCESS);
				ar.setDescription(ResponseMessageCodes.DATA_RETRIEVED_SUCCESSFULLY.toString());
				// merchants.forEach(m->logger.debug(m.toString()));
			} catch (Exception e) {
				ar.setStatus(APIResponseCode.FAILURE);
				ar.setDescription(ResponseMessageCodes.NO_RESULTS_FOUND_FOR_YOUR_SEARCH_QUERY.toString());
			}
		} else if (apiReq.getFunction().equals(APIRequestFunctionCode.SEARCH.toString())
				&& apiReq.getScope().equals(APIRequestScopeCode.SINGLE.toString())) {
			if (apiReq.getData().getQuery().getBy().equals(FindByCodes.MERCHANT_ID.toString())
					&& apiReq.getData().getQuery().getValue() != null) {
				try {
					
					MerchantDTO merchant = merchantService
							.getById(Long.parseLong(apiReq.getData().getQuery().getValue()));
					//logger.error(merchant.toString());
					if (merchant != null) {
						ar.setData(merchant);
						ar.setStatus(APIResponseCode.SUCCESS);
						ar.setDescription(ResponseMessageCodes.DATA_RETRIEVED_SUCCESSFULLY.toString());
					} else {
						throw new NoResultException("Merchant not found exception!");
					}
				} catch (Exception e) {
					logger.error(e.getMessage());
					ar.setStatus(APIResponseCode.FAILURE);
					ar.setDescription(ResponseMessageCodes.NO_RESULTS_FOUND_FOR_YOUR_SEARCH_QUERY.toString());
				}
			}else if (apiReq.getData().getQuery().getBy().equals(FindByCodes.EMAIL_ID.toString())
					&& apiReq.getData().getQuery().getValue() != null) {
				try {
					MerchantDTO merchant = merchantService
							.getMerchantByEmailId(apiReq.getData().getQuery().getValue());
					if (merchant != null) {
						ar.setData(merchant);
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
			}else{
				ar.setStatus(APIResponseCode.FAILURE);
				ar.setDescription(ResponseMessageCodes.NO_RESULTS_FOUND_FOR_YOUR_SEARCH_QUERY.toString());
			}

		} else {
			ar.setStatus(APIResponseCode.FAILURE);
			ar.setDescription(ResponseMessageCodes.NO_RESULTS_FOUND_FOR_YOUR_SEARCH_QUERY.toString());
		}
		return ResponseEntity.ok(ar);
	}

	@PutMapping("")
	public ResponseEntity<APIResponse> updateMerchantDetails(@Valid @RequestBody APIRequest apiReq) {
		APIResponse ar = new APIResponse();
		if (apiReq.getFunction().equals(APIRequestFunctionCode.UPDATE_DATA.toString())
				&& apiReq.getScope().equals(APIRequestScopeCode.SINGLE.toString())) {
			if (apiReq.getData().getQuery().getBy().equals("MERCHANT_ID")
					&& apiReq.getData().getQuery().getValue() != null) {
				try {
					Merchant merchant = new Merchant(apiReq.getData().getMerchantRequest());
					merchant.setId(Long.parseLong(apiReq.getData().getQuery().getValue()));
					// merchant.setUpdatedBy(UserSecurityService.getUsername());
					merchant = merchantService.update(merchant);
					ar.setData(merchant);
					ar.setStatus(APIResponseCode.SUCCESS);
					ar.setDescription(ResponseMessageCodes.UPDATED_SUCCESSFULLY.toString());

				} catch (Exception e) {
					System.err.println(e);
					ar.setStatus(APIResponseCode.FAILURE);
					ar.setDescription(ResponseMessageCodes.UPDATE_FAILED.toString());
				}
			}
		}
		return ResponseEntity.ok(ar);

	}

}
//	/* This api is used for creating merchant and merchant user. */
//
//	@GetMapping("/{id}")
//	public String getMerchant(@PathVariable("id") int id, @RequestBody APIRequest apiRequest) {
//		APIResponse ar = new APIResponse();
//		if (apiRequest.getFunction().equals(APIRequestFunctionCode.SEARCH.toString())
//				&& apiRequest.getScope().equals(APIRequestScopeCode.SINGLE.toString())) {
//			if (!merchantService.getMerchantById(id).isEmpty()) {
//				ar.setStatus(APIResponseCode.SUCCESS.toString());
//				ar.setScope(APIResponseScopeCode.SINGLE.toString());
//				ar.setDataType("Merchant");
//				ar.setDescription("Api for fetching user by id.");
//				ar.setData(merchantService.getMerchantById(id).get());
//			} else {
//				ar.setStatus(APIResponseCode.FAILURE.toString());
//				ar.setScope(APIResponseScopeCode.SINGLE.toString());
//				ar.setDataType("Merchant");
//				ar.setDescription("No such user found.");
//			}
//		} else {
//			ar.setStatus(APIResponseCode.FAILURE.toString());
//			ar.setScope(APIResponseScopeCode.SINGLE.toString());
//			ar.setDataType("Merchant");
//			ar.setDescription("Invalid request!");
//		}
//		return ObjectToJson.toJson(ar);
//	}
//
//	/* Get all merchant details */
//	@GetMapping("")
//	public String getMerchants(@RequestBody APIRequest apiRequest) {
//		APIResponse ar = new APIResponse();
//		ar = new APIResponse();
//		if (apiRequest.getFunction().equals(APIRequestFunctionCode.SEARCH.toString())
//				&& apiRequest.getScope().equals(APIRequestScopeCode.ALL.toString())) {
//			if (!merchantService.getAllMerchants().isEmpty()) {
//				ar.setStatus(APIResponseCode.SUCCESS.toString());
//				ar.setScope(APIResponseScopeCode.MULTIPLE.toString());
//				ar.setDataType("Merchant");
//				ar.setDescription("Api for fetching user by id.");
//				List<MerchantDTO> merchantsDto = merchantService.getAllMerchants();
//				ar.setData(merchantsDto);
//			} else {
//				ar.setStatus(APIResponseCode.FAILURE.toString());
//				ar.setScope(APIResponseScopeCode.MULTIPLE.toString());
//				ar.setDataType("Merchant");
//				ar.setDescription("No users found.");
//			}
//		} else {
//			ar.setStatus(APIResponseCode.FAILURE.toString());
//			ar.setScope(APIResponseScopeCode.MULTIPLE.toString());
//			ar.setDataType("Merchant");
//			ar.setDescription("Invalid request!");
//		}
//		return ObjectToJson.toJson(ar);
//	}
//
//	/* Get paginated merchant details */
//	@GetMapping("/getMerchantPaginated")
//	public String getMerchantsPaginated(@RequestBody APIRequest apiRequest, @RequestParam int page,
//			@RequestParam int size) {
//		APIResponse ar = new APIResponse();
//		ar = new APIResponse();
//		if (apiRequest.getFunction().equals(APIRequestFunctionCode.SEARCH)
//				&& apiRequest.getScope().equals(APIRequestScopeCode.ALL.toString())) {
//			if (!merchantService.getAllMerchants().isEmpty()) {
//				ar.setStatus(APIResponseCode.SUCCESS.toString());
//				ar.setScope(APIResponseScopeCode.MULTIPLE.toString());
//				ar.setDataType("Merchant");
//				ar.setDescription("Api for fetching user by id.");
//				Page<Merchant> merchantsDto = merchantService.getAllMerchants(PageRequest.of(page, size));
//				ar.setData(merchantsDto);
//			} else {
//				ar.setStatus(APIResponseCode.FAILURE.toString());
//				ar.setScope(APIResponseScopeCode.MULTIPLE.toString());
//				ar.setDataType("Merchant");
//				ar.setDescription("No users found.");
//			}
//		} else {
//			ar.setStatus(APIResponseCode.FAILURE.toString());
//			ar.setScope(APIResponseScopeCode.MULTIPLE.toString());
//			ar.setDataType("Merchant");
//			ar.setDescription("Invalid request!");
//		}
//		return ObjectToJson.toJson(ar);
//	}
//
//	/* Add merchant to the database */
//	@PostMapping("")
//	public String addMerchant(@RequestBody APIRequest apiRequest) {
//
//		APIResponse ar = new APIResponse();
//
//		if (apiRequest.getFunction().equals(APIRequestFunctionCode.ADD_DATA)
//				&& apiRequest.getScope().equals(APIRequestScopeCode.ALL.toString())) {
//			MerchantRequest merchantRequest = apiRequest.getData().getMerchantRequest();
//			if (merchantService.getMerchantByEmailId(merchantRequest.getEmailId()).isEmpty()) {
//				Merchant merchant = merchantService.addMerchant(merchantRequest);
//				ar.setStatus("SUCCESS");
//				ar.setDescription("User created successfully");
//				ar.setData(merchant);
//			} else {
//				ar.setStatus(APIResponseCode.FAILURE.toString());
//				ar.setDescription("Failed to add Merchant to the database! Duplicate field email!");
//			}
//
//		}
//
//		else {
//			ar.setStatus(APIResponseCode.FAILURE.toString());
//			ar.setDescription("Failed to add Merchant to the database!");
//		}
//		return ObjectToJson.toJson(ar);
//
//	}
//
//	/* Update Merchant details */
//	@PatchMapping("/{id}")
//	public String updateMerchant(@PathVariable("id") Long id, @RequestBody APIRequest apiRequest) {
//
//		APIResponse ar = new APIResponse();
//
//		if (apiRequest.getFunction().equals(APIRequestFunctionCode.UPDATE_DATA.toString())
//				&& apiRequest.getScope().equals(APIRequestScopeCode.SINGLE.toString())) {
//			Merchant merchant = apiRequest.getData().getMerchant();
//			System.out.println(merchant);
//			if (!merchantService.getMerchantByEmailId(merchant.getEmailId()).isEmpty()) {
//				System.out.println("Hello");
//				merchant = merchantService.updateMerchant(merchant);
//				ar.setStatus("SUCCESS");
//				ar.setDescription("User updated successfully");
//				ar.setData(merchant);
//			} else {
//				ar.setStatus(APIResponseCode.FAILURE.toString());
//				ar.setDescription("Failed to update Merchant to the database!");
//			}
//		}
//		return ObjectToJson.toJson(ar);
//
//	}
