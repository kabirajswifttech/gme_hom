package com.gme.hom.merchants.stockholders.services;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import com.gme.hom.merchants.stockholders.model.MerchantsStockholdersDetails;

public interface MerchantsStockholdersDetailsService {

	MerchantsStockholdersDetails save(MerchantsStockholdersDetails stockholdersDetails) throws NoSuchAlgorithmException, IOException;

	List<MerchantsStockholdersDetailsDTO> getAll();

	Optional<MerchantsStockholdersDetailsDTO> getById(long parseLong);

	List<MerchantsStockholdersDetailsDTO> getByMerchantId(long parseLong);

	MerchantsStockholdersDetails update(MerchantsStockholdersDetails merchantsStockholdersDetails);

}
