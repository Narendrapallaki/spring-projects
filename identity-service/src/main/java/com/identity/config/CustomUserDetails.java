package com.identity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.client.RestTemplate;


import com.identity.entity.UserCredential;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Slf4j
public class CustomUserDetails implements UserDetails {

	public String username;
	public String password;
	
	//public List<GrantedAuthority>authority;


	/**
	 * @param username
	 * @param password
	 * @param accesslevel
	 */
	public CustomUserDetails(UserCredential credential) {
		super();
		this.username = credential.getEmail();
		this.password = credential.getPassword();
	    // this.authority = Arrays.stream(credential.getAccessLevel().split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
		
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;

//		       log.info("autho:", authority);
//		return authority;
	}

	/**
	 * @param userCredential
	 * @param access
	 */

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
