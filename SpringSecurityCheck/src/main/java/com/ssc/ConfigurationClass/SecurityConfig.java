package com.ssc.ConfigurationClass;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ssc.SecurityFilter.JwtAuthorizationFilter;
import com.ssc.UserRepo.UserRepo;



@EnableWebSecurity
@Configuration
public class SecurityConfig {
	
	@Autowired
	private UserRepo repo;
     @Autowired
     public AuthenticationProvider authenticationProvider; 
     

	@Autowired
	private JwtAuthorizationFilter authorizationFilter;


	    @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http
	            .csrf(csrf->csrf.disable())
	            .authorizeRequests(authorizeRequests ->
	                authorizeRequests
	                    .requestMatchers("/login").permitAll()
	                    .requestMatchers("/auth/save").permitAll()
	                    .anyRequest().authenticated()
	            )
	            .exceptionHandling()
	                .authenticationEntryPoint(new InvaildUserAuthenticationEntryPoint())
	            .and()
	            .authenticationProvider(authenticationProvider)
	            .sessionManagement(sessionManagement ->
	                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	            )
	            .addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class);

	        return http.build();
	    }

	    
	   
}
