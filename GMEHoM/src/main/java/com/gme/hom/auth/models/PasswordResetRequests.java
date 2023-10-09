package com.gme.hom.auth.models;

import java.io.Serializable;
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

@RequiredArgsConstructor
@Getter
@Setter
@Entity(name="PasswordResetRequests")
@Table(name="password_reset_requests")
public class PasswordResetRequests implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7092353491793013128L;
	
	
		@Id
		@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "password_reset_requests_id_seq")
		@SequenceGenerator(name = "password_reset_requests_id_seq", sequenceName = "password_reset_requests_id_seq", allocationSize = 1)
		@Column(name = "id", updatable = false)
		private Long id;	

	    @Column(name="user_id")
	    private Long userId;

	    @Column(name="token", nullable = false)
	    private String token;

	    @Column(name="token_expiration_timestamp")
	    private java.sql.Timestamp tokenExpirationTimestamp;

	    @Column(name="request_timestamp")
	    private java.sql.Timestamp requestTimestamp;

	    @Column(name="request_ip_address")
	    private String requestIpAddress;

	    @Column(name="reset_timestamp")
	    private java.sql.Timestamp resetTimestamp;

	    @Column(name="reset_ip_address")
	    private String resetIpAddress;

	    @Column(name="reset_method")
	    private String resetMethod;

	    @Column(name="reset_source")
	    private String resetSource;

	    @Column(name="additional_info")
	    private String additionalInfo;

	    @Column(name="remarks")
	    private String remarks;

	    @Column(name="status", nullable = false)
	    private String status;

	    @Column(name="is_active", nullable = false)
	    private boolean isActive;

	    @Column(name="entity_hash")
	    private String entityHash;

	    @Column(name="created_by", nullable = false)
	    private String createdBy;

	    @Column(name="created_date")
	    private java.sql.Timestamp createdDate;

	    @Column(name="created_date_utc")
	    private java.sql.Timestamp createdDateUtc;

//	    public Password_reset_requests(Password_reset_requestsRequest passwordResetRequestRequest) {
//	        this.id = passwordResetRequestRequest.getId();
//	        this.userId = passwordResetRequestRequest.getUser_id();
//	        this."token" = passwordResetRequestRequest.get"token"();
//	        this.tokenExpirationTimestamp = passwordResetRequestRequest.getToken_expiration_timestamp();
//	        this.requestTimestamp = passwordResetRequestRequest.getRequest_timestamp();
//	        this.requestIpAddress = passwordResetRequestRequest.getRequest_ip_address();
//	        this.resetTimestamp = passwordResetRequestRequest.getReset_timestamp();
//	        this.resetIpAddress = passwordResetRequestRequest.getReset_ip_address();
//	        this.resetMethod = passwordResetRequestRequest.getReset_method();
//	        this.resetSource = passwordResetRequestRequest.getReset_source();
//	        this.additionalInfo = passwordResetRequestRequest.getAdditional_info();
//	        this.remarks = passwordResetRequestRequest.getRemarks();
//	        this.status = passwordResetRequestRequest.getStatus();
//	        this.isActive = passwordResetRequestRequest.getIs_active();
//	        this.entityHash = passwordResetRequestRequest.getEntity_hash();
//	        this.createdBy = passwordResetRequestRequest.getCreated_by();
//	        this.createdDate = passwordResetRequestRequest.getCreated_date();
//	        this.createdDateUtc = passwordResetRequestRequest.getCreated_date_utc();
//	    } 
}
