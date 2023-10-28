package com.identity.controller;

import com.identity.dto.AuthRequest;
import com.identity.entity.UserCredential;
import com.identity.service.AuthService;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping("/security")
public class AuthController {
    @Autowired
    private AuthService service;

    @Autowired
    private AuthenticationManager authenticationManager;
    
    Map<String,Object>response=new HashMap<>();

    @PostMapping("/register")
    public ResponseEntity<Map<String,Object>> addNewUser(@RequestBody UserCredential user) {
    	
    	UserCredential saveUser = service.saveUser(user);
    	
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
        	    
        	String generateToken = service.generateToken(authRequest.getUsername());
        	response.put("Message", "User register details");
    		response.put("Result",generateToken);
    		response.put("Status", HttpStatus.OK.value());
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
          //  return service.generateToken(authRequest.getUsername());
        } else {
            throw new RuntimeException("invalid access");
        }
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
    
    @GetMapping("/welcome")
    public String welcome() {
        return "Welcomer";
    }
}
