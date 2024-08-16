package com.SECURITY.SERVICES;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.SECURITY.CONFIG.JwtConfig;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
	
	@Autowired
	 private  JwtConfig jwtConfig;

	    

	    // Change visibility to public
	    public String generateToken(UserDetails userDetails) {
	        return Jwts.builder().setSubject(userDetails.getUsername())
	                .setIssuedAt(new Date(System.currentTimeMillis()))
	                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
	                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
	                .compact();
	    }

	    
	   

	    
	    
	    public String extractUserName(String token) {
	        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody().getSubject();
	    }

	   

	    private Key getSigningKey() {
	        byte[] keyBytes = jwtConfig.getSecret().getBytes();
	        return Keys.hmacShaKeyFor(keyBytes);
	    }

	   
	    
	    
	    public boolean isTokenValid(String token,UserDetails userDetails) {
	    	
	    	final String username=extractUserName(token);
	    	return (username.equals(userDetails.getUsername())  && !isTokenExpired(token));
	    }
	    
	    private boolean isTokenExpired(String token) {
	    	
	    	//return extractClaims(token,Claims::getExpiration).before(new Date());
	    	return (Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody().getExpiration()).before(new Date());
	    }

}
