package com.Eidiko.Employee.Exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.Eidiko.Employee.Model.ApiError;

public class CustomExceptionHandler {

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<Object> accessDeniedExceptionHandler(AccessDeniedException ex){
		String message=ex.getMessage();
		List<String> details=new ArrayList<>();
		details.add("Access Denied");
		ApiError errors=new ApiError(message,details,HttpStatus.BAD_REQUEST,LocalDateTime.now());
		return ResponseEntity.badRequest().body(errors);
	}
	
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<Object> idNotFoundExceptionHandler(IdNotFoundException ex){
		String message=ex.getMessage();
		List<String> details=new ArrayList<>();
		details.add("Id not found !!");
		ApiError errors=new ApiError(message,details,HttpStatus.NOT_FOUND,LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
	}
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Object> ResourceNotFoundException(ResourceNotFoundException ex){
		String message=ex.getMessage();
		List<String> details=new ArrayList<>();
		details.add("Id not found !!");
		ApiError errors=new ApiError(message,details,HttpStatus.NOT_FOUND,LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
	}
	
}
