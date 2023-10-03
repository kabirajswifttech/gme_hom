package com.gme.hom.users.models;

import static jakarta.persistence.GenerationType.SEQUENCE;

import java.util.UUID;

import com.gme.hom.common.models.PersistenceEntity;
import com.gme.hom.users.config.UserContactTypes;
import com.gme.hom.users.config.UserSourceTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Entity(name = "UserSignup")
@Table(name = "users_signup")
public class UserSignup extends PersistenceEntity {

	@Id
	@SequenceGenerator(name = "users_signup_id_seq", sequenceName = "users_signup_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = SEQUENCE, generator = "users_signup_id_seq")
	@Column(name = "id", nullable = false)
	protected long id;

	// @GenericGenerator(name = "uuid2", strategy = "uuid2")
	// @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
	@Column(name = "user_signup_id", updatable = false)
	protected UUID userSignupId;

	@Enumerated(EnumType.STRING)
	@Column(name = "source_type", nullable = false, updatable = false)
	protected UserSourceTypes sourceType;
	
	
	@Column(name = "association_type", updatable = false)
	protected String associationType;

	@Column(name = "incorporation_country", nullable = false, updatable = false)
	protected String incorporationCountry;	

	@Enumerated(EnumType.STRING)
	@Column(name = "contact_type", nullable = false, updatable = false)
	protected UserContactTypes contactType;
	
	@Column(name = "contact_info", nullable = false, updatable = false)
	protected String contactInfo;

	@Column(name = "phone_code", updatable = false)
	protected String phoneCode;

	@Column(name = "phone_number", updatable = false)
	protected String phoneNumber;

	@Column(name = "is_email_otp_verified")
	protected Boolean isEmailOtpVerified;

	@Column(name = "is_sms_otp_verified")
	protected Boolean isSmsOtpVerified;

	// UserStatusCode
	@Column(name = "status")
	protected String status;

	@Column(name = "ip_address")
	protected String ipAddress;

	// device information
	@Column(name = "signup_source")
	protected String signupSource;
	
	@Transient
	protected String password;
	
	public UserSignup() {
		super();
	}

	public UserSignup(UserSignupRequest userSignupRequest) {
		this.userSignupId = userSignupRequest.getUserSignupId();
		this.sourceType = UserSourceTypes.valueOf(userSignupRequest.getSourceType());
		this.associationType = userSignupRequest.getAssociationType();
		this.incorporationCountry = userSignupRequest.getIncorporationCountry();
		this.contactType = UserContactTypes.valueOf(userSignupRequest.getContactType());
		this.contactInfo = userSignupRequest.getContactInfo();
		this.phoneCode = userSignupRequest.getPhoneCode();
		this.phoneNumber = userSignupRequest.getPhoneNumber();
		this.signupSource = userSignupRequest.getSignupSource();
		this.password = userSignupRequest.getPassword();
	}
}
