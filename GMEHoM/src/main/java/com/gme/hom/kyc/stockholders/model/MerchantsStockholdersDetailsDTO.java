package com.gme.hom.kyc.stockholders.model;

import java.sql.Date;
import java.util.UUID;

public interface MerchantsStockholdersDetailsDTO {
    long getId();

    UUID getMerchant_stockholder_id();

    long getMerchant_id();

    String getFull_name();

    String getFull_name_native();

    String getNationality();

    Date getDob();

    String getPhone_code();

    String getPhone_number();

    String getEmail_id();

    String getResidence_country();

    Float getPercentage_of_share();

    String getStatus();

    boolean getIs_active();

    String getRemarks();

    String getEntity_hash();
    
}
