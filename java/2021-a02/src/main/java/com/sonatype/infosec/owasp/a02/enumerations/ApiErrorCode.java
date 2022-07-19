package com.sonatype.infosec.owasp.a02.enumerations;

public enum ApiErrorCode {
	PERSISTANT_DATA_CANNOT_CONNECT(3),
	PERSISTANT_DATA_CANNOT_CREATE_USER(4),
	PERSISTANT_DATA_CANNOT_FIND_HASH_ALGORITHM(5),
	;
	
	private final Integer errorCode;
	
	private ApiErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}
	
	public Integer getErrorCode() {
		return errorCode;
	}
}
