package com.gme.hom.merchants.kyc.services;

import java.sql.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.gme.hom.merchants.config.MerchantType;

public interface MerchantDTO {

	@JsonInclude(Include.NON_NULL)
    UUID getMerchant_id();

	@JsonInclude(Include.NON_NULL)
    MerchantType getMerchant_type();

	@JsonInclude(Include.NON_NULL)
    String getEmail_id();

	@JsonInclude(Include.NON_NULL)
    String getPhone_code();

	@JsonInclude(Include.NON_NULL)
    String getPhone_number();

	@JsonInclude(Include.NON_NULL)
    String getIncorporation_country();

	@JsonInclude(Include.NON_NULL)
    String getBusiness_name();

	@JsonInclude(Include.NON_NULL)
    String getBusiness_name_native();

	@JsonInclude(Include.NON_NULL)
    String getBusiness_type();

	@JsonInclude(Include.NON_NULL)
    String getIndustry_type();

	@JsonInclude(Include.NON_NULL)
    String getProduct_type();

	@JsonInclude(Include.NON_NULL)
    String getBusiness_nature();

	@JsonInclude(Include.NON_NULL)
    Date getIncorporation_date();

	@JsonInclude(Include.NON_NULL)
    String getBizz_reg_no();

	@JsonInclude(Include.NON_NULL)
    String getCorp_reg_no();

	@JsonInclude(Include.NON_NULL)
    String getBusiness_profile();

	@JsonInclude(Include.NON_NULL)
    String getPostal_code();

	@JsonInclude(Include.NON_NULL)
    String getAddress1();

	@JsonInclude(Include.NON_NULL)
    String getAddress2();

	@JsonInclude(Include.NON_NULL)
    String getCity();

	@JsonInclude(Include.NON_NULL)
    String getWebsite();

	@JsonInclude(Include.NON_NULL)
    String getCurrency_preference();

	@JsonInclude(Include.NON_NULL)
    Integer getApprox_txn_monthly_volume();

	@JsonInclude(Include.NON_NULL)
    Integer getApprox_txn_yearly_volume();

	@JsonInclude(Include.NON_NULL)
    String getKyc_status();

	@JsonInclude(Include.NON_NULL)
    String getKyc_remarks();

	@JsonInclude(Include.NON_NULL)
    String getRba_status();
	
	@JsonInclude(Include.NON_NULL)
    String getRba_remarks();
	
	@JsonInclude(Include.NON_NULL)
    String getCompliance_status();
	
	@JsonInclude(Include.NON_NULL)
    String getCompliance_remarks();
	
	@JsonInclude(Include.NON_NULL)
    String getDoc_path();
	
	@JsonInclude(Include.NON_NULL)
    String getNotification_method();
	
	@JsonInclude(Include.NON_NULL)
    String getPreferred_date_format();
	
	@JsonInclude(Include.NON_NULL)
    String getPreferred_time_zone();
	
	@JsonInclude(Include.NON_NULL)
    UUID getSecurity_stamp();
	
	@JsonInclude(Include.NON_NULL)
    Boolean getTerms_conditions_accepted();
	
	@JsonInclude(Include.NON_NULL)
    Boolean getPrivacy_policy_accepted();
	
	@JsonInclude(Include.NON_NULL)
    Boolean getPricing_policy_accepted();
	
	@JsonInclude(Include.NON_NULL)
    Boolean getMarketing_email_subscription();
	
	@JsonInclude(Include.NON_NULL)
    String getStatus();
	
	@JsonInclude(Include.NON_NULL)
    Boolean getIs_active();
	
	@JsonInclude(Include.NON_NULL)
    String getRemarks();
	
	@JsonInclude(Include.NON_NULL)
    String getExt_map_id_1();

	@JsonInclude(Include.NON_NULL)
    String getExt_map_id_2();

	@JsonInclude(Include.NON_NULL)
    String getExt_map_id_3();

	@JsonInclude(Include.NON_NULL)
    String getRef_col_1();

	@JsonInclude(Include.NON_NULL)
	String getRef_col_2();

	@JsonInclude(Include.NON_NULL)
    String getRef_col_3();

	@JsonInclude(Include.NON_NULL)
    String getRef_col_4();

	@JsonInclude(Include.NON_NULL)
    String getRef_col_5();

   
	
	
	
	
	
	
	
	
	
}
