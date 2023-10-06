package com.gme.hom.templates.models;

import static jakarta.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import java.util.UUID;

import com.gme.hom.common.models.PersistenceEntity;
import com.gme.hom.messaging.config.MessageTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "message_templates")
public class MessageTemplate extends PersistenceEntity implements Serializable {

	private static final long serialVersionUID = 982976501504216407L;
	@Id
	@SequenceGenerator(name = "message_templates_id_seq", sequenceName = "message_templates_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = SEQUENCE, generator = "message_templates_id_seq")
	@Column(name = "id", updatable = false)
	private long id;

	@GeneratedValue()
	@Column(name = "message_tempate_id")
	private UUID messageTempateId;

	@Column(name = "template_type")
	private MessageTypes templateType;

	@Column(name = "purpose")
	private String purpose;

	@Column(name = "template")
	private String template;

	@Column(name = "description")
	private String description;

	@Column(name = "valid_from_date")
	private java.util.Date validFromDate;

	@Column(name = "valid_from_date_utc")
	private java.util.Date validFromDateUtc;
	@Column(name = "valid_to_date")
	private java.util.Date validToDate;

	@Column(name = "valid_to_date_utc")
	private java.util.Date validToDateUtc;

	@Column(name = "remarks")
	private String remarks;

	@Column(name = "approved_by")
	private long approvedBy;

	@Column(name = "approved_date")
	private java.util.Date approvedDate;

	@Column(name = "approved_date_utc")
	private java.util.Date approvedDateUtc;

	@Column(name = "updated_by")
	private String updatedBy;

	@Column(name = "updated_date")
	private java.util.Date updatedDate;

	@Column(name = "updated_date_utc")
	private java.util.Date updatedDateUtc;

	@Column(name = "subject")
	private String subject;
}