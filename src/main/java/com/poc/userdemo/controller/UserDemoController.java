package com.poc.userdemo.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.poc.userdemo.model.User;
import com.poc.userdemo.request.UserRequest;
import com.poc.userdemo.response.UserResponse;
import com.poc.userdemo.services.UserDemoService;

@RestController
@RequestMapping("v1/users")
public class UserDemoController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	UserDemoService userService;

	@GetMapping(value = "/count", produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody UserResponse getUsersCount() {
	
		int userCount = userService.getUsersCount();
		LOGGER.info("Total unique users count : {}", userCount);
		
		UserResponse response = new UserResponse(userCount, null);
		LOGGER.info("Response : {}", response);
		return response;
	
	}
	
	@PutMapping(value = "/{itemId}", 
			consumes = { MediaType.APPLICATION_JSON_VALUE }, 
			produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody UserResponse updateItem(@PathVariable(value="itemId", required=true) int itemId, 
			@RequestBody Optional<UserRequest> userRequest) {
		
		UserResponse response = new UserResponse();;
		
		LOGGER.info("Item id : {}", itemId);
		
		User userDetails = userService.updateUserDetails(itemId, userRequest);
		LOGGER.debug("User details : {}", userDetails);
		
		if (userDetails != null) {
			response.setUserDetails(userDetails);
		} else {
			Map<String, String> errorMap = new HashMap<String, String>();
			errorMap.put("ERROR1", "User not available");
			response.setError(errorMap);
		}
		
		LOGGER.info("Response : {}", response);
		
		return response;
	}
	
}
