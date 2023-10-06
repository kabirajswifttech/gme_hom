package com.gme.hom.documents.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocumentRequest {
	
    @NotNull
	@JsonProperty(value="doc_type")
    private String docType;
    
    @JsonInclude(Include.NON_NULL)
    @JsonProperty("sub_type")
    private String subType;
    
    @NotNull
    @JsonInclude(Include.NON_NULL)
    @JsonProperty(value="source_type")
    private String sourceType;

    @JsonInclude(Include.NON_NULL)
    @JsonProperty(value="source_id", required=true)
    private Long sourceId;

    @JsonInclude(Include.NON_NULL)
    @JsonProperty("association_type")
    private String associationType;

    @JsonInclude(Include.NON_NULL)
    @JsonProperty("association_id")
    private Long associationId;

    @JsonInclude(Include.NON_NULL)
    @JsonProperty("doc_path")
    private String docPath;

    @NotNull
    @JsonInclude(Include.NON_NULL)
    @JsonProperty(value="doc_name", required=true)
    private String docName;

    @JsonInclude(Include.NON_NULL)
    @JsonProperty("doc_alias")
    private String docAlias;

    @JsonInclude(Include.NON_NULL)
    @JsonProperty("doc_id_number")
    private String docIdNumber;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonProperty("issue_date")
    private java.sql.Date issueDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonInclude(Include.NON_NULL)
    @JsonProperty("start_date")
    private java.sql.Date startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonInclude(Include.NON_NULL)
    @JsonProperty("expiry_date")
    private java.sql.Date expiryDate;

    @JsonInclude(Include.NON_NULL)
    @JsonProperty("issuing_country")
    private String issuingCountry;

    @JsonInclude(Include.NON_NULL)
    @JsonProperty("issuing_place")
    private String issuingPlace;

    @JsonInclude(Include.NON_NULL)
    @JsonProperty("issuing_authority")
    private String issuingAuthority;

    @JsonInclude(Include.NON_NULL)
    @JsonProperty("ext_map_id_1")
    private String extMapId1;

    @JsonInclude(Include.NON_NULL)
    @JsonProperty("ext_map_id_2")
    private String extMapId2;

    @JsonInclude(Include.NON_NULL)
    @JsonProperty("ext_map_id_3")
    private String extMapId3;

    @JsonInclude(Include.NON_NULL)
    @JsonProperty("ref_col_1")
    private String refCol1;

    @JsonInclude(Include.NON_NULL)
    @JsonProperty("ref_col_2")
    private String refCol2;

    @JsonInclude(Include.NON_NULL)
    @JsonProperty("ref_col_3")
    private String refCol3;

    @JsonInclude(Include.NON_NULL)
    @JsonProperty("ref_col_4")
    private String refCol4;

    @JsonInclude(Include.NON_NULL)
    @JsonProperty("ref_col_5")
    private String refCol5;

    @JsonInclude(Include.NON_NULL)
    @JsonProperty("remarks")
    private String remarks;

    @JsonInclude(Include.NON_NULL)
    @JsonProperty("status")
    private String status;

    @NotNull
    @JsonProperty("file")
    private String file;
 

}