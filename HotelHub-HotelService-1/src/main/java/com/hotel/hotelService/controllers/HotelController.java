package com.hotel.hotelService.controllers;

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

import com.hotel.hotelService.entites.Hotel;
import com.hotel.hotelService.repositories.HotelRepository;
import com.hotel.hotelService.services.HotelService;

@RestController
@RequestMapping("/hotels")
public class HotelController {

	@Autowired
	HotelRepository hotelRepo;
	
	@Autowired
	private HotelService hotelServ;
	
	
	//create
	@PostMapping
	public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
		
		String prefix = "HID";
		String randomId;
	    

	    do {
	    	int number = new Random().nextInt(100); // Generate a random number up to 99
	      randomId = String.format("%s%01d", prefix, number);
	    }while(hotelRepo.existsById(randomId));
	    	
	     	hotel.setId(randomId);
		
		
		return ResponseEntity.status(HttpStatus.CREATED).body(hotelServ.create( hotel));
		
	}
	
	
	//get single
		@GetMapping("/{hotelId}")
		public ResponseEntity<Hotel> getSingleHotel(@PathVariable String hotelId){
			
			return ResponseEntity.status(HttpStatus.OK).body(hotelServ.getHotel( hotelId));
			
		}
		
	//create
	@GetMapping
	public ResponseEntity<List<Hotel>> getAllHotel(){
			
			return ResponseEntity.status(HttpStatus.OK).body(hotelServ.getAllHotel( ));
			
		}
	
}
