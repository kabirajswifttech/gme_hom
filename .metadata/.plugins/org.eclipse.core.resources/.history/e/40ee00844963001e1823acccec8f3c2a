package com.gme.hom.kyc.representatives.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gme.hom.kyc.representatives.model.MerchantsRepresentativeDetails;

public interface MerchantsRepresentativeDetailsRepository extends JpaRepository<MerchantsRepresentativeDetails, Long> {

	List<MerchantsRepresentativeDetails> findByMerchantId(long merchantId);

}
