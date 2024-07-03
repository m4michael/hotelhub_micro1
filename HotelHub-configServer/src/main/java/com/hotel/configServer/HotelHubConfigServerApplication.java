package com.hotel.configServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class HotelHubConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelHubConfigServerApplication.class, args);
	}

}
