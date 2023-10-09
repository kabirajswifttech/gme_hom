package com.gme.hom.merchants.representatives.model;

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
@Entity(name="Merchants_representative_details")
@Table(name="merchants_representative_details")
@AllArgsConstructor
@NoArgsConstructor

public class MerchantsRepresentativeDetails extends PersistenceEntityWithUpdate implements Serializable{

	private static final long serialVersionUID = -1649894393654961445L;

	@Id
	@SequenceGenerator(name = "merchants_representative_details_seq", sequenceName = "merchants_representative_details_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = SEQUENCE, generator = "merchants_representative_details_seq")
	@Column(name="id", nullable = false)
    private Long id;

    @Column(name="representative_uuid", nullable = false)
    private UUID representativeUuid = UUID.randomUUID();

    @Column(name="merchant_id", nullable = false)
    private Long merchantId;

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

    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private MerchantStatusCodes status;

    @Column(name="entity_hash")
    private String entityHash;

    public MerchantsRepresentativeDetails(MerchantsRepresentativeDetailsRequest merchantRepresentativeRequest) {
        this.merchantId = merchantRepresentativeRequest.getMerchantId();
        this.firstName = merchantRepresentativeRequest.getFirstName();
        this.middleName = merchantRepresentativeRequest.getMiddleName();
        this.lastName = merchantRepresentativeRequest.getLastName();
        this.fullName = merchantRepresentativeRequest.getFullName();
        this.fullNameNative = merchantRepresentativeRequest.getFullNameNative();
        this.designation = merchantRepresentativeRequest.getDesignation();
        this.nationality = merchantRepresentativeRequest.getNationality();
        this.phoneCode = merchantRepresentativeRequest.getPhoneCode();
        this.phoneNumber = merchantRepresentativeRequest.getPhoneNumber();
        this.emailId = merchantRepresentativeRequest.getEmailId();
        this.dob = merchantRepresentativeRequest.getDob();
        this.residenceCountry = merchantRepresentativeRequest.getResidenceCountry();
        this.postalCode = merchantRepresentativeRequest.getPostalCode();
        this.address1 = merchantRepresentativeRequest.getAddress1();
        this.address2 = merchantRepresentativeRequest.getAddress2();
        this.city = merchantRepresentativeRequest.getCity();
    } 
}
