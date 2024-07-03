package com.hotel.dashboard1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class HotelHubDashBoard1Application {

	@Bean
    @LoadBalanced
     RestTemplate restTemplate() {
        return new RestTemplate();
    }
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(HotelHubDashBoard1Application.class, args);
	}

}
