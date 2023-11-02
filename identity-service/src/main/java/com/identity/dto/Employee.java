package com.identity.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Employee {

	public long id;
	public long empid;
	public Long accesCode;
	public String name;
	public Long mobile;
	public String work;
	public String email;
	public String password;
	public LocalDate startDate;
	public LocalDate endDate;
	public String skill;
	public Integer managerId;

}
