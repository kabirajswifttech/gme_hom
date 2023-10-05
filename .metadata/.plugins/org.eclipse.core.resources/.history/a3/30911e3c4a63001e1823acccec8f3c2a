package com.gme.hom.kyc.stockholders.services;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import com.gme.hom.kyc.stockholders.model.MerchantsStockholdersDetails;

public interface MerchantsStockholdersDetailsService {

	MerchantsStockholdersDetails save(MerchantsStockholdersDetails stockholdersDetails) throws NoSuchAlgorithmException, IOException;

	List<MerchantsStockholdersDetails> getAll();

	Optional<MerchantsStockholdersDetails> getById(long parseLong);

	List<MerchantsStockholdersDetails> getByMerchantId(long parseLong);

	MerchantsStockholdersDetails update(MerchantsStockholdersDetails merchantsStockholdersDetails);

}
