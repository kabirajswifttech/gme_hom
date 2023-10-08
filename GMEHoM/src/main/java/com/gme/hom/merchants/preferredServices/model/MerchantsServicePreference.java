package com.gme.hom.merchants.preferredServices.model;

import static jakarta.persistence.GenerationType.SEQUENCE;

import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import com.gme.hom.common.models.PersistenceEntity;
import com.gme.hom.merchants.config.MerchantStatusCodes;

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
@Entity(name="Merchants_service_preferences")
@Table(name="merchants_service_preferences")
@AllArgsConstructor
@NoArgsConstructor
public class MerchantsServicePreference extends PersistenceEntity{
    @Id
    @SequenceGenerator(name = "merchants_service_preference_seq", sequenceName = "merchants_service_preferences_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = SEQUENCE, generator = "merchants_service_preference_seq")
	@Column(name="id", nullable = false)
    private long id;
    
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Column(name="merchant_service_preference_id")
    private UUID merchantServicePreferenceId = UUID.randomUUID();

    @Column(name="merchant_id", nullable = false)
    private long merchantId;

    @Column(name="service_type")
    private String serviceType;

    @Column(name="service_id")
    private String serviceId;

    @Column(name="status")
    private MerchantStatusCodes status;

    @Column(name="is_active")
    private boolean isActive;

    @Column(name="entity_hash")
    private String entityHash;

    public MerchantsServicePreference(MerchantsServicePreferenceRequest merchantServicePreferenceRequest) {
        this.merchantId = merchantServicePreferenceRequest.getMerchantId();
        this.serviceType = merchantServicePreferenceRequest.getServiceType();
        this.serviceId = merchantServicePreferenceRequest.getServiceId();
    } 
}
