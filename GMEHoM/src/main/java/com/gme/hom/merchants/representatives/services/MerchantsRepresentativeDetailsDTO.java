package com.gme.hom.merchants.representatives.services;

import java.sql.Date;
import java.util.UUID;

public interface MerchantsRepresentativeDetailsDTO {
    long getId();

    UUID getMerchant_representative_id();

    long getMerchant_id();

    String getFirst_name();

    String getMiddle_name();

    String getLast_name();

    String getFull_name();

    String getFull_name_native();

    String getDesignation();

    String getNationality();

    String getPhone_code();

    String getPhone_number();

    String getEmail_id();

    Date getDob();

    String getResidence_country();

    String getPostal_code();

    String getAddress1();

    String getAddress2();

    String getCity();

    String getStatus();

    boolean getIs_active();

}
