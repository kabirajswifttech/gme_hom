package com.gme.hom.kyc.owners.model;

import static jakarta.persistence.GenerationType.SEQUENCE;

import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

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
@Entity(name="Merchants_owners_details")
@Table(name="merchants_owners_details")
public class MerchantsOwnersDetails extends PersistenceEntityWithUpdateApproval {
    @Id
    @SequenceGenerator(name = "merchants_owners_seq", sequenceName = "merchants_owners_details_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = SEQUENCE, generator = "merchants_owners_seq")
	@Column(name="id", nullable = false)
    private Long id;

    @GenericGenerator(name = "uuid2", strategy = "uuid2")
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Column(name="merchant_owener_id")
    private UUID merchantOwenerId;

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


    public MerchantsOwnersDetails(MerchantsOwnersDetailsRequest merchantOwnersDetailsRequest) {
        this.merchantId = merchantOwnersDetailsRequest.getMerchantId();
        this.fullName = merchantOwnersDetailsRequest.getFullName();
        this.fullNameNative = merchantOwnersDetailsRequest.getFullNameNative();
        this.role = merchantOwnersDetailsRequest.getRole();
        this.nationality = merchantOwnersDetailsRequest.getNationality();
        this.residenceCountry = merchantOwnersDetailsRequest.getResidenceCountry();
        this.phoneCode = merchantOwnersDetailsRequest.getPhoneCode();
        this.phoneNumber = merchantOwnersDetailsRequest.getPhoneNumber();
        this.emailId = merchantOwnersDetailsRequest.getEmailId();
    } 
}