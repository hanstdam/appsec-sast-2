package com.sonatype.infosec.owasp.a09.exceptions;

import com.sonatype.infosec.owasp.a09.enumerations.ApiErrorCode;

public class NotFoundException extends Exception {
	private static final long serialVersionUID = 1L;
	ApiErrorCode errorCode;

	public NotFoundException(ApiErrorCode errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}
	
	public ApiErrorCode getErrorCode() {
		return this.errorCode;
	}
}
