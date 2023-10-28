package com.Validation.Repositery;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Validation.Entity.Student;

@Repository
public interface Repo extends CrudRepository<Student, Integer>{

	
	
	

}
