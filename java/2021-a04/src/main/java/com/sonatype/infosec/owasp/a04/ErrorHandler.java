package com.sonatype.infosec.owasp.a04;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.sonatype.infosec.owasp.a04.exceptions.NotFoundException;
import com.sonatype.infosec.owasp.a04.responses.JsonResponse;

@RestControllerAdvice
public class ErrorHandler {
	@ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<JsonResponse> handleNotFoundException(NotFoundException e) {
        return new ResponseEntity<JsonResponse>(
    		new JsonResponse(e.getErrorCode(), e.getMessage()),
			HttpStatus.NOT_FOUND
		);
    }
}
