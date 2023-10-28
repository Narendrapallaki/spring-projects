package com.Eidiko.Manager.CustomException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.Eidiko.Manager.Model.ApiError;

@RestControllerAdvice
public class CustomException extends ResponseEntityExceptionHandler {
	
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		String message=ex.getMessage();
		List<String> details=new ArrayList<>();
		details.add("Method Not Supported");
		ApiError errors=new ApiError(message,details, status,LocalDateTime.now());
		return ResponseEntity.status(status).body(errors);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		String message=ex.getMessage();
		List<String> details=new ArrayList<>();
		details.add("Media TYpe Not sopprted");
		ApiError errors=new ApiError(message,details,status,LocalDateTime.now());
		return ResponseEntity.status(status).body(errors);
	}

	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
			HttpStatusCode status, WebRequest request) {
		String message=ex.getMessage();
		List<String> details=new ArrayList<>();
		details.add("Path Variable is Missing !!");
		ApiError errors=new ApiError(message,details,status,LocalDateTime.now());
		return ResponseEntity.status(status).body(errors);
	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatusCode status, WebRequest request) {
		String message=ex.getMessage();
		List<String> details=new ArrayList<>();
		details.add("MissMatch of type !!");
		ApiError errors=new ApiError(message,details,status,LocalDateTime.now());
		return ResponseEntity.status(status).body(errors);
	}
	
	
	

}
