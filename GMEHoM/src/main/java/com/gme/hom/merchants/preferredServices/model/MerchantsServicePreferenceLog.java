package com.gme.hom.merchants.preferredServices.model;

import static jakarta.persistence.GenerationType.SEQUENCE;

import java.sql.Date;
import java.util.UUID;

import com.gme.hom.merchants.config.MerchantStatusCodes;
import com.gme.hom.merchants.config.ServicePreferenceType;

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
@Entity(name="Merchants_service_preferences_log")
@Table(name="merchants_service_preferences_log")
@AllArgsConstructor
@NoArgsConstructor
public class MerchantsServicePreferenceLog {
    @Id
    @SequenceGenerator(name = "merchants_service_preference_seq", sequenceName = "merchants_service_preferences_log_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = SEQUENCE, generator = "merchants_service_preference_seq")
    @Column(name="id", nullable = false)
    private long id;
    
    @Column(name="service_preference_id")
    private Long servicePreferenceId;
    
    @Column(name="service_preference_uuid")
    private UUID servicePreferenceUuid;

    @Column(name="merchant_id", nullable = false)
    private long merchantId;

    @Enumerated(EnumType.STRING)
    @Column(name="service_type")
    private ServicePreferenceType serviceType;

    @Column(name="service_id")
    private String serviceId;

    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private MerchantStatusCodes status;

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
		this.servicePreferenceId = m.getId();
		this.servicePreferenceUuid = m.getServicePreferenceUuid();
		this.merchantId = m.getMerchantId();
		this.serviceType = m.getServiceType();
		this.serviceId = m.getServiceId();
		this.status = m.getStatus();
		this.entityHash = m.getEntityHash();
	} 
    
    
}
