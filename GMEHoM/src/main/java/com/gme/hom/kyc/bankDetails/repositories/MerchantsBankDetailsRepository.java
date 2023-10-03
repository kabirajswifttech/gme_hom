package com.gme.hom.kyc.bankDetails.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gme.hom.kyc.bankDetails.model.MerchantsBankDetails;
import com.gme.hom.kyc.bankDetails.model.MerchantsBankDetailsDTO;

public interface MerchantsBankDetailsRepository extends JpaRepository<MerchantsBankDetails, Long> {
	@Query(value="SELECT m.merchant_id, m.bank_id, m.account_name, m.account_number, m.swift_code, m.bic_code FROM merchants_bank_details m WHERE m.merchant_id=?1", nativeQuery = true)
	MerchantsBankDetailsDTO findByMerchantId(Long id);
	
	@Query(value="SELECT m.merchant_id, m.bank_id, m.account_name, m.account_number, m.swift_code, m.bic_code FROM merchants_bank_details m", nativeQuery = true)
	List<MerchantsBankDetailsDTO> findAllBankDetails();

	@Query(value="SELECT m.merchant_id, m.bank_id, m.account_name, m.account_number, m.swift_code, m.bic_code FROM merchants_bank_details m WHERE m.id = ?1", nativeQuery = true)
	MerchantsBankDetailsDTO findByBankDetailsId(Long id);

}