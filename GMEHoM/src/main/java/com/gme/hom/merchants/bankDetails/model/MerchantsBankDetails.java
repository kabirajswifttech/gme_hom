package com.gme.hom.merchants.bankDetails.model;

import static jakarta.persistence.GenerationType.SEQUENCE;

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
@Entity(name="Merchants_bank_details")
@Table(name="merchants_bank_details")
@AllArgsConstructor
@NoArgsConstructor
public class MerchantsBankDetails extends PersistenceEntity{
    @Id
    @SequenceGenerator(name = "merchants_bank_details_seq", sequenceName = "merchants_bank_details_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = SEQUENCE, generator = "merchants_bank_details_seq")
	@Column(name="id", nullable = false)
    private Long id;
    
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Column(name="merchant_bank_id", nullable = false)
    private UUID merchantBankId;

    @Column(name="merchant_id", nullable = false)
    private Long merchantId;

    @Column(name="bank_id", nullable = false)
    private String bankId;

    @Column(name="account_name", nullable = false)
    private String accountName;

    @Column(name="account_number", nullable = false)
    private String accountNumber;

    @Column(name="swift_code")
    private String swiftCode;

    @Column(name="bic_code")
    private String bicCode;

    @Column(name="ifsc_code")
    private String ifscCode;

    @Column(name="iban_cbu_card_number")
    private String ibanCbuCardNumber;

    @Column(name="is_verified")
    private Boolean isVerified;

    @Column(name="status")
    private String status;

    @Column(name="is_active")
    private Boolean isActive;

    @Column(name="entity_hash")
    private String entityHash;



    public MerchantsBankDetails(MerchantsBankDetailsRequest merchantBankDetailsRequest) {
       this.merchantId = merchantBankDetailsRequest.getMerchantId();
        this.bankId = merchantBankDetailsRequest.getBankId();
        this.accountName = merchantBankDetailsRequest.getAccountName();
        this.accountNumber = merchantBankDetailsRequest.getAccountNumber();
        this.swiftCode = merchantBankDetailsRequest.getSwiftCode();
        this.bicCode = merchantBankDetailsRequest.getBicCode();
        this.ifscCode = merchantBankDetailsRequest.getIfscCode();
        this.ibanCbuCardNumber = merchantBankDetailsRequest.getIbanCbuCardNumber();
    } 
}
