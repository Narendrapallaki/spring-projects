package com.identity.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import com.identity.dto.Employee;


import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

import java.util.Collection;
import java.util.List;


@Slf4j
public class CustomUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;
	public String username;
	public String password;
	public Long accesslevel;
	//public List<GrantedAuthority>authority;


	/**
	 * @param username
	 * @param password
	 * @param accesslevel
	 */
	public CustomUserDetails(Employee credential) {
		super();
		this.username = credential.getEmail();
		this.password = credential.getPassword();
		this.accesslevel=credential.getAccesCode();
	    // this.authority = Arrays.stream(credential.getAccessLevel().split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
		
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	    List<GrantedAuthority> list = new ArrayList<>();
	    list.add(new SimpleGrantedAuthority(("ROLE_" + accesslevel)));
	    System.out.println("Accesslevel code :"+accesslevel);
	    log.info(list.toString());
	    return list;
	}

//		       log.info("autho:", authority);
//		return authority;
	

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
