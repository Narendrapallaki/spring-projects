package com.esr.Entity;

import java.util.Date;

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
public class EmpSkill {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer empSkilId;
	public String skill;
	public String work;
	public Date startDate;
	public Date endDate;
	@OneToOne
	@JoinColumn(name="empId")
	@JsonBackReference
	public EmployeeData employeeData;
	
	@JsonProperty("employeId")
	public Integer getemId()
	{
		return employeeData!=null? employeeData.getEmpId() :null;
		
	}

	/**
	 * @return the empSkilId
	 */
	public Integer getEmpSkilId() {
		return empSkilId;
	}

	/**
	 * @param empSkilId the empSkilId to set
	 */
	public void setEmpSkilId(Integer empSkilId) {
		this.empSkilId = empSkilId;
	}

	/**
	 * @return the skill
	 */
	public String getSkill() {
		return skill;
	}

	/**
	 * @param skill the skill to set
	 */
	public void setSkill(String skill) {
		this.skill = skill;
	}

	/**
	 * @return the work
	 */
	public String getWork() {
		return work;
	}

	/**
	 * @param work the work to set
	 */
	public void setWork(String work) {
		this.work = work;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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
