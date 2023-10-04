package com.gme.hom.kyc.merchants.model;


import static jakarta.persistence.GenerationType.SEQUENCE;

import java.sql.Date;
import java.util.UUID;


import com.gme.hom.common.models.PersistenceEntity;
import com.gme.hom.kyc.merchants.config.MerchantType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
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
@Entity
@Table(name = "merchants_log")
public class MerchantLog extends PersistenceEntity{
	
	@Id
	@GeneratedValue
	Long x;
	
	//@Id
	//@SequenceGenerator(name = "merchants_log_seq", sequenceName = "merchants_log_id_seq", allocationSize = 1)
	//@GeneratedValue(strategy = SEQUENCE, generator = "merchants_log_seq")
	@Column(name="id", nullable = false)
    private Long id;
	
    @Column(name="merchant_id", nullable=false)
    private UUID merchantId;
	
	@NotNull
	@Enumerated(EnumType.STRING)
    @Column(name="merchant_type")
    private MerchantType merchantType;
	
	@Email(message = "Invalid email address")
	@NotNull(message = "email field cannot be empty")
    @Column(name="email_id")
    private String emailId;
	
    @Column(name="phone_code")
    private String phoneCode;
    
    @Pattern(regexp = "\\d+", message = "Phone number can have only numbers")
    @Column(name="phone_number")
    private String phoneNumber;
    
    @NotNull
    @Column(name="incorporation_country", nullable=false)
    private String incorporationCountry;
   
    @Column(name="business_name")
    private String businessName;

    @Column(name="business_name_native")
    private String businessNameNative;

    @Column(name="business_type", nullable=false)
    private String businessType;
    
    @Column(name="industry_type")
    private String industryType;

    @Column(name="product_type")
    private String productType;

    @Column(name="business_nature")
    private String businessNature;

    @Column(name="incorporation_date")
    private Date incorporationDate;

    @Column(name="bizz_reg_no")
    private String bizzRegNo;

    @Column(name="corp_reg_no")
    private String corpRegNo;

    @Column(name="business_profile")
    private String businessProfile;

    @Column(name="postal_code")
    private String postalCode;

    @Column(name="address1")
    private String address1;

    @Column(name="address2")
    private String address2;

    @Column(name="city")
    private String city;

    @Column(name="website")
    private String website;

    @Column(name="currency_preference")
    private String currencyPreference;

    @Column(name="approx_txn_monthly_volume")
    private Integer approxTxnMonthlyVolume;

    @Column(name="approx_txn_yearly_volume")
    private Integer approxTxnYearlyVolume;

    @Column(name="kyc_status")
    private String kycStatus;

    @Column(name="kyc_remarks")
    private String kycRemarks;

    @Column(name="rba_status")
    private String rbaStatus;

    @Column(name="rba_remarks")
    private String rbaRemarks;

    @Column(name="compliance_status")
    private String complianceStatus;

    @Column(name="compliance_remarks")
    private String complianceRemarks;

    @Column(name="doc_path")
    private String docPath;

    @Column(name="notification_method")
    private String notificationMethod;

    @Column(name="preferred_date_format")
    private String preferredDateFormat;

    @Column(name="preferred_time_zone")
    private String preferredTimeZone;

    @Column(name="security_stamp")
    private UUID securityStamp;

    @Column(name="terms_conditions_accepted")
    private Boolean termsConditionsAccepted;

    @Column(name="privacy_policy_accepted")
    private Boolean privacyPolicyAccepted;

    @Column(name="pricing_policy_accepted")
    private Boolean pricingPolicyAccepted;

    @Column(name="marketing_email_subscription")
    private Boolean marketingEmailSubscription;

    @Column(name="status")
    private String status;

    @Column(name="is_active")
    private Boolean isActive;

    @Column(name="remarks")
    private String remarks;

    @Column(name="ext_map_id_1")
    private String extMapId1;

    @Column(name="ext_map_id_2")
    private String extMapId2;

    @Column(name="ext_map_id_3")
    private String extMapId3;

    @Column(name="ref_col_1")
    private String refCol1;

    @Column(name="ref_col_2")
    private String refCol2;

    @Column(name="ref_col_3")
    private String refCol3;

    @Column(name="ref_col_4")
    private String refCol4;

    @Column(name="ref_col_5")
    private String refCol5;

    @Column(name="entity_hash")
    private String entityHash;

    
    public MerchantLog(Merchant merchant) {
    	super();
    	this.id=merchant.getId();
		this.merchantId = merchant.getMerchantId();
		this.merchantType = merchant.getMerchantType();
        this.emailId = merchant.getEmailId();
        this.phoneCode = merchant.getPhoneCode();
        this.phoneNumber = merchant.getPhoneNumber();
        this.incorporationCountry = merchant.getIncorporationCountry();
        this.businessName = merchant.getBusinessName();
        this.businessNameNative = merchant.getBusinessNameNative();
        this.businessType = merchant.getBusinessType();
        this.industryType = merchant.getIndustryType();
        this.productType = merchant.getProductType();
        this.businessNature = merchant.getBusinessNature();
        this.incorporationDate = merchant.getIncorporationDate();
        this.bizzRegNo = merchant.getBizzRegNo();
        this.corpRegNo = merchant.getCorpRegNo();
        this.businessProfile = merchant.getBusinessProfile();
        this.postalCode = merchant.getPostalCode();
        this.address1 = merchant.getAddress1();
        this.address2 = merchant.getAddress2();
        this.city = merchant.getCity();
        this.website = merchant.getWebsite();
        this.currencyPreference = merchant.getCurrencyPreference();
        this.approxTxnMonthlyVolume = merchant.getApproxTxnMonthlyVolume();
        this.approxTxnYearlyVolume = merchant.getApproxTxnYearlyVolume();
        this.kycStatus = merchant.getKycStatus();
        this.kycRemarks = merchant.getKycRemarks();
        this.rbaStatus = merchant.getRbaStatus();
        this.rbaRemarks = merchant.getRbaRemarks();
        this.complianceStatus = merchant.getComplianceStatus();
        this.complianceRemarks = merchant.getComplianceRemarks();
        this.notificationMethod = merchant.getNotificationMethod();
        this.preferredDateFormat = merchant.getPreferredDateFormat();
        this.preferredTimeZone = merchant.getPreferredTimeZone();
        this.securityStamp = merchant.getSecurityStamp();
        this.termsConditionsAccepted = merchant.getTermsConditionsAccepted();
        this.privacyPolicyAccepted = merchant.getPrivacyPolicyAccepted();
        this.pricingPolicyAccepted = merchant.getPricingPolicyAccepted();
        this.marketingEmailSubscription = merchant.getMarketingEmailSubscription();
        this.remarks = merchant.getRemarks();
        this.extMapId1 = merchant.getExtMapId1();
        this.extMapId2 = merchant.getExtMapId2();
        this.extMapId3 = merchant.getExtMapId3();
        this.refCol1 = merchant.getRefCol1();
        this.refCol2 = merchant.getRefCol2();
        this.refCol3 = merchant.getRefCol3();
        this.refCol4 = merchant.getRefCol4();
        this.refCol5 = merchant.getRefCol5();
    	this.entityHash = merchant.getEntityHash();
    }
	
	
}