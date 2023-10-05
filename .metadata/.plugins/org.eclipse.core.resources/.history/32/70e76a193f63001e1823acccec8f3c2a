package com.gme.hom.kyc.stockholders.model;

import static jakarta.persistence.GenerationType.SEQUENCE;

import java.sql.Date;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import com.gme.hom.common.models.PersistenceEntity;
import com.gme.hom.common.models.PersistenceEntityWithUpdateApproval;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "Merchants_stockholders_details")
@Table(name = "merchants_stockholders_details")
public class MerchantsStockholdersDetails extends PersistenceEntityWithUpdateApproval {
	@Id
	@SequenceGenerator(name = "merchants_stockholder_details_seq", sequenceName = "merchants_stockholder_details_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = SEQUENCE, generator = "merchants_stockholder_details_seq")
	@Column(name = "id", nullable = false)
	private long id;

	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
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

	public MerchantsStockholdersDetails(MerchantsStockholdersDetailsRequest merchantsStockholdersDetailsRequest) {
		this.merchantId = merchantsStockholdersDetailsRequest.getMerchantId();
		this.fullName = merchantsStockholdersDetailsRequest.getFullName();
		this.fullNameNative = merchantsStockholdersDetailsRequest.getFullNameNative();
		this.nationality = merchantsStockholdersDetailsRequest.getNationality();
		this.dob = merchantsStockholdersDetailsRequest.getDob();
		this.phoneCode = merchantsStockholdersDetailsRequest.getPhoneCode();
		this.phoneNumber = merchantsStockholdersDetailsRequest.getPhoneNumber();
		this.emailId = merchantsStockholdersDetailsRequest.getEmailId();
		this.residenceCountry = merchantsStockholdersDetailsRequest.getResidenceCountry();
		this.percentageOfShare = merchantsStockholdersDetailsRequest.getPercentageOfShare();
		this.status = merchantsStockholdersDetailsRequest.getStatus();
	}
}
