package com.Eidiko.Employee.Exception;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.Eidiko.Employee.Model.ApiError;
import com.Eidiko.Employee.Model.ErrorHandle;

@RestControllerAdvice
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
	
	

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> exceptionHandle(Exception ex) {
		ErrorHandle error=new ErrorHandle();
		 error.message = ex.getMessage();
		 error.details="Somethig went wrong";
		 error.date=LocalDate.now();
		 error.status=HttpStatus.BAD_GATEWAY;
		

		//(message, details, HttpStatus.BAD_REQUEST, LocalDate.now());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);

	}
	
	
//	 @ExceptionHandler(MonthlyLeaveExpiredException.class)
//	    public ResponseEntity<ErrorHandle> handleMonthlyLeaveExpiredException(MonthlyLeaveExpiredException e) {
//		 ErrorHandle errorResponse = new ErrorHandle(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error", e.getMessage());
//	        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
//	    }
}
