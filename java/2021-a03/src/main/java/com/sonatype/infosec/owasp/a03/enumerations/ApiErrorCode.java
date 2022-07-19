package com.sonatype.infosec.owasp.a03.enumerations;

public enum ApiErrorCode {
	PERSISTANT_DATA_CANNOT_CONNECT(6),
	PERSISTANT_DATA_CANNOT_EXECUTE_USER_SEARCH(7),
	USER_NOT_FOUND(8),
	USER_SETTING_NOT_FOUND(9),
	;
	
	private final Integer errorCode;
	
	private ApiErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}
	
	public Integer getErrorCode() {
		return errorCode;
	}
}
