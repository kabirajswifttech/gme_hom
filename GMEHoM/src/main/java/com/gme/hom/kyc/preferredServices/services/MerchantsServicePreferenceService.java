package com.gme.hom.kyc.preferredServices.services;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import com.gme.hom.kyc.preferredServices.model.MerchantsServicePreference;

public interface MerchantsServicePreferenceService {

	public List<MerchantsServicePreferenceDTO> getAll();

	public Optional<MerchantsServicePreferenceDTO> getById(Long merchantsServicePreferenceId) throws Exception;

	public List<MerchantsServicePreferenceDTO> getByIdMerchantId(Long merchantId)throws Exception;

	public MerchantsServicePreference update(MerchantsServicePreference merchantServicePref) throws Exception;

	MerchantsServicePreference save(MerchantsServicePreference servicePref) throws NoSuchAlgorithmException, IOException;
	
}
