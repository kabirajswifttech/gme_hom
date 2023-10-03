package com.example.demo.api;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class PersistenceEntityWithUpdateApproval {

	@Column(name = "is_active")
	private boolean isActive;

	@Column(name = "remarks")
	private String remarks;

	@Column(name = "created_by")
	private String createdBy;

	// @CreationTimestamp
	// @GeneratedValue
	@Column(name = "created_date", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false)
	private java.sql.Date createdDate;

	// @CreationTimestamp
	@Column(name = "created_date_utc", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false)
	private java.sql.Date createdDateUTC;

	// @UpdateTimestamp
	@Column(name = "updated_by")
	private String updatedBy;

	@Column(name = "updated_date")
	private java.sql.Date updatedDate;

	@Column(name = "updated_date_utc")
	private java.sql.Date updatedDateUTC;

	@Column(name = "approved_by")
	private String approvedBy;

	@Column(name = "approved_date")
	private java.sql.Date approvedDate;

	@Column(name = "approved_date_utc")
	private java.sql.Date approvedDateUTC;

	@Column(name = "entity_hash")
	private String entityHash;
}