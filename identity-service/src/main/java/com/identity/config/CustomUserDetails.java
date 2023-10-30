package com.identity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.client.RestTemplate;

import com.identity.dto.Access;
import com.identity.entity.UserCredential;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Slf4j
public class CustomUserDetails implements UserDetails {

    private String username;
    private String password;
    @Autowired
    private RestTemplate restTemplate;

    public CustomUserDetails(UserCredential userCredential) {
        this.username = userCredential.getEmail();
        this.password = userCredential.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    	
//    	ResponseEntity<Access> exchange = restTemplate.exchange("http://access-level/access/getAllRoles", 
//    			HttpMethod.GET, null,
//    			 Access.class );
//    	
//     Access body = exchange.getBody();
// 
//    	log.info("fetch body variables", body);
//    	Collection<GrantedAuthority>sp=new ArrayList<>();
//
//    	sp.add(new SimpleGrantedAuthority(body.getDescription()));
//        return sp;
    	
    	return null;
    }

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
