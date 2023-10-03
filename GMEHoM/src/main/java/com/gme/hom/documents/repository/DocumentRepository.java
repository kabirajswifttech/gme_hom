package com.gme.hom.documents.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gme.hom.documents.models.Document;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long>{
	
	

}
