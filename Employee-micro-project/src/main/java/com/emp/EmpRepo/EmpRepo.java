package com.emp.EmpRepo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.emp.Entity.Employee;

@Repository
public interface EmpRepo extends CrudRepository<Employee, Integer> {

	Employee findByEmpid(Integer empid);

	Optional<Employee> findByEmail(String email);


}
