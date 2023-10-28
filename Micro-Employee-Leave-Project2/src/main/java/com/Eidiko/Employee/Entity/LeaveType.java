package com.Eidiko.Employee.Entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class LeaveType {
	
	
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long id;
   private String createdBy;
   private LocalDate createDate;
   private String leaveCode;

}
