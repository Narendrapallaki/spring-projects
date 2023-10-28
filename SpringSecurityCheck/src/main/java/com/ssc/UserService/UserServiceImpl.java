package com.ssc.UserService;

import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssc.Entity.User;
import com.ssc.UserRepo.UserRepo;
@Service
public class UserServiceImpl implements IUserServiceInterface{
             //,UserDetailsService
    @Autowired
	private UserRepo userRepo;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public Integer saveuser(User user) {
			
		//encode password	
		String encode = bCryptPasswordEncoder.encode(user.getPassword());
		
		user.setPassword(encode);
		Integer id = userRepo.save(user).getId();
		
		return id;
	}

//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		// TODO Auto-generated method stub
//		return null;
//	}

//	@Override
//	public Optional<User> findByUsername(String username) {
//		// TODO Auto-generated method stub
//		
//		
//		
//		return userRepo.findByUsername(username);
//	}
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		// TODO Auto-generated method stub
//		
//		   Optional<User> byUsername = findByUsername(username);
//		   
//		   if (byUsername.isEmpty()) {
//			   
//			   throw new UsernameNotFoundException("User name not found"+username);
//			
//		}
//		   
//		  User user=byUsername.get();
//		return new org.springframework.security.core.userdetails.User(username, user.getPassword(),
//				user.getRoles().stream().map(role->new SimpleGrantedAuthority(role)).collect(Collectors.toList()));
//	}
//	
//	
	
	

}
