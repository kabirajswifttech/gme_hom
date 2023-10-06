package com.gme.hom.merchants.bankDetails.services;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public interface MerchantsBankDetailsDTO {
    
	@JsonInclude(Include.NON_NULL)
    UUID getMerchant_bank_id();

	@JsonInclude(Include.NON_NULL)
    Long getMerchant_id();

	@JsonInclude(Include.NON_NULL)
    String getBank_id();

	@JsonInclude(Include.NON_NULL)
    String getAccount_name();

	@JsonInclude(Include.NON_NULL)
    String getAccount_number();

	@JsonInclude(Include.NON_NULL)
    String getSwift_code();

	@JsonInclude(Include.NON_NULL)
    String getBic_code();

	@JsonInclude(Include.NON_NULL)
    String getIfsc_code();

	@JsonInclude(Include.NON_NULL)
    String getIban_cbu_card_number();

	@JsonInclude(Include.NON_NULL)
    Boolean getIs_verified();

	@JsonInclude(Include.NON_NULL)
    String getStatus();

	@JsonInclude(Include.NON_NULL)
    Boolean getIs_active();


}
