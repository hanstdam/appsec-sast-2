package com.sonatype.infosec.owasp.a03.exceptions;

import com.sonatype.infosec.owasp.a03.enumerations.ApiErrorCode;

public class PersistentDataException extends Exception {
	private static final long serialVersionUID = 1L;
	ApiErrorCode errorCode;

	public PersistentDataException(ApiErrorCode errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}
	
	public ApiErrorCode getErrorCode() {
		return this.errorCode;
	}
}
