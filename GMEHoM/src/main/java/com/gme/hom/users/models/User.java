package com.gme.hom.users.models;

import java.sql.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gme.hom.common.models.PersistenceEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity(name = "User")
@Table(name = "users")
public class User extends PersistenceEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
	@SequenceGenerator(name = "user_sequence", sequenceName = "user_id_seq", allocationSize = 1)
	@Column(name = "id", updatable = false)
	private Long id;

	@GeneratedValue()
	@Column(name = "user_id")
	private UUID userId;

	@Column(name = "user_type")
	private String userType;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "middle_name")
	private String middleName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email_id", unique = true, nullable = false, updatable = false)
	private String emailId;

	@Column(name = "username", unique = true, nullable = false, updatable = false)
	private String username;

	@ToString.Exclude
	@JsonIgnore
	@Column(name = "password", nullable = false, columnDefinition = "text")
	private String password;

	@ToString.Exclude
	@JsonIgnore
	@Column(name = "txn_pwd", nullable = false, columnDefinition = "text")
	private String txnPassword;

	@Column(name = "security_stamp")
	private String securityStamp;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "salutation")
	private String salutation;

	@Column(name = "full_name")
	private String fullName;

	@Column(name = "full_name_native")
	private String fullNameNative;

	@Column(name = "gender")
	private String gender;

	@Column(name = "dob")
	private Date dob;

	@Column(name = "country")
	private String country;

	@Column(name = "address1")
	private String address1;

	@Column(name = "address2")
	private String address2;

	@Column(name = "profile_image")
	private String profileImage;

	@Column(name = "language_preference")
	private String languagePreference;

	@Column(name = "notification_preference")
	private String notificationPreference;

	@Column(name = "session_timeout_period")
	private Integer sessionTimeoutPeriod;

	@Column(name = "pwd_warning_days")
	private Integer pwdWarningDays;

	@Column(name = "pwd_expiry_days")
	private Integer pwdExpiryDays;

	@Column(name = "is_email_id_verified")
	private Boolean isEmailIdVerified;

	@Column(name = "is_force_pwd_change")
	private Boolean isForcePwdChange;

	@Column(name = "is_2fa_enabled")
	private Boolean is2faEnabled;

	@Column(name = "access_failed_count")
	private Integer accessFailedCount;

	@Column(name = "is_api_user")
	private Boolean isApiUser;
	
	@Column(name = "source_id")
	private Long sourceId;
	
	@Column(name = "source_type")
	private String sourceType;
	
	@Column(name = "association_id")
	private Long associationId;
	
	@Column(name = "association_type")
	private String associationType;

	@Column(name = "roles", nullable = false)
	private String roles;

	@Column(name = "remarks")
	private String remarks;

	@Column(name = "status")
	private String status;

	@Column(name = "is_active")
	private Boolean isActive;

	@Column(name = "ext_map_id_1")
	private String extMapId1;

	@Column(name = "ext_map_id_2")
	private String extMapId2;

	@Column(name = "ext_map_id_3")
	private String extMapId3;

	@Column(name = "ref_col_1")
	private String refCol1;

	@Column(name = "ref_col_2")
	private String refCol2;

	@Column(name = "ref_col_3")
	private String refCol3;

	@Column(name = "ref_col_4")
	private String refCol4;

	@Column(name = "ref_col_5")
	private String refCol5;

	@Column(name = "last_login_date")
	private Date lastLoginDate;

	@Column(name = "last_login_date_utc")
	private Date lastLoginDateUtc;

	@Column(name = "last_ip_address")
	private String lastIpAddress;

	@Column(name = "last_pwd_changed_date")
	private Date lastPwdChangedDate;

	@Column(name = "last_pwd_changed_date_utc")
	private Date lastPwdChangedDateUtc;

}
