package com.Eidiko.Employee.vo;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeValueObject {
	
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
