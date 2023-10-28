package com.Eidiko.Manager.valueObject;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Employee {
	
	 public Integer id;
		public Integer empId;
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
