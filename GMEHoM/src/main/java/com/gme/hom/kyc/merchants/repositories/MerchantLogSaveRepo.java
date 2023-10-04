package com.gme.hom.kyc.merchants.repositories;

import org.springframework.stereotype.Repository;

import com.gme.hom.kyc.merchants.model.MerchantLog;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class MerchantLogSaveRepo {
	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	public Long save(MerchantLog m) {
	    return (long) entityManager.createNativeQuery("INSERT INTO merchants_log ("
	    												+ "id, merchant_id, merchant_type, email_id,"			//4
	    												+ "phone_code, phone_number, incorporation_country, "	//3
	    												+ "business_name, business_name_native, business_type, industry_type, product_type, business_nature, "		//6
	    												+ "incorporation_date, bizz_reg_no, corp_reg_no, business_profile, "		//4
	    												+ "postal_code, address1, address2, city, website, currency_preference, "	//6
	    												+ "approx_txn_monthly_volume, approx_txn_yearly_volume,"		//2
	    												+ "kyc_status, kyc_remarks,"		//2
	    												+ "rba_status, rba_remarks,"		//2
	    												+ "compliance_status, compliance_remarks,"		//2
	    												+ "doc_path, notification_method, "				//2
	    												+ "preferred_date_format, preferred_time_zone, "		//2
	    												+ "security_stamp, terms_conditions_accepted, privacy_policy_accepted, pricing_policy_accepted, marketing_email_subscription"		//5
	    												+ "status, is_active, remarks, "		//3
	    												+ "ext_map_id_1, ext_map_id_2, ext_map_id_3, "		//3
	    												+ "ref_col_1, ref_col_2, ref_col_3, ref_col_4, ref_col_5, entity_hash"		//6
	    												+ " ) VALUES ("
	    												+ "?,?,?,?,?,?,?,?,?,?,"
	    												+ "?,?,?,?,?,?,?,?,?,?,"
	    												+ "?,?,?,?,?,?,?,?,?,?,"
	    												+ "?,?,?,?,?,?,?,?,?,?,"
	    												+ "?,?,?,?,?,?,?,?,?,?,"
	    												+ "?,?,?,?"
	    												+ ") returning id")
	      .setParameter(1, m.getId()) 
	      .setParameter(2, m.getMerchantId())
	      .setParameter(4, m.getMerchantType())
	      .setParameter(3, m.getEmailId())
	      .setParameter(5, m.getPhoneCode())
	      .setParameter(6, m.getPhoneNumber())
	      .setParameter(7, m.getIncorporationCountry())
	      .setParameter(8, m.getBusinessName())
	      .setParameter(9, m.getBusinessNameNative())
	      .setParameter(10, m.getBusinessType())
	      .setParameter(11, m.getIndustryType())
	      .setParameter(12, m.getProductType())
	      .setParameter(13, m.getBusinessNature())
	      
	      .setParameter(14, m.getIncorporationDate())
	      .setParameter(15, m.getBizzRegNo())
	      .setParameter(16, m.getCorpRegNo())
	      .setParameter(17, m.getBusinessProfile())
	      
	      .setParameter(18, m.getPostalCode())
	      .setParameter(19, m.getAddress1())
	      .setParameter(20, m.getAddress2())
	      .setParameter(21, m.getCity())
	      .setParameter(22, m.getWebsite())
	      .setParameter(23, m.getCurrencyPreference())
	      .setParameter(24, m.getApproxTxnMonthlyVolume())
	      .setParameter(25, m.getApproxTxnYearlyVolume())
	      .setParameter(26, m.getKycStatus())
	      .setParameter(27, m.getKycRemarks())
	      .setParameter(28, m.getRbaStatus())
	      .setParameter(29, m.getRbaRemarks())
	      .setParameter(30, m.getComplianceStatus())
	      .setParameter(31, m.getComplianceRemarks())
	      .setParameter(32, m.getDocPath())
	      .setParameter(33, m.getNotificationMethod())
 
	      .setParameter(34, m.getPreferredDateFormat())
	      .setParameter(35, m.getPreferredTimeZone())
	      .setParameter(36, m.getSecurityStamp())
	      .setParameter(37, m.getTermsConditionsAccepted())
	      .setParameter(38, m.getPrivacyPolicyAccepted())
	      .setParameter(39, m.getPricingPolicyAccepted())
	      .setParameter(40, m.getMarketingEmailSubscription())
	      .setParameter(41, m.getStatus())
	      .setParameter(42, m.isActive())
	      .setParameter(43, m.getRemarks())
	      .setParameter(44, m.getExtMapId1())
	      .setParameter(45, m.getExtMapId2())
	      .setParameter(46, m.getExtMapId3())
	      .setParameter(47, m.getRefCol1())
	      .setParameter(48, m.getRefCol2())
	      .setParameter(49, m.getRefCol3())
	      .setParameter(50, m.getRefCol4())
	      .setParameter(51, m.getRefCol5())
	      .setParameter(52, m.getEntityHash())
	      //.executeUpdate();
	      .getSingleResult();
	}
	
}
