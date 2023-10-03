package com.gme.hom.category.models;

import static jakarta.persistence.GenerationType.SEQUENCE;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.gme.hom.common.models.PersistenceEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "Category")
@Table(name = "category_roots")
public class Category extends PersistenceEntity {

	@Id
	@SequenceGenerator(name = "category_sequence", sequenceName = "category_sequence", allocationSize = 1)
	@GeneratedValue(strategy = SEQUENCE, generator = "category_sequence")
	@Column(name = "id", updatable = false)
	@JsonIgnore
	private Long id;

	@JsonInclude(Include.NON_NULL)
	@Column(name = "category_root_id")
	private String categoryRootId;

	@JsonInclude(Include.NON_NULL)
	@Column(name = "category_type")
	private String categoryType;

	@JsonInclude(Include.NON_NULL)
	@Column(name = "sub_type")
	private String subType;

	@JsonInclude(Include.NON_NULL)
	@Column(name = "category_code")
	private String categoryCode;

	@JsonInclude(Include.NON_NULL)
	@Column(name = "description")
	private String description;

	@JsonInclude(Include.NON_NULL)
	@Column(name = "parent_type")
	private String parentType;

	@JsonInclude(Include.NON_NULL)
	@Column(name = "parent_code")
	private String parentCode;

	@JsonInclude(Include.NON_NULL)
	@Column(name = "ref_col_1")
	private String refCol1;

	@JsonInclude(Include.NON_NULL)
	@Column(name = "ref_col_2")
	private String refCol2;

	@JsonInclude(Include.NON_NULL)
	@Column(name = "ref_col_3")
	private String refCol3;

	@JsonInclude(Include.NON_NULL)
	@Column(name = "ref_col_4")
	private String refCol4;

	@JsonInclude(Include.NON_NULL)
	@Column(name = "ref_col_5")
	private String refCol5;

}
