package com.sonatype.infosec.owasp.a08.enumerations;

public enum ApiErrorCode {
	PERSISTANT_DATA_CANNOT_DESERIALIZE_USER_LIST(11),
	PERSISTANT_DATA_CANNOT_SERIALIZE_USER_LIST(12),
	USER_CONTROLLER_CANNOT_DESERIALIZE_USER(13),
	;
	
	private final Integer errorCode;
	
	private ApiErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}
	
	public Integer getErrorCode() {
		return errorCode;
	}
}
