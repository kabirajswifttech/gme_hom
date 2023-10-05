package com.gme.hom.kyc.config;

public enum ResponseMessageCodes {
	
	//CRUD_MESSAGES
	CREATED_SUCCESSFULLY ("CREATED_SUCCESSFULLY"),
	CREATION_FAILED ("CREATION_FAILED"),
	UPDATED_SUCCESSFULLY ("UPDATED_SUCCESSFULLY"),
	UPDATE_FAILED ("UPDATE_FAILED"),
	UPLOADED_SUCCESSFULLY ("UPLOADED_SUCCESSFULLY"),
	UPLOAD_FAILED ("UPLOAD_FAILED"),
	DELETED_SUCCESSFULLY ("DELETED_SUCCESSFULLY"),
	DELETION_FAILED ("DELETION_FAILED"),
	
	//SEARCH
	DATA_RETRIEVED_SUCCESSFULLY ("DATA_RETRIEVED_SUCCESSFULLY"),
	NO_RESULTS_FOUND_FOR_YOUR_SEARCH_QUERY ("NO_RESULTS_FOUND_FOR_YOUR_SEARCH_QUERY"),
	
	
	INVALID_FUNCTION_CODE_OR_SCOPE ("INVALID_FUNCTION_CODE_OR_SCOPE");
	
	private String val;
	ResponseMessageCodes(String val) {
		this.val = val;
	}
	@Override
	public String toString(){
		return val;
	}
	
	
}
