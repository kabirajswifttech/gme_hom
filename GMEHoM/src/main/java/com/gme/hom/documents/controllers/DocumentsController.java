package com.gme.hom.documents.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gme.hom.api.config.APIRequestFunctionCode;
import com.gme.hom.api.config.APIResponseCode;
import com.gme.hom.api.models.APIRequest;
import com.gme.hom.api.models.APIResponse;
import com.gme.hom.documents.models.DocumentRequest;
import com.gme.hom.documents.services.DocumentService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/documents")
public class DocumentsController {

	@Autowired
	DocumentService documentService;

	private static final Logger logger = LoggerFactory.getLogger(DocumentsController.class);

	@PostMapping(value = "")
	public ResponseEntity<APIResponse> fileUpload(@Valid @RequestBody APIRequest apiRequest,
			HttpServletRequest httpRequest, HttpServletResponse httpResponse) {

		APIResponse ar = new APIResponse();

		if (apiRequest.getFunction().equals(APIRequestFunctionCode.UPLOAD.toString())) {

			List<DocumentRequest> dr = apiRequest.getData().getDocumentRequests();

			if (dr != null) {

				// System.out.println("Saving " + dr.size() + " documents...");
				logger.debug("Saving " + dr.size() + " documents...");
			
				boolean success = documentService.saveDocuments(dr, httpRequest);

				if (success) {

					ar.setStatus(APIResponseCode.SUCCESS.toString());

					// ar.setData(mr.getContactInfo());

					ar.setDescription("Documents upload successful.");
				} else {
					ar.setStatus(APIResponseCode.FAILURE.toString());

					// ar.setData(mr.getContactInfo());

					ar.setDescription("Documents upload unsuccessful.");
				}

				return ResponseEntity.ok(ar);
			}

		}
		ar.setStatus(APIResponseCode.FAILURE.toString());

		return ResponseEntity.ok(ar);

	}

}
