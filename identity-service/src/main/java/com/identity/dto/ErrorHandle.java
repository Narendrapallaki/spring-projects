package com.identity.dto;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ErrorHandle {
	
	
	public String message;
	public String details;
	public HttpStatus status;
	public LocalDate date;
	

}
