package com.gme.hom.kyc.bankDetails.model;

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
@Entity(name="Merchants_bank_details_log")
@Table(name="merchants_bank_details_log")
@AllArgsConstructor
@NoArgsConstructor
public class MerchantsBankDetailsLog{
    @Id
    @SequenceGenerator(name = "merchants_bank_details_seq", sequenceName = "merchants_bank_details_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = SEQUENCE, generator = "merchants_bank_details_seq")
    @Column(name="log_id", nullable = false)
    private Long logId;
    
	@Column(name="id", nullable = false)
    private Long id;
    
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

	@Column(name = "created_by", nullable = false)
	private String createdBy;

	// @CreationTimestamp
	// @GeneratedValue
	@Column(name = "created_date", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
	private Date createdDate;

	// @CreationTimestamp
	@Column(name = "created_date_utc", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
	private Date createdDateUTC;


 


	public MerchantsBankDetailsLog(MerchantsBankDetails m) {

		this.id = m.getId();
		this.merchantBankId =m.getMerchantBankId();
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
