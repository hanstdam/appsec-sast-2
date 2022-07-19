package com.sonatype.infosec.owasp.a09.responses;

import com.sonatype.infosec.owasp.a09.enumerations.ApiErrorCode;

public class JsonResponse {
	String message;
	Integer errorCode;

    public JsonResponse(ApiErrorCode errorCode, String message) {
        this.message = message;
        this.errorCode = errorCode.getErrorCode();
    }

    public String getMessage() {
        return message;
    }

    public Integer getErrorCode() {
    	return errorCode;
    }
}
