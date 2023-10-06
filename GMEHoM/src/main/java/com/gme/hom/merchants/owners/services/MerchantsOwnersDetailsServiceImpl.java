package com.gme.hom.merchants.owners.services;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gme.hom.GlobalConfig;
import com.gme.hom.merchants.owners.model.MerchantsOwnersDetails;
import com.gme.hom.merchants.owners.model.MerchantsOwnersDetailsLog;
import com.gme.hom.merchants.owners.repositories.MerchantsOwnersDetailsLogRepository;
import com.gme.hom.merchants.owners.repositories.MerchantsOwnersDetailsRepository;
import com.gme.hom.security.services.ChecksumService;
import com.gme.hom.usersecurity.services.UserSecurityService;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class MerchantsOwnersDetailsServiceImpl implements MerchantsOwnersDetailsService {
	
	@Autowired
	private MerchantsOwnersDetailsRepository ownersRepo;
	
	@Autowired
	private MerchantsOwnersDetailsLogRepository ownersLogRepo;
	
	@Override
	public MerchantsOwnersDetails save(MerchantsOwnersDetails ownersDetails) throws NoSuchAlgorithmException, IOException {

		ownersDetails.setCreatedBy(UserSecurityService.getUsername());
		ownersDetails.setEntityHash(ChecksumService.getChecksum(ownersDetails, GlobalConfig.DATA_ENTITY_HASH));
		ownersDetails = ownersRepo.save(ownersDetails);
		MerchantsOwnersDetailsLog ownerLog = new MerchantsOwnersDetailsLog(ownersDetails);
		ownersLogRepo.save(ownerLog);
		return ownersDetails;
	}

	@Override
	public List<MerchantsOwnersDetailsDTO> getAll() {
		return ownersRepo.findAllOwners();
	}

	@Override
	public Optional<MerchantsOwnersDetailsDTO> getById(long id) {
		return ownersRepo.findOwnersById(id);
	}

	@Override
	public List<MerchantsOwnersDetailsDTO> getByMerchantId(long id) {
		return ownersRepo.findOwnersByMerchantId(id);
	}

	public MerchantsOwnersDetails update(MerchantsOwnersDetails ownersDetails) {
		//owner.setUpdatedBy(UserSecurityService.getUsername());
		ownersDetails = ownersRepo.save(ownersDetails);
		MerchantsOwnersDetailsLog ownerLog = new MerchantsOwnersDetailsLog(ownersDetails);
		ownersLogRepo.save(ownerLog);
		return ownersDetails;
	}

}
