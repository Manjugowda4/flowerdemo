package com.poc.userdemo.services;

import java.util.Optional;

import com.poc.userdemo.model.User;
import com.poc.userdemo.request.UserRequest;

public interface UserDemoService {

	public int getUsersCount();

	public User updateUserDetails(int itemId, Optional<UserRequest> request);

}
