package com.gme.hom.documents.models;

import java.io.Serializable;
import java.util.UUID;

import com.gme.hom.common.models.PersistenceEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "Document")
@Table(name = "documents")
public class Document extends PersistenceEntity implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -5268321454221314406L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "documents_sequence")
	@SequenceGenerator(name = "documents_sequence", sequenceName = "documents_id_seq", allocationSize = 1)
	@Column(name = "id", updatable = false)
	private long id;

	@Column(name = "doc_id")
	private UUID docId;

	@Column(name = "doc_type")
	private String docType;

	@Column(name = "sub_type")
	private String subType;

	@Column(name = "source_type")
	private String sourceType;

	@Column(name = "source_id")
	private long sourceId;

	@Column(name = "association_type")
	private String associationType;

	@Column(name = "association_id")
	private String associationId;

	@Column(name = "doc_path")
	private String docPath;

	@Column(name = "doc_name")
	private String docName;

	@Column(name = "doc_alias")
	private String docAlias;

	@Column(name = "doc_id_number")
	private String docIdNumber;

	@Column(name = "issue_date")
	private java.sql.Date issueDate;

	@Column(name = "start_date")
	private java.sql.Date startDate;

	@Column(name = "expiry_date")
	private java.sql.Date expiryDate;

	@Column(name = "issuing_country")
	private String issuingCountry;

	@Column(name = "issuing_place")
	private String issuingPlace;

	@Column(name = "issuing_authority")
	private String issuingAuthority;

	@Column(name = "ext_map_id_1")
	private String extMapId1;

	@Column(name = "ext_map_id_2")
	private String extMapId2;

	@Column(name = "ext_map_id_3")
	private String extMapId3;

	@Column(name = "ref_col_1")
	private String refCol1;

	@Column(name = "ref_col_2")
	private String refCol2;

	@Column(name = "ref_col_3")
	private String refCol3;

	@Column(name = "ref_col_4")
	private String refCol4;

	@Column(name = "ref_col_5")
	private String refCol5;

	@Column(name = "remarks")
	private String remarks;

	@Column(name = "status")
	private String status;
	
	public Document(DocumentRequest docRequest) {
		super();
		this.docType = docRequest.getDocType();
		this.subType = docRequest.getSubType();
		this.sourceType = docRequest.getSourceType();
		this.sourceId = docRequest.getSourceId();
		this.associationType = docRequest.getAssociationType();
		this.associationId = docRequest.getAssociationId();
		this.docPath = docRequest.getDocPath();
		this.docName = docRequest.getDocName();
		this.docAlias = docRequest.getDocAlias();
		this.docIdNumber = docRequest.getDocIdNumber();
		this.issueDate = docRequest.getIssueDate();
		this.startDate = docRequest.getStartDate();
		this.expiryDate = docRequest.getExpiryDate();
		this.issuingCountry = docRequest.getIssuingCountry();
		this.issuingPlace = docRequest.getIssuingPlace();
		this.issuingAuthority = docRequest.getIssuingAuthority();
		this.extMapId1 = docRequest.getExtMapId1();
		this.extMapId2 = docRequest.getExtMapId2();
		this.extMapId3 = docRequest.getExtMapId3();
		this.refCol1 = docRequest.getRefCol1();
		this.refCol2 = docRequest.getRefCol2();
		this.refCol3 = docRequest.getRefCol3();
		this.refCol4 = docRequest.getRefCol4();
		this.refCol5 = docRequest.getRefCol5();
		this.remarks = docRequest.getRemarks();
		this.status = docRequest.getStatus();

	}
}
