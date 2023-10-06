package com.gme.hom.documents.repository;

import java.util.UUID;

public interface DocumentDTO {
    long getId();

    UUID getDoc_id();

    String getDoc_type();

    String getSub_type();

    String getSource_type();

    String getSource_id();

    String getAssociation_type();

    String getAssociation_id();

    String getDoc_path();

    String getDoc_name();

    String getDoc_alias();

    String getDoc_id_number();

    java.sql.Date getIssue_date();

    java.sql.Date getStart_date();

    java.sql.Date getExpiry_date();

    String getIssuing_country();

    String getIssuing_place();

    String getIssuing_authority();

    String getExt_map_id_1();

    String getExt_map_id_2();

    String getExt_map_id_3();

    String getRef_col_1();

    String getRef_col_2();

    String getRef_col_3();

    String getRef_col_4();

    String getRef_col_5();

    String getRemarks();

    String getStatus();

    boolean getIs_active();

    String getEntity_hash();

    String getCreated_by();

    java.sql.Timestamp getCreated_date();

    java.sql.Timestamp getCreated_date_utc();

    String getUpdated_by();

    java.sql.Timestamp getUpdated_date();

    java.sql.Timestamp getUpdated_date_utc();

    String getApproved_by();

    java.sql.Timestamp getApproved_date();

    java.sql.Timestamp getApproved_date_utc();

}