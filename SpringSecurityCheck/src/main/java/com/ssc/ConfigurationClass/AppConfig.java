package com.ssc.ConfigurationClass;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.ssc.UserRepo.UserRepo;

@Configuration
public class AppConfig {
	
	@Autowired
	public UserRepo repo;
	
	 @Bean
	    public UserDetailsService detailsService()
	    {
			return name->repo.findByUsername(name);
	    	
	    }
	    @Bean
	    public AuthenticationProvider authenticationProvider1()
	    {
			
	    	DaoAuthenticationProvider ad=new DaoAuthenticationProvider();
	    	ad.setPasswordEncoder(en());
	    	ad.setUserDetailsService(detailsService());
	    	return ad;
	    }
	  
		@Bean
		public BCryptPasswordEncoder en()
		{
			return new BCryptPasswordEncoder(); 
			
		}
		
		@Bean
		public AuthenticationManager authenticationManager(
				AuthenticationConfiguration authConfig) 
						throws Exception {
			return authConfig.getAuthenticationManager();
		}
		
}
