package com.gme.hom.merchants.directors.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gme.hom.merchants.directors.model.MerchantsDirectorsDetails;
import com.gme.hom.merchants.directors.services.MerchantsDirectorsDetailsDTO;

public interface MerchantsDirectorsDetailsRepository extends JpaRepository<MerchantsDirectorsDetails, Long> {
	
	@Query(value = "select * from merchants_directors_details m where merchant_id=?1", nativeQuery = true)
	List<MerchantsDirectorsDetailsDTO> findByMerchantId(Long id);
	
	@Query(value = "select * from merchants_directors_details m", nativeQuery = true)
	List<MerchantsDirectorsDetailsDTO> findAllDirectors();

	@Query(value = "select * from merchants_directors_details m where id=?1", nativeQuery = true)
	Optional<MerchantsDirectorsDetailsDTO> findByDirectorsId(Long id);

}
