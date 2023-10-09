package com.gme.hom.merchants.preferredServices.model;

import static jakarta.persistence.GenerationType.SEQUENCE;

import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import com.gme.hom.common.models.PersistenceEntity;
import com.gme.hom.common.models.PersistenceEntityWithUpdate;
import com.gme.hom.merchants.config.MerchantStatusCodes;
import com.gme.hom.merchants.config.ServicePreferenceType;

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
@Entity(name="Merchants_service_preferences")
@Table(name="merchants_service_preferences")
@AllArgsConstructor
@NoArgsConstructor
public class MerchantsServicePreference extends PersistenceEntityWithUpdate{
    @Id
    @SequenceGenerator(name = "merchants_service_preference_seq", sequenceName = "merchants_service_preferences_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = SEQUENCE, generator = "merchants_service_preference_seq")
	@Column(name="id", nullable = false)
    private long id;
    
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Column(name="service_preference_uuid")
    private UUID servicePreferenceUuid = UUID.randomUUID();

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

    public MerchantsServicePreference(MerchantsServicePreferenceRequest merchantServicePreferenceRequest) {
        this.merchantId = merchantServicePreferenceRequest.getMerchantId();
        this.serviceType = merchantServicePreferenceRequest.getServiceType();
        this.serviceId = merchantServicePreferenceRequest.getServiceId();
    } 
}
