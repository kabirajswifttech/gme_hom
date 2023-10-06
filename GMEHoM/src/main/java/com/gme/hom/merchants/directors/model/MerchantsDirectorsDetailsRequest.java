package com.gme.hom.merchants.directors.model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gme.hom.common.models.PersistenceEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MerchantsDirectorsDetailsRequest extends PersistenceEntity {

    @JsonInclude(Include.NON_NULL)
    @JsonProperty("merchant_id")
    private Long merchantId;

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("full_name_native")
    private String fullNameNative;

    @JsonProperty("nationality")
    private String nationality;

    @JsonProperty("dob")
    private Date dob;

    @JsonProperty("phone_code")
    private String phoneCode;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("email_id")
    private String emailId;

    @JsonProperty("residence_country")
    private String residenceCountry;

}
