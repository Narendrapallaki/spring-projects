package com.Eidiko.Employee.Controller;

import java.util.HashMap;
import java.util.List;
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
import org.springframework.web.bind.annotation.RestController;

import com.Eidiko.Employee.Entity.AccessLevel;
import com.Eidiko.Employee.Entity.AccessRole;
import com.Eidiko.Employee.Entity.EmpLeave;
import com.Eidiko.Employee.Entity.LeaveType;
import com.Eidiko.Employee.Exception.ResourceNotFoundException;
import com.Eidiko.Employee.Service.LeaveService;
import com.Eidiko.Employee.Service.LeaveServiceImpl;
import com.Eidiko.Employee.vo.leaveToEmployee;



@RestController
@RequestMapping("/leave")
public class EmpLeaveController {

	@Autowired
	private LeaveService leaveService;
	@Autowired
	public LeaveServiceImpl leaveImp;
	
	Map<String, Object>response=new HashMap<>(); 
	
	
	//Save Entities 		-------------------------------------------------------------
	@PostMapping("/saveAccesslevel")
	public ResponseEntity<Map<String, Object>> saveAccessLevel(@RequestBody AccessLevel accessLevel){
		leaveService.saveAccessLevel(accessLevel);
		
		
		response.put("Message", "AccessLevel data saveed !!");
		response.put("status", HttpStatus.CREATED);
		response.put("result", "Success");
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	@PostMapping("/saveAccessRole")
	public ResponseEntity<Map<String, Object>> saveAccessRole(@RequestBody AccessRole accessRole){
		leaveService.saveAccessRole(accessRole);
		response.put("Message", "AccessRole data saveed !!");
		response.put("status", HttpStatus.CREATED);
		response.put("result", "Success");
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	
	
	@PostMapping("/saveEmpLeave")
	public ResponseEntity<Map<String, Object>> saveEmpLeave(@RequestBody EmpLeave empLeave) {
		leaveService.saveEmpLeave(empLeave);
		
	//	leaveImp.sendMail("narendrapallaki2018@gmail.com", "Leave Letter");
		response.put("Message", "EmpLeave data saveed !!");
		response.put("status", HttpStatus.CREATED);
		response.put("result", "Success");
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	
	@PutMapping("/approved/{leaveid}")
	public ResponseEntity<Map<String, Object>> approvidLeave(@PathVariable long leaveid)
	{
		 Object updateLeave = leaveService.updateLeave(leaveid);
		
		
		if (updateLeave!=null) {
			
			response.put("Message", "Your leave status is updated !!");
			response.put("status", HttpStatus.CREATED);
			response.put("result", updateLeave);
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
		}
		else
		{
			
			response.put("Message", "Your leave status is issuess !!");
			response.put("status", HttpStatus.BAD_REQUEST);
			response.put("error", updateLeave);
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
			
		}
		//return null;
		
		
		
		
	}
	
	@PostMapping("/saveLeaveType")
	public ResponseEntity<Map<String, Object>> saveLeaveType(@RequestBody LeaveType leaveType){
		leaveService.saveLeaveType(leaveType);
		response.put("Message", "AccessLevel data saveed !!");
		response.put("status", HttpStatus.CREATED);
		response.put("result", "Success");
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("/deleteById/{leaveid}")
    public ResponseEntity<Map<String, Object>> deleteByLeaveId(@PathVariable long leaveid) {
		
		leaveImp.leaveDelete(leaveid);
        Map<String, Object> response = new HashMap<>();
        response.put("Message", "Data deleted in db!!");
        response.put("status", HttpStatus.CREATED);
        response.put("result", "Success");

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
	
	
	
	@PutMapping("/updateById/{leaveid}")
    public ResponseEntity<Map<String, Object>> updateById(@RequestBody EmpLeave empLeave,@PathVariable long leaveid) {
		
//		
//		
//		//EmpLeave updateLeave = leaveService.updateLeave(empLeave, leaveid);
//		System.out.println(updateLeave);
//        Map<String, Object> response = new HashMap<>();
//        response.put("Message", "Date updated in db!!");
//        response.put("status", updateLeave);
//        response.put("result", "Success");

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
	
	
	
	@Autowired
	public LeaveServiceImpl leaveServiceImpl;
	
	@GetMapping("/getleave/{leaveid}")
	public ResponseEntity<Map<String, Object>> getEmpToleave(@PathVariable Long leaveid){
	 leaveToEmployee leaveTypeEmp = leaveServiceImpl.leaveTypeEmp(leaveid);
		if(leaveTypeEmp!=null) {
		response.put("Data",leaveTypeEmp);
		response.put("status", HttpStatus.CREATED);
		response.put("result", "Success");
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
		}else {
			throw new ResourceNotFoundException("Data is Null");
		}
	}
	
	
	
	
	
	
	
	//get Data by Id		-------------------------------------------------------------
	@GetMapping("/getAccessLevel/{id}")
	public ResponseEntity<Map<String, Object>> getAccessLevel(@PathVariable Long id){
		AccessLevel accessLevel=leaveService.getAccessLevel(id);
		if(accessLevel!=null) {
		response.put("Data",accessLevel);
		response.put("status", HttpStatus.CREATED);
		response.put("result", "Success");
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
		}else {
			throw new ResourceNotFoundException("Data is Null");
		}
	}
	@GetMapping("/getAccessRole/{accessId}")
	public ResponseEntity<Map<String, Object>> getAccessRole(@PathVariable Long accessId){
		AccessRole accessRole=leaveService.getAccessRole(accessId);
		if(accessRole!=null) {
		response.put("Data",accessRole);
		response.put("status", HttpStatus.CREATED);
		response.put("result", "Success");
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
		}else {
			throw new ResourceNotFoundException("Data is Null");
		}
	}
	@GetMapping("/getEmpLeave/{empId}")
	public ResponseEntity<Map<String, Object>> getEmpLeave(@PathVariable Long empId){
		EmpLeave empLeave=leaveService.getEmpLeave(empId);
		if(empLeave!=null) {
		response.put("Data",empLeave);
		response.put("status", HttpStatus.CREATED);
		response.put("result", "Success");
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
		}else {
			throw new ResourceNotFoundException("Data is Null");
		}
	}
	
	@GetMapping("/getLeaveType/{id}")
	public ResponseEntity<Map<String, Object>> getLeaveType(@PathVariable Long id){
		LeaveType leaveType=leaveService.getLeaveType(id);
		if(leaveType!=null) {
		response.put("Data",leaveType);
		response.put("status", HttpStatus.CREATED);
		response.put("result", "Success");
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
		}else {
			throw new ResourceNotFoundException("Data is Null");
		}
	}
	
	@GetMapping("/pendingLeaves/{status}")
	public ResponseEntity<Map<String, Object>> getPendingLeavesByStatus(@PathVariable String  status){
	 List<EmpLeave> pendingLeaves = leaveImp.getPendingLeaves(status);
		if(pendingLeaves!=null) {
		response.put("Data",pendingLeaves);
		response.put("status", HttpStatus.OK);
		response.put("result", "Success");
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
		}else {
			throw new ResourceNotFoundException("Data is Null");
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//Get All Entity Data		-------------------------------------------------------------
	@GetMapping("/getAllAccessLevel")
	public ResponseEntity<Map<String, Object>> getAllAccessLevel(){
		List<AccessLevel> accessLevel=leaveService.getAllAccessLevel();
		if(accessLevel!=null) {
		response.put("Data",accessLevel);
		response.put("status", HttpStatus.CREATED);
		response.put("result", "Success");
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
		}else {
			throw new ResourceNotFoundException("Data is Null");
		}
	}
	
	@GetMapping("/getAllAccessRole")
	public ResponseEntity<Map<String, Object>> getAllAccessRole(){
		List<AccessRole> accessRole=leaveService.getAllAccessRole();
		if(accessRole!=null) {
		response.put("Data",accessRole);
		response.put("status", HttpStatus.CREATED);
		response.put("result", "Success");
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
		}else {
			throw new ResourceNotFoundException("Data is Null");
		}
	}
	
	@GetMapping("/getAllEmpLeave")
	public ResponseEntity<Map<String, Object>> getAllEmpLeave(){
		List<EmpLeave> empLeave=leaveService.getAllEmpLeave();
		if(empLeave!=null) {
		response.put("Data",empLeave);
		response.put("status", HttpStatus.CREATED);
		response.put("result", "Success");
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
		}else {
			throw new ResourceNotFoundException("Data is Null");
		}
	}
	
	@GetMapping("/getAllLeaveType")
	public ResponseEntity<Map<String, Object>> getAllLeaveType(){
		List<LeaveType> leaveType=leaveService.getAllLeaveType();
		if(leaveType!=null) {
		response.put("Data",leaveType);
		response.put("status", HttpStatus.CREATED);
		response.put("result", "Success");
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
		}else {
			throw new ResourceNotFoundException("Data is Null");
		}
	}
	
}
