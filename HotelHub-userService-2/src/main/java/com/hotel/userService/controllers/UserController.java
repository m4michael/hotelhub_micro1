package com.hotel.userService.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;


@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userServ;
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	//create
	
	@PostMapping
	public ResponseEntity<User1> createUser(@RequestBody User1 user){
		
		User1 user1=userServ.saveUser(user);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(user1); //returning ResponseEntity without new Operator
		//		return new ResponseEntity<>(user1,HttpStatus.CREATED); // using new operator

	}
	

	int retryCount=1;
	//single user
	
	@GetMapping("/{userId}")
	//@Retry(name="Retry",fallbackMethod="ratingHotelFallBack")
	//@CircuitBreaker(name="Breaker",fallbackMethod = "ratingHotelFallBack")
	@RateLimiter(name="Limiter",fallbackMethod = "ratingHotelFallBack")
	public ResponseEntity<User1> getSingleUser(@PathVariable String userId){
		
		logger.info("Get single User Handler:UserController");
		logger.info("Retry count:{}",retryCount);
		
		retryCount++;
		
		User1 user = userServ.getUser(userId);
		
		return ResponseEntity.ok(user);
		
	}
	
	//creating fall back method for circuitbreaker
	
	public ResponseEntity<User1> ratingHotelFallBack(String userId,Exception ex){
		
		
		logger.info("Fallback is executed because service is down: ", ex.getMessage());
		
		User1 user1=User1.builder()
		.email("dummy@gmail.com")
		.name("Dummy")
		.about("This is Dummy user because some service is down")
		.build();
		
		return new ResponseEntity<>(user1,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	//all user get
	
	@GetMapping
	public ResponseEntity<List<User1>> getAllUser(){
		
		List<User1> allUser = userServ.getAllUser();
		
		return ResponseEntity.ok(allUser);
		
	}
	
}
