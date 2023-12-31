package com.gme.hom.merchants.kyc.model;

import static jakarta.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import java.sql.Date;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import com.gme.hom.common.models.PersistenceEntityWithUpdateApproval;
import com.gme.hom.merchants.config.MerchantStatusCodes;
import com.gme.hom.merchants.config.MerchantType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
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
@Entity(name = "Merchant")
@Table(name = "merchants")
public class Merchant extends PersistenceEntityWithUpdateApproval implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1702359111843567528L;

	@Id
	@SequenceGenerator(name = "merchants_id_seq", sequenceName = "merchants_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = SEQUENCE, generator = "merchants_id_seq")
	@Column(name="id", nullable = false, updatable = false)
    private Long id;
	
	@GenericGenerator(strategy="org.hibernate.id.UUIDGenerator",name = "uuid2")
	@GeneratedValue(generator = "uuid2")
    @Column(name="uuid", updatable = false, nullable = false)
    private UUID uuid = UUID.randomUUID();
	
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

    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private MerchantStatusCodes status;

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

    

    public Merchant(MerchantRequest merchantRequest) {
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




	public Merchant(Long id, UUID uuid, MerchantType merchantType, String emailId, String phoneNumber,
			String incorporationCountry, String businessName, String businessNameNative, String businessType,String productType,
			String businessNature, Date incorporationDate, String bizzRegNo, String corpRegNo,
			String businessProfile, String postalCode, String address1, String address2, String city, String website,
			String currencyPreference, Integer approxTxnMonthlyVolume, Integer approxTxnYearlyVolume,
			String kycStatus, String kycRemarks, String rbaStatus,
			String rbaRemarks, String complianceStatus, String complianceRemarks, String docPath,
			String notificationMethod, String preferredDateFormat, String preferredTimeZone) {
		super();
		this.id = id;
		this.uuid = uuid;
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




//	public Merchant(UserSignup userSignup) {
//        this.id = userSignup.getId();
//        //this.merchantType = userSignup.getMerchantType();
//        //this.emailId = userSignup.getEmailId();
//        this.phoneCode = userSignup.getPhoneCode();
//        this.phoneNumber = userSignup.getPhoneNumber();
//        this.incorporationCountry = userSignup.getIncorporationCountry();
//        this.status = userSignup.getStatus();
//        this.isActive = userSignup.isActive();
//        this.remarks = userSignup.getRemarks();
//    } 
	
	



	
	

	
	
	
	

}
