package com.gme.hom.kyc.representatives.services;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.gme.hom.kyc.representatives.model.MerchantsRepresentativeDetails;
import com.gme.hom.kyc.representatives.model.MerchantsRepresentativeDetailsRequest;


public interface MerchantsRepresentativeDetailsService {

	MerchantsRepresentativeDetails addMerchantRepresentativesDetails(
			MerchantsRepresentativeDetailsRequest representativeReq, Long merchantId) throws NoSuchAlgorithmException, IOException;

	MerchantsRepresentativeDetails save(MerchantsRepresentativeDetails representative);

	List<MerchantsRepresentativeDetails> getAll();

	MerchantsRepresentativeDetails getById(long merchantsRepresentativeId) throws Exception;

	List<MerchantsRepresentativeDetails> getByMerchantId(long parseLong) throws Exception;

	MerchantsRepresentativeDetails update(MerchantsRepresentativeDetails representativeDetails);

	
}
