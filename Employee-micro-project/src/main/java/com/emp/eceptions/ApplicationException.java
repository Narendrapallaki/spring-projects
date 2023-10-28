package com.emp.eceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationException {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> applicationExceptionHandle(MethodArgumentNotValidException ex) {

		Map<String, String> errorsMap = new HashMap<>();

		ex.getBindingResult().getFieldErrors().forEach(error -> {
			errorsMap.put(error.getField(), error.getDefaultMessage());
		});
		return errorsMap;

	}

}
