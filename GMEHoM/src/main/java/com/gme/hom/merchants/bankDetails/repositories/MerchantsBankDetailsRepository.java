package com.gme.hom.merchants.bankDetails.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gme.hom.merchants.bankDetails.model.MerchantsBankDetails;
import com.gme.hom.merchants.bankDetails.services.MerchantsBankDetailsDTO;

public interface MerchantsBankDetailsRepository extends JpaRepository<MerchantsBankDetails, Long> {
	@Query(value="SELECT * FROM merchants_bank_details m WHERE m.merchant_id=?1", nativeQuery = true)
	MerchantsBankDetailsDTO findByMerchantId(Long id);
	
	@Query(value="SELECT  FROM merchants*_bank_details m", nativeQuery = true)
	List<MerchantsBankDetailsDTO> findAllBankDetails();

	@Query(value="SELECT * FROM merchants_bank_details m WHERE m.id = ?1", nativeQuery = true)
	MerchantsBankDetailsDTO findByBankDetailsId(Long id);

}
