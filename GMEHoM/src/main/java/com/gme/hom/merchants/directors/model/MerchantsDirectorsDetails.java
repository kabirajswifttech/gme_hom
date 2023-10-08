package com.gme.hom.merchants.directors.model;

import static jakarta.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import java.sql.Date;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import com.gme.hom.common.models.PersistenceEntity;
import com.gme.hom.common.models.PersistenceEntityWithUpdateApproval;
import com.gme.hom.merchants.config.MerchantStatusCodes;

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
public class MerchantsDirectorsDetails extends PersistenceEntity implements Serializable {

	private static final long serialVersionUID = -8241707224809528906L;

	@Id
	@SequenceGenerator(name = "merchants_directors_seq", sequenceName = "merchants_directors_details_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = SEQUENCE, generator = "merchants_directors_seq")
	@Column(name="id", nullable = false)
    private Long id;
	
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Column(name="uuid", nullable = false)
    private UUID uuid = UUID.randomUUID();

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
    
    @Column(name="isVerified")
    private Boolean isVerified;


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