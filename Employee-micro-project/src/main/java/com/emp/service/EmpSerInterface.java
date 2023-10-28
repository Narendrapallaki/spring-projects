package com.emp.service;

import java.util.List;
import java.util.Optional;

import com.emp.Entity.Employee;

public interface EmpSerInterface {
	
	
	public String saveEmployee(Employee employee);
	
	//public Iterable<Employee> getAll();
	
	//public String updateByEmployee(Employee employee,Integer empid);
	
//	public String deleteById(Integer empid);
	
	public Employee getById(Integer empid);

}
