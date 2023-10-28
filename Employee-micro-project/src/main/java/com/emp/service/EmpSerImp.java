package com.emp.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emp.EmpRepo.EmpRepo;
import com.emp.Entity.Employee;
import com.emp.eceptions.EmployeIdNotPresent;

@Service
public class EmpSerImp implements EmpSerInterface {

	public Logger log = org.slf4j.LoggerFactory.getLogger(EmpSerImp.class);

	@Autowired
	public EmpRepo empRepo;

	@Override
	public String saveEmployee(Employee employee) {
		// TODO Auto-generated method stub
		Employee save = empRepo.save(employee);
		log.info("Data save in data base");
		return "User details are saved in data base";
	}

	@Override
	public Employee getById(Integer id) {
		// TODO Auto-generated method stub

		Employee elseThrow = empRepo.findById(id).orElseThrow(() -> new EmployeIdNotPresent("Id not found in Db"));
		log.info("get by id data " + elseThrow);
		return elseThrow;
	}

	public String getEmployeDetails(Integer empid) {

		Employee findByEmpid = empRepo.findByEmpid(empid);
		// System.out.println(findByEmpid);

		return findByEmpid.getEmail();

	}
	
	public Employee getEmpById(Integer id)
	{
		Employee empId = empRepo.findByEmpid(id);
		return empId;
	}
	
	

	public Employee getByEmail(String email) {

		Employee getemail = empRepo.findByEmail(email).orElseThrow(() -> new EmployeIdNotPresent(email));
		return getemail;

	}

//	@Override
//	public Iterable<Employee> getAll() {
//		// TODO Auto-generated method stub
//		return empRepo.findAll();
//	}

//	@Override
//	public String updateByEmployee(Employee employee, Integer empid) {
//		
//		     Employee emp = empRepo.findById(empid).get();
//		     
//		     emp.setDoj(employee.getDoj());
//		     emp.setMobile(employee.getMobile());
//		     emp.setUsername(employee.getUsername());
//		     Employee save = empRepo.save(emp);
//		     
//		return "Employee detailse are modified in database";
//	}

//	@Override
//	public String deleteById(Integer empid) {
//		// TODO Auto-generated method stub
//		 Employee orElseThrow = empRepo.findById(empid)
//				 .orElseThrow(()->new EmployeIdNotPresent("employe id not matched in data base"));
//		 empRepo.deleteById(empid);
//		return "Data deleted";
//		
//	//	empRepo.findById(empid).orElseThrow(()->new RunTimeException("EMpID not Found"));
//		
//	}

}
