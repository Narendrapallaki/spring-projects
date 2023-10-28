package com.Validation.Exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ApplicationException {
	
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String , String>exceptionHande(MethodArgumentNotValidException ettx)
	{
		Map<String , String>exc=new HashMap<>();
		ettx.getBindingResult().getFieldErrors().forEach(error ->
		{
		//	String field = error.getField();
			exc.put(error.getField(), error.getDefaultMessage());
		});
		return exc;
	}
	



	

	

}
