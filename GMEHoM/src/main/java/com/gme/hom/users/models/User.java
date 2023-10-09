package com.gme.hom.users.models;

import java.io.Serializable;
import java.sql.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gme.hom.common.models.PersistenceEntityWithUpdateApproval;

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
@RequiredArgsConstructor
@Entity(name = "User")
@Table(name = "users")
public class User extends PersistenceEntityWithUpdateApproval implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3220496322705333686L;	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_seq")
	@SequenceGenerator(name = "users_id_seq", sequenceName = "users_id_seq", allocationSize = 1)
	@Column(name = "id", updatable = false)
	private Long id;

	@GeneratedValue()
	@Column(name = "uuid")
	private UUID uuid;

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
	private String txnPwd;

	@Column(name = "security_stamp")
	private String securityStamp;

	@Column(name = "phone_code")
	private String phoneCode;

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
	private java.sql.Date dob;

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

	@Column(name = "is_email_id_verified", nullable = false)
	private boolean isEmailIdVerified;

	@Column(name = "is_force_pwd_change", nullable = false)
	private boolean isForcePwdChange;

	@Column(name = "is_2fa_enabled", nullable = false)
	private boolean is2faEnabled;

	@Column(name = "access_failed_count")
	private Integer accessFailedCount;

	@Column(name = "is_api_user", nullable = false)
	private boolean isApiUser;

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

	@Column(name = "status", nullable = false)
	private String status;

	@Column(name = "is_active", nullable = false)
	private boolean isActive;

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

	public User(UserRequest userRequest) {
		this.id = userRequest.getId();
		this.uuid = userRequest.getUuid();
		this.username = userRequest.getUsername();
		this.emailId = userRequest.getEmailId();
		this.userType = userRequest.getUserType();
		this.password = userRequest.getPassword();
		this.txnPwd = userRequest.getTxnPwd();
		this.securityStamp = userRequest.getSecurityStamp();
		this.phoneCode = userRequest.getPhoneCode();
		this.phoneNumber = userRequest.getPhoneNumber();
		this.salutation = userRequest.getSalutation();
		this.firstName = userRequest.getFirstName();
		this.middleName = userRequest.getMiddleName();
		this.lastName = userRequest.getLastName();
		this.fullName = userRequest.getFullName();
		this.fullNameNative = userRequest.getFullNameNative();
		this.gender = userRequest.getGender();
		this.dob = userRequest.getDob();
		this.country = userRequest.getCountry();
		this.address1 = userRequest.getAddress1();
		this.address2 = userRequest.getAddress2();
		this.profileImage = userRequest.getProfileImage();
		this.languagePreference = userRequest.getLanguagePreference();
		this.notificationPreference = userRequest.getNotificationPreference();
		this.sourceId = userRequest.getSourceId();
		this.sourceType = userRequest.getSourceType();
		this.associationId = userRequest.getAssociationId();
		this.associationType = userRequest.getAssociationType();
		this.sessionTimeoutPeriod = userRequest.getSessionTimeoutPeriod();
		this.pwdWarningDays = userRequest.getPwdWarningDays();
		this.pwdExpiryDays = userRequest.getPwdExpiryDays();
		this.isEmailIdVerified = userRequest.getIsEmailIdVerified();
		this.isForcePwdChange = userRequest.getIsForcePwdChange();
		this.is2faEnabled = userRequest.getIs2faEnabled();
		this.accessFailedCount = userRequest.getAccessFailedCount();
		this.isApiUser = userRequest.getIsApiUser();
		this.roles = userRequest.getRoles();
		this.remarks = userRequest.getRemarks();
		this.status = userRequest.getStatus();
		this.isActive = userRequest.getIsActive();
		this.extMapId1 = userRequest.getExtMapId1();
		this.extMapId2 = userRequest.getExtMapId2();
		this.extMapId3 = userRequest.getExtMapId3();
		this.refCol1 = userRequest.getRefCol1();
		this.refCol2 = userRequest.getRefCol2();
		this.refCol3 = userRequest.getRefCol3();
		this.refCol4 = userRequest.getRefCol4();
		this.refCol5 = userRequest.getRefCol5();

	}

}
