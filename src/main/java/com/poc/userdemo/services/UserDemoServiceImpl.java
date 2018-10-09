package com.poc.userdemo.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poc.userdemo.dao.UserDemoDAO;
import com.poc.userdemo.model.User;
import com.poc.userdemo.request.UserRequest;

@Service(value="userService")
public class UserDemoServiceImpl implements UserDemoService{
	
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	private static final String DEFAULT_TITLE = "1-800 Flower Title";
	
	private static final String DEFAULT_BODY = "1-800 Flower Body";
	
	@Autowired
	UserDemoDAO userDAO;
	
	@Override
	public int getUsersCount() {
		return userDAO.getUsersCount();
	}

	@Override
	public User updateUserDetails(int itemId, Optional<UserRequest> request) {

		UserRequest userReq = request.orElse(new UserRequest(DEFAULT_TITLE, DEFAULT_BODY));
		LOGGER.debug("User Request : {}", userReq);
		
		User userDetails = userDAO.updateUserDetails(itemId, userReq);
		LOGGER.debug("Updated User Details : {}", userDetails);
		
		return userDetails;
	}

}
