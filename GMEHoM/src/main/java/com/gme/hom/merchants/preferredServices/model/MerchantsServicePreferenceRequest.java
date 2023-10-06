package com.gme.hom.merchants.preferredServices.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MerchantsServicePreferenceRequest {
   

    @JsonInclude(Include.NON_NULL)
    @JsonProperty("merchant_id")
    private long merchantId;

    @JsonProperty("service_type")
    private String serviceType;

    @JsonProperty("service_id")
    private String serviceId;


}