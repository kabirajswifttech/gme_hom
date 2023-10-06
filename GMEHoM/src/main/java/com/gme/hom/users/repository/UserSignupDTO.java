package com.gme.hom.users.repository;
import java.util.UUID;

public interface UserSignupDTO {

    UUID getUser_signup_id();

    String getMerchant_type();

    String getIncorporation_country();

    String getEmail_id();

    String getPhone_code();

    String getPhone_number();

    Boolean getIs_email_otp_verified();

    Boolean getIs_sms_otp_verified();

    String getStatus();

    String getIp_address();

    String getSignup_source(); 
    
    String getFull_name();

}