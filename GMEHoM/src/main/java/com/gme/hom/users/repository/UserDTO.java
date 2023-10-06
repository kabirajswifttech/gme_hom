package com.gme.hom.users.repository;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public interface UserDTO {

	@JsonInclude(Include.NON_NULL)
	Long getId();

	@JsonInclude(Include.NON_NULL)

	UUID getUser_id();

	@JsonInclude(Include.NON_NULL)

	String getUsername();

	@JsonInclude(Include.NON_NULL)

	String getEmail_id();

	@JsonInclude(Include.NON_NULL)

	String getUser_type();

	@JsonInclude(Include.NON_NULL)

	String getPassword();

	@JsonInclude(Include.NON_NULL)

	String getTxn_pwd();

	@JsonInclude(Include.NON_NULL)

	String getSecurity_stamp();

	@JsonInclude(Include.NON_NULL)

	String getPhone_code();

	@JsonInclude(Include.NON_NULL)

	String getPhone_number();

	@JsonInclude(Include.NON_NULL)

	String getSalutation();

	@JsonInclude(Include.NON_NULL)

	String getFirst_name();

	@JsonInclude(Include.NON_NULL)

	String getMiddle_name();

	@JsonInclude(Include.NON_NULL)

	String getLast_name();

	@JsonInclude(Include.NON_NULL)

	String getFull_name();

	@JsonInclude(Include.NON_NULL)

	String getFull_name_native();

	@JsonInclude(Include.NON_NULL)

	String getGender();

	@JsonInclude(Include.NON_NULL)

	java.sql.Date getDob();

	@JsonInclude(Include.NON_NULL)

	String getCountry();

	@JsonInclude(Include.NON_NULL)

	String getAddress1();

	@JsonInclude(Include.NON_NULL)

	String getAddress2();

	@JsonInclude(Include.NON_NULL)

	String getProfile_image();

	@JsonInclude(Include.NON_NULL)

	String getLanguage_preference();

	@JsonInclude(Include.NON_NULL)

	String getNotification_preference();

	@JsonInclude(Include.NON_NULL)

	Long getSource_id();

	@JsonInclude(Include.NON_NULL)

	String getSource_type();

	@JsonInclude(Include.NON_NULL)

	Long getAssociation_id();

	@JsonInclude(Include.NON_NULL)

	String getAssociation_type();

	@JsonInclude(Include.NON_NULL)

	Integer getSession_timeout_period();

	@JsonInclude(Include.NON_NULL)

	Integer getPwd_warning_days();

	@JsonInclude(Include.NON_NULL)

	Integer getPwd_expiry_days();

	@JsonInclude(Include.NON_NULL)

	Boolean getIs_email_id_verified();

	@JsonInclude(Include.NON_NULL)

	Boolean getIs_force_pwd_change();

	@JsonInclude(Include.NON_NULL)

	Boolean getIs_2fa_enabled();

	@JsonInclude(Include.NON_NULL)

	Integer getAccess_failed_count();

	@JsonInclude(Include.NON_NULL)

	Boolean getIs_api_user();

	@JsonInclude(Include.NON_NULL)

	String getRoles();

	@JsonInclude(Include.NON_NULL)

	String getRemarks();

	@JsonInclude(Include.NON_NULL)

	String getStatus();

	@JsonInclude(Include.NON_NULL)

	Boolean getIs_active();

	@JsonInclude(Include.NON_NULL)

	String getExt_map_id_1();

	@JsonInclude(Include.NON_NULL)

	String getExt_map_id_2();

	@JsonInclude(Include.NON_NULL)

	String getExt_map_id_3();

	@JsonInclude(Include.NON_NULL)

	String getRef_col_1();

	@JsonInclude(Include.NON_NULL)

	String getRef_col_2();

	@JsonInclude(Include.NON_NULL)

	String getRef_col_3();

	@JsonInclude(Include.NON_NULL)

	String getRef_col_4();

	@JsonInclude(Include.NON_NULL)

	String getRef_col_5();

}
