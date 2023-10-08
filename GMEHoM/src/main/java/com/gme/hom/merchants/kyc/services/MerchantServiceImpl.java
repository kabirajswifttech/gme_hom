package com.gme.hom.merchants.kyc.services;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.gme.hom.GlobalConfig;
import com.gme.hom.merchants.config.MerchantStatusCodes;
import com.gme.hom.merchants.kyc.model.Merchant;
import com.gme.hom.merchants.kyc.model.MerchantLog;
import com.gme.hom.merchants.kyc.repositories.MerchantLogRepository;
import com.gme.hom.merchants.kyc.repositories.MerchantRepository;
import com.gme.hom.security.services.ChecksumService;
import com.gme.hom.usersecurity.services.UserSecurityService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
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
		
		merchant.setStatus(MerchantStatusCodes.PENDING);
		merchant.setIsActive(true);
		merchant.setCreatedBy(UserSecurityService.getUsername());
		
		merchant.setEntityHash(ChecksumService.getChecksum(merchant, GlobalConfig.DATA_ENTITY_HASH));
		
		Merchant newMerchant = merchantRepo.save(merchant);
		merchantLogRepo.save(new MerchantLog(newMerchant));
		return merchant;
		}
		throw new DuplicateKeyException("Merchant already Registered!");
	}

	@Override
	public MerchantDTO getById(long id) {
		Optional<MerchantDTO> merchant = merchantRepo.findByMerchantId(id);
		if(!merchant.isEmpty()) {
			return merchant.get();
		}else {
			throw new EntityNotFoundException("Do such data found.");
		}
	}

	@Override
	public MerchantDTO getMerchantByEmailId(String email) {
		Optional<MerchantDTO> merchant =  merchantRepo.findByEmailId(email);
		if(!merchant.isEmpty()) {
			return merchant.get();
		}else {
			throw new EntityNotFoundException("Do such data found.");
		}
	}

	@Override
	//@Transactional
	public Merchant update(Merchant merchant) {
		
		Merchant newMerchant = merchantRepo.save(merchant);
		merchantLogRepo.save(new MerchantLog(newMerchant));
		return merchant;
		//merchant.setUpdatedBy(UserSecurityService.getUsername());
//		try {
//			Optional<Merchant> merchantDB = merchantRepo.findById(merchant.getId());
//			if(!merchantDB.isEmpty()) {
//				merchant.setMerchantId(merchantDB.get().getMerchantId());
//				System.out.println(merchant);
//				Merchant newMerchant = merchantRepo.save(merchant);
//				System.out.println(newMerchant);
//				MerchantLog mLog = new MerchantLog(newMerchant);
//				mLog.setId(newMerchant.getId());
//				System.out.println(mLog);
//				//System.out.println(merchantLogRepo.save(mLog));;
//				System.out.println(merchantLogRepo.save(mLog));;
//				return merchant;
//			}
//			else {
//				throw new CredentialNotFoundException();
//			}
//		}catch (Exception e) {
//			System.err.println(e);
//		}
//		return null;
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
