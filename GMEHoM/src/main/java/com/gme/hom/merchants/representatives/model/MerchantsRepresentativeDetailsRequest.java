package com.gme.hom.merchants.representatives.model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MerchantsRepresentativeDetailsRequest {
    

    @JsonInclude(Include.NON_NULL)
    @JsonProperty("merchant_id")
    private Long merchantId;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("middle_name")
    private String middleName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("full_name_native")
    private String fullNameNative;

    @JsonProperty("designation")
    private String designation;

    @JsonProperty("nationality")
    private String nationality;

    @JsonProperty("phone_code")
    private String phoneCode;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("email_id")
    private String emailId;

    @JsonProperty("dob")
    private Date dob;

    @JsonProperty("residence_country")
    private String residenceCountry;

    @JsonProperty("postal_code")
    private String postalCode;

    @JsonProperty("address1")
    private String address1;

    @JsonProperty("address2")
    private String address2;

    @JsonProperty("city")
    private String city;


}