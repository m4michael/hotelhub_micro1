package com.hotel.serviceRegistry1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class HotelHubServiceRegistry1Application {

	public static void main(String[] args) {
		SpringApplication.run(HotelHubServiceRegistry1Application.class, args);
	}

}

