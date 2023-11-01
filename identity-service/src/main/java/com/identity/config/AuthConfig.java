package com.identity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity

public class AuthConfig {

	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomUserDetailsService();
	}

//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		return http.csrf().disable().authorizeHttpRequests()
//				.requestMatchers("/security/register", "/security/token", 
//						"/security/validate","/security/welcome",
//						"/security/saveroles","/security/getAllRoles").permitAll()
//			
//				.and().build();
//				
//	}
	
//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//	    return http.csrf().disable()
//	            .authorizeHttpRequests()
//	                .requestMatchers("/security/welcome").hasAuthority("1001")
//	                .anyRequest().authenticated()
//	            .and().build();
//	}

	
	  @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        return http.csrf(AbstractHttpConfigurer::disable)
	                .authorizeHttpRequests(auth->{
	                    auth.requestMatchers("/security/register", "/security/token", 
	"/security/validate").permitAll()
	                            .requestMatchers("/security/**").hasAuthority("1001").anyRequest().authenticated();
	                })
	            //    .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	             //   .authenticationProvider(authenticationProvider())
	             //   .addFilterBefore(jwtAuthFilter(), UsernamePasswordAuthenticationFilter.class)
	                .build();
	    }
	
	
	
	
	
	
	
	
	
	
	
	


	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
}
