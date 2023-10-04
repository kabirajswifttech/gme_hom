package com.example.demo.services;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.demo.model.Merchant;
import com.example.demo.model.MerchantDTO;
import com.example.demo.model.MerchantLog;
import com.example.demo.repos.MerchantLogRepository;
import com.example.demo.repos.MerchantRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MerchantServiceImpl implements MerchantService {
	@Autowired
	MerchantRepository merchantRepo;
	@Autowired
	MerchantLogRepository merchantLogRepo;
	
	private static final Logger logger = LoggerFactory.getLogger(MerchantServiceImpl.class);


	@Override
	@Transactional
	public Merchant save(Merchant merchant) throws NoSuchAlgorithmException, IOException {
		if(!emailAlreadyRegistered(merchant.getEmailId()) && !phoneNumberAlreadyRegistered(merchant.getPhoneNumber())) {
		//merchant.setCreatedBy(UserSecurityService.getUsername());
		//merchant.setEntityHash(ChecksumService.getChecksum(merchant, GlobalConfig.DATA_ENTITY_HASH));
		merchant.setId(1l);
		merchant.setCreatedBy("Kabiraj");
		merchant = merchantRepo.save(merchant);
		merchantLogRepo.save(new MerchantLog(merchant));
		return merchant;
		}
		throw new DuplicateKeyException("Merchant already Registered!");
	}

	@Override
	public MerchantDTO getById(long id) {
		MerchantDTO merchant = merchantRepo.getMerchantById(id);
		return merchant;
	}

	@Override
	public MerchantDTO getMerchantByEmailId(String email) {
		return merchantRepo.findByEmailId(email);
	}

	@Override
	public Merchant update(Merchant merchant) {
		try {
			return merchantRepo.save(merchant);
		}catch (Exception e) {
			System.err.println(e);
		}
		return null;
	}

	@Override
	public List<MerchantDTO> getAllMerchants() {
		return merchantRepo.getAllMerchants();
	}
	
	@Override
	public Page<Merchant> getAllMerchants(PageRequest pageRequest) {
		return merchantRepo.findAll(pageRequest);
	}
	
	@Override
	public boolean emailAlreadyRegistered( String email ) {
		return merchantRepo.findByEmailId(email) != null;
	}
	

	@Override
	public boolean phoneNumberAlreadyRegistered(String phoneNumber) {
		return merchantRepo.findByPhoneNumber(phoneNumber) != null;
	}



}
