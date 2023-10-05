package com.gme.hom.kyc.owners.services;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import com.gme.hom.kyc.owners.model.MerchantsOwnersDetails;

public interface MerchantsOwnersDetailsService {
	MerchantsOwnersDetails save(MerchantsOwnersDetails ownersDetails) throws NoSuchAlgorithmException, IOException;

	List<MerchantsOwnersDetailsDTO> getAll();

	Optional<MerchantsOwnersDetailsDTO> getById(long id);

	List<MerchantsOwnersDetailsDTO> getByMerchantId(long parseLong);

	MerchantsOwnersDetails update(MerchantsOwnersDetails owner);
}
