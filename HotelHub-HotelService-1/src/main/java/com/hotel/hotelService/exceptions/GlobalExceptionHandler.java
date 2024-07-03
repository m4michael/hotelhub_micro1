package com.hotel.hotelService.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice

public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Map<String,Object>> notFoundHandler(ResourceNotFoundException ex){
		
		Map<String, Object> map=new HashMap<>();
		
		map.put("message", ex.getMessage());
		map.put("success", false);
		map.put("status", HttpStatus.NOT_FOUND );
		
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
		
		
	}
	
	//here  ResponseBody is of Map (shortcut no need to create extra class like ApiResponse)
	//earlier  in HotelRating-userService we had Response body of ApiResponse which we had
	//create seperately in GlobalExceptionHandler class
	
	
}
