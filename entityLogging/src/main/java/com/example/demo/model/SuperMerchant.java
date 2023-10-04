package com.example.demo.model;


import java.sql.Date;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import com.example.demo.api.PersistenceEntityWithUpdateApproval;
import com.example.demo.config.MerchantType;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
public class SuperMerchant extends PersistenceEntityWithUpdateApproval{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
    protected Long id;
	
	@GenericGenerator(name = "uuid2")
	@GeneratedValue(strategy = GenerationType.UUID, generator = "uuid2")
    @Column(name="merchant_id", insertable = false, updatable = false, nullable = false)
    protected UUID merchantId;
	
	@NotNull
	@Enumerated(EnumType.STRING)
    @Column(name="merchant_type")
    protected MerchantType merchantType;
	
	@Email(message = "Invalid email address")
	@NotNull(message = "email field cannot be empty")
    @Column(name="email_id")
    protected String emailId;
	
    @Column(name="phone_code")
    protected String phoneCode;
    
    @Pattern(regexp = "\\d+", message = "Phone number can have only numbers")
    @Column(name="phone_number")
    protected String phoneNumber;
    
    @NotNull
    @Column(name="incorporation_country")
    protected String incorporationCountry;
   
    @Column(name="business_name")
    protected String businessName;

    @Column(name="business_name_native")
    protected String businessNameNative;

    @Column(name="business_type")
    protected String businessType;
    
    @Column(name="industry_type")
    protected String industryType;

    @Column(name="product_type")
    protected String productType;

    @Column(name="business_nature")
    protected String businessNature;

    @Column(name="incorporation_date")
    protected Date incorporationDate;

    @Column(name="bizz_reg_no")
    protected String bizzRegNo;

    @Column(name="corp_reg_no")
    protected String corpRegNo;

    @Column(name="business_profile")
    protected String businessProfile;

    @Column(name="postal_code")
    protected String postalCode;

    @Column(name="address1")
    protected String address1;

    @Column(name="address2")
    protected String address2;

    @Column(name="city")
    protected String city;

    @Column(name="website")
    protected String website;

    @Column(name="currency_preference")
    protected String currencyPreference;

    @Column(name="approx_txn_monthly_volume")
    protected Integer approxTxnMonthlyVolume;

    @Column(name="approx_txn_yearly_volume")
    protected Integer approxTxnYearlyVolume;

    @Column(name="kyc_status")
    protected String kycStatus;

    @Column(name="kyc_remarks")
    protected String kycRemarks;

    @Column(name="rba_status")
    protected String rbaStatus;

    @Column(name="rba_remarks")
    protected String rbaRemarks;

    @Column(name="compliance_status")
    protected String complianceStatus;

    @Column(name="compliance_remarks")
    protected String complianceRemarks;

    @Column(name="doc_path")
    protected String docPath;

    @Column(name="notification_method")
    protected String notificationMethod;

    @Column(name="preferred_date_format")
    protected String preferredDateFormat;

    @Column(name="preferred_time_zone")
    protected String preferredTimeZone;

    @Column(name="security_stamp")
    protected UUID securityStamp;

    @Column(name="terms_conditions_accepted")
    protected Boolean termsConditionsAccepted;

    @Column(name="privacy_policy_accepted")
    protected Boolean privacyPolicyAccepted;

    @Column(name="pricing_policy_accepted")
    protected Boolean pricingPolicyAccepted;

    @Column(name="marketing_email_subscription")
    protected Boolean marketingEmailSubscription;

    @Column(name="status")
    protected String status;

    @Column(name="is_active")
    protected Boolean isActive;

    @Column(name="remarks")
    protected String remarks;

    @Column(name="ext_map_id_1")
    protected String extMapId1;

    @Column(name="ext_map_id_2")
    protected String extMapId2;

    @Column(name="ext_map_id_3")
    protected String extMapId3;

    @Column(name="ref_col_1")
    protected String refCol1;

    @Column(name="ref_col_2")
    protected String refCol2;

    @Column(name="ref_col_3")
    protected String refCol3;

    @Column(name="ref_col_4")
    protected String refCol4;

    @Column(name="ref_col_5")
    protected String refCol5;

