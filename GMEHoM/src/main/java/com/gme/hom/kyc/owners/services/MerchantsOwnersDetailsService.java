package com.gme.hom.kyc.owners.services;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import com.gme.hom.kyc.owners.model.MerchantsOwnersDetails;

public interface MerchantsOwnersDetailsService {
	MerchantsOwnersDetails save(MerchantsOwnersDetails ownersDetails) throws NoSuchAlgorithmException, IOException;

	List<MerchantsOwnersDetails> getAll();

	Optional<MerchantsOwnersDetails> getById(long id);

	List<MerchantsOwnersDetails> getByMerchantId(long parseLong);

	MerchantsOwnersDetails update(MerchantsOwnersDetails owner);
}
