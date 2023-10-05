package com.gme.hom.kyc.preferredServices.model;

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
@Entity(name="Merchants_service_preferences_log")
@Table(name="merchants_service_preferences_log")
@AllArgsConstructor
@NoArgsConstructor
public class MerchantsServicePreferenceLog {
    @Id
    @SequenceGenerator(name = "merchants_service_preference_seq", sequenceName = "merchants_service_preferences_log_log_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = SEQUENCE, generator = "merchants_service_preference_seq")
    @Column(name="log_id", nullable = false)
    private Long logId;
    
	@Column(name="id", nullable = false)
    private long id;
    
    @Column(name="merchant_service_preference_id")
    private UUID merchantServicePreferenceId;

    @Column(name="merchant_id", nullable = false)
    private long merchantId;

    @Column(name="service_type")
    private String serviceType;

    @Column(name="service_id")
    private String serviceId;

    @Column(name="status")
    private String status;

    @Column(name="is_active")
    private boolean isActive;

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

   

	public MerchantsServicePreferenceLog(MerchantsServicePreference m) {

		this.id = getId();
		this.merchantServicePreferenceId = m.getMerchantServicePreferenceId();
		this.merchantId = m.getMerchantId();
		this.serviceType = m.getServiceType();
		this.serviceId = m.getServiceId();
		this.status = m.getStatus();
		this.isActive = m.isActive();
		this.entityHash = m.getEntityHash();
	} 
    
    
}
