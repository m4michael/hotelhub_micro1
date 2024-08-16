package com.SECURITY.CONTROLLER;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SECURITY.DTO.JwtAuthenticationResponse;
import com.SECURITY.DTO.SigninRequest;
import com.SECURITY.DTO.SignupRequest;
import com.SECURITY.ENTITY.userCredential;
import com.SECURITY.SERVICES.authService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	authService authServ;
	
	@PostMapping("/register")
	public String addNewUser(@RequestBody SignupRequest sur) {
		
		userCredential userCred=authServ.signup(sur);
		
		if(userCred!=null) {
			
			return "User ADDed";
		}
		
		return "User Not Added";
	}
	
	 @PostMapping("/token")
	    public ResponseEntity<?> getToken(@RequestBody SigninRequest sir) {
	        try {
	            JwtAuthenticationResponse token = authServ.signin(sir);
	            return ResponseEntity.ok(token.getToken());
	        } 
	         catch (RuntimeException e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Invalid username or password");
	        }
	    }
	
	
	@GetMapping("/validate/{token}")
	public String validateToken(@PathVariable String token ) {
		
		String valid=authServ.validate( token);
		if(valid!=null) {
			
			return valid;
		}
		
		
		return "Token is not valid";
	}

}
