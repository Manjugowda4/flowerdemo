package com.poc.userdemo.dao;

import com.poc.userdemo.model.User;
import com.poc.userdemo.request.UserRequest;

public interface UserDemoDAO {

	public int getUsersCount();

	public User updateUserDetails(int itemId, UserRequest userRequest);

}
