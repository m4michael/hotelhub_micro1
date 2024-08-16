package com.SECURITY.SERVICES;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.SECURITY.DTO.JwtAuthenticationResponse;
import com.SECURITY.DTO.SigninRequest;
import com.SECURITY.DTO.SignupRequest;
import com.SECURITY.ENTITY.ROLE;
import com.SECURITY.ENTITY.userCredential;
import com.SECURITY.REPOSITORY.userCredRepository;

@Service
public class authService {
	
	
    @Autowired
	
	private  userCredRepository userRepo;
	
	@Autowired
	private PasswordEncoder passEnc;
	
	@Autowired
	private AuthenticationManager authMang;
	
	@Autowired
	private JwtService  jwtserv;
	
	public userCredential  signup(SignupRequest sur) {
		
		userCredential userCred=new userCredential();
		
		
		String prefix = "UCID";
	    String randomId;
	    do {
	        int number = new Random().nextInt(100); // Generate a random number up to 99
	        randomId = String.format("%s%02d", prefix, number);
	    } while (userRepo.existsById(randomId)); // Check if the ID already exists

	    userCred.setId(randomId);
		
		
		userCred.setEmail(sur.getEmail());
		userCred.setName(sur.getName());
		userCred.setRole(ROLE.USER);

		userCred.setPassword(passEnc.encode(sur.getPassword()));
		
		
		return userRepo.save(userCred);
	}
	
	
		public JwtAuthenticationResponse signin(SigninRequest signinRequest) {
		
			try {
				
		        Authentication authen = authMang.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getEmail(), signinRequest.getPassword()));

		        if (authen.isAuthenticated()) {
		            var user = userRepo.findByEmail(signinRequest.getEmail())
		                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

		            String jwt = jwtserv.generateToken(user);

		            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
		            jwtAuthenticationResponse.setToken(jwt);

		            return jwtAuthenticationResponse;
		        } else {
		            throw new IllegalArgumentException("Invalid email or password");
		        }
		    } catch (Exception e) {
		        throw new RuntimeException("Invalid email or password", e);
		    }
	
	
	}

		

	public String validate(String token) {
	
			String userEmail=jwtserv.extractUserName(token);
			userCredential user=userRepo.findByEmail(userEmail).orElseThrow();
	
			if(jwtserv.isTokenValid(token,user)) {
		
				
		
		return "Token is valid";
		
			
			}
	
	return null;

	}

}
