package com.hotel.userService.external.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hotel.userService.entities.Rating;

@FeignClient(name="RatingService-1")
public interface RatingService {
	
	
	@GetMapping("/ratings/users/{userId}")
	Rating[] getRating(@PathVariable String userId);

}
