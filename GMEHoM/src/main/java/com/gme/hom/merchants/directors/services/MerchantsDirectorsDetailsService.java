package com.gme.hom.merchants.directors.services;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import com.gme.hom.merchants.directors.model.MerchantsDirectorsDetails;
import com.gme.hom.merchants.directors.model.MerchantsDirectorsDetailsRequest;

public interface MerchantsDirectorsDetailsService {

	MerchantsDirectorsDetails addMerchantDirectorsDetails(MerchantsDirectorsDetailsRequest directorsReq, Long merchantId);

	MerchantsDirectorsDetails save(MerchantsDirectorsDetails merchantDirectorsDetails) throws NoSuchAlgorithmException, IOException;

	List<MerchantsDirectorsDetailsDTO> getAll();

	Optional<MerchantsDirectorsDetailsDTO> getById(Long id);

	List<MerchantsDirectorsDetailsDTO> getByMerchantId(Long id);

	MerchantsDirectorsDetails update(MerchantsDirectorsDetails merchantsDirectorsDetails);

		

	

}
