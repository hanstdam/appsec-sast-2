package com.sonatype.infosec.owasp.a09.enumerations;

public enum ApiErrorCode {
	USER_NOT_FOUND(8),
	USER_CONTROLLER_FAILED_TO_PARSE_USER_ID(14),
	;
	
	private final Integer errorCode;
	
	private ApiErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}
	
	public Integer getErrorCode() {
		return errorCode;
	}
}
