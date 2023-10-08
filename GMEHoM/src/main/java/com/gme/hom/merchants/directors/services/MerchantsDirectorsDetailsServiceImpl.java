package com.gme.hom.merchants.directors.services;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gme.hom.GlobalConfig;
import com.gme.hom.merchants.config.MerchantStatusCodes;
import com.gme.hom.merchants.directors.model.MerchantsDirectorsDetails;
import com.gme.hom.merchants.directors.model.MerchantsDirectorsDetailsLog;
import com.gme.hom.merchants.directors.model.MerchantsDirectorsDetailsRequest;
import com.gme.hom.merchants.directors.repositories.MerchantsDirectorsDetailsLogRepository;
import com.gme.hom.merchants.directors.repositories.MerchantsDirectorsDetailsRepository;
import com.gme.hom.security.services.ChecksumService;
import com.gme.hom.usersecurity.services.UserSecurityService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MerchantsDirectorsDetailsServiceImpl implements MerchantsDirectorsDetailsService{
	
	private static final Logger logger = LoggerFactory.getLogger(MerchantsDirectorsDetailsServiceImpl.class); 
	
	@Autowired
	private MerchantsDirectorsDetailsRepository directorsRepo;
	
	@Autowired
	private MerchantsDirectorsDetailsLogRepository directorsLogRepo;
	
	@Override
	public MerchantsDirectorsDetails addMerchantDirectorsDetails(MerchantsDirectorsDetailsRequest directorsReq, Long merchantId) {
		MerchantsDirectorsDetails director = new MerchantsDirectorsDetails(directorsReq);
		director.setMerchantId(merchantId);
		director.setIsActive(true);
		director.setIsVerified(false);
		director.setStatus(MerchantStatusCodes.PENDING);
		director = directorsRepo.save(director);
		MerchantsDirectorsDetailsLog directorsLog = new MerchantsDirectorsDetailsLog(director);
		directorsLog.setCreatedBy(UserSecurityService.getUsername());
		directorsLogRepo.save(directorsLog);
		return director;
		
	}
	@Override
	public MerchantsDirectorsDetails save(MerchantsDirectorsDetails directorsDetails) throws NoSuchAlgorithmException, IOException {
		directorsDetails.setCreatedBy(UserSecurityService.getUsername());
		directorsDetails.setEntityHash(ChecksumService.getChecksum(directorsDetails, GlobalConfig.DATA_ENTITY_HASH));
		directorsDetails.setIsActive(true);
		directorsDetails.setStatus(MerchantStatusCodes.PENDING);
		directorsDetails = directorsRepo.save(directorsDetails);
		MerchantsDirectorsDetailsLog directorsLog = new MerchantsDirectorsDetailsLog(directorsDetails);
		directorsLog.setCreatedBy(UserSecurityService.getUsername());
		directorsLogRepo.save(directorsLog);
		return directorsDetails;
	}
	@Override
	public List<MerchantsDirectorsDetailsDTO> getAll() {
		return directorsRepo.findAllDirectors();
	}
	@Override
	public Optional<MerchantsDirectorsDetailsDTO> getById(Long id) {
		return directorsRepo.findByDirectorsId(id);
	}
	@Override
	public List<MerchantsDirectorsDetailsDTO> getByMerchantId(Long id) {
		return directorsRepo.findByMerchantId(id);
	}
	@Override
	public MerchantsDirectorsDetails update(MerchantsDirectorsDetails directorsDetails) {
		//merchantsDirectorsDetails.setCreatedBy(UserSecurityService.getUsername());
		Optional<MerchantsDirectorsDetailsDTO> directorInDB = getById(directorsDetails.getId());
		logger.error(directorInDB.get().getStatus());
		//directorsDetails.setUpdatedBy(UserSecurityService.getUsername());
		directorsDetails.setIsActive(directorInDB.get().getIs_active());
		logger.error(directorInDB.get().getStatus().getClass().toString());
		directorsDetails.setStatus(MerchantStatusCodes.valueOf(directorInDB.get().getStatus()));
		directorsDetails = directorsRepo.save(directorsDetails);
		MerchantsDirectorsDetailsLog directorsLog = new MerchantsDirectorsDetailsLog(directorsDetails);
		directorsLog.setCreatedBy(UserSecurityService.getUsername());
		directorsLog.setId(directorsDetails.getId());
		directorsLogRepo.save(directorsLog);
		return directorsDetails;
	}

}
