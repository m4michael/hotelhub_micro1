package com.hotel.userService.services;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hotel.userService.entities.Hotel;
import com.hotel.userService.entities.Rating;
import com.hotel.userService.entities.User1;
import com.hotel.userService.exceptions.ResourceNotFoundException;

//import com.hotel.userService.external.service.HotelService;
//import com.hotel.userService.external.service.RatingService;

import com.hotel.userService.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private RestTemplate restTemp;
	
	@Autowired
	private UserRepository userRepo;
	
	private Logger logger=LoggerFactory.getLogger(UserServiceImpl.class);
	
/*	@Autowired
	private HotelService hotelServ;//feign client
	
	@Autowired
	private RatingService ratingServ;//feign client  */

	@Override
	public User1 saveUser(User1 user) {
		
		//String randomId = UUID.randomUUID().toString();
		
		String prefix = "UID";
	    String randomId;
	    do {
	        int number = new Random().nextInt(100); // Generate a random number up to 99
	        randomId = String.format("%s%02d", prefix, number);
	    } while (userRepo.existsById(randomId)); // Check if the ID already exists

	    user.setUserId(randomId);
	    return userRepo.save(user);
	}

	@Override
	public List<User1> getAllUser() {
		long count = userRepo.count();
		System.out.println("total entities:"+count);
		
		return userRepo.findAll() ;
	}

	@Override
	public User1 getUser(String userId) {
		
		
		User1 user1 = userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User with given id is not Found on server !!:"+userId)) ;
		
		//fetching Rating of the above user form Rating serrvice
		//url-> http://localhost:8082/ratings/users/UID01
		
		//ArrayList<Rating> ratingOfUser = restTemp.getForObject("http://localhost:8082/ratings/users/"+user1.getUserId(), ArrayList.class);
		
		Rating[] ratingsArray = restTemp.getForObject("http://RATINGSERVICE-1/ratings/users/" + user1.getUserId(), Rating[].class);
		
	/*	Rating[] ratingsArray=ratingServ.getRating(user1.getUserId());
		logger.info("using feign for getting Rating");   */
		
		List<Rating> ratingOfUser = Arrays.asList(ratingsArray);

		
		
		logger.info("{}",ratingOfUser);
		
		List<Rating> ratingList=ratingOfUser.stream().map(rating->{
			
			//http://localhost:8081/hotels/HID40
			
	     ResponseEntity<Hotel> forEntity=restTemp.getForEntity("http://HOTELSERVICE/hotels/"+rating.getHotelId(),Hotel.class );
			Hotel hotel=forEntity.getBody();
			logger.info("response Status Code:{}",forEntity.getStatusCode());
			
			
		/*	Hotel hotel=hotelServ.getHotel(rating.getHotelId()); 
			logger.info("USing FeignClient for hotelService");  */
			rating.setHotel(hotel);
			
			return rating;
			
		}).collect(Collectors.toList());
		
		user1.setRatings(ratingList);
	
	   return user1;
	}

	@Override
	public void deleteUser(String userId) {

        //User1 user = getUser(userId); // Check if user exists
	User1 user	=userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User with given id is not Found on server !!:"+userId)) ;

         userRepo.delete(user);
		
	}

	@Override
	public User1 updateUser(String userId, User1 user) {		
		
		User1 user1	=userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User with given id is not Found on server !!:"+userId)) ;
	        user1.setName(user.getName());
	        user1.setEmail(user.getEmail());
	        user1.setEmail(user.getAbout());

	        // Update other fields as necessary
	        return userRepo.save(user1);

	}
	
	
	
	
}
