package com.gme.hom.kyc.preferredServices.services;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.gme.hom.kyc.preferredServices.model.MerchantsServicePreference;
import com.gme.hom.kyc.preferredServices.model.MerchantsServicePreferenceRequest;

public interface MerchantsServicePreferenceService {

	public List<MerchantsServicePreference> getAll();

	public MerchantsServicePreference getById(Long merchantsServicePreferenceId) throws Exception;

	public List<MerchantsServicePreference> getByIdMerchantId(Long merchantId)throws Exception;

	public MerchantsServicePreference update(MerchantsServicePreference merchantServicePref) throws Exception;

	MerchantsServicePreference save(MerchantsServicePreference servicePref);
	
}
