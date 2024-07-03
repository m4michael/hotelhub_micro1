package com.hotel.rateService.service;

import java.util.List;

import com.hotel.rateService.entities.Rating;

public interface RatingService {

	
	//create
	
	Rating create(Rating rating);
	
	//getall ratings
	
	List<Rating> getAllRatings();
	
	//get ratings by userId
	List<Rating> getRatingByUserID(String userId); 
	
	//get all by hotel
	List<Rating> getRatingByHotelId(String hotelId);
	
}


//whenever we getRatingByUserID(String userId) it will provide list of smae userId because userId is not primary key
//and return type is List<Rating> so all the fields related to Rating class wil return associated with userId.