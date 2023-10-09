package com.gme.hom.common.models;


import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class PersistenceEntity {

	@Column(name = "is_active")
	@JsonIgnore
	private Boolean isActive;

	@Column(name = "remarks")
	@JsonIgnore
	private String remarks;

	@Column(name = "created_by", nullable = false)
	@JsonIgnore
	private String createdBy;

	// @CreationTimestamp
	// @GeneratedValue
	@Column(name = "created_date", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
	@JsonIgnore
	private Date createdDate;

	// @CreationTimestamp
	@Column(name = "created_date_utc", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
	@JsonIgnore
	private Date createdDateUTC;

	@Column(name = "entity_hash")
	@JsonIgnore
	private String entityHash;
}
