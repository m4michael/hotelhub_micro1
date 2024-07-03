package com.hotel.userService.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hotel.userService.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ResourceNotFoundException ex){
		
		String message = ex.getMessage();
		ApiResponse response=ApiResponse.builder().message(message).success(true).status(HttpStatus.NOT_FOUND).build();
	
	return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	
	
	}
	

	//ApiResponse is response body
	//if we returning Response Entity with new operator then
	//while returning ResponseEntity it is must to send status in argument, response body is optional.
	//first argument is body ,2nd argument is status.
	
}
