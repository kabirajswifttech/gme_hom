package com.gme.hom.kyc.directors.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gme.hom.kyc.directors.model.MerchantsDirectorsDetails;

public interface MerchantsDirectorsDetailsRepository extends JpaRepository<MerchantsDirectorsDetails, Long> {

	List<MerchantsDirectorsDetails> findByMerchantId(Long id);

}