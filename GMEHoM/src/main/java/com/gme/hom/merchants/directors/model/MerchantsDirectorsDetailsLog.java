package com.gme.hom.merchants.directors.model;

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
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity(name="Merchants_directors_details_log")
@Table(name="merchants_directors_details_log")
@AllArgsConstructor
@NoArgsConstructor
public class MerchantsDirectorsDetailsLog {
	@Id
	@SequenceGenerator(name = "merchants_directors_log_seq", sequenceName = "merchants_directors_details_log_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = SEQUENCE, generator = "merchants_directors_log_seq")
	@Column(name="id", nullable = false)
    private Long id;
		
	@Column(name="director_id", nullable = false)
	private Long directorId;
    
	@Column(name="director_uuid", nullable = false)
    private UUID directorUuid;

	@NotNull
    @Column(name="merchant_id", nullable = false)
    private Long merchantId;
	
	@NotNull
    @Column(name="full_name", nullable = false)
    private String fullName;

    @Column(name="full_name_native")
    private String fullNameNative;

    @Column(name="nationality")
    private String nationality;

    @Column(name="dob")
    private Date dob;

    @Column(name="phone_code")
    private String phoneCode;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="email_id")
    private String emailId;

    @Column(name="residence_country")
    private String residenceCountry;

    @Column(name="remarks")
    private String remarks;

    @Column(name="status")
    private MerchantStatusCodes status;

    @Column(name="is_active")
    private Boolean isActive;

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


	public MerchantsDirectorsDetailsLog(MerchantsDirectorsDetails m) {
		this.directorUuid =m.getDirectorUuid();
		this.directorId =m.getId();
		this.merchantId = m.getMerchantId();
		this.fullName = m.getFullName();
		this.fullNameNative = m.getFullNameNative();
		this.nationality = m.getNationality();
		this.dob = m.getDob();
		this.phoneCode = m.getPhoneCode();
		this.phoneNumber = m.getPhoneNumber();
		this.emailId = m.getEmailId();
		this.residenceCountry = m.getResidenceCountry();
		this.remarks = m.getRemarks();
		this.status = m.getStatus();
		this.isActive = m.getIsActive();
		this.entityHash = m.getEntityHash();
	} 
    
}