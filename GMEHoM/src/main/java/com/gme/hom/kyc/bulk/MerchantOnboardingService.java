package com.gme.hom.kyc.bulk;

import com.gme.hom.api.models.APIData;

public interface MerchantOnboardingService {

	String onBoard(APIData data, Long id) throws Exception;
	
	
	
}
