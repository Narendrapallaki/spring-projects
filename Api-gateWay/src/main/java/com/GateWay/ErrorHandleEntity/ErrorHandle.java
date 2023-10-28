package com.GateWay.ErrorHandleEntity;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorHandle {

	
	public ErrorHandle(String message2, List<String> details2, HttpStatus badRequest, LocalDate now) {
		// TODO Auto-generated constructor stub
	}
	public String message;
	public List<Object>details;
	public String status;
	public LocalDate date;
}
