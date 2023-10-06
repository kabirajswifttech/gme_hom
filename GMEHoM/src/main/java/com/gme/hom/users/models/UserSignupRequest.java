package com.gme.hom.users.models;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignupRequest {
    @JsonInclude(Include.NON_NULL)
    @JsonProperty("id")
    private Long id;

    @JsonInclude(Include.NON_NULL)
    @JsonProperty("user_signup_id")
    private UUID userSignupId;

    @NotNull
    @JsonProperty("source_type")
    private String sourceType;
    
    @JsonInclude(Include.NON_NULL)
    @JsonProperty("association_type")
    private String associationType;
    
    @NotNull
    @JsonProperty("contact_type")
    private String contactType;
    
    @NotNull
    @JsonProperty("contact_info")
    private String contactInfo;

    @NotNull
    @JsonProperty("incorporation_country")
    private String incorporationCountry;

    @JsonInclude(Include.NON_NULL)
    @JsonProperty("email_id")
    private String emailId;

    @JsonInclude(Include.NON_NULL)
    @JsonProperty("phone_code")
    private String phoneCode;

    @JsonInclude(Include.NON_NULL)
    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonInclude(Include.NON_NULL)
    @JsonProperty("signup_source")
    private String signupSource;
    
    @NotNull
    @JsonProperty("full_name")
	protected String fullName;

    @JsonInclude(Include.NON_NULL)
    @JsonProperty("password")
    private String password;

}