package com.sonatype.infosec.owasp.a01.enumerations;

public enum ApiErrorCode {
	PROFILE_ENTITY_NOT_FOUND(1),
	PROFILE_IMAGE_NOT_FOUND(2),
	;
	
	private final Integer errorCode;
	
	private ApiErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}
	
	public Integer getErrorCode() {
		return errorCode;
	}
}
