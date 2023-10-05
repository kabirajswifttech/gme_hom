package com.gme.hom.kyc.stockholders.model;

import static jakarta.persistence.GenerationType.SEQUENCE;

import java.sql.Date;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import com.gme.hom.common.models.PersistenceEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "Merchants_stockholders_details_log")
@Table(name = "merchants_stockholders_details_log")
@AllArgsConstructor
@NoArgsConstructor
public class MerchantsStockholdersDetailsLog extends PersistenceEntity {
	@Id
	@SequenceGenerator(name = "merchants_stockholder_details_seq", sequenceName = "merchants_stockholders_details_log_log_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = SEQUENCE, generator = "merchants_stockholder_details_seq")
	@Column(name="log_id", nullable = false)
    private Long logId;
    
	@Column(name = "id", nullable = false)
	private long id;

    @Column(name = "merchant_stockholder_id")
	private UUID merchantStockholderId;

	@Column(name = "merchant_id", nullable = false)
	private long merchantId;

	@Column(name = "full_name")
	private String fullName;

	@Column(name = "full_name_native")
	private String fullNameNative;

	@Column(name = "nationality")
	private String nationality;

	@Column(name = "dob")
	private Date dob;

	@Column(name = "phone_code")
	private String phoneCode;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "email_id")
	private String emailId;

	@Column(name = "residence_country")
	private String residenceCountry;

	@Column(name = "percentage_of_share")
	private Float percentageOfShare;

	@Column(name = "status")
	private String status;

	@Column(name = "is_active")
	private boolean isActive;

	@Column(name = "remarks")
	private String remarks;

	@Column(name = "entity_hash")
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



	public MerchantsStockholdersDetailsLog(MerchantsStockholdersDetails m) {
	
		this.id = m.getId();
		this.merchantStockholderId = m.getMerchantStockholderId();
		this.merchantId = m.getMerchantId();
		this.fullName = m.getFullName();
		this.fullNameNative = m.getFullNameNative();
		this.nationality = m.getNationality();
		this.dob = m.getDob();
		this.phoneCode = m.getPhoneCode();
		this.phoneNumber = m.getPhoneNumber();
		this.emailId = m.getEmailId();
		this.residenceCountry = m.getResidenceCountry();
		this.percentageOfShare = m.getPercentageOfShare();
		this.status = m.getStatus();
		this.isActive = m.isActive();
		this.remarks = m.getRemarks();
		this.entityHash = m.getEntityHash();
	}
	
	
}