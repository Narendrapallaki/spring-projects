package com.Eidiko.Employee.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AccessRole {
	
	
	@Id
	private long accessId;
	private String level;
	private String description;

}
