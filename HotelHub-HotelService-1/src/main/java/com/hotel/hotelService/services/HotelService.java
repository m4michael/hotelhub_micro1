package com.hotel.hotelService.services;

import java.util.List;

import com.hotel.hotelService.entites.Hotel;

public interface HotelService {
	
	
	//create
	Hotel create(Hotel hotel);
	
	//getall
	List<Hotel> getAllHotel();
	
	//getSingle
	Hotel getHotel(String id);

}
