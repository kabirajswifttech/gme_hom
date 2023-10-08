package com.gme.hom.merchants.bankDetails.services;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.gme.hom.merchants.bankDetails.model.MerchantsBankDetails;
import com.gme.hom.merchants.bankDetails.model.MerchantsBankDetailsRequest;

public interface MerchantsBankDetailsService {

	MerchantsBankDetails addMerchantBankDetails(MerchantsBankDetailsRequest bankDetailsReq, Long merchantId) throws NoSuchAlgorithmException, IOException;

	MerchantsBankDetails save(MerchantsBankDetails merchantBankDetails) throws Exception;

	List<MerchantsBankDetailsDTO>  getAll();

	MerchantsBankDetailsDTO getById(Long id) throws Exception;

	MerchantsBankDetailsDTO getByMerchantId(Long id) throws Exception;

	MerchantsBankDetails update(MerchantsBankDetailsRequest merchantBankDetailsReq) throws Exception;

	

}
