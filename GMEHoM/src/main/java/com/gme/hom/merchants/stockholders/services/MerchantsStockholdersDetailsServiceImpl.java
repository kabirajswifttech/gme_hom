package com.gme.hom.merchants.stockholders.services;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gme.hom.GlobalConfig;
import com.gme.hom.merchants.config.MerchantStatusCodes;
import com.gme.hom.merchants.stockholders.model.MerchantsStockholdersDetails;
import com.gme.hom.merchants.stockholders.model.MerchantsStockholdersDetailsLog;
import com.gme.hom.merchants.stockholders.repositories.MerchantsStockHolderDetailsRepository;
import com.gme.hom.merchants.stockholders.repositories.MerchantsStockholderDetailsLogRepository;
import com.gme.hom.security.services.ChecksumService;
import com.gme.hom.usersecurity.services.UserSecurityService;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MerchantsStockholdersDetailsServiceImpl implements MerchantsStockholdersDetailsService{
	
	@Autowired
	private MerchantsStockHolderDetailsRepository stockholdersRepo;
	
	@Autowired
	private MerchantsStockholderDetailsLogRepository stockholdersLogRepo;
	
	@Override
	public MerchantsStockholdersDetails save(MerchantsStockholdersDetails stockholder) throws NoSuchAlgorithmException, IOException {
		stockholder.setCreatedBy(UserSecurityService.getUsername());
		stockholder.setEntityHash(ChecksumService.getChecksum(stockholder, GlobalConfig.DATA_ENTITY_HASH));
		stockholder.setIsActive(true);
		stockholder.setStatus(MerchantStatusCodes.PENDING);
		stockholder = stockholdersRepo.save(stockholder);
		MerchantsStockholdersDetailsLog stockholderLog = new MerchantsStockholdersDetailsLog(stockholder);
		stockholderLog.setCreatedBy(UserSecurityService.getUsername());
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
		Optional<MerchantsStockholdersDetails> dbStockholder = stockholdersRepo.findById(stockholder.getId());
		if(!dbStockholder.isEmpty() && dbStockholder.get().getMerchantId()==stockholder.getMerchantId()) {
			stockholder.setUpdatedBy(UserSecurityService.getUsername());
			stockholder.setIsActive(dbStockholder.get().getIsActive());
			stockholder.setEntityHash(dbStockholder.get().getEntityHash());
			stockholder.setStatus(dbStockholder.get().getStatus());
			stockholder.setCreatedBy(dbStockholder.get().getCreatedBy());
			stockholder = stockholdersRepo.save(stockholder);
			MerchantsStockholdersDetailsLog stockholderLog = new MerchantsStockholdersDetailsLog(stockholder);
			stockholderLog.setCreatedBy(UserSecurityService.getUsername());
			stockholdersLogRepo.save(stockholderLog);
			return stockholder;
		}
		else {
			throw new EntityNotFoundException("No such data found!");
		}
	}

}
