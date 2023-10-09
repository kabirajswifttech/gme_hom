package com.gme.hom.merchants.representatives.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.UUID;

import com.gme.hom.merchants.config.MerchantStatusCodes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="Merchants_representative_details_log")
@Table(name="merchants_representative_details_log")
public class MerchantsRepresentativeDetailsLog{
	@Id
	@SequenceGenerator(name = "merchants_representative_details_seq", sequenceName = "merchants_representative_details_log_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "merchants_representative_details_seq")
	@Column(name="id", nullable = false)
    private Long id;

    @Column(name="representative_id")
    private Long representativeId;

    @Column(name="representative_uuid")
    private UUID representativeUuid;

    @Column(name="merchant_id", nullable = false)
    private Long merchantId;

    @Column(name="first_name")
    private String firstName;

    @Column(name="middle_name")
    private String middleName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="full_name")
    private String fullName;

    @Column(name="full_name_native")
    private String fullNameNative;

    @Column(name="designation")
    private String designation;

    @Column(name="nationality")
    private String nationality;

    @Column(name="phone_code")
    private String phoneCode;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="email_id")
    private String emailId;

    @Column(name="dob")
    private Date dob;

    @Column(name="residence_country")
    private String residenceCountry;

    @Column(name="postal_code")
    private String postalCode;

    @Column(name="address1")
    private String address1;

    @Column(name="address2")
    private String address2;

    @Column(name="city")
    private String city;

    @Column(name="remarks")
    private String remarks;

    @Enumerated(EnumType.STRING)
    @Column(name="status", nullable = false)
    private MerchantStatusCodes status;

    @Column(name="is_active", nullable = false)
    private boolean isActive;

    @Column(name="entity_hash")
    private String entityHash;

    @Column(name="created_by", nullable = false)
    private String createdBy;

    @Column(name="created_date", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdDate;

    @Column(name="created_date_utc", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdDateUtc;

	public MerchantsRepresentativeDetailsLog(MerchantsRepresentativeDetails m) {

	this.id = m.getId();
	this.representativeId = m.getId();
	this.representativeUuid = m.getRepresentativeUuid();
	this.merchantId = m.getMerchantId();
	this.firstName = m.getFirstName();
	this.middleName = m.getMiddleName();
	this.lastName = m.getLastName();
	this.fullName = m.getFullName();
	this.fullNameNative = m.getFullNameNative();
	this.designation = m.getDesignation();
	this.nationality = m.getNationality();
	this.phoneCode = m.getPhoneCode();
	this.phoneNumber = m.getPhoneNumber();
	this.emailId = m.getEmailId();
	this.dob = m.getDob();
	this.residenceCountry = m.getResidenceCountry();
	this.postalCode = m.getPostalCode();
	this.address1 = m.getAddress1();
	this.address2 = m.getAddress2();
	this.city = m.getCity();
	this.status = m.getStatus();
	this.isActive = m.getIsActive();
	this.entityHash = m.getEntityHash();
} 
}


//
//import static jakarta.persistence.GenerationType.SEQUENCE;
//
//import java.sql.Date;
//import java.util.UUID;
//
//import com.gme.hom.merchants.config.MerchantStatusCodes;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.EnumType;
//import jakarta.persistence.Enumerated;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.Id;
//import jakarta.persistence.SequenceGenerator;
//import jakarta.persistence.Table;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.ToString;
//
//@Getter
//@Setter
//@Entity(name="Merchants_representative_details_log")
//@Table(name="merchants_representative_details_log")
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString
//
//public class MerchantsRepresentativeDetailsLog {
//	@Id
//	@SequenceGenerator(name = "merchants_representative_details_seq", sequenceName = "merchants_representative_details_log_id_seq", allocationSize = 1)
//	@GeneratedValue(strategy = SEQUENCE, generator = "merchants_representative_details_seq")
//	@Column(name="id", nullable = false)
//    private Long id;
//	
//	@Column(name="representative_id", nullable = false)
//	private Long representativeId;
//	
//    @Column(name="representative_uuid", nullable = false)
//    private UUID representativeUuid;
//
//    @Column(name="merchant_id", nullable = false)
//    private long merchantId;
//
//    @Column(name="first_name", nullable = false)
//    private String firstName;
//
//    @Column(name="middle_name")
//    private String middleName;
//
//    @Column(name="last_name")
//    private String lastName;
//
//    @Column(name="full_name")
//    private String fullName;
//
//    @Column(name="full_name_native")
//    private String fullNameNative;
//
//    @Column(name="designation")
//    private String designation;
//
//    @Column(name="nationality")
//    private String nationality;
//
//    @Column(name="phone_code")
//    private String phoneCode;
//
//    @Column(name="phone_number")
//    private String phoneNumber;
//
//    @Column(name="email_id")
//    private String emailId;
//
//    @Column(name="dob")
//    private Date dob;
//
//    @Column(name="residence_country")
//    private String residenceCountry;
//
//    @Column(name="postal_code")
//    private String postalCode;
//
//    @Column(name="address1")
//    private String address1;
//
//    @Column(name="address2")
//    private String address2;
//
//    @Column(name="city")
//    private String city;
//    
//    @Column(name="remarks")
//    private String remarks;
//    
//    @Enumerated(EnumType.STRING)
//    @Column(name="status")
//    private MerchantStatusCodes status;
//
//    @Column(name="is_active")
//    private Boolean isActive;
//    
//    @Column(name="entity_hash")
//    private String entityHash;
//
//    @Column(name = "created_by", nullable = false)
//	private String createdBy;
//
//	// @CreationTimestamp
//	// @GeneratedValue
//	@Column(name = "created_date", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
//	private Date createdDate;
//
//	// @CreationTimestamp
//	@Column(name = "created_date_utc", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
//	private Date createdDateUTC;
//
//   	public MerchantsRepresentativeDetailsLog(MerchantsRepresentativeDetails m) {
//
//		this.id = m.getId();
//		this.representativeId = m.getId();
//		this.representativeUuid = m.getRepresentativeUuid();
//		this.merchantId = m.getMerchantId();
//		this.firstName = m.getFirstName();
//		this.middleName = m.getMiddleName();
//		this.lastName = m.getLastName();
//		this.fullName = m.getFullName();
//		this.fullNameNative = m.getFullNameNative();
//		this.designation = m.getDesignation();
//		this.nationality = m.getNationality();
//		this.phoneCode = m.getPhoneCode();
//		this.phoneNumber = m.getPhoneNumber();
//		this.emailId = m.getEmailId();
//		this.dob = m.getDob();
//		this.residenceCountry = m.getResidenceCountry();
//		this.postalCode = m.getPostalCode();
//		this.address1 = m.getAddress1();
//		this.address2 = m.getAddress2();
//		this.city = m.getCity();
//		this.status = m.getStatus();
//		this.isActive = m.getIsActive();
//		this.entityHash = m.getEntityHash();
//	} 
//    
//    
//}
