package com.Eidiko.Manager.Controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Eidiko.Manager.CustomException.ResourceNotFoundException;
import com.Eidiko.Manager.Entity.ManagerEntity;
import com.Eidiko.Manager.Service.ManagerService;
import com.Eidiko.Manager.valueObject.ResponseTemplateVo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/manager")
public class ManagerController {

	//private Logger logger=LoggerFactory.getLogger(ManagerController.class);
	
	@Autowired
	private ManagerService managerService;
	
	Map<String, Object> response=new HashMap<>();
	@PostMapping("/saveManager")
	public ResponseEntity<Map<String, Object>> saveManagerEntity(@RequestBody ManagerEntity managerEntity){
		
		managerService.saveManagerEntity(managerEntity);
		//logger.info("manager entity data getting from service !!");
		log.info("manager entity data getting from service !!");
		response.put("Message", "Manager data saveed !!");
		response.put("status", HttpStatus.CREATED);
		response.put("result", "Success");
		
		log.info("response sent:"+response);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
		
	}
	
	@GetMapping("/getById/{id}")
	public ResponseEntity<Map<String, Object>> getManagerById(@PathVariable Long id){
		ManagerEntity manageById = managerService.getManageById(id);
		log.info("Data fetched from Service");
		
		if(manageById!=null) {
			log.info("managById is Not Null !!");
			response.put("Data",manageById);
			response.put("status", HttpStatus.OK);
			response.put("result", "Success");
			log.info("response created: "+response);
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
			}else {
				log.error("ResourceNotFoundException is throwing !!");
				throw new ResourceNotFoundException("Data is Null");
			}
	}
	
	@GetMapping("/get/{manaid}")
	public ResponseTemplateVo getResonseTemplateVO(@PathVariable Long manaid)
	{
	System.out.println("controller");	
		log.info("getResonseTemplateVO entity data getting from service !!");
		
		return managerService.getManagerWithEmployeeId(manaid);
		
	}
	
	
}
