package com.gme.hom.auth.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.gme.hom.common.models.PersistenceEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "OTP")
@Table(name = "otp")
public class OTP extends PersistenceEntity {

	@Id
	@JsonInclude(Include.NON_NULL)
	@Column(name = "id")
	private Long id;

	@JsonProperty("otp_id")
	@JsonInclude(Include.NON_NULL)
	@Column(name = "otp_id")
	private String otpId;

	@JsonProperty("otp_type")
	@JsonInclude(Include.NON_NULL)
	@Column(name = "otp_type")
	private String otpType;

	private String purpose;

	@JsonProperty("contact_info")
	@JsonInclude(Include.NON_NULL)
	@Column(name = "contact_info")
	private String contactInfo;

	@JsonProperty("otp_code")
	@JsonInclude(Include.NON_NULL)
	@Column(name = "otp_code")
	private String otpCode;

	@JsonProperty("otp_status_id")
	@JsonInclude(Include.NON_NULL)
	@Column(name = "otp_status_id")
	private int otpStatusId;

	@JsonProperty("otp_expire_time")
	@JsonInclude(Include.NON_NULL)
	@Column(name = "otp_expire_time")
	private java.sql.Timestamp otpExpireTime;

	@JsonProperty("otp_expire_time_utc")
	@JsonInclude(Include.NON_NULL)
	@Column(name = "otp_expire_time_utc")
	private java.sql.Timestamp otpExpireTimeUTC;

}
