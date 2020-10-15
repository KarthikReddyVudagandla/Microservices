package com.example.RDService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
//@EnableEurekaClient
public class RdServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(RdServiceApplication.class, args);
	}

}
