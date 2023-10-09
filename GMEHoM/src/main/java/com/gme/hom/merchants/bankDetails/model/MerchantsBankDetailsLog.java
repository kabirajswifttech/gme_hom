package com.gme.hom.merchants.bankDetails.model;

import static jakarta.persistence.GenerationType.SEQUENCE;

import java.util.UUID;

import com.gme.hom.common.models.PersistenceEntity;
import com.gme.hom.merchants.config.MerchantStatusCodes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity(name="Merchants_bank_details_log")
@Table(name="merchants_bank_details_log")
@AllArgsConstructor
@NoArgsConstructor
public class MerchantsBankDetailsLog extends PersistenceEntity{
    @Id
    @SequenceGenerator(name = "merchants_bank_details_seq", sequenceName = "merchants_bank_details_log_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = SEQUENCE, generator = "merchants_bank_details_seq")
    @Column(name="id", nullable = false)
    private Long Id;

	@Column(name="bank_details_id", nullable = false)
    private Long bankDetailsId;
	
    @Column(name="bank_details_uuid", nullable = false)
    private UUID bankDetailsUuid;

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
    
    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private MerchantStatusCodes status;

    @Column(name="is_active")
    private Boolean isActive;

    @Column(name="entity_hash")
    private String entityHash;


 


	public MerchantsBankDetailsLog(MerchantsBankDetails m) {

		this.bankDetailsId = m.getId();
		this.bankDetailsUuid =m.getBankUuid();
		this.merchantId = m.getMerchantId();
		this.bankId = m.getBankId();
		this.accountName = m.getAccountName();
		this.accountNumber = m.getAccountNumber();
		this.swiftCode = m.getSwiftCode();
		this.bicCode = m.getBicCode();
		this.ifscCode = m.getIfscCode();
		this.ibanCbuCardNumber = m.getIbanCbuCardNumber();
		this.isVerified = m.getIsVerified();
		this.status = m.getStatus();
		this.isActive = m.getIsActive();
		this.entityHash = m.getEntityHash();
	} 
    
}
