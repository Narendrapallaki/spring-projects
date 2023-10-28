package com.Validation.Entity;


import org.springframework.validation.annotation.Validated;

import com.Validation.validationMethod.vs;
import com.fasterxml.jackson.core.StreamReadConstraints.Builder;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Table
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;
    @NotEmpty(message = "name need")
	public String name;
	@Email(message = "mail format")
	//(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$",message = "must be email formate")
	public String email;
	@NotEmpty(message = "Ander@445")
	public String password;
	@Pattern(regexp = ("^\\d{10}$"),message = "10 digit")
	public String mobile;

	/**
	 * @param name
	 * @param email
	 * @param password
	 * @param mobile
	 */
	
	
	
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Student(String name, String email, String password, String mobile) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.mobile = mobile;
	}

	/**
	 * 
	 */
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
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
	 * @return the email
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

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		
		if (vs.isPassword(password)) {
			this.password = password;
		} else {
             System.out.println("not match pass");
		}
		
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		
		this.mobile = mobile;
//		if (vs.isMobile(mobile)) {
//			this.mobile = mobile;
//		}
//		else
//		{
//			System.out.println("not valid");
//		}
		
	}
	

}
