package com.gme.hom.merchants.representatives.services;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

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

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MerchantsRepresentativeDetailsServiceImpl implements MerchantsRepresentativeDetailsService{
	
	@Autowired
	MerchantsRepresentativeDetailsRepository representativeRepo;
	
	@Autowired
	MerchantsRepresentativeDetailsLogRepository representativeLogRepo;

	@Override
	public MerchantsRepresentativeDetails addMerchantRepresentativesDetails(MerchantsRepresentativeDetailsRequest representativeReq,
			Long merchantId) throws NoSuchAlgorithmException, IOException {
		MerchantsRepresentativeDetails representative = new MerchantsRepresentativeDetails(representativeReq);
		representative.setCreatedBy(UserSecurityService.getUsername());
		representative.setEntityHash(ChecksumService.getChecksum(representative, GlobalConfig.DATA_ENTITY_HASH));
		representative.setMerchantId(merchantId);
		representative.setActive(true);
		representative.setStatus(MerchantStatusCodes.PENDING);
		representative = representativeRepo.save(representative);
		MerchantsRepresentativeDetailsLog representativeLog = new MerchantsRepresentativeDetailsLog(representative);
		representativeLogRepo.save(representativeLog);
		return representative;
	}

	@Override
	public MerchantsRepresentativeDetails save(MerchantsRepresentativeDetails representative) {
		representative.setActive(true);
		representative.setStatus(MerchantStatusCodes.PENDING);
		return representativeRepo.save(representative);
		
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
	public MerchantsRepresentativeDetails update(MerchantsRepresentativeDetails representative) {
		//representativeDetails.setUpdatedBy(UserSecurityService.getUsername());
		representative = representativeRepo.save(representative);
		MerchantsRepresentativeDetailsLog representativeLog = new MerchantsRepresentativeDetailsLog(representative);
		representativeLogRepo.save(representativeLog);
		return representative;
	}

}
