package com.ssc.UserRepo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ssc.Entity.User;
@Repository
public interface UserRepo extends CrudRepository<User, Integer> {
	
	User findByUsername(String username);

}
