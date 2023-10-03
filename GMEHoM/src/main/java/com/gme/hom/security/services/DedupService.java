package com.gme.hom.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gme.hom.security.repository.SecurityRepository;

import jakarta.transaction.Transactional;

@Service
// check for duplicate entities in given tables
public class DedupService {
	
	@Autowired 
	SecurityRepository securityRepository;
	
	@Transactional
	public boolean checkDuplicate(String tableName, String hash) {
		
		if(securityRepository.checkDuplicateData(tableName, hash).size() > 0) {
			return true;
		}		
		
		return false;
	}

}
