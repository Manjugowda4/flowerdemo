package com.poc.userdemo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.poc.userdemo.integrations.gateway.UserIntegrationService;
import com.poc.userdemo.model.User;

@Service
public class UserDemoDAOCache {
	
	@Autowired
	UserIntegrationService userIntegrationService;

	@Cacheable("users")
	public List<User> getAllUsers() {
		return userIntegrationService.getAllUsers();
	}
	
}
