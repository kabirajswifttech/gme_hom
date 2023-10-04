package com.gme.hom.kyc.preferredServices.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.gme.hom.kyc.codes.MerchantStatusCodes;
import com.gme.hom.kyc.codes.ResponseMessageCodes;
import com.gme.hom.kyc.preferredServices.model.MerchantsServicePreference;
import com.gme.hom.kyc.preferredServices.repositories.MerchantsServicePreferenceRepository;
import com.gme.hom.usersecurity.services.UserSecurityService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MerchantsServicePreferenceServiceImpl implements MerchantsServicePreferenceService {
	
	private MerchantsServicePreferenceRepository servicePreferenceRepo;
	
	private static final Logger logger = LoggerFactory.getLogger(MerchantsServicePreferenceServiceImpl.class);
	
	@Override
	public MerchantsServicePreference save(MerchantsServicePreference servicePref) {
		servicePref.setActive(true);
		servicePref.setStatus(MerchantStatusCodes.PENDING.toString());
		return servicePreferenceRepo.save(servicePref);
	}

	@Override
	public List<MerchantsServicePreference> getAll() {
		return servicePreferenceRepo.findAll();
	}

	@Override
	public MerchantsServicePreference getById(Long merchantsServicePreferenceId) throws Exception {
		Optional<MerchantsServicePreference> servicePref = servicePreferenceRepo.findById(merchantsServicePreferenceId);
		if(servicePref.isEmpty()) {
			throw new Exception(ResponseMessageCodes.NO_RESULTS_FOUND_FOR_YOUR_SEARCH_QUERY.toString());
		}else {
			return servicePref.get();
		}
	}

	@Override
	public List<MerchantsServicePreference> getByIdMerchantId(Long merchantId) throws Exception {
		return servicePreferenceRepo.findByMerchantId(merchantId);	
	}

	@Override
	public MerchantsServicePreference update(MerchantsServicePreference servicePref)
			throws Exception {
		servicePref.setCreatedBy(UserSecurityService.getUsername());
		return servicePreferenceRepo.save(servicePref);
	}

	
	
	
	
}
