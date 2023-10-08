package com.gme.hom.merchants.kyc.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gme.hom.merchants.kyc.model.Merchant;
import com.gme.hom.merchants.kyc.services.MerchantDTO;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, Long> {
	
	@Query(value = "select m.email_id, m.phone_number, m.incorporation_country from merchants m", nativeQuery = true)
	List<MerchantDTO> getAllMerchants();
	
	@Query(value = "SELECT * FROM merchants m WHERE m.email_id = ?1", nativeQuery = true)
	Optional<MerchantDTO> findByEmailId(String username);
	
	@Query(value = "select * from merchants m WHERE m.phone_number = ?1", nativeQuery = true)
	Optional<MerchantDTO> findByPhoneNumber(String phoneNumber);
	
	@Query(value = "select * from merchants m WHERE m.id = ?1", nativeQuery = true)
	Optional<MerchantDTO> findByMerchantId(Long id);
	
	}
