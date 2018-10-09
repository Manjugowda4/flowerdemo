package com.poc.userdemo.dao;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.poc.userdemo.model.User;
import com.poc.userdemo.request.UserRequest;

@Repository
public class UserDemoDAOImpl implements UserDemoDAO{
	
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	UserDemoDAOCache userDaoCache;

	@Override
	public int getUsersCount() {
		
		List<User> users = userDaoCache.getAllUsers();
		
		if (CollectionUtils.isEmpty(users)) {
			return 0;
		}
		
		LOGGER.debug("Total Users : {}", users.size());
		
		Map userMap = users.stream().collect(Collectors.groupingBy(User::getUserId));
		LOGGER.debug("All Users details : {}",userMap.values());
		
		int uniqueUsers = userMap.size();
		LOGGER.debug("Unique Users Count : {}", uniqueUsers);
		
		return uniqueUsers;
	}

	@Override
	public User updateUserDetails(int itemId, UserRequest userRequest) {
		User updateUser = null;
		
		List<User> users = userDaoCache.getAllUsers();
		
		if (CollectionUtils.isEmpty(users)) {
			return updateUser;
		}
		
		LOGGER.debug("Total Users : {}", users.size());
		
		updateUser = users.stream().filter(user -> user.getId() == itemId).findAny().orElse(null);
		
		if (updateUser != null) {
			updateUser.setTitle(userRequest.getTitle());
			updateUser.setBody(userRequest.getBody());
			LOGGER.debug("Updated Users details : {}",updateUser);
		}
		
		// Update here to DB if required
		// Just returning without updating anything here
		
		return updateUser;
	}
	
	

}
