package com.esr.Entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class EmployeeData {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer empId;
	public String name;
	public Long number;
	public String email;
	@OneToOne(mappedBy = "employeeData",cascade = CascadeType.ALL)
	public EmpSkill empSkill;
	@OneToOne(mappedBy = "employeeData",cascade = CascadeType.ALL)
	public EmpRating empRating;
	/**
	 * @return the empId
	 */
	
	
	public Integer getEmpId() {
		return empId;
	}
	/**
	 * @return the email
	 */
	
	/**
	 * @param empId the empId to set
	 */
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the number
	 */
	public Long getNumber() {
		return number;
	}
	/**
	 * @param number the number to set
	 */
	public void setNumber(Long number) {
		this.number = number;
	}
	/**
	 * @return the empSkill
	 */
	
	
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	public EmpSkill getEmpSkill() {
		return empSkill;
	}
	/**
	 * @param empSkill the empSkill to set
	 */
	public void setEmpSkill(EmpSkill empSkill) {
		this.empSkill = empSkill;
	}
	/**
	 * @return the empRating
	 */
	public EmpRating getEmpRating() {
		return empRating;
	}
	/**
	 * @param empRating the empRating to set
	 */
	public void setEmpRating(EmpRating empRating) {
		this.empRating = empRating;
	}
	

}
