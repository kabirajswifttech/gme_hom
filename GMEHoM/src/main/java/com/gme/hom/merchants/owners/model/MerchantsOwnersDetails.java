package com.gme.hom.merchants.owners.model;

import static jakarta.persistence.GenerationType.SEQUENCE;

import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import com.gme.hom.common.models.PersistenceEntity;
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
@Entity(name="Merchants_owners_details")
@Table(name="merchants_owners_details")
@AllArgsConstructor
@NoArgsConstructor
public class MerchantsOwnersDetails extends PersistenceEntityWithUpdate {
    @Id
    @SequenceGenerator(name = "merchants_owners_seq", sequenceName = "merchants_owners_details_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = SEQUENCE, generator = "merchants_owners_seq")
	@Column(name="id", nullable = false)
    private Long id;

    @GenericGenerator(name = "uuid2", strategy = "uuid2")
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Column(name="owner_uuid")
    private UUID ownerUuid = UUID.randomUUID();

    @Column(name="merchant_id", nullable = false)
    private Long merchantId;

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