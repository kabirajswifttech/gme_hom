package com.gme.hom.merchants.kyc.model;

import java.sql.Date;
import java.util.UUID;

import com.gme.hom.merchants.config.MerchantStatusCodes;
import com.gme.hom.merchants.config.MerchantType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
@Entity(name = "Merchant_log")
@Table(name = "merchants_log")
public class MerchantLog {

	@Id
	@SequenceGenerator(name = "merchants_log_id_seq", sequenceName = "merchants_log_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "merchants_log_id_seq")
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;

	@Column(name = "uuid", updatable = false, nullable = false)
	private UUID uuid;
	
	@Column(name = "merchant_id", updatable = false, nullable = false)
	private Long merchantId;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "merchant_type")
	private MerchantType merchantType;

	@Email(message = "Invalid email address")
	@NotNull(message = "email field cannot be empty")
	@Column(name = "email_id")
	private String emailId;

	@Column(name = "phone_code")
	private String phoneCode;

	@Pattern(regexp = "\\d+", message = "Phone number can have only numbers")
	@Column(name = "phone_number")
	private String phoneNumber;

	@NotNull
	@Column(name = "incorporation_country", nullable = false)
	private String incorporationCountry;

	@Column(name = "business_name")
	private String businessName;

	@Column(name = "business_name_native")
	private String businessNameNative;

	@Column(name = "business_type", nullable = false)
	private String businessType;

	@Column(name = "industry_type")
	private String industryType;

	@Column(name = "product_type")
	private String productType;

	@Column(name = "business_nature")
	private String businessNature;

	@Column(name = "incorporation_date")
	private Date incorporationDate;

	@Column(name = "bizz_reg_no")
	private String bizzRegNo;

	@Column(name = "corp_reg_no")
	private String corpRegNo;

	@Column(name = "business_profile")
	private String businessProfile;

	@Column(name = "postal_code")
	private String postalCode;

	@Column(name = "address1")
	private String address1;

	@Column(name = "address2")
	private String address2;

	@Column(name = "city")
	private String city;

	@Column(name = "website")
	private String website;

	@Column(name = "currency_preference")
	private String currencyPreference;

	@Column(name = "approx_txn_monthly_volume")
	private Integer approxTxnMonthlyVolume;

	@Column(name = "approx_txn_yearly_volume")
	private Integer approxTxnYearlyVolume;

	@Column(name = "kyc_status")
	private String kycStatus;

	@Column(name = "kyc_remarks")
	private String kycRemarks;

	@Column(name = "rba_status")
	private String rbaStatus;

	@Column(name = "rba_remarks")
	private String rbaRemarks;

	@Column(name = "compliance_status")
	private String complianceStatus;

	@Column(name = "compliance_remarks")
	private String complianceRemarks;

	@Column(name = "doc_path")
	private String docPath;

	@Column(name = "notification_method")
	private String notificationMethod;

	@Column(name = "preferred_date_format")
	private String preferredDateFormat;

	@Column(name = "preferred_time_zone")
	private String preferredTimeZone;

	@Column(name = "security_stamp")
	private UUID securityStamp;

	@Column(name = "terms_conditions_accepted")
	private Boolean termsConditionsAccepted;

	@Column(name = "privacy_policy_accepted")
	private Boolean privacyPolicyAccepted;

	@Column(name = "pricing_policy_accepted")
	private Boolean pricingPolicyAccepted;

	@Column(name = "marketing_email_subscription")
	private Boolean marketingEmailSubscription;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private MerchantStatusCodes status;

	@Column(name = "is_active")
	private Boolean isActive;

	@Column(name = "remarks")
	private String remarks;

	@Column(name = "ext_map_id_1")
	private String extMapId1;

	@Column(name = "ext_map_id_2")
	private String extMapId2;

	@Column(name = "ext_map_id_3")
	private String extMapId3;

	@Column(name = "ref_col_1")
	private String refCol1;

	@Column(name = "ref_col_2")
	private String refCol2;

	@Column(name = "ref_col_3")
	private String refCol3;

	@Column(name = "ref_col_4")
	private String refCol4;

	@Column(name = "ref_col_5")
	private String refCol5;

	@Column(name = "entity_hash")
	private String entityHash;

	@Column(name = "created_by", nullable = false)
	private String createdBy;

	// @CreationTimestamp
	// @GeneratedValue
	@Column(name = "created_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
	private Date createdDate;

	// @CreationTimestamp
	@Column(name = "created_date_utc", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
	private Date createdDateUTC;

	public MerchantLog(Merchant m) {
		super();
		// this.id = m.getId();
		this.merchantId = m.getId();
		this.uuid = m.getUuid();
		this.merchantType = m.getMerchantType();
		this.emailId = m.getEmailId();
		this.phoneCode = m.getPhoneCode();
		this.phoneNumber = m.getPhoneNumber();
		this.incorporationCountry = m.getIncorporationCountry();
		this.businessName = m.getBusinessName();
		this.businessNameNative = m.getBusinessNameNative();
		this.businessType = m.getBusinessType();
		this.industryType = m.getIndustryType();
		this.productType = m.getProductType();
		this.businessNature = m.getBusinessNature();
		this.incorporationDate = m.getIncorporationDate();
		this.bizzRegNo = m.getBizzRegNo();
		this.corpRegNo = m.getCorpRegNo();
		this.businessProfile = m.getBusinessProfile();
		this.postalCode = m.getPostalCode();
		this.address1 = m.getAddress1();
		this.address2 = m.getAddress2();
		this.city = m.getCity();
		this.website = m.getWebsite();
		this.currencyPreference = m.getCurrencyPreference();
		this.approxTxnMonthlyVolume = m.getApproxTxnMonthlyVolume();
		this.approxTxnYearlyVolume = m.getApproxTxnYearlyVolume();
		this.kycStatus = m.getKycStatus();
		this.kycRemarks = m.getKycRemarks();
		this.rbaStatus = m.getRbaStatus();
		this.rbaRemarks = m.getRbaRemarks();
		this.complianceStatus = m.getComplianceStatus();
		this.complianceRemarks = m.getComplianceRemarks();
		this.docPath = m.getDocPath();
		this.notificationMethod = m.getNotificationMethod();
		this.preferredDateFormat = m.getPreferredDateFormat();
		this.preferredTimeZone = m.getPreferredTimeZone();
		this.securityStamp = m.getSecurityStamp();
		this.termsConditionsAccepted = m.getTermsConditionsAccepted();
		this.privacyPolicyAccepted = m.getPrivacyPolicyAccepted();
		this.pricingPolicyAccepted = m.getPricingPolicyAccepted();
		this.marketingEmailSubscription = m.getMarketingEmailSubscription();
		this.status = m.getStatus();
		this.isActive = m.getIsActive();
		this.remarks = m.getRemarks();
		this.extMapId1 = m.getExtMapId1();
		this.extMapId2 = m.getExtMapId2();
		this.extMapId3 = m.getExtMapId3();
		this.refCol1 = m.getRefCol1();
		this.refCol2 = m.getRefCol2();
		this.refCol3 = m.getRefCol3();
		this.refCol4 = m.getRefCol4();
		this.refCol5 = m.getRefCol5();
		this.entityHash = m.getEntityHash();
		this.createdBy = m.getCreatedBy();
	}

}
