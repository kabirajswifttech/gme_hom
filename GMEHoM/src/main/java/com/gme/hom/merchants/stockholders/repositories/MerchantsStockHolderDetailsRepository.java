package com.gme.hom.merchants.stockholders.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gme.hom.merchants.stockholders.model.MerchantsStockholdersDetails;
import com.gme.hom.merchants.stockholders.services.MerchantsStockholdersDetailsDTO;

@Repository
public interface MerchantsStockHolderDetailsRepository extends JpaRepository<MerchantsStockholdersDetails, Long> {

	List<MerchantsStockholdersDetails> findByMerchantId(Long id);
	
	@Query(value="SELECT * FROM merchants_stockholders_details", nativeQuery = true)
	List<MerchantsStockholdersDetailsDTO> findAllStockholders();

	@Query(value="SELECT * FROM merchants_stockholders_details WHERE id=?1", nativeQuery = true)
	Optional<MerchantsStockholdersDetailsDTO> findStockholderById(long id);

	@Query(value="SELECT * FROM merchants_stockholders_details WHERE merchant_id=?1", nativeQuery = true)
	List<MerchantsStockholdersDetailsDTO> findStockholderByMerchantId(long id);

}
