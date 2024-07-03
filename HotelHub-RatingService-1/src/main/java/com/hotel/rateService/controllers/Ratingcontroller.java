package com.hotel.rateService.controllers;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.rateService.entities.Rating;
import com.hotel.rateService.repositories.RatingRepository;
import com.hotel.rateService.service.RatingService;

@RestController
@RequestMapping("/ratings")
public class Ratingcontroller {
	
	@Autowired
	RatingRepository RatingRepo;
	
	@Autowired
	RatingService ratingServ;

	//create rating
	@PostMapping
	public ResponseEntity<Rating> create(@RequestBody Rating rating ){
		
		String prefix = "RID";
	    String randomId;
	    do {
	        int number = new Random().nextInt(100); // Generate a random number up to 99
	        randomId = String.format("%s%02d", prefix, number);
	    } while (RatingRepo.existsById(randomId)); // Check if the ID already exists

	    rating.setRatingId(randomId);
	    
		
		return  ResponseEntity.status(HttpStatus.CREATED).body(ratingServ.create(rating));
	}
	
	//get all
	@GetMapping
	public ResponseEntity<List<Rating>> getRatings(){
		
		return ResponseEntity.status(HttpStatus.OK).body(ratingServ.getAllRatings());
	}
	
	
	//
	@GetMapping("/users/{userId}")
	public ResponseEntity<List<Rating>> getRatingsByUserId(@PathVariable String userId){
		
		return ResponseEntity.status(HttpStatus.OK).body(ratingServ.getRatingByUserID(userId));
	}
	
	
	
	@GetMapping("/hotels/{hotelId}")
	public ResponseEntity<?> getRatingsByHotelId(@PathVariable String hotelId){
		
		 List<Rating> ratings = ratingServ.getRatingByHotelId(hotelId);
		    if (ratings.isEmpty()) {
		        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No ratings found for hotel ID: " + hotelId);
		    }
		
		
		return ResponseEntity.status(HttpStatus.OK).body(ratings);
	}
	
	
	
}
