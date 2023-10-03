package com.gme.hom.kyc.stockholders.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gme.hom.kyc.stockholders.model.MerchantsStockholdersDetails;

public interface MerchantsStockHolderDetailsRepository extends JpaRepository<MerchantsStockholdersDetails, Long> {

	List<MerchantsStockholdersDetails> findByMerchantId(Long id);

}
