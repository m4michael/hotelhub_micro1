package com.SECURITY;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.SECURITY.CONFIG.JwtConfig;

@SpringBootApplication
@EnableConfigurationProperties(JwtConfig.class)
@EnableDiscoveryClient
public class HotelHubSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelHubSecurityApplication.class, args);
	}

}
