package com.gme.hom.merchants.owners.model;

import static jakarta.persistence.GenerationType.SEQUENCE;

import java.sql.Date;
import java.util.UUID;

import com.gme.hom.merchants.config.MerchantStatusCodes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Entity(name="Merchants_owners_details_log")
@Table(name="merchants_owners_details_log")
@AllArgsConstructor
@NoArgsConstructor
public class MerchantsOwnersDetailsLog {
    @Id
    @SequenceGenerator(name = "merchants_owners_seq", sequenceName = "merchants_owners_details_log_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = SEQUENCE, generator = "merchants_owners_seq")
    @Column(name="id", nullable = false)
    private Long id;

    @Column(name="owner_id")
    private Long ownerId;
    
    @Column(name="owner_uuid")
    private UUID ownerUuid;

    @Column(name="merchant_id", nullable = false)
    private long merchantId;

    @Column(name="full_name")
    private String fullName;

    @Column(name="full_name_native")
    private String fullNameNative;

    @Column(name="role")
    private String role;

    @Column(name="nationality")
    private String nationality;

    @Column(name="residence_country")
    private String residenceCountry;

    @Column(name="phone_code")
    private String phoneCode;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="email_id")
    private String emailId;
    
    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private MerchantStatusCodes status;

	@Column(name = "created_by", nullable = false)
	private String createdBy;

	// @CreationTimestamp
	// @GeneratedValue
	@Column(name = "created_date", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
	private Date createdDate;

	// @CreationTimestamp
	@Column(name = "created_date_utc", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
	private Date createdDateUTC;


	public MerchantsOwnersDetailsLog(MerchantsOwnersDetails m) {
	
		this.id = m.getId();
		this.ownerId = m.getId();
		this.ownerUuid = m.getOwnerUuid();
		this.merchantId = m.getMerchantId();
		this.fullName = m.getFullName();
		this.fullNameNative = m.getFullNameNative();
		this.role = m.getRole();
		this.nationality = m.getNationality();
		this.residenceCountry = m.getResidenceCountry();
		this.phoneCode = m.getPhoneCode();
		this.phoneNumber = m.getPhoneNumber();
		this.emailId = m.getEmailId();
		this.status = m.getStatus();
	} 
    
    
    
}