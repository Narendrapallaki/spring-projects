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
public class EmpLeave {
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long leaveid;
	private long empId;
	private String leaveReason;
	private String createdBy;
	private LocalDate createDate;
	private LocalDate startDate;
	private LocalDate endDate;
    private String status;
	private String leaveType; 
	
}
