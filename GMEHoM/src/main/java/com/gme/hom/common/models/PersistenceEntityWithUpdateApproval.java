package com.gme.hom.common.models;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class PersistenceEntityWithUpdateApproval {

	@Column(name = "is_active")
	protected boolean isActive;

	@Column(name = "remarks")
	protected String remarks;

	@Column(name = "created_by", nullable = false)
	protected String createdBy;

	// @CreationTimestamp
	// @GeneratedValue
	@Column(name = "created_date", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
	protected Date createdDate;

	// @CreationTimestamp
	@Column(name = "created_date_utc", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
	protected Date createdDateUTC;

	// @UpdateTimestamp
	@Column(name = "updated_by")
	protected String updatedBy;

	@Column(name = "updated_date")
	protected java.sql.Date updatedDate;

	@Column(name = "updated_date_utc")
	protected java.sql.Date updatedDateUTC;

	@Column(name = "approved_by")
	protected String approvedBy;

	@Column(name = "approved_date")
	protected java.sql.Date approvedDate;

	@Column(name = "approved_date_utc")
	protected java.sql.Date approvedDateUTC;

	@Column(name = "entity_hash")
	protected String entityHash;
}
