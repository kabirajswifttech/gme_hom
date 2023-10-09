package com.gme.hom.merchants.stockholders.model;

import static jakarta.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import java.sql.Date;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import com.gme.hom.common.models.PersistenceEntityWithUpdate;
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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "Merchants_stockholders_details")
@Table(name = "merchants_stockholders_details")
@AllArgsConstructor
@NoArgsConstructor
public class MerchantsStockholdersDetails extends PersistenceEntityWithUpdate implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5297631251152899567L;

	@Id
	@SequenceGenerator(name = "merchants_stockholder_details_seq", sequenceName = "merchants_stockholders_details_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = SEQUENCE, generator = "merchants_stockholder_details_seq")
	@Column(name = "id", nullable = false)
	private long id;

	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Column(name = "stockholder_uuid")
	private UUID stockholderUuid = UUID.randomUUID();

	@Column(name = "merchant_id", nullable = false)
	private Long merchantId;

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

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private MerchantStatusCodes status;

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
