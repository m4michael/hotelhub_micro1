package com.hotel.dashboard1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import com.hotel.dashboard1.dto.Hotel;
import com.hotel.dashboard1.dto.Rating;
import com.hotel.dashboard1.dto.User;

@Controller
public class DashboardController {

	@Autowired
    private RestTemplate restTemplate;
	
	
	 @GetMapping("/dashboard")
	    public String getDashboard(Model model) {
	        User[] users = restTemplate.getForObject("http://UserService/users", User[].class);
	        Rating[] ratings = restTemplate.getForObject("http://RATINGSERVICE-1/ratings", Rating[].class);
	        Hotel[] hotel=restTemplate.getForObject("http://HOTELSERVICE/hotels", Hotel[].class);

	        model.addAttribute("users", users);
	        model.addAttribute("ratings", ratings);
	        model.addAttribute("hotels", hotel);


	        return "dashboard";
	    }
	
	
	
	
}
