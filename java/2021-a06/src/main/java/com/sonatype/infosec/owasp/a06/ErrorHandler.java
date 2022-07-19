package com.sonatype.infosec.owasp.a06;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.sonatype.infosec.owasp.a06.exceptions.NotFoundException;
import com.sonatype.infosec.owasp.a06.exceptions.PersistentDataException;
import com.sonatype.infosec.owasp.a06.responses.JsonResponse;

@RestControllerAdvice
public class ErrorHandler {
	@ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<JsonResponse> handleNotFoundException(NotFoundException e) {
        return new ResponseEntity<JsonResponse>(
    		new JsonResponse(e.getErrorCode(), e.getMessage()),
			HttpStatus.NOT_FOUND
		);
    }
	
	@ExceptionHandler(value = PersistentDataException.class)
    public ResponseEntity<JsonResponse> handlePersistentDataException(PersistentDataException e) {
        return new ResponseEntity<JsonResponse>(
    		new JsonResponse(e.getErrorCode(), e.getMessage()),
			HttpStatus.INTERNAL_SERVER_ERROR
		);
    }
}
