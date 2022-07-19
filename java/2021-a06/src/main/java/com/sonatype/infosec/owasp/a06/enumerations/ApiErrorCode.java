package com.sonatype.infosec.owasp.a06.enumerations;

public enum ApiErrorCode {
	PERSISTANT_DATA_CANNOT_CONNECT(6),
	PERSISTANT_DATA_CANNOT_EXECUTE_USER_SEARCH(7),
	USER_NOT_FOUND(8),
	PERSISTANT_DATA_CANNOT_CREATE_USER_SEARCH_STATEMENT(10),
	;
	
	private final Integer errorCode;
	
	private ApiErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}
	
	public Integer getErrorCode() {
		return errorCode;
	}
}
