package com.identity.dto;

import java.sql.Date;
import java.time.LocalDate;


import org.springframework.lang.NonNull;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