    @Column(name="entity_hash")
    protected String entityHash;
    
    
    public SuperMerchant(MerchantRequest merchantRequest) {
        this.merchantType = merchantRequest.getMerchantType();
        this.emailId = merchantRequest.getEmailId();
        this.phoneCode = merchantRequest.getPhoneCode();
        this.phoneNumber = merchantRequest.getPhoneNumber();
        this.incorporationCountry = merchantRequest.getIncorporationCountry();
        this.businessName = merchantRequest.getBusinessName();
        this.businessNameNative = merchantRequest.getBusinessNameNative();
        this.businessType = merchantRequest.getBusinessType();
        this.industryType = merchantRequest.getIndustryType();
        this.productType = merchantRequest.getProductType();
        this.businessNature = merchantRequest.getBusinessNature();
        this.incorporationDate = merchantRequest.getIncorporationDate();
        this.bizzRegNo = merchantRequest.getBizzRegNo();
        this.corpRegNo = merchantRequest.getCorpRegNo();
        this.businessProfile = merchantRequest.getBusinessProfile();
        this.postalCode = merchantRequest.getPostalCode();
        this.address1 = merchantRequest.getAddress1();
        this.address2 = merchantRequest.getAddress2();
        this.city = merchantRequest.getCity();
        this.website = merchantRequest.getWebsite();
        this.currencyPreference = merchantRequest.getCurrencyPreference();
        this.approxTxnMonthlyVolume = merchantRequest.getApproxTxnMonthlyVolume();
        this.approxTxnYearlyVolume = merchantRequest.getApproxTxnYearlyVolume();
        this.kycStatus = merchantRequest.getKycStatus();
        this.kycRemarks = merchantRequest.getKycRemarks();
        this.rbaStatus = merchantRequest.getRbaStatus();
        this.rbaRemarks = merchantRequest.getRbaRemarks();
        this.complianceStatus = merchantRequest.getComplianceStatus();
        this.complianceRemarks = merchantRequest.getComplianceRemarks();
        this.notificationMethod = merchantRequest.getNotificationMethod();
        this.preferredDateFormat = merchantRequest.getPreferredDateFormat();
        this.preferredTimeZone = merchantRequest.getPreferredTimeZone();
        this.securityStamp = merchantRequest.getSecurityStamp();
        this.termsConditionsAccepted = merchantRequest.getTermsConditionsAccepted();
        this.privacyPolicyAccepted = merchantRequest.getPrivacyPolicyAccepted();
        this.pricingPolicyAccepted = merchantRequest.getPricingPolicyAccepted();
        this.marketingEmailSubscription = merchantRequest.getMarketingEmailSubscription();
        this.remarks = merchantRequest.getRemarks();
        this.extMapId1 = merchantRequest.getExtMapId1();
        this.extMapId2 = merchantRequest.getExtMapId2();
        this.extMapId3 = merchantRequest.getExtMapId3();
        this.refCol1 = merchantRequest.getRefCol1();
        this.refCol2 = merchantRequest.getRefCol2();
        this.refCol3 = merchantRequest.getRefCol3();
        this.refCol4 = merchantRequest.getRefCol4();
        this.refCol5 = merchantRequest.getRefCol5();
    } 
    
