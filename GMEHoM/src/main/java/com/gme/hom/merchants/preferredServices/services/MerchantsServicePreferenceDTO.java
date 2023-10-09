package com.gme.hom.merchants.preferredServices.services;

import java.util.UUID;

public interface MerchantsServicePreferenceDTO {
    Long getId();

    Long getService_preference_id();

    UUID getService_preference_uuid();

    Long getMerchant_id();

    String getService_type();

    String getService_id();

    String getStatus();

    Boolean getIs_active();

    String getEntity_hash();

}
