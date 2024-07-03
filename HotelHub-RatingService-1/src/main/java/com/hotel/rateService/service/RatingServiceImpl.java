package com.hotel.rateService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.rateService.entities.Rating;
import com.hotel.rateService.repositories.RatingRepository;


@Service
public class RatingServiceImpl implements RatingService {

	@Autowired
	RatingRepository ratingRepo;
	
	@Override
	public Rating create(Rating rating) {
		return ratingRepo.save(rating);
	}

	@Override
	public List<Rating> getAllRatings() {
		return ratingRepo.findAll();
	}

	@Override
	public List<Rating> getRatingByUserID(String userId) {
		return ratingRepo.findByUserId(userId);
	}

	@Override
	public List<Rating> getRatingByHotelId(String hotelId) {
		return ratingRepo.findByHotelId(hotelId);
	}

	
	
	
}
