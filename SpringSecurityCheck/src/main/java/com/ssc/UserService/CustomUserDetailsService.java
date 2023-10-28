/*
 * package com.ssc.UserService;
 * 
 * import java.util.List; import java.util.stream.Collectors;
 * 
 * import org.hibernate.mapping.Collection; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.security.core.authority.SimpleGrantedAuthority; import
 * org.springframework.security.core.userdetails.UserDetails; import
 * org.springframework.security.core.userdetails.UserDetailsService; import
 * org.springframework.security.core.userdetails.UsernameNotFoundException;
 * import org.springframework.stereotype.Service;
 * 
 * import com.ssc.Entity.User; import com.ssc.UserRepo.UserRepo;
 * 
 * @Service public class CustomUserDetailsService implements UserDetailsService{
 * 
 * 
 * @Autowired private UserRepo userRepo;
 * 
 * @Override public UserDetails loadUserByUsername(String username) throws
 * UsernameNotFoundException { // TODO Auto-generated method stub
 * 
 * User user = userRepo.findByUsername(username);
 * 
 * 
 * if (user==null) { throw new
 * UsernameNotFoundException("User not found :-"+username); }
 * 
 * List<SimpleGrantedAuthority> list = user.getRoles().stream() .map(role -> new
 * SimpleGrantedAuthority(role)) .collect(Collectors.toList());
 * 
 * UserDetails userDetails = org.springframework.security.core.userdetails.User
 * .withUsername(user.getUsername()) .password(user.getPassword())
 * //.authorities(user.getRoles()) // Assuming roles is a Collection of
 * GrantedAuthority .authorities(list) .build();
 * 
 * return userDetails; }
 * 
 * }
 */