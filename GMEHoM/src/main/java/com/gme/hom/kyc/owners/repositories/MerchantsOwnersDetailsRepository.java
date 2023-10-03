package com.gme.hom.kyc.owners.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gme.hom.kyc.owners.model.MerchantsOwnersDetails;

public interface MerchantsOwnersDetailsRepository extends JpaRepository<MerchantsOwnersDetails, Long> {

	List<MerchantsOwnersDetails> findByMerchantId(Long id);

}
