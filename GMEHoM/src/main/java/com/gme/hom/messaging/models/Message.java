package com.gme.hom.messaging.models;

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
@Table(name = "message_queue")
public class Message extends PersistenceEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 982976501504216407L;

	@Id
	@SequenceGenerator(name = "message_queue_id_seq", sequenceName = "message_queue_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = SEQUENCE, generator = "message_queue_id_seq")
	@Column(name = "id", updatable = false)
	private long id;

	@GeneratedValue()
	@Column(name = "message_queue_id")
	private UUID messageQueueId;

	
	@Column(name = "message_type")
	private MessageTypes messageType;

	@Column(name = "priority")
	private String priority;

	@Column(name = "contact_info")
	private String contactInfo;

	@Column(name = "cc")
	private String cc;

	@Column(name = "bcc")
	private String bcc;

	@Column(name = "content")
	private String content;

	@Column(name = "subject")
	private String subject;

	@Column(name = "association_id")
	private long associationId;

	@Column(name = "association_type")
	private String associationType;

	@Column(name = "purpose_id")
	private long purposeId;

	@Column(name = "purpose_type")
	private String purposeType;

	@Column(name = "status")
	private String status;

	@Column(name = "schedule_time")
	private java.util.Date scheduleTime;

	@Column(name = "schedule_time_utc")
	private java.util.Date scheduleTimeUtc;

	@Column(name = "reference")
	private String reference;

	public Message(MessageRequest mr) {
		super();		
		this.messageType = MessageTypes.valueOf(mr.getMessageType());
		this.priority = mr.getPriority();
		this.contactInfo = mr.getContactInfo();
		this.cc = mr.getCc();
		this.bcc = mr.getBcc();
		this.content = mr.getContent();
		this.subject = mr.getSubject();
		this.associationId = mr.getAssociationId();
		this.associationType = mr.getAssociationType();
		this.purposeId = mr.getPurposeId();
		this.purposeType = mr.getPurposeType();
		this.status = mr.getStatus();
		this.scheduleTime = mr.getScheduleTime();
		this.scheduleTimeUtc = mr.getScheduleTimeUtc();
		this.reference = mr.getReference();
	}
	
	
	
	

}
