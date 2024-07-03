package com.hotel.userService.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.userService.entities.User1;
import com.hotel.userService.services.UserService;


@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userServ;
	
	//create
	
	@PostMapping
	public ResponseEntity<User1> createUser(@RequestBody User1 user){
		
		User1 user1=userServ.saveUser(user);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(user1); //returning ResponseEntity without new Operator
		//		return new ResponseEntity<>(user1,HttpStatus.CREATED); // using new operator

	}
	

	
	//single user
	
	@GetMapping("/{userId}")
	public ResponseEntity<User1> getSingleUser(@PathVariable String userId){
		
		User1 user = userServ.getUser(userId);
		
		return ResponseEntity.ok(user);
		
	}
	
	
	//all user get
	
	@GetMapping
	public ResponseEntity<List<User1>> getAllUser(){
		
		List<User1> allUser = userServ.getAllUser();
		
		return ResponseEntity.ok(allUser);
		
	}
	
}
