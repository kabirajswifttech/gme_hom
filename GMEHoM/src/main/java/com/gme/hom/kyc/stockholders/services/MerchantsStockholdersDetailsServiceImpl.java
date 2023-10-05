package com.gme.hom.kyc.stockholders.services;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gme.hom.GlobalConfig;
import com.gme.hom.kyc.codes.MerchantStatusCodes;
import com.gme.hom.kyc.stockholders.model.MerchantsStockholdersDetails;
import com.gme.hom.kyc.stockholders.model.MerchantsStockholdersDetailsDTO;
import com.gme.hom.kyc.stockholders.model.MerchantsStockholdersDetailsLog;
import com.gme.hom.kyc.stockholders.repositories.MerchantsStockHolderDetailsRepository;
import com.gme.hom.kyc.stockholders.repositories.MerchantsStockholderDetailsLogRepository;
import com.gme.hom.security.services.ChecksumService;
import com.gme.hom.usersecurity.services.UserSecurityService;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@AllArgsConstructor
public class MerchantsStockholdersDetailsServiceImpl implements MerchantsStockholdersDetailsService{
	
	private MerchantsStockHolderDetailsRepository stockholdersRepo;
	private MerchantsStockholderDetailsLogRepository stockholdersLogRepo;
	
	@Override
	public MerchantsStockholdersDetails save(MerchantsStockholdersDetails stockholder) throws NoSuchAlgorithmException, IOException {
		stockholder.setCreatedBy(UserSecurityService.getUsername());
		stockholder.setEntityHash(ChecksumService.getChecksum(stockholder, GlobalConfig.DATA_ENTITY_HASH));
		stockholder.setActive(true);
		stockholder.setStatus(MerchantStatusCodes.PENDING.toString());
		stockholder = stockholdersRepo.save(stockholder);
		MerchantsStockholdersDetailsLog stockholderLog = new MerchantsStockholdersDetailsLog(stockholder);
		stockholdersLogRepo.save(stockholderLog);
		return stockholder;
	}

	@Override
	public List<MerchantsStockholdersDetailsDTO> getAll() {
		return stockholdersRepo.findAllStockholders();
	}

	@Override
	public Optional<MerchantsStockholdersDetailsDTO> getById(long id) {
		return stockholdersRepo.findStockholderById(id);
	}

	@Override
	public List<MerchantsStockholdersDetailsDTO> getByMerchantId(long id) {
		return stockholdersRepo.findStockholderByMerchantId(id);
	}

	@Override
	public MerchantsStockholdersDetails update(MerchantsStockholdersDetails stockholder) {
		//merchantsStockholdersDetails.setUpdatedBy(UserSecurityService.getUsername());
		stockholder = stockholdersRepo.save(stockholder);
		MerchantsStockholdersDetailsLog stockholderLog = new MerchantsStockholdersDetailsLog(stockholder);
		stockholdersLogRepo.save(stockholderLog);
		return stockholder;
	}

}
