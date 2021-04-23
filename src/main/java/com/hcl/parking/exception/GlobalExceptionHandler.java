package com.hcl.parking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CommonException.class)
	public ResponseEntity<ErrorResponse> commonException(Exception e) {
		return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage()),
				HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleAllExceptions(Exception e) {
		return new ResponseEntity<>(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(org.springframework.dao.CannotAcquireLockException.class)
	public ResponseEntity<ErrorResponse> handleAllExceptionsSam(Exception e) {
		return new ResponseEntity<>(new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), "already locked"),
				HttpStatus.UNAUTHORIZED);
	}

}
