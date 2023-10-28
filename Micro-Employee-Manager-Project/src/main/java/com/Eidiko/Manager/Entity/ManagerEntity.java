package com.Eidiko.Manager.Entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Manager")
public class ManagerEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long manaid;
	private long empid;
	private long managerId;
	private String working;
	private String skill;
	private LocalDate startDate;
	private LocalDate endDate;
	private long modifiedBy;
	private long createdBy;
	
}
