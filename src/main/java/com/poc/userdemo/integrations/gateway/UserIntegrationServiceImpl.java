package com.poc.userdemo.integrations.gateway;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.poc.userdemo.model.User;

@Service
public class UserIntegrationServiceImpl implements UserIntegrationService{

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Value("${user.service.url}")
	private String userServiceURL;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Override
	@HystrixCommand(commandKey = "UserDataCommand", threadPoolKey = "UserDataThreadPool", fallbackMethod = "getAllUsersFallback")
	public List<User> getAllUsers() {
		
		LOGGER.debug("Service Integration URI : {}", userServiceURL);
		
		ResponseEntity<User[]> response = restTemplate.getForEntity(userServiceURL, User[].class);
		return Arrays.asList(response.getBody() != null? response.getBody(): new User[0]);
	}
	
	protected List<User> getAllUsersFallback(Throwable failure) {
		LOGGER.error("Failed to get the user data!! Exception :: ", failure);
		return new ArrayList<User>();
	}
	
}
