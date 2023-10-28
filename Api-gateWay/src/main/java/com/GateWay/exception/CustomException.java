package com.GateWay.exception;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.GateWay.ErrorHandleEntity.ErrorHandle;

@RestControllerAdvice
public class CustomException {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> exceptionHandle(Exception ex) {

		// ErrorHandle eh=new ErrorHandle();

		String message = ex.getMessage();
		List<String> details = new ArrayList<>();
		details.add("Exception occure");

		ErrorHandle eh = new ErrorHandle(message, details, HttpStatus.BAD_REQUEST, LocalDate.now());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(eh);
	}

}
