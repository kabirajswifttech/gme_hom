package com.gme.hom.kyc.bankDetails.services;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.gme.hom.GlobalConfig;
import com.gme.hom.kyc.bankDetails.model.MerchantsBankDetails;
import com.gme.hom.kyc.bankDetails.model.MerchantsBankDetailsDTO;
import com.gme.hom.kyc.bankDetails.model.MerchantsBankDetailsLog;
import com.gme.hom.kyc.bankDetails.model.MerchantsBankDetailsRequest;
import com.gme.hom.kyc.bankDetails.repositories.MerchantsBankDetailsLogRepository;
import com.gme.hom.kyc.bankDetails.repositories.MerchantsBankDetailsRepository;
import com.gme.hom.kyc.codes.MerchantStatusCodes;
import com.gme.hom.kyc.codes.ResponseMessageCodes;
import com.gme.hom.security.services.ChecksumService;
import com.gme.hom.usersecurity.services.UserSecurityService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MerchantsBankDetailsServiceImpl implements MerchantsBankDetailsService{
	
	MerchantsBankDetailsRepository bankDetailsRepo;
	MerchantsBankDetailsLogRepository bankDetailsLogRepo;
	
	private static final Logger logger = LoggerFactory.getLogger(MerchantsBankDetailsServiceImpl.class);
	
	@Override
	@Transactional
	public MerchantsBankDetails addMerchantBankDetails(MerchantsBankDetailsRequest bankDetailsReq, Long merchantId) throws NoSuchAlgorithmException, IOException {
		MerchantsBankDetails bankDetails = new MerchantsBankDetails(bankDetailsReq);
		bankDetails.setCreatedBy(UserSecurityService.getUsername());
		bankDetails.setEntityHash(ChecksumService.getChecksum(bankDetails, GlobalConfig.DATA_ENTITY_HASH));
		bankDetails.setMerchantId(merchantId);
		bankDetails.setActive(true);
		bankDetails.setStatus(MerchantStatusCodes.PENDING.toString());
		bankDetails = bankDetailsRepo.save(bankDetails);
		MerchantsBankDetailsLog bankDetaiilsLog = new MerchantsBankDetailsLog(bankDetails);
		bankDetaiilsLog.setId(bankDetails.getId());
		bankDetailsLogRepo.save(new MerchantsBankDetailsLog(bankDetails));
		return bankDetails;
	}

	@Override
	public MerchantsBankDetails save(MerchantsBankDetails merchantsBankDetails) throws Exception{
			if(getByMerchantId(merchantsBankDetails.getMerchantId()) == null) {
				//merchantsBankDetails.setCreatedBy(UserSecurityService.getUsername());			//move to service
				//merchantsBankDetails.setEntityHash(ChecksumService.getChecksum(merchantsBankDetails, GlobalConfig.DATA_ENTITY_HASH)); //move to service
				merchantsBankDetails.setActive(true);
				merchantsBankDetails.setStatus(MerchantStatusCodes.PENDING.toString());
				return bankDetailsRepo.save(merchantsBankDetails);
			}
			throw new DuplicateKeyException("Merchnats bank details already set!");
	}

	@Override
	public List<MerchantsBankDetailsDTO> getAll() {
		return bankDetailsRepo.findAllBankDetails();
	}

	@Override
	public MerchantsBankDetailsDTO getById(Long id) throws Exception {
		MerchantsBankDetailsDTO bankDetails = bankDetailsRepo.findByBankDetailsId(id);
		if(bankDetails == null) {
			throw new Exception(ResponseMessageCodes.NO_RESULTS_FOUND_FOR_YOUR_SEARCH_QUERY.toString());
		}
		return bankDetails;
	}

	@Override
	public MerchantsBankDetailsDTO getByMerchantId(Long id) throws Exception {
		MerchantsBankDetailsDTO bankDetails = bankDetailsRepo.findByMerchantId(id);
		return bankDetails;
	}

	@Override
	public MerchantsBankDetails update(MerchantsBankDetailsRequest merchantBankDetailsReq) {
		MerchantsBankDetails bankDetails = new MerchantsBankDetails(merchantBankDetailsReq);
		//merchantsBankDetails.setUpdatedBy(UserSecurityService.getUsername());			//move to service
		bankDetails = bankDetailsRepo.save(bankDetails);
		MerchantsBankDetailsLog bankDetaiilsLog = new MerchantsBankDetailsLog(bankDetails);
		bankDetaiilsLog.setId(bankDetails.getId());
		bankDetailsLogRepo.save(new MerchantsBankDetailsLog(bankDetails));
		return bankDetails;
	}


}
