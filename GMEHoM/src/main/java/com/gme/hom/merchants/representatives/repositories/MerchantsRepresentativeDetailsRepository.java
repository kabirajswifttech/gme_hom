package com.gme.hom.merchants.representatives.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gme.hom.merchants.representatives.model.MerchantsRepresentativeDetails;
import com.gme.hom.merchants.representatives.services.MerchantsRepresentativeDetailsDTO;

public interface MerchantsRepresentativeDetailsRepository extends JpaRepository<MerchantsRepresentativeDetails, Long> {

	List<MerchantsRepresentativeDetails> findByMerchantId(long merchantId);
	@Query(value = "SELECT * FROM merchants_representative_details", nativeQuery = true)
	List<MerchantsRepresentativeDetailsDTO> findAllRepresentatives();

	@Query(value = "SELECT * FROM merchants_representative_details WHERE id=?1", nativeQuery = true)
	Optional<MerchantsRepresentativeDetailsDTO> findRepresentativeById(long merchantsRepresentativeId);

	@Query(value = "SELECT * FROM merchants_representative_details WHERE merchant_id=?1", nativeQuery = true)
	List<MerchantsRepresentativeDetailsDTO> findRepresentativesByMerchantId(long merchantId);

}
