package com.gme.hom.merchants.owners.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gme.hom.merchants.owners.model.MerchantsOwnersDetails;
import com.gme.hom.merchants.owners.services.MerchantsOwnersDetailsDTO;

public interface MerchantsOwnersDetailsRepository extends JpaRepository<MerchantsOwnersDetails, Long> {
	List<MerchantsOwnersDetails> findByMerchantId(Long id);

	@Query(value="SELECT * FROM merchants_owners_details", nativeQuery = true)
	List<MerchantsOwnersDetailsDTO> findAllOwners();

	@Query(value="SELECT * FROM merchants_owners_details WHERE id=?1", nativeQuery = true)
	Optional<MerchantsOwnersDetailsDTO> findOwnersById(long id);

	@Query(value="SELECT * FROM merchants_owners_details WHERE merchant_id=?1", nativeQuery = true)
	List<MerchantsOwnersDetailsDTO> findOwnersByMerchantId(long id);

}
