package com.gme.hom.kyc.stockholders.services;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gme.hom.GlobalConfig;
import com.gme.hom.kyc.codes.MerchantStatusCodes;
import com.gme.hom.kyc.stockholders.model.MerchantsStockholdersDetails;
import com.gme.hom.kyc.stockholders.repositories.MerchantsStockHolderDetailsRepository;
import com.gme.hom.security.services.ChecksumService;
import com.gme.hom.usersecurity.services.UserSecurityService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MerchantsStockholdersDetailsServiceImpl implements MerchantsStockholdersDetailsService{
	
	private MerchantsStockHolderDetailsRepository stockholdersRepo;
	
	@Override
	public MerchantsStockholdersDetails save(MerchantsStockholdersDetails stockholder) throws NoSuchAlgorithmException, IOException {
		stockholder.setCreatedBy(UserSecurityService.getUsername());
		stockholder.setEntityHash(ChecksumService.getChecksum(stockholder, GlobalConfig.DATA_ENTITY_HASH));
		stockholder.setActive(true);
		stockholder.setStatus(MerchantStatusCodes.PENDING.toString());
		return stockholdersRepo.save(stockholder);
	}

	@Override
	public List<MerchantsStockholdersDetails> getAll() {
		return stockholdersRepo.findAll();
	}

	@Override
	public Optional<MerchantsStockholdersDetails> getById(long id) {
		return stockholdersRepo.findById(id);
	}

	@Override
	public List<MerchantsStockholdersDetails> getByMerchantId(long id) {
		return stockholdersRepo.findByMerchantId(id);
	}

	@Override
	public MerchantsStockholdersDetails update(MerchantsStockholdersDetails merchantsStockholdersDetails) {
		merchantsStockholdersDetails.setUpdatedBy(UserSecurityService.getUsername());
		return stockholdersRepo.save(merchantsStockholdersDetails);
	}

}