package com.gme.hom.kyc.preferredServices.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.gme.hom.kyc.codes.MerchantStatusCodes;
import com.gme.hom.kyc.codes.ResponseMessageCodes;
import com.gme.hom.kyc.preferredServices.model.MerchantsServicePreference;
import com.gme.hom.kyc.preferredServices.model.MerchantsServicePreferenceDTO;
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
	public List<MerchantsServicePreferenceDTO> getAll() {
		return servicePreferenceRepo.findAllServices();
	}

	@Override
	public Optional<MerchantsServicePreferenceDTO> getById(Long merchantsServicePreferenceId) throws Exception {
		return servicePreferenceRepo.findServiceById(merchantsServicePreferenceId);
	}

	@Override
	public List<MerchantsServicePreferenceDTO> getByIdMerchantId(Long merchantId) throws Exception {
		return servicePreferenceRepo.findServiceByMerchantId(merchantId);	
	}

	@Override
	public MerchantsServicePreference update(MerchantsServicePreference servicePref)
			throws Exception {
		//servicePref.setUpdatedBy(UserSecurityService.getUsername());
		return servicePreferenceRepo.save(servicePref);
	}

	
	
	
	
}
