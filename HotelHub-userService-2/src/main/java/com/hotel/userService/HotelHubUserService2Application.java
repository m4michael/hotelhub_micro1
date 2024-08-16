package com.hotel.userService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
//@EnableFeignClients
public class HotelHubUserService2Application {
	
	@Bean
	@LoadBalanced
	 RestTemplate restTemplate() {
		
		return new RestTemplate();
	}
	

	public static void main(String[] args) {
		SpringApplication.run(HotelHubUserService2Application.class, args);
	}

}
