package com.hotel.hotelService.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.hotelService.entites.Hotel;
import com.hotel.hotelService.exceptions.ResourceNotFoundException;
import com.hotel.hotelService.repositories.HotelRepository;


@Service
public class HotelServiceImpl implements HotelService{
	
	
	@Autowired
	HotelRepository hotelRepo;
	

	@Override
	public Hotel create(Hotel hotel) {
		return hotelRepo.save(hotel);
	}

	@Override
	public List<Hotel> getAllHotel() {
		return hotelRepo.findAll();
	}

	@Override
	public Hotel getHotel(String id) {
		return hotelRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("hotel with given id not found!!"));
	}

	
	
	
	
	
}
