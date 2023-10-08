package com.gme.hom.merchants.representatives.model;

import static jakarta.persistence.GenerationType.SEQUENCE;

import java.sql.Date;
import java.util.UUID;

import com.gme.hom.merchants.config.MerchantStatusCodes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity(name="Merchants_representative_details_log")
@Table(name="merchants_representative_details_log")
@AllArgsConstructor
@NoArgsConstructor

public class MerchantsRepresentativeDetailsLog {
	@Id
	@SequenceGenerator(name = "merchants_representative_details_seq", sequenceName = "merchants_representative_details_log_log_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = SEQUENCE, generator = "merchants_representative_details_seq")
	@Column(name="log_id", nullable = false)
    private Long logId;
    
	@Column(name="id", nullable = false)
    private long id;
	
    @Column(name="merchant_representative_id", nullable = false)
    private UUID merchantRepresentativeId;

    @Column(name="merchant_id", nullable = false)
    private long merchantId;

    @Column(name="first_name", nullable = false)
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

    @Column(name="status")
    private MerchantStatusCodes status;

    @Column(name="is_active")
    private boolean isActive;

    @Column(name="entity_hash")
    private String entityHash;
    
	@Column(name = "created_by", nullable = false)
	private String createdBy;

	// @CreationTimestamp
	// @GeneratedValue
	@Column(name = "created_date", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
	private Date createdDate;

	// @CreationTimestamp
	@Column(name = "created_date_utc", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
	private Date createdDateUTC;

   	public MerchantsRepresentativeDetailsLog(MerchantsRepresentativeDetails m) {

		this.id = m.getId();
		this.merchantRepresentativeId = m.getMerchantRepresentativeId();
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
