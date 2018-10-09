package com.poc.userdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.ConfigurableApplicationContext;

import com.poc.userdemo.services.UserDemoService;
import com.poc.userdemo.services.UserDemoServiceImpl;

@EnableCircuitBreaker
@EnableCaching
@SpringBootApplication
public class UserDemoApplication {
	
	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(UserDemoApplication.class, args);
		UserDemoService userService = context.getBean("userService", UserDemoServiceImpl.class);
		int totalUsers = userService.getUsersCount();
		
		System.out.println("Total no. of unique Users :: "+ totalUsers);
		
	}
}
