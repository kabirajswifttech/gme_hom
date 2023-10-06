package com.gme.hom.auth.models;

import static jakarta.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.gme.hom.auth.config.OtpStatusCodes;
import com.gme.hom.auth.config.OtpTypes;
import com.gme.hom.common.models.EntityTypes;
import com.gme.hom.common.models.PersistenceEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "OTP")
@Table(name = "otp")
public class Otp extends PersistenceEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3278329331557639201L;

	@Id
	@SequenceGenerator(name = "otp_id_seq", sequenceName = "otp_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = SEQUENCE, generator = "otp_id_seq")
	@Column(name = "id",  updatable = false)
	@JsonIgnore
	private Long id;

	@GeneratedValue()	
	@Column(name = "otp_id")
	@JsonIgnore
	private UUID otpId;

	@Enumerated(EnumType.STRING)
	@Column(name = "otp_type")
	@JsonInclude(Include.NON_NULL)
	private OtpTypes otpType;

	@JsonInclude(Include.NON_NULL)
	@Column(name = "purpose")
	private String purpose;

	@JsonInclude(Include.NON_NULL)
	@Column(name = "contact_info")
	private String contactInfo;

	@JsonInclude(Include.NON_NULL)
	@Column(name = "otp_code")
	private String otpCode;

	@Enumerated(EnumType.STRING)
	@NotNull
	@Column(name = "status")
	@JsonIgnore
	private OtpStatusCodes status;

	@Enumerated(EnumType.STRING)
	@Column(name = "source_type")
	@JsonInclude(Include.NON_NULL)
	private EntityTypes sourceType;

	@Column(name = "source_id")
	@JsonInclude(Include.NON_NULL)
	private Long sourceId;

	
	@Column(name = "otp_expire_time")
	@JsonIgnore
	private java.sql.Timestamp otpExpireTime;

	@Column(name = "otp_expire_time_utc")
	@JsonIgnore
	private java.sql.Timestamp otpExpireTimeUTC;

	public Otp() {
		super();
	}

	public Otp(OtpRequest otpRequest) {
		super();
		this.sourceType = otpRequest.getSourceType();
		this.contactInfo = otpRequest.getContactInfo();
		this.otpCode = otpRequest.getOtpCode();
		this.sourceId = otpRequest.getId();
	}

}
