package com.gme.hom.kyc.bankDetails.services;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import com.gme.hom.kyc.bankDetails.model.MerchantsBankDetails;
import com.gme.hom.kyc.bankDetails.model.MerchantsBankDetailsDTO;
import com.gme.hom.kyc.bankDetails.model.MerchantsBankDetailsRequest;

import jakarta.servlet.http.HttpServletRequest;

public interface MerchantsBankDetailsService {

	MerchantsBankDetails addMerchantBankDetails(MerchantsBankDetailsRequest bankDetailsReq, Long merchantId);

	MerchantsBankDetails save(MerchantsBankDetails merchantBankDetails) throws Exception;

	List<MerchantsBankDetailsDTO>  getAll();

	MerchantsBankDetailsDTO getById(Long id) throws Exception;

	MerchantsBankDetailsDTO getByMerchantId(Long id) throws Exception;

	MerchantsBankDetails update(MerchantsBankDetailsRequest merchantBankDetailsReq);

	

}
