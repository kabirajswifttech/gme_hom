package com.gme.hom.kyc.owners.services;

import java.util.UUID;

public interface MerchantsOwnersDetailsDTO {
    Long getId();

    UUID getMerchantOwenerId();

    long getMerchantId();

    String getFullName();

    String getFullNameNative();

    String getRole();

    String getNationality();

    String getResidenceCountry();

    String getPhoneCode();

    String getPhoneNumber();

    String getEmailId();

    String getRemarks();

    String getStatus();

    boolean getIsActive();


}