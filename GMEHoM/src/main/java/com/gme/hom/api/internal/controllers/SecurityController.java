package com.gme.hom.api.internal.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gme.hom.api.config.APIRequestFunctionCode;
import com.gme.hom.api.config.APIResponseCode;
import com.gme.hom.api.models.APIRequest;
import com.gme.hom.api.models.APIResponse;
import com.gme.hom.security.models.DedupEntityHash;
import com.gme.hom.security.services.DedupService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/internal/security")
public class SecurityController {

	private final DedupService dedupService;

	SecurityController(DedupService dedupService) {
		this.dedupService = dedupService;
	}

	// INFO: To be used by internal services. MUST BE DISABLED FOR PRODUCTION
	@PostMapping("")
	public ResponseEntity<APIResponse> internalSecurity(@RequestBody APIRequest apiRequest, HttpServletRequest httpRequest, HttpServletResponse httpResponse) {

		APIResponse ar = new APIResponse();

		if (apiRequest.getFunction().equals(APIRequestFunctionCode.CALCULATE_HASH.toString())) {

			/*
			 * Merchant merchant = apiRequest.getData().getMerchant();
			 * 
			 * if (merchant != null) {
			 * 
			 * try {
			 * 
			 * String checksum = ChecksumService.getChecksum(merchant,
			 * GlobalConfig.DATA_ENTITY_HASH);
			 * 
			 * ar.setStatus(APIResponseCodes.SUCCESS);
			 * 
			 * ar.setData(checksum);
			 * 
			 * ar.setDescription("Checksum data");
			 * 
			 * ar.
			 * setDescription("INFO: To be used by internal services only. MUST BE DISABLED FOR PRODUCTION"
			 * );
			 * 
			 * return ResponseEntity.ok(ar);
			 * 
			 * } catch (IOException | NoSuchAlgorithmException e) {
			 * 
			 * e.printStackTrace();
			 * 
			 * } }
			 */

		}

		else if (apiRequest.getFunction().equals(APIRequestFunctionCode.CHECK_DUPLICATE.toString())) {

			DedupEntityHash dedupEntityHash = apiRequest.getData().getEntityHash();

			if (dedupEntityHash != null) {

				if (dedupService.checkDuplicate(dedupEntityHash.getTableName(), dedupEntityHash.getEntityHash())) {
					ar.setStatus(APIResponseCode.DUPLICATE.toString());

					ar.setDescription("Duplicate hash found");

				} else {
					ar.setStatus(APIResponseCode.UNIQUE.toString());

					ar.setDescription("The hash is unique in the given table.");

				}
				ar.setDescription("INFO: To be used by internal services only. MUST BE DISABLED FOR PRODUCTION");

				return ResponseEntity.ok(ar);
			}

		}
		ar.setStatus(APIResponseCode.FAILURE.toString());

		return ResponseEntity.ok(ar);

	}

}
