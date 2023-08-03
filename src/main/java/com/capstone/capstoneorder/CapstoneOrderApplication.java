package com.capstone.capstoneorder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class CapstoneOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(CapstoneOrderApplication.class, args);
	}

}
