package com.example.demo.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Merchant;
import com.example.demo.model.MerchantDTO;


@Repository
public interface MerchantRepository extends JpaRepository<Merchant, Long> {
	
	@Query(value = "select m.email_id, m.phone_number, m.incorporation_country from merchants m", nativeQuery = true)
	List<MerchantDTO> getAllMerchants();
	
	@Query(value = "SELECT m.email_id, m.phone_number, m.incorporation_country  FROM merchants m WHERE m.email_id = ?1", nativeQuery = true)
	MerchantDTO findByEmailId(String username);
	
	@Query(value = "select m.email_id, m.phone_number, m.incorporation_country from merchants m WHERE m.phone_number = ?1", nativeQuery = true)
	MerchantDTO findByPhoneNumber(String phoneNumber);
	
	@Query(value = "select m.email_id, m.phone_number, m.incorporation_country from merchants m WHERE m.id = ?1", nativeQuery = true)
	MerchantDTO getMerchantById(Long id);
	
	}
