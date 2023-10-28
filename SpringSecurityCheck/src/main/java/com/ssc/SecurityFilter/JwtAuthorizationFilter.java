package com.ssc.SecurityFilter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ssc.JwtClass.JwtClass;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class JwtAuthorizationFilter extends OncePerRequestFilter {

		
		@Autowired
		private UserDetailsService customUserDetailsService;;
		

	    private final JwtClass jwtClass;

	    public JwtAuthorizationFilter(JwtClass jwtClass) {
	        this.jwtClass = jwtClass;
	    }

	    @Override
	    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	            throws ServletException, IOException {
	    	
	    	String username = null;
	        String token = null;
	    	
	         token = request.getHeader("Authorization");

	        if (token != null && token.startsWith("Bearer ")) {
	        	
	            token = token.substring(7);
	       
	             username = jwtClass.getUsernameFromToken(token);
	        }

	        if (username!=null && SecurityContextHolder.getContext().getAuthentication()==null) 
	        {
				try {
					UserDetails userDetails =this.customUserDetailsService.loadUserByUsername(username);
		        	
		        	if (jwtClass.validateToken(token, userDetails)) {
						
		        		System.out.println("tokenwork");
			        	
			UsernamePasswordAuthenticationToken upat=
		                    new UsernamePasswordAuthenticationToken(userDetails.getUsername(), 
		    		userDetails.getPassword(), userDetails.getAuthorities());
			upat.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	        SecurityContextHolder.getContext().setAuthentication(upat);
	        
	        System.out.println(upat.toString());
	        System.out.println(upat.getAuthorities());
	        	                // Log successful authentication
	        	                System.out.println("User authenticated: " + userDetails.getUsername());
					}
		        	
					
				}
				catch (Exception e) {
					// TODO: handle exception
					System.out.println("user not autherized");
					e.printStackTrace();
				}
	        	
			}
	        

	        filterChain.doFilter(request, response);
	    }


	
}
