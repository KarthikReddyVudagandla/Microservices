package com.example.MIService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
//@EnableEurekaClient
public class MiServiceApplication {

	public static void main(String[] args) {
		System.out.println("Movie INfo Service!!");
		SpringApplication.run(MiServiceApplication.class, args);
	}

}
