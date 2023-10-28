package com.identity.exception;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.identity.dto.ErrorHandle;

@RestControllerAdvice
public class CustomException extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> exceptionHandle(Exception ex) {
		ErrorHandle error=new ErrorHandle();
		 error.message = ex.getMessage();
		 error.details="";
		 error.date=LocalDate.now();
		 error.status=HttpStatus.BAD_GATEWAY;
		

		//(message, details, HttpStatus.BAD_REQUEST, LocalDate.now());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);

	}

}
