package com.gme.hom.documents.services;

import java.io.File;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gme.hom.GlobalConfig;
import com.gme.hom.documents.config.DocumentCodes;
import com.gme.hom.documents.models.Document;
import com.gme.hom.documents.models.DocumentRequest;
import com.gme.hom.documents.repository.DocumentRepository;
import com.gme.hom.security.services.ChecksumService;
import com.gme.hom.users.services.UserService;
import com.gme.hom.usersecurity.services.UserSecurityService;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class DocumentServiceImpl implements DocumentService {

	@Autowired
	DocumentRepository merchantDocumentsRepository;

	@Autowired
	UserService userService;

	private static final Logger logger = LoggerFactory.getLogger(DocumentServiceImpl.class);

	@Override
	public boolean saveDocuments(List<DocumentRequest> documents, HttpServletRequest httpRequest) {

		boolean success = true;

		String clientAddress = UserSecurityService.getClientAddress(httpRequest);

		for (DocumentRequest document : documents) {
			// System.out.println("Saving document: " + document.getDocName());

			if (!saveDocument(document, clientAddress)) {
				success = false;
			}
		}

		return success;
	}

	boolean saveDocument(DocumentRequest documentRequest, String clientAddress) {

		// prepare document metadata
		Document doc = new Document(documentRequest);
		final UUID uuid = UUID.randomUUID();
		doc.setDocId(uuid);
		doc.setSourceId(userService.getSourceIdByUsername(UserSecurityService.getUsername()));
		doc.setSourceType(userService.getUserTypeByUsername(UserSecurityService.getUsername()));
		doc.setCreatedBy(UserSecurityService.getUsername());

		// prepare document location
		String parentDir = GlobalConfig.FILES_DOCUMENT_LOC + File.separator + doc.getSourceType() + File.separator
				+ doc.getSourceId() + File.separator;

		String filename = uuid + "_" + doc.getDocName();

		logger.debug("Client address: " + clientAddress);
		doc.setDocPath(parentDir + filename);
		doc.setActive(DocumentCodes.DOCUMENT_IS_ACTIVE_DEFAULT);
		doc.setStatus(DocumentCodes.DOCUMENT_STATUS_DEFAULT);

		try {
			doc.setEntityHash(ChecksumService.getChecksum(doc, GlobalConfig.DATA_ENTITY_HASH));
			merchantDocumentsRepository.save(doc);
			byte[] file = Base64.getDecoder().decode(documentRequest.getFile());
			saveFile(parentDir, filename, file);

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	boolean saveFile(String parentDir, String filename, byte[] file) {

		try {

			File parentPathDir = new File(parentDir);

			if (parentPathDir.exists() && parentPathDir.isDirectory()) {

				// System.out.println("Parent path '" + parentDir + " exists.");
				// System.out.println("Writing file '" + parentDir + filename + "'...");

				FileUtils.writeByteArrayToFile(new File(parentDir + filename), file);

			} else {

				System.out.println("Parent path '" + parentDir + " does not exist.");
				System.out.println("Creating '" + parentPathDir + "' directories...");
				boolean dirsCreated = parentPathDir.mkdirs();

				if (dirsCreated) {

					System.out.println("Writing file '" + parentDir + filename + "'...");
					FileUtils.writeByteArrayToFile(new File(parentDir + filename), file);
				}
			}
		} catch (

		Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
}
