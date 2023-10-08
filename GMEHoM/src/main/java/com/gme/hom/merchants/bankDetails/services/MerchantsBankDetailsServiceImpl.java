package com.gme.hom.merchants.bankDetails.services;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.gme.hom.GlobalConfig;
import com.gme.hom.merchants.bankDetails.model.MerchantsBankDetails;
import com.gme.hom.merchants.bankDetails.model.MerchantsBankDetailsLog;
import com.gme.hom.merchants.bankDetails.model.MerchantsBankDetailsRequest;
import com.gme.hom.merchants.bankDetails.repositories.MerchantsBankDetailsLogRepository;
import com.gme.hom.merchants.bankDetails.repositories.MerchantsBankDetailsRepository;
import com.gme.hom.merchants.config.MerchantStatusCodes;
import com.gme.hom.merchants.config.ResponseMessageCodes;
import com.gme.hom.security.services.ChecksumService;
import com.gme.hom.usersecurity.services.UserSecurityService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MerchantsBankDetailsServiceImpl implements MerchantsBankDetailsService{
	
	@Autowired
	MerchantsBankDetailsRepository bankDetailsRepo;
	
	@Autowired
	MerchantsBankDetailsLogRepository bankDetailsLogRepo;
	
	private static final Logger logger = LoggerFactory.getLogger(MerchantsBankDetailsServiceImpl.class);
	
	@Override
	@Transactional
	public MerchantsBankDetails addMerchantBankDetails(MerchantsBankDetailsRequest bankDetailsReq, Long merchantId) throws NoSuchAlgorithmException, IOException {
		MerchantsBankDetails bankDetails = new MerchantsBankDetails(bankDetailsReq);
		bankDetails.setCreatedBy(UserSecurityService.getUsername());
		bankDetails.setEntityHash(ChecksumService.getChecksum(bankDetails, GlobalConfig.DATA_ENTITY_HASH));
		bankDetails.setMerchantId(merchantId);
		bankDetails.setIsActive(true);
		bankDetails.setIsVerified(true);
		bankDetails.setStatus(MerchantStatusCodes.PENDING);
		logger.error(bankDetails.toString());
		bankDetails = bankDetailsRepo.save(bankDetails);
		MerchantsBankDetailsLog bankDetaiilsLog = new MerchantsBankDetailsLog(bankDetails);
		bankDetaiilsLog.setId(bankDetails.getId());
		bankDetailsLogRepo.save(new MerchantsBankDetailsLog(bankDetails));
		return bankDetails;
	}

	@Override
	public MerchantsBankDetails save(MerchantsBankDetails merchantsBankDetails) throws Exception{
			if(getByMerchantId(merchantsBankDetails.getMerchantId()) == null) {
				merchantsBankDetails.setCreatedBy(UserSecurityService.getUsername());			//move to service
				merchantsBankDetails.setEntityHash(ChecksumService.getChecksum(merchantsBankDetails, GlobalConfig.DATA_ENTITY_HASH)); //move to service
				merchantsBankDetails.setIsActive(true);
				merchantsBankDetails.setStatus(MerchantStatusCodes.PENDING);
				merchantsBankDetails.setIsVerified(false);
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
	public MerchantsBankDetails update(MerchantsBankDetailsRequest merchantBankDetailsReq) throws Exception {
		//bankDetails.setUpdatedBy(UserSecurityService.getUsername());			//move to service
		try {
			MerchantsBankDetails bankDetails = new MerchantsBankDetails(merchantBankDetailsReq);
			MerchantsBankDetailsDTO bankDetailsInDB = getById(bankDetails.getId());
			bankDetails.setIsVerified(bankDetailsInDB.getIs_verified());
			bankDetails = bankDetailsRepo.save(bankDetails);
			MerchantsBankDetailsLog bankDetaiilsLog = new MerchantsBankDetailsLog(bankDetails);
			bankDetaiilsLog.setId(bankDetails.getId());
			bankDetailsLogRepo.save(new MerchantsBankDetailsLog(bankDetails));
			return bankDetails;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


}
