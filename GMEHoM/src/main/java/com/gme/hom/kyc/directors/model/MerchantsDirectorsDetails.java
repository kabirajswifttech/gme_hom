package com.gme.hom.kyc.directors.model;

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
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity(name="Merchants_directors_details")
@Table(name="merchants_directors_details")
@AllArgsConstructor
@NoArgsConstructor
public class MerchantsDirectorsDetails extends PersistenceEntity {
	@Id
	@SequenceGenerator(name = "merchants_directors_seq", sequenceName = "merchants_directors_details_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = SEQUENCE, generator = "merchants_directors_seq")
	@Column(name="id", nullable = false)
    private Long id;
	
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Column(name="merchant_director_id", nullable = false)
    private UUID merchantDirectorId;

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
    private String status;

    @Column(name="is_active")
    private Boolean isActive;

    @Column(name="entity_hash")
    private String entityHash;


    public MerchantsDirectorsDetails(MerchantsDirectorsDetailsRequest merchantsDirectorsDetailsRequest) {
        this.merchantId = merchantsDirectorsDetailsRequest.getMerchantId();
        this.fullName = merchantsDirectorsDetailsRequest.getFullName();
        this.fullNameNative = merchantsDirectorsDetailsRequest.getFullNameNative();
        this.nationality = merchantsDirectorsDetailsRequest.getNationality();
        this.dob = merchantsDirectorsDetailsRequest.getDob();
        this.phoneCode = merchantsDirectorsDetailsRequest.getPhoneCode();
        this.phoneNumber = merchantsDirectorsDetailsRequest.getPhoneNumber();
        this.emailId = merchantsDirectorsDetailsRequest.getEmailId();
        this.residenceCountry = merchantsDirectorsDetailsRequest.getResidenceCountry();
        this.remarks = merchantsDirectorsDetailsRequest.getRemarks();
    } 
}