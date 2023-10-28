package com.esr.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class EmpRating {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer empRId;
	public int month;
	public int year;
	public double techRating;
	public double commRating;
	
	@OneToOne
	@JoinColumn(name="empId")
	@JsonBackReference
	public EmployeeData employeeData;
	@JsonProperty("employeId")
	public Integer getInt()
	{
		return employeeData!=null? employeeData.getEmpId():null;
		
	}
	/**
	 * @return the empRId
	 */
	public Integer getEmpRId() {
		return empRId;
	}
	/**
	 * @param empRId the empRId to set
	 */
	public void setEmpRId(Integer empRId) {
		this.empRId = empRId;
	}
	/**
	 * @return the month
	 */
	public int getMonth() {
		return month;
	}
	/**
	 * @param month the month to set
	 */
	public void setMonth(int month) {
		this.month = month;
	}
	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}
	/**
	 * @param year the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}
	/**
	 * @return the techRating
	 */
	public double getTechRating() {
		return techRating;
	}
	/**
	 * @param techRating the techRating to set
	 */
	public void setTechRating(double techRating) {
		this.techRating = techRating;
	}
	/**
	 * @return the commRating
	 */
	public double getCommRating() {
		return commRating;
	}
	/**
	 * @param commRating the commRating to set
	 */
	public void setCommRating(double commRating) {
		this.commRating = commRating;
	}
	/**
	 * @return the employeeData
	 */
	public EmployeeData getEmployeeData() {
		return employeeData;
	}
	/**
	 * @param employeeData the employeeData to set
	 */
	public void setEmployeeData(EmployeeData employeeData) {
		this.employeeData = employeeData;
	}
	

}
