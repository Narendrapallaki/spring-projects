package com.identity.service;


import com.identity.dto.AccessLevel;
import com.identity.dto.Employee;
import com.identity.entity.UserCredential;
import com.identity.repository.AccessLevelRepo;
import com.identity.repository.UserCredentialRepository;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Slf4j
@Service
public class AuthService {

    @Autowired
    private UserCredentialRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;
    @Autowired
    private AccessLevelRepo accessLevelRepo;
    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<Object> saveUser(Employee employee) {
    	log.info("inside hhhiiii***********");
//        credential.setPassword(passwordEncoder.encode(credential.getPassword()));
//        UserCredential save = repository.save(credential);
    	ResponseEntity exchange = restTemplate.exchange("http://localhost:8081/emp/save", HttpMethod.POST, new HttpEntity<>(employee), Object.class);
    	log.info("out ***********");
        return exchange;
    }

    public String generateToken(String username) {
        return jwtService.generateToken(username);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }

    
	public AccessLevel saveRole(AccessLevel accessRole)
	{
		log.info("********Inside save service******");
		
		AccessLevel save = accessLevelRepo.save(accessRole);
		
		return save;
		
	}
    
	
	public List<AccessLevel>getAllRoles()
	{
		
		log.info("*******Inside getAllRoles*******");
		Iterable<AccessLevel> findAll = accessLevelRepo.findAll();
		if (findAll==null) {
			
			throw new RuntimeException("data not available");
		}
		return (List<AccessLevel>) findAll;
		
	}


}
