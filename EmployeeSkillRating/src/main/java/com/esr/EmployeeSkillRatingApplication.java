package com.esr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EmployeeSkillRatingApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeSkillRatingApplication.class, args);
	}

}
