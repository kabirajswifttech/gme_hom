package com.gme.hom.documents.services;

import java.util.List;

import com.gme.hom.documents.models.DocumentRequest;

import jakarta.servlet.http.HttpServletRequest;

public interface DocumentService {
	
	boolean saveDocuments(List<DocumentRequest> documents, HttpServletRequest httpRequest);
}
