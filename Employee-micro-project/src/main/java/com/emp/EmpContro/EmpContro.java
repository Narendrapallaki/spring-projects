package com.emp.EmpContro;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emp.Entity.Employee;
import com.emp.eceptions.EmployeIdNotPresent;
import com.emp.service.EmpSerImp;



@RestController
@RequestMapping("/emp")
public class EmpContro {

	@Autowired
	public EmpSerImp empSerImp;
	
	  Map<String,Object>map=new HashMap<>();
   
	@PostMapping("/save")
	public ResponseEntity<Map<String,Object>>saveEmploye(@RequestBody Employee employee)
	  {  
		  String employee2 = empSerImp.saveEmployee(employee);
		  
		
		  
		   if (employee2!=null) {
			   
			   map.put("message", "Success");
			   map.put("result", employee2);
			   map.put("status", HttpStatus.CREATED.value());
			   return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);		
		}
		  else
		   {
			   map.put("Error", "Data not saved in data base");   
			   map.put("status", HttpStatus.CREATED.value());
			   return new ResponseEntity<Map<String,Object>>(map, HttpStatus.BAD_REQUEST);
		   }
	  }
	
	@GetMapping("/getById/{id}")
	public ResponseEntity<Map<String,Object>>getEmployee(@PathVariable Integer id)
	{
		Employee byId = empSerImp.getById(id);

		   if (byId!=null) {
			   
			   map.put("message", "Success");
			   map.put("result", byId);
			   map.put("status", HttpStatus.CREATED.value());
			   return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);		
		}
		  else
		   {
			   map.put("Error", "Data not saved in data base");
			   map.put("result", byId);
			   map.put("status", HttpStatus.CREATED.value());
			   return new ResponseEntity<Map<String,Object>>(map, HttpStatus.BAD_REQUEST);
		   }
	}
//	
//	@Autowired
//	private EmpSerImp empSerImp2;

	@GetMapping("/get/{empid}")
	public String getEmployeDetailss(@PathVariable Integer empid)
	{
		
		String employeDetails = empSerImp.getEmployeDetails(empid);
		
		return employeDetails;
		
		
//		 map.put("message", "Success");
//		   map.put("result", employeDetails);
//		   map.put("status", HttpStatus.CREATED.value());
//		   return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		
		
	}
	
	
	
	
	
	

	@GetMapping("/getempId/{empid}")
	public ResponseEntity<Map<String, Object>> getByE(@PathVariable Integer empid)
	{
		
		Employee empById = empSerImp.getEmpById(empid);
		
		if (empById!=null) {
			   
			   map.put("message", "Success");
			   map.put("result", empById);
			   map.put("status", HttpStatus.CREATED.value());
			   return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);		
		}
		  else
		   {
//			   map.put("Error", "Data not saved in data base");
//			   map.put("result", "error");
//			   map.put("status", HttpStatus.CREATED.value());
			  // return new ResponseEntity<Map<String,Object>>(map, HttpStatus.BAD_REQUEST);
			   
			   throw new EmployeIdNotPresent("no id is not available");
		   }
		//return empSerImp.getEmpById(empid);
		
	}
	
	
	
	
	
	
	@GetMapping("/getByMail/{email}")
	public Employee getByEmail(@PathVariable String email)
	{
		return empSerImp.getByEmail(email);
		
	}
	
	
	
	
	
	
	/*
	  @GetMapping("/getAll")
	  public ResponseEntity<Map<String,Object>>getAllEmployee()
	  {
		  Iterable<Employee> all = empSerImp.getAll();
		  Map<String ,Object>map=new HashMap<>();
		  map.put("Message", "all employes ");
		  map.put("result", all);
		  map.put("status", HttpStatus.CREATED.value());
		  
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		  
	  }
	
	  @DeleteMapping("/deleteById")
	  public ResponseEntity<Map<String,Object>>deleteById(@RequestParam("id") Integer id)
	  {
		   String deleteById = empSerImp.deleteById(id);
		  Map<String ,Object>map=new HashMap<>();
		  map.put("Message", "Delete by employe id ");
		  map.put("result", deleteById);
		  map.put("status", HttpStatus.CREATED.value());
		  
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);	  
	  }
	
	  @PutMapping("/updateData/{id}")
	  public ResponseEntity<Map<String,Object>>updateById(@PathVariable Integer id,@RequestBody Employee employee)
	  {
		 String updateByEmployee = empSerImp.updateByEmployee(employee, id);
		  Map<String ,Object>map=new HashMap<>();
		  map.put("Message", "all employes ");
		  map.put("result", updateByEmployee);
		  map.put("status", HttpStatus.CREATED.value());
		  
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		  
	  }
	
	*/
	
	

}
