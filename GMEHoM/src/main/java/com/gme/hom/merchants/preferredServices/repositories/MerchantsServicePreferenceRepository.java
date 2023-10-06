package com.gme.hom.merchants.preferredServices.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gme.hom.merchants.preferredServices.model.MerchantsServicePreference;
import com.gme.hom.merchants.preferredServices.services.MerchantsServicePreferenceDTO;

public interface MerchantsServicePreferenceRepository extends JpaRepository<MerchantsServicePreference, Long>{

	List<MerchantsServicePreference> findByMerchantId(Long merchantId);
	@Query(value="SELECT * FROM merchants_service_preferences", nativeQuery = true)
	List<MerchantsServicePreferenceDTO> findAllServices();

	@Query(value="SELECT * FROM merchants_service_preferences WHERE id=?1", nativeQuery = true)
	Optional<MerchantsServicePreferenceDTO> findServiceById(Long merchantsServicePreferenceId);

	@Query(value="SELECT * FROM merchants_service_preferences WHERE merchant_id=?1", nativeQuery = true)
	List<MerchantsServicePreferenceDTO> findServiceByMerchantId(Long merchantId);

}
