package com.gme.hom.kyc.directors.services;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import com.gme.hom.kyc.bankDetails.model.MerchantsBankDetailsRequest;
import com.gme.hom.kyc.directors.model.MerchantsDirectorsDetails;
import com.gme.hom.kyc.directors.model.MerchantsDirectorsDetailsRequest;
import com.gme.hom.kyc.representatives.model.MerchantsRepresentativeDetails;
import com.gme.hom.kyc.representatives.model.MerchantsRepresentativeDetailsRequest;

public interface MerchantsDirectorsDetailsService {

	MerchantsDirectorsDetails addMerchantDirectorsDetails(MerchantsDirectorsDetailsRequest directorsReq, Long merchantId);

	MerchantsDirectorsDetails save(MerchantsDirectorsDetails merchantDirectorsDetails) throws NoSuchAlgorithmException, IOException;

	List<MerchantsDirectorsDetailsDTO> getAll();

	Optional<MerchantsDirectorsDetailsDTO> getById(Long id);

	List<MerchantsDirectorsDetailsDTO> getByMerchantId(Long id);

	MerchantsDirectorsDetails update(MerchantsDirectorsDetails merchantsDirectorsDetails);

		

	

}
