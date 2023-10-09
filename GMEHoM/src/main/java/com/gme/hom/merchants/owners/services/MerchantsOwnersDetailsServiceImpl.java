package com.gme.hom.merchants.owners.services;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gme.hom.GlobalConfig;
import com.gme.hom.merchants.config.MerchantStatusCodes;
import com.gme.hom.merchants.owners.model.MerchantsOwnersDetails;
import com.gme.hom.merchants.owners.model.MerchantsOwnersDetailsLog;
import com.gme.hom.merchants.owners.repositories.MerchantsOwnersDetailsLogRepository;
import com.gme.hom.merchants.owners.repositories.MerchantsOwnersDetailsRepository;
import com.gme.hom.security.services.ChecksumService;
import com.gme.hom.usersecurity.services.UserSecurityService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class MerchantsOwnersDetailsServiceImpl implements MerchantsOwnersDetailsService {
	
	@Autowired
	private MerchantsOwnersDetailsRepository ownersRepo;
	
	@Autowired
	private MerchantsOwnersDetailsLogRepository ownersLogRepo;
	
	@Override
	@Transactional
	public MerchantsOwnersDetails save(MerchantsOwnersDetails ownersDetails) throws NoSuchAlgorithmException, IOException {

		ownersDetails.setCreatedBy(UserSecurityService.getUsername());
		ownersDetails.setEntityHash(ChecksumService.getChecksum(ownersDetails, GlobalConfig.DATA_ENTITY_HASH));
		ownersDetails.setStatus(MerchantStatusCodes.PENDING);
		ownersDetails = ownersRepo.save(ownersDetails);
		MerchantsOwnersDetailsLog ownerLog = new MerchantsOwnersDetailsLog(ownersDetails);
		ownerLog.setCreatedBy(UserSecurityService.getUsername());
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

	@Transactional
	@Override
	public MerchantsOwnersDetails update(MerchantsOwnersDetails ownersDetails) {
		Optional<MerchantsOwnersDetails> dbOwner = ownersRepo.findById(ownersDetails.getId());
		if(!dbOwner.isEmpty() && dbOwner.get().getMerchantId()==ownersDetails.getMerchantId()) {
			ownersDetails.setUpdatedBy(UserSecurityService.getUsername());
			ownersDetails.setStatus(dbOwner.get().getStatus());
			ownersDetails.setIsActive(dbOwner.get().getIsActive());
			ownersDetails.setCreatedBy(dbOwner.get().getCreatedBy());
			ownersDetails = ownersRepo.save(ownersDetails);
			MerchantsOwnersDetailsLog ownerLog = new MerchantsOwnersDetailsLog(ownersDetails);
			ownerLog.setCreatedBy(UserSecurityService.getUsername());
			ownersLogRepo.save(ownerLog);
			return ownersDetails;
		}
		else {
			throw new EntityNotFoundException("No such entity found!");
		}
	}

}
