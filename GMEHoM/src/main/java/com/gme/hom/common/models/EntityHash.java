package com.gme.hom.common.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "EntityHash")
@Table(name = "entity_hash_set")
public class EntityHash {

	@Id
	Long id;

	@JsonProperty("hash")
	private String hash;

}
