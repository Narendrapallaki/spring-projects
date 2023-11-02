package com.identity.controller;

import com.identity.dto.AccessLevel;
import com.identity.dto.AuthRequest;
import com.identity.dto.Employee;
import com.identity.entity.UserCredential;
import com.identity.service.AuthService;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
@Slf4j
@RestController
@RequestMapping("/security")
public class AuthController {
	
	
	 private static final String employeeBasePath="http://localhost:8081/emp";
    @Autowired
    private AuthService service;

    @Autowired
    private AuthenticationManager authenticationManager;
    
    Map<String,Object>response=new HashMap<>();
    
    
    
    @GetMapping("/itiswork")
	public String welcom1e() {
		log.info("*******inside Welcome AuthController");
		String welcomeOk = service.welcomeOk();
		log.info("itiswork",welcomeOk);
		return "Hello This is welcomePage";
	}

    @PostMapping("/register")
    public ResponseEntity<Map<String,Object>> addNewUser(@RequestBody Employee user) {
    	
    	Object saveUser = service.saveUser(user);
    	
    	if (saveUser!=null) {
    		log.info("user data saved into database");
    		
    		response.put("Message", "User register details");
    		response.put("Result", "User details are add to databaase");
    		response.put("Status", HttpStatus.OK.value());
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
		}else
		{
			response.put("Message", "User register details something went wrong");
    		response.put("Error", saveUser);
    		response.put("Status", HttpStatus.BAD_REQUEST.value());
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
		}
        
    }

    @PostMapping("/token")
    public ResponseEntity<Map<String,Object>> getToken(@RequestBody AuthRequest authRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authenticate.isAuthenticated()) {
               log.info(authRequest.getUsername());
        	String generateToken = service.generateToken(authRequest.getUsername());
        	response.put("Message", "User register details");
    		response.put("Result",generateToken);
    		response.put("Status", HttpStatus.OK.value());
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
          //  return service.generateToken(authRequest.getUsername());
       } 
    //    else {
//            throw new RuntimeException("invalid access");
//        }
		return null;
    }

    @GetMapping("/validate")
    public  ResponseEntity<Map<String,Object>> validateToken(@RequestParam("token") String token) {
        service.validateToken(token);
       
        try {
        	  response.put("Message", "User token checking");
      		response.put("Result","token validated");
      		response.put("Status", HttpStatus.OK.value());
      		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
        }
        catch (Exception e) {
			// TODO: handle exception
        	throw new RuntimeException("not valid token");
		}
      
    }
    
    @PostMapping("/saveroles")
	public ResponseEntity<Map<String,Object>>saveRole(@RequestBody AccessLevel accessRole)
	{
		
		log.info("********Inside save controller*********");
		AccessLevel saveRole = service.saveRole(accessRole);	
		response.put("Message", "User roles are saved in system");
		response.put("Result", saveRole);
		response.put("Status code", HttpStatus.OK.value());
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
		
	}
	
	
	@GetMapping("/getAllRoles")
	//@PostAuthorize("hasAuthority('1001')")
	public ResponseEntity<Map<String,Object>>getAllRoles()
	{
		
		log.info("******Inside getAllRoles controller********");
		 List<AccessLevel> allRoles = service.getAllRoles();
		if(allRoles==null)
		{
		 throw new RuntimeException("User details not found in db");
		}
		else
		{
			response.put("Message", "User data fetch from database");
			response.put("Result", allRoles);
			response.put("Status code", HttpStatus.OK.value());	
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
		}
	}
    
    
   
    @GetMapping("/welcome")
 //   @PreAuthorize("hasAuthority('1001')")
    public String welcome() {
        return "Welcomer";
    }
}
