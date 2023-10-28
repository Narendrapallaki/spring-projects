package com.Eidiko.Employee.Exception;


import org.springframework.context.annotation.Bean;

import lombok.NoArgsConstructor;


@NoArgsConstructor
public class AccessDeniedException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public AccessDeniedException(String message) {
		super (message);
	}
	

}

