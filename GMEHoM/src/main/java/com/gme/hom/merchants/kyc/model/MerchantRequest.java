package com.gme.hom.merchants.kyc.model;

import java.sql.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gme.hom.merchants.config.MerchantType;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class MerchantRequest {
	@Enumerated(EnumType.STRING)
    @JsonProperty("merchant_type")
    private MerchantType merchantType;
	
	@Email(message = "Invalid email address")
	@NotNull(message = "email field cannot be empty")
    @JsonProperty("email_id")
    private String emailId;
	
    @JsonProperty("phone_code")
    private String phoneCode;
    
    @Pattern(regexp = "\\d+", message = "Phone number can have only numbers")
    @JsonProperty("phone_number")
    private String phoneNumber;

    @NotNull(message = "incorporation country cannot be empty")
    @JsonProperty("incorporation_country")
    private String incorporationCountry;

    @JsonProperty("business_name")
    private String businessName;

    @JsonProperty("business_name_native")
    private String businessNameNative;

    @JsonProperty("business_type")
    private String businessType;

    @JsonProperty("industry_type")
    private String industryType;

    @JsonProperty("product_type")
    private String productType;

    @JsonProperty("business_nature")
    private String businessNature;

    @JsonProperty("incorporation_date")
    private Date incorporationDate;

    @JsonProperty("bizz_reg_no")
    private String bizzRegNo;

    @JsonProperty("corp_reg_no")
    private String corpRegNo;

    @JsonProperty("business_profile")
    private String businessProfile;

    @JsonProperty("postal_code")
    private String postalCode;

    @JsonProperty("address1")
    private String address1;

    @JsonProperty("address2")
    private String address2;

    @JsonProperty("city")
    private String city;

    @JsonProperty("website")
    private String website;

    @JsonProperty("currency_preference")
    private String currencyPreference;

    @JsonProperty("approx_txn_monthly_volume")
    private Integer approxTxnMonthlyVolume;

    @JsonProperty("approx_txn_yearly_volume")
    private Integer approxTxnYearlyVolume;

    @JsonProperty("kyc_status")
    private String kycStatus;

    @JsonProperty("kyc_remarks")
    private String kycRemarks;

    @JsonProperty("rba_status")
    private String rbaStatus;

    @JsonProperty("rba_remarks")
    private String rbaRemarks;

    @JsonProperty("compliance_status")
    private String complianceStatus;

    @JsonProperty("compliance_remarks")
    private String complianceRemarks;

    @JsonProperty("notification_method")
    private String notificationMethod;

    @JsonProperty("preferred_date_format")
    private String preferredDateFormat;

    @JsonProperty("preferred_time_zone")
    private String preferredTimeZone;

    @JsonProperty("security_stamp")
    private UUID securityStamp;

    @JsonProperty("terms_conditions_accepted")
    private Boolean termsConditionsAccepted;

    @JsonProperty("privacy_policy_accepted")
    private Boolean privacyPolicyAccepted;

    @JsonProperty("pricing_policy_accepted")
    private Boolean pricingPolicyAccepted;

    @JsonProperty("marketing_email_subscription")
    private Boolean marketingEmailSubscription;

    @JsonProperty("remarks")
    private String remarks;

    @JsonProperty("ext_map_id_1")
    private String extMapId1;

    @JsonProperty("ext_map_id_2")
    private String extMapId2;

    @JsonProperty("ext_map_id_3")
    private String extMapId3;

    @JsonProperty("ref_col_1")
    private String refCol1;

    @JsonProperty("ref_col_2")
    private String refCol2;

    @JsonProperty("ref_col_3")
    private String refCol3;

    @JsonProperty("ref_col_4")
    private String refCol4;

    @JsonProperty("ref_col_5")
    private String refCol5;
    
    


}