    public SuperMerchant(UUID merchantId, MerchantType merchantType, String emailId, String phoneNumber,
			String incorporationCountry, String businessName, String businessNameNative, String businessType,String productType,
			String businessNature, Date incorporationDate, String bizzRegNo, String corpRegNo,
			String businessProfile, String postalCode, String address1, String address2, String city, String website,
			String currencyPreference, Integer approxTxnMonthlyVolume, Integer approxTxnYearlyVolume,
			String kycStatus, String kycRemarks, String rbaStatus,
			String rbaRemarks, String complianceStatus, String complianceRemarks, String docPath,
			String notificationMethod, String preferredDateFormat, String preferredTimeZone) {
		super();
		this.merchantId = merchantId;
		this.merchantType = merchantType;
		this.emailId = emailId;
		this.phoneNumber = phoneNumber;
		this.incorporationCountry = incorporationCountry;
		this.businessName = businessName;
		this.businessNameNative = businessNameNative;
		this.businessType = businessType;
		this.productType = productType;
		this.businessNature = businessNature;
		this.incorporationDate = incorporationDate;
		this.bizzRegNo = bizzRegNo;
		this.corpRegNo = corpRegNo;
		this.businessProfile = businessProfile;
		this.postalCode = postalCode;
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.website = website;
		this.currencyPreference = currencyPreference;
		this.approxTxnMonthlyVolume = approxTxnMonthlyVolume;
		this.approxTxnYearlyVolume = approxTxnYearlyVolume;
		this.kycStatus = kycStatus;
		this.kycRemarks = kycRemarks;
		this.rbaStatus = rbaStatus;
		this.rbaRemarks = rbaRemarks;
		this.complianceStatus = complianceStatus;
		this.complianceRemarks = complianceRemarks;
		this.docPath = docPath;
		this.notificationMethod = notificationMethod;
		this.preferredDateFormat = preferredDateFormat;
		this.preferredTimeZone = preferredTimeZone;
	}
    public SuperMerchant(Merchant merchantRequest) {
    	super();
    	this.id=merchantRequest.getId();
		this.merchantId = merchantRequest.getMerchantId();
		this.merchantType = merchantRequest.getMerchantType();
        this.emailId = merchantRequest.getEmailId();
        this.phoneCode = merchantRequest.getPhoneCode();
        this.phoneNumber = merchantRequest.getPhoneNumber();
        this.incorporationCountry = merchantRequest.getIncorporationCountry();
        this.businessName = merchantRequest.getBusinessName();
        this.businessNameNative = merchantRequest.getBusinessNameNative();
        this.businessType = merchantRequest.getBusinessType();
        this.industryType = merchantRequest.getIndustryType();
        this.productType = merchantRequest.getProductType();
        this.businessNature = merchantRequest.getBusinessNature();
        this.incorporationDate = merchantRequest.getIncorporationDate();
        this.bizzRegNo = merchantRequest.getBizzRegNo();
        this.corpRegNo = merchantRequest.getCorpRegNo();
        this.businessProfile = merchantRequest.getBusinessProfile();
        this.postalCode = merchantRequest.getPostalCode();
        this.address1 = merchantRequest.getAddress1();
        this.address2 = merchantRequest.getAddress2();
        this.city = merchantRequest.getCity();
        this.website = merchantRequest.getWebsite();
        this.currencyPreference = merchantRequest.getCurrencyPreference();
        this.approxTxnMonthlyVolume = merchantRequest.getApproxTxnMonthlyVolume();
        this.approxTxnYearlyVolume = merchantRequest.getApproxTxnYearlyVolume();
        this.kycStatus = merchantRequest.getKycStatus();
        this.kycRemarks = merchantRequest.getKycRemarks();
        this.rbaStatus = merchantRequest.getRbaStatus();
        this.rbaRemarks = merchantRequest.getRbaRemarks();
        this.complianceStatus = merchantRequest.getComplianceStatus();
        this.complianceRemarks = merchantRequest.getComplianceRemarks();
        this.notificationMethod = merchantRequest.getNotificationMethod();
        this.preferredDateFormat = merchantRequest.getPreferredDateFormat();
        this.preferredTimeZone = merchantRequest.getPreferredTimeZone();
        this.securityStamp = merchantRequest.getSecurityStamp();
        this.termsConditionsAccepted = merchantRequest.getTermsConditionsAccepted();
        this.privacyPolicyAccepted = merchantRequest.getPrivacyPolicyAccepted();
        this.pricingPolicyAccepted = merchantRequest.getPricingPolicyAccepted();
        this.marketingEmailSubscription = merchantRequest.getMarketingEmailSubscription();
        this.remarks = merchantRequest.getRemarks();
        this.extMapId1 = merchantRequest.getExtMapId1();
        this.extMapId2 = merchantRequest.getExtMapId2();
        this.extMapId3 = merchantRequest.getExtMapId3();
        this.refCol1 = merchantRequest.getRefCol1();
        this.refCol2 = merchantRequest.getRefCol2();
        this.refCol3 = merchantRequest.getRefCol3();
        this.refCol4 = merchantRequest.getRefCol4();
        this.refCol5 = merchantRequest.getRefCol5();
    	this.entityHash = merchantRequest.getEntityHash();
    }
    

}
