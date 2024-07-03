package com.hotel.userService.services;

import java.util.List;

import com.hotel.userService.entities.User1;

public interface UserService {
	
	User1 saveUser(User1 user);
	
	List<User1> getAllUser();
	
	
	User1 getUser(String userId);
	
	void deleteUser(String userId);
	
	public User1 updateUser(String userId, User1 user) ;	

}
