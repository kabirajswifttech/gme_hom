package com.gme.hom.kyc.stockholders.model;

import java.sql.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MerchantsStockholdersDetailsRequest {

    @JsonInclude(Include.NON_NULL)
    @JsonProperty("merchant_id")
    private long merchantId;

    @JsonInclude(Include.NON_NULL)
    @JsonProperty("full_name")
    private String fullName;

    @JsonInclude(Include.NON_NULL)
    @JsonProperty("full_name_native")
    private String fullNameNative;

    @JsonInclude(Include.NON_NULL)
    @JsonProperty("nationality")
    private String nationality;

    @JsonInclude(Include.NON_NULL)
    @JsonProperty("dob")
    private Date dob;

    @JsonInclude(Include.NON_NULL)
    @JsonProperty("phone_code")
    private String phoneCode;

    @JsonInclude(Include.NON_NULL)
    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonInclude(Include.NON_NULL)
    @JsonProperty("email_id")
    private String emailId;

    @JsonInclude(Include.NON_NULL)
    @JsonProperty("residence_country")
    private String residenceCountry;

    @JsonInclude(Include.NON_NULL)
    @JsonProperty("percentage_of_share")
    private Float percentageOfShare;

    @JsonInclude(Include.NON_NULL)
    @JsonProperty("status")
    private String status;

  

}
