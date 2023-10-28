package com.ssc.UserController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssc.Entity.User;
import com.ssc.Entity.UserReponse;
import com.ssc.Entity.UserRequest;
import com.ssc.JwtClass.JwtClass;
import com.ssc.UserRepo.UserRepo;
import com.ssc.UserService.IUserServiceInterface;
@RestController
public class UserContro {
	@Autowired
	public UserRepo repo;
	
	@Autowired
	public IUserServiceInterface iUserServiceInterface;
	@Autowired
	public JwtClass jwtClass;
	@Autowired
	public AuthenticationManager authenticationManager;

	@PostMapping("/auth/save")
	public ResponseEntity<String> saveData(@RequestBody User usr)
	{
		Integer saveuser = iUserServiceInterface.saveuser(usr);
		System.out.println(saveuser);
		return new ResponseEntity<String>("User data save", HttpStatus.OK);
	}
 
	
	@PostMapping("/login")
	public ResponseEntity<UserReponse>loginUser(@RequestBody UserRequest userRequest)
	{
	
		
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(userRequest.getUsername(),
						userRequest.getPassword()));
		//System.out.println("Username from userRequest: " + userRequest.getUsername());
		
		
		String token = jwtClass.generateToken(repo.findByUsername(userRequest.getUsername()));
		
		return ResponseEntity.ok(new UserReponse(token,"done"));
		
	}
	
	
	
	     
	
	
	
}
