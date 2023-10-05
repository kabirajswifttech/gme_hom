package com.gme.hom.kyc.preferredServices.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gme.hom.kyc.preferredServices.model.MerchantsServicePreference;

public interface MerchantsServicePreferenceRepository extends JpaRepository<MerchantsServicePreference, Long>{

	List<MerchantsServicePreference> findByMerchantId(Long merchantId);

}
