package com.identity.config;

import com.identity.dto.AuthRequest;
import com.identity.dto.Employee;
import com.identity.entity.UserCredential;
import com.identity.repository.UserCredentialRepository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Slf4j
@Component
public class CustomUserDetailsService implements UserDetailsService {

	 private static final String employeeBasePath="http://localhost:8081/emp";
//	@Autowired
//	private UserCredentialRepository repository;
 
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		RestTemplate restTemplate=new RestTemplate();
		            ResponseEntity<Employee> exchange = restTemplate.exchange(employeeBasePath+"/getByMail/"+username, HttpMethod.GET, null,Employee.class );
	                   
	                 log.info(exchange.getBody().toString());
      return new CustomUserDetails(exchange.getBody());
//		return ((Optional<Employee>) exchange).map(CustomUserDetails::new)
//				.orElseThrow(() -> new UsernameNotFoundException("user not found with name :" + username));
	}
}
