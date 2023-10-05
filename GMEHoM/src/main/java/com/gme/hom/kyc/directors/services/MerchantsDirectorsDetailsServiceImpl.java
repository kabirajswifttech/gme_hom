package com.gme.hom.kyc.directors.services;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gme.hom.GlobalConfig;
import com.gme.hom.kyc.codes.MerchantStatusCodes;
import com.gme.hom.kyc.directors.model.MerchantsDirectorsDetails;
import com.gme.hom.kyc.directors.model.MerchantsDirectorsDetailsDTO;
import com.gme.hom.kyc.directors.model.MerchantsDirectorsDetailsRequest;
import com.gme.hom.kyc.directors.repositories.MerchantsDirectorsDetailsRepository;
import com.gme.hom.security.services.ChecksumService;
import com.gme.hom.usersecurity.services.UserSecurityService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MerchantsDirectorsDetailsServiceImpl implements MerchantsDirectorsDetailsService{
	
	private MerchantsDirectorsDetailsRepository directorsRepo;
	@Override
	public MerchantsDirectorsDetails addMerchantDirectorsDetails(MerchantsDirectorsDetailsRequest directorsReq, Long merchantId) {
		MerchantsDirectorsDetails director = new MerchantsDirectorsDetails(directorsReq);
		director.setMerchantId(merchantId);
		director.setActive(true);
		director.setStatus(MerchantStatusCodes.PENDING.toString());
		return directorsRepo.save(director);
	}
	@Override
	public MerchantsDirectorsDetails save(MerchantsDirectorsDetails directorsDetails) throws NoSuchAlgorithmException, IOException {
		directorsDetails.setCreatedBy(UserSecurityService.getUsername());
		directorsDetails.setEntityHash(ChecksumService.getChecksum(directorsDetails, GlobalConfig.DATA_ENTITY_HASH));
		directorsDetails.setActive(true);
		directorsDetails.setStatus(MerchantStatusCodes.PENDING.toString());
		return directorsRepo.save(directorsDetails);
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
	public MerchantsDirectorsDetails update(MerchantsDirectorsDetails merchantsDirectorsDetails) {
		//merchantsDirectorsDetails.setCreatedBy(UserSecurityService.getUsername());
		return directorsRepo.save(merchantsDirectorsDetails);
	}

}
