package com.gme.hom.merchants.directors.services;

import java.sql.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.gme.hom.merchants.config.MerchantStatusCodes;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public interface MerchantsDirectorsDetailsDTO {
    @JsonInclude(Include.NON_NULL)
	Long getId();

    @JsonInclude(Include.NON_NULL)
    UUID getMerchant_director_id();

    @JsonInclude(Include.NON_NULL)
    Long getMerchant_id();

    @JsonInclude(Include.NON_NULL)
    String getFull_name();

    @JsonInclude(Include.NON_NULL)
    String getFull_name_native();

    @JsonInclude(Include.NON_NULL)
    String getNationality();

    @JsonInclude(Include.NON_NULL)
    Date getDob();

    @JsonInclude(Include.NON_NULL)
    String getPhone_code();

    @JsonInclude(Include.NON_NULL)
    String getPhone_number();

    @JsonInclude(Include.NON_NULL)
    String getEmail_id();

    @JsonInclude(Include.NON_NULL)
    String getResidence_country();

    @JsonInclude(Include.NON_NULL)
    String getRemarks();
    
    //@Enumerated(EnumType.STRING)
    @JsonInclude(Include.NON_NULL)
    MerchantStatusCodes getStatus();
    //String getStatus();

    @JsonInclude(Include.NON_NULL)
    Boolean getIs_active();

    @JsonInclude(Include.NON_NULL)
    String getEntity_hash();


}
