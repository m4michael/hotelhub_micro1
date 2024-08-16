package com.hotel.gateway.util;

import java.security.Key;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class jwtUtil {

	
   
	 public static final String SECRET ="hjhfgkjsnjsgJSJHGFJSNVSRH45788fskjnsfkjgbsjvnhjUFBZBKS875647ajbfb";
    
    
   

   

    private Key getSigningKey() {
        byte[] keyBytes = SECRET.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }

   
    
    
    public void isTokenValid(String token) {
    
    	Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token);
    	
    }
    
   

	
	
}
