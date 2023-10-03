package com.gme.hom.users.models;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRequest {

	@JsonProperty("first_name")
	private String firstName;

	@JsonProperty("middle_name")
	private String middleName;

	@JsonProperty("last_name")
	private String lastName;

	@JsonProperty("email_id")
	private String emailId;

	@JsonProperty("password")
	private String password;

	@JsonProperty("roles")
	private String roles;

	@JsonProperty("username")
	private String username;

	@JsonProperty("txn_pwd")
	private String txnPwd;

	@JsonProperty("security_stamp")
	private String securityStamp;

	@JsonProperty("phone_code")
	private String phoneCode;

	@JsonProperty("phone_number")
	private String phoneNumber;

	@JsonProperty("salutation")
	private String salutation;

	@JsonProperty("full_name")
	private String fullName;

	@JsonProperty("full_name_native")
	private String fullNameNative;

	@JsonProperty("gender")
	private String gender;

	@JsonProperty("dob")
	Date dob;

	@JsonProperty("country")
	private String country;

	@JsonProperty("address1")
	private String address1;

	@JsonProperty("address2")
	private String address2;

	@JsonProperty("profile_image")
	private String profileImage;

	@JsonProperty("language_preference")
	private String languagePreference;

	@JsonProperty("notification_preference")
	private String notificationPreference;

	@JsonProperty("merchant_id")
	private long merchantId;

	@JsonProperty("partner_id")
	private long partnerId;

	@JsonProperty("session_timeout_period")
	private int sessionTimeoutPeriod;

	@JsonProperty("pwd_warning_days")
	private int pwdWarningDays;

	@JsonProperty("pwd_expiry_days")
	private int pwdExpiryDays;

	@JsonProperty("is_email_id_verified")
	private boolean isEmailIdVerified;

	@JsonProperty("is_force_pwd_change")
	private boolean isForcePwdChange;

	@JsonProperty("is_2fa_enabled")
	private boolean is2faEnabled;

	@JsonProperty("access_failed_count")
	private int accessFailedCount;

	@JsonProperty("is_api_user")
	private boolean isApiUser;

	@JsonProperty("remarks")
	private String remarks;

	@JsonProperty("status")
	private String status;

	@JsonProperty("is_active")
	private boolean isActive;

	@JsonProperty("last_ip_address")
	private String lastIpAddress;
}
