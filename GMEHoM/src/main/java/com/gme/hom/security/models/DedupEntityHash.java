package com.gme.hom.security.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DedupEntityHash {

	@JsonProperty("table_name")
	private String tableName;

	@JsonProperty("entity_hash")
	private String entityHash;

	public DedupEntityHash(String tableName, String entityHash) {
		super();
		this.tableName = tableName;
		this.entityHash = entityHash;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getEntityHash() {
		return entityHash;
	}

	public void setEntityHash(String entityHash) {
		this.entityHash = entityHash;
	}

}
