package com.gme.hom.kyc.owners.services;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import com.gme.hom.GlobalConfig;
import com.gme.hom.kyc.merchants.model.Merchant;
import com.gme.hom.kyc.owners.model.MerchantsOwnersDetails;
import com.gme.hom.kyc.owners.repositories.MerchantsOwnersDetailsRepository;
import com.gme.hom.security.services.ChecksumService;
import com.gme.hom.usersecurity.services.UserSecurityService;

public class MerchantsOwnersDetailsServiceImpl implements MerchantsOwnerDetailsService {
	private MerchantsOwnersDetailsRepository ownersRepo;
	@Override
	public MerchantsOwnersDetails save(MerchantsOwnersDetails ownersDetails) throws NoSuchAlgorithmException, IOException {

		ownersDetails.setCreatedBy(UserSecurityService.getUsername());
		ownersDetails.setEntityHash(ChecksumService.getChecksum(ownersDetails, GlobalConfig.DATA_ENTITY_HASH));
		return ownersRepo.save(ownersDetails);
	}

	@Override
	public List<MerchantsOwnersDetails> getAll() {
		return ownersRepo.findAll();
	}

	@Override
	public Optional<MerchantsOwnersDetails> getById(long id) {
		return ownersRepo.findById(id);
	}

	@Override
	public List<MerchantsOwnersDetails> getByMerchantId(long id) {
		return ownersRepo.findByMerchantId(id);
	}

	public MerchantsOwnersDetails update(MerchantsOwnersDetails owner) {
		owner.setUpdatedBy(UserSecurityService.getUsername());
		return ownersRepo.save(owner);
	}

}
