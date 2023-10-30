package com.Eidiko.Employee.LeaveRange;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class LeaveRange {
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long leaveid;
	//private long empId;
	private String leaveReason;
	//private String createdBy;
	private LocalDate createDate;
	private LocalDate startDate;
	private LocalDate endDate;
    private String status;
	//private String leaveType; 

}
