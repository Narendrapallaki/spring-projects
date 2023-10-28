package com.ssc.UserService;

import java.util.Optional;

import com.ssc.Entity.User;

public interface IUserServiceInterface {
	
	public Integer saveuser(User user);
	//Optional<User>findByUsername(String username);
}
