package com.gme.hom.kyc.representatives.services;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import com.gme.hom.kyc.representatives.model.MerchantsRepresentativeDetails;
import com.gme.hom.kyc.representatives.model.MerchantsRepresentativeDetailsRequest;


public interface MerchantsRepresentativeDetailsService {

	MerchantsRepresentativeDetails addMerchantRepresentativesDetails(
			MerchantsRepresentativeDetailsRequest representativeReq, Long merchantId) throws NoSuchAlgorithmException, IOException;

	MerchantsRepresentativeDetails save(MerchantsRepresentativeDetails representative);

	List<MerchantsRepresentativeDetailsDTO> getAll();

	Optional<MerchantsRepresentativeDetailsDTO> getById(long merchantsRepresentativeId) throws Exception;

	List<MerchantsRepresentativeDetailsDTO> getByMerchantId(long parseLong) throws Exception;

	MerchantsRepresentativeDetails update(MerchantsRepresentativeDetails representativeDetails);

	
}
