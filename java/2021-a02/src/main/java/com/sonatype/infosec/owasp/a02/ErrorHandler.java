package com.sonatype.infosec.owasp.a02;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.sonatype.infosec.owasp.a02.exceptions.PersistentDataException;
import com.sonatype.infosec.owasp.a02.responses.JsonResponse;

@RestControllerAdvice
public class ErrorHandler {
	@ExceptionHandler(value = PersistentDataException.class)
    public ResponseEntity<JsonResponse> handlePersistentDataException(PersistentDataException e) {
        return new ResponseEntity<JsonResponse>(
    		new JsonResponse(e.getErrorCode(), e.getMessage()),
			HttpStatus.INTERNAL_SERVER_ERROR
		);
    }
}
