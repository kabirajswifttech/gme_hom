package com.gme.hom.merchants.representatives.services;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gme.hom.GlobalConfig;
import com.gme.hom.merchants.config.MerchantStatusCodes;
import com.gme.hom.merchants.representatives.model.MerchantsRepresentativeDetails;
import com.gme.hom.merchants.representatives.model.MerchantsRepresentativeDetailsLog;
import com.gme.hom.merchants.representatives.model.MerchantsRepresentativeDetailsRequest;
import com.gme.hom.merchants.representatives.repositories.MerchantsRepresentativeDetailsLogRepository;
import com.gme.hom.merchants.representatives.repositories.MerchantsRepresentativeDetailsRepository;
import com.gme.hom.security.services.ChecksumService;
import com.gme.hom.usersecurity.services.UserSecurityService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MerchantsRepresentativeDetailsServiceImpl implements MerchantsRepresentativeDetailsService{
	
	@Autowired
	MerchantsRepresentativeDetailsRepository representativeRepo;
	
	@Autowired
	MerchantsRepresentativeDetailsLogRepository representativeLogRepo;
	
	private static final Logger logger = LoggerFactory.getLogger(MerchantsRepresentativeDetailsServiceImpl.class);

	@Override
	public MerchantsRepresentativeDetails addMerchantRepresentativesDetails(MerchantsRepresentativeDetailsRequest representativeReq,
			Long merchantId) throws NoSuchAlgorithmException, IOException {
		MerchantsRepresentativeDetails representative = new MerchantsRepresentativeDetails(representativeReq);
		representative.setCreatedBy(UserSecurityService.getUsername());
		representative.setEntityHash(ChecksumService.getChecksum(representative, GlobalConfig.DATA_ENTITY_HASH));
		representative.setMerchantId(merchantId);
		representative.setIsActive(true);
		representative.setStatus(MerchantStatusCodes.PENDING);
		representative = representativeRepo.save(representative);
		MerchantsRepresentativeDetailsLog representativeLog = new MerchantsRepresentativeDetailsLog(representative);
		representativeLogRepo.save(representativeLog);
		return representative;
	}

	@Override
	@Transactional
	public MerchantsRepresentativeDetails save(MerchantsRepresentativeDetails representative) throws NoSuchAlgorithmException, IOException {
		representative.setCreatedBy(UserSecurityService.getUsername());
		representative.setEntityHash(ChecksumService.getChecksum(representative, GlobalConfig.DATA_ENTITY_HASH));
		representative.setIsActive(true);
		representative.setStatus(MerchantStatusCodes.PENDING);
		representativeRepo.save(representative);
		MerchantsRepresentativeDetailsLog representativeLog = new MerchantsRepresentativeDetailsLog(representative);
		representativeLog.setCreatedBy(UserSecurityService.getUsername());
		representativeLogRepo.save(representativeLog);
		return representative;
		
	}

	@Override
	public List<MerchantsRepresentativeDetailsDTO> getAll() {
		return representativeRepo.findAllRepresentatives();
	}

	@Override
	public Optional<MerchantsRepresentativeDetailsDTO> getById(long merchantsRepresentativeId) throws Exception {
		return representativeRepo.findRepresentativeById(merchantsRepresentativeId);
		
	}

	@Override
	public List<MerchantsRepresentativeDetailsDTO> getByMerchantId(long merchantId) throws Exception {
		return representativeRepo.findRepresentativesByMerchantId(merchantId);
		
	}

	@Override
	@Transactional
	public MerchantsRepresentativeDetails update(MerchantsRepresentativeDetails representative) {
		try {
			Optional<MerchantsRepresentativeDetails> dbRep = representativeRepo.findById(representative.getId());
			if(!dbRep.isEmpty()) {
				representative.setUpdatedBy(UserSecurityService.getUsername());
				representative.setCreatedBy(dbRep.get().getCreatedBy());
				representative.setEntityHash(dbRep.get().getEntityHash());
				representative.setIsActive(dbRep.get().getIsActive());
				representative.setStatus(dbRep.get().getStatus());
				representative = representativeRepo.save(representative);
				
				MerchantsRepresentativeDetailsLog representativeLog = new MerchantsRepresentativeDetailsLog(representative);				
				representativeLog.setCreatedBy(UserSecurityService.getUsername());
				//representativeLog.setCreatedDate(new Date(System.currentTimeMillis()));
				//representativeLog.setCreatedDateUTC(new Date(System.currentTimeMillis()));
				
				//logger.error("Rep: "+representativeLog.toString());
				representativeLogRepo.save(representativeLog);
				
				//logger.error("HERE");
				return representative;
			}
			else {
				throw new EntityNotFoundException("no such data found!");
			}
		}catch(Exception e) {
			var msg=e.getMessage();
			logger.error(msg);
			throw new EntityNotFoundException("no such data found!");
		}
	
		
	}

}
