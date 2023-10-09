package com.gme.hom.users.models;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRequest {

	  @JsonInclude(Include.NON_NULL)
	    @JsonProperty("id")
	    private Long id;

	    @JsonProperty("uuid")
	    private UUID uuid;

	    @JsonProperty("username")
	    private String username;

	    @JsonProperty("email_id")
	    private String emailId;

	    @JsonProperty("user_type")
	    private String userType;

	    @JsonProperty("password")
	    private String password;

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

	    @JsonProperty("first_name")
	    private String firstName;

	    @JsonProperty("middle_name")
	    private String middleName;

	    @JsonProperty("last_name")
	    private String lastName;

	    @JsonProperty("full_name")
	    private String fullName;

	    @JsonProperty("full_name_native")
	    private String fullNameNative;

	    @JsonProperty("gender")
	    private String gender;

	    @JsonProperty("dob")
	    private java.sql.Date dob;

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

	    @JsonProperty("source_id")
	    private Long sourceId;

	    @JsonProperty("source_type")
	    private String sourceType;

	    @JsonProperty("association_id")
	    private Long associationId;

	    @JsonProperty("association_type")
	    private String associationType;

	    @JsonProperty("session_timeout_period")
	    private Integer sessionTimeoutPeriod;

	    @JsonProperty("pwd_warning_days")
	    private Integer pwdWarningDays;

	    @JsonProperty("pwd_expiry_days")
	    private Integer pwdExpiryDays;

	    @JsonInclude(Include.NON_NULL)
	    @JsonProperty("is_email_id_verified")
	    private Boolean isEmailIdVerified;

	    @JsonInclude(Include.NON_NULL)
	    @JsonProperty("is_force_pwd_change")
	    private Boolean isForcePwdChange;

	    @JsonInclude(Include.NON_NULL)
	    @JsonProperty("is_2fa_enabled")
	    private Boolean is2faEnabled;

	    @JsonProperty("access_failed_count")
	    private Integer accessFailedCount;

	    @JsonInclude(Include.NON_NULL)
	    @JsonProperty("is_api_user")
	    private Boolean isApiUser;

	    @JsonProperty("roles")
	    private String roles;

	    @JsonProperty("remarks")
	    private String remarks;

	    @JsonInclude(Include.NON_NULL)
	    @JsonProperty("status")
	    private String status;

	    @JsonInclude(Include.NON_NULL)
	    @JsonProperty("is_active")
	    private Boolean isActive;

	    @JsonProperty("ext_map_id_1")
	    private String extMapId1;

	    @JsonProperty("ext_map_id_2")
	    private String extMapId2;

	    @JsonProperty("ext_map_id_3")
	    private String extMapId3;

	    @JsonProperty("ref_col_1")
	    private String refCol1;

	    @JsonProperty("ref_col_2")
	    private String refCol2;

	    @JsonProperty("ref_col_3")
	    private String refCol3;

	    @JsonProperty("ref_col_4")
	    private String refCol4;

	    @JsonProperty("ref_col_5")
	    private String refCol5;

}
