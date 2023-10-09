package com.gme.hom.merchants.preferredServices.services;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gme.hom.GlobalConfig;
import com.gme.hom.merchants.config.MerchantStatusCodes;
import com.gme.hom.merchants.preferredServices.model.MerchantsServicePreference;
import com.gme.hom.merchants.preferredServices.model.MerchantsServicePreferenceLog;
import com.gme.hom.merchants.preferredServices.repositories.MerchantsServicePreferenceLogRepository;
import com.gme.hom.merchants.preferredServices.repositories.MerchantsServicePreferenceRepository;
import com.gme.hom.security.services.ChecksumService;
import com.gme.hom.usersecurity.services.UserSecurityService;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MerchantsServicePreferenceServiceImpl implements MerchantsServicePreferenceService {
	
	@Autowired
	MerchantsServicePreferenceRepository servicePreferenceRepo;
	
	@Autowired
	MerchantsServicePreferenceLogRepository servicePreferenceLogRepo;
	
	private static final Logger logger = LoggerFactory.getLogger(MerchantsServicePreferenceServiceImpl.class);
	
	@Override
	public MerchantsServicePreference save(MerchantsServicePreference servicePref) throws NoSuchAlgorithmException, IOException {
		servicePref.setCreatedBy(UserSecurityService.getUsername());
		servicePref.setEntityHash(ChecksumService.getChecksum(servicePref, GlobalConfig.DATA_ENTITY_HASH));
		servicePref.setIsActive(true);
		servicePref.setStatus(MerchantStatusCodes.PENDING);
		servicePref = servicePreferenceRepo.save(servicePref);
		MerchantsServicePreferenceLog serviceLog = new MerchantsServicePreferenceLog(servicePref);
		serviceLog.setCreatedBy(UserSecurityService.getUsername());
		servicePreferenceLogRepo.save(serviceLog);
		return servicePref;
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
		Optional<MerchantsServicePreference> dbService = servicePreferenceRepo.findById(servicePref.getId());
		if(!dbService.isEmpty() && dbService.get().getMerchantId()==servicePref.getMerchantId()) {
			servicePref.setUpdatedBy(UserSecurityService.getUsername());
			servicePref.setIsActive(dbService.get().getIsActive());
			servicePref.setCreatedBy(dbService.get().getCreatedBy());
			servicePref.setStatus(dbService.get().getStatus());
			servicePref = servicePreferenceRepo.save(servicePref);
			MerchantsServicePreferenceLog serviceLog = new MerchantsServicePreferenceLog(servicePref);
			serviceLog.setCreatedBy(UserSecurityService.getUsername());
			servicePreferenceLogRepo.save(serviceLog);
			return servicePref;
		}else {
			throw new EntityNotFoundException("No such entity found!");
		}
	}

	
	
	
	
}
