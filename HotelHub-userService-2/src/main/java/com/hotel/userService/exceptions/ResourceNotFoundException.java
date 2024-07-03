package com.hotel.userService.exceptions;

public class ResourceNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException() {
		super("Resource Not Found Exception !!");
		
		
	}

	public ResourceNotFoundException(String message) {
		super(message);
		
		
	}
	
	
	
	

}
