package com.hotel.rateService.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotel.rateService.entities.Rating;

public interface RatingRepository extends JpaRepository<Rating,String> {

	List<Rating> findByUserId(String userId);
	
	List<Rating> findByHotelId(String hotelId);
	
}
