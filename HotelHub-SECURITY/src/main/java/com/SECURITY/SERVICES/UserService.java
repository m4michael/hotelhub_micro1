package com.SECURITY.SERVICES;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.SECURITY.ENTITY.userCredential;
import com.SECURITY.REPOSITORY.userCredRepository;


@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	userCredRepository ucr;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		userCredential userCred = ucr.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
		
		
		
		return userCred ;
	}
	
	

}
