package com.gme.hom.kyc.preferredServices.services;

import java.util.UUID;

public interface MerchantsServicePreferenceDTO {
    long getId();

    UUID getMerchant_service_preference_id();

    long getMerchant_id();

    String getService_type();

    String getService_id();

    String getStatus();

    boolean getIs_active();

    String getEntity_hash();

}
