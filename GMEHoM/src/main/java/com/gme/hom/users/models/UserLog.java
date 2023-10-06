package com.gme.hom.users.models;

import static jakarta.persistence.GenerationType.SEQUENCE;

import java.sql.Date;
import java.util.UUID;

import com.gme.hom.common.models.PersistenceEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@ToString
@RequiredArgsConstructor
@Entity(name = "UsersLog")
@Table(name = "users_log")
public class UserLog extends PersistenceEntity{

	@Id
	@SequenceGenerator(name = "users_log_log_id_seq", sequenceName = "users_log_log_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = SEQUENCE, generator = "users_log_log_id_seq")
	@Column(name = "log_id", nullable = false)
	protected long log_id;

	@Column(name = "id")
	private Long id;

	@Column(name = "user_id")
	private UUID userId;

	@Column(name = "username")
	private String username;

	@Column(name = "email_id")
	private String emailId;

	@Column(name = "user_type")
	private String userType;

	@Column(name = "password")
	private String password;

	@Column(name = "txn_pwd")
	private String txnPwd;

	@Column(name = "security_stamp")
	private String securityStamp;

	@Column(name = "phone_code")
	private String phoneCode;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "salutation")
	private String salutation;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "middle_name")
	private String middleName;

	@Column(name = "last_name")
	private String lastName;

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

	@Column(name = "source_id")
	private Long sourceId;

	@Column(name = "source_type")
	private String sourceType;

	@Column(name = "association_id")
	private Long associationId;

	@Column(name = "association_type")
	private String associationType;

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

	@Column(name = "roles")
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

	@Column(name = "entity_hash")
	private String entityHash;

	@Column(name = "created_by", nullable = false)
	private String createdBy;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "created_date_utc")
	private Date createdDateUtc;

	public UserLog(User usersLogRequest) {
		super();
		this.id = usersLogRequest.getId();
		this.userId = usersLogRequest.getUserId();
		this.username = usersLogRequest.getUsername();
		this.emailId = usersLogRequest.getEmailId();
		this.userType = usersLogRequest.getUserType();
		this.password = usersLogRequest.getPassword();
		this.txnPwd = usersLogRequest.getTxnPwd();
		this.securityStamp = usersLogRequest.getSecurityStamp();
		this.phoneCode = usersLogRequest.getPhoneCode();
		this.phoneNumber = usersLogRequest.getPhoneNumber();
		this.salutation = usersLogRequest.getSalutation();
		this.firstName = usersLogRequest.getFirstName();
		this.middleName = usersLogRequest.getMiddleName();
		this.lastName = usersLogRequest.getLastName();
		this.fullName = usersLogRequest.getFullName();
		this.fullNameNative = usersLogRequest.getFullNameNative();
		this.gender = usersLogRequest.getGender();
		this.dob = usersLogRequest.getDob();
		this.country = usersLogRequest.getCountry();
		this.address1 = usersLogRequest.getAddress1();
		this.address2 = usersLogRequest.getAddress2();
		this.profileImage = usersLogRequest.getProfileImage();
		this.languagePreference = usersLogRequest.getLanguagePreference();
		this.notificationPreference = usersLogRequest.getNotificationPreference();
		this.sourceId = usersLogRequest.getSourceId();
		this.sourceType = usersLogRequest.getSourceType();
	    this.associationId = usersLogRequest.getAssociationId();
		this.associationType = usersLogRequest.getAssociationType();
		this.sessionTimeoutPeriod = usersLogRequest.getSessionTimeoutPeriod();
		this.pwdWarningDays = usersLogRequest.getPwdWarningDays();
		this.pwdExpiryDays = usersLogRequest.getPwdExpiryDays();
		this.isEmailIdVerified = usersLogRequest.isEmailIdVerified();
		this.isForcePwdChange = usersLogRequest.isForcePwdChange();
		this.is2faEnabled = usersLogRequest.is2faEnabled();
		this.accessFailedCount = usersLogRequest.getAccessFailedCount();
		this.isApiUser = usersLogRequest.isApiUser();
		this.roles = usersLogRequest.getRoles();
		this.remarks = usersLogRequest.getRemarks();
		this.status = usersLogRequest.getStatus();
		this.isActive = usersLogRequest.isActive();
		this.extMapId1 = usersLogRequest.getExtMapId1();
		this.extMapId2 = usersLogRequest.getExtMapId2();
		this.extMapId3 = usersLogRequest.getExtMapId3();
		this.refCol1 = usersLogRequest.getRefCol1();
		this.refCol2 = usersLogRequest.getRefCol2();
		this.refCol3 = usersLogRequest.getRefCol3();
		this.refCol4 = usersLogRequest.getRefCol4();
		this.refCol5 = usersLogRequest.getRefCol5();
		this.lastLoginDate = usersLogRequest.getLastLoginDate();
		this.lastLoginDateUtc = usersLogRequest.getLastLoginDateUtc();
		this.lastIpAddress = usersLogRequest.getLastIpAddress();
		this.lastPwdChangedDate = usersLogRequest.getLastPwdChangedDate();
		this.lastPwdChangedDateUtc = usersLogRequest.getLastPwdChangedDateUtc();
		this.entityHash = usersLogRequest.getEntityHash();
		this.createdBy = usersLogRequest.getCreatedBy();
		this.createdDate = usersLogRequest.getCreatedDate();
		this.createdDateUtc = usersLogRequest.getCreatedDateUTC();
	}
}

