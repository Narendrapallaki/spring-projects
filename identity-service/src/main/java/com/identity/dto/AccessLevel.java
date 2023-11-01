package com.identity.dto;

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
@Table(name="AccessLevel")
public class AccessLevel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long empId;
	private long accessLevelId;
}
