package com.gme.hom.kyc.merchants.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gme.hom.kyc.merchants.model.MerchantLog;

public interface MerchantLogRepository extends JpaRepository<MerchantLog, Long>{
	//@Transactional
	//@Modifying(clearAutomatically = true)
	//@Query(value="insert into merchants_log (id, merchant_id, email_id) values (?1,?2,?3) returning id ", nativeQuery=true)
	//public MerchantLog saveAndGet(Long id, UUID merchantId, String emailId);
	
//	@Modifying
//	@Query("save MerchantLog m set m.id = e.id where u.lastLoginDate < :date")
//	public MerchantLog save(MerchantLog e);
	

}
