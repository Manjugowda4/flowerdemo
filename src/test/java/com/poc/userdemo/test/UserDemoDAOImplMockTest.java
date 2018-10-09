package com.poc.userdemo.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.poc.userdemo.dao.UserDemoDAOCache;
import com.poc.userdemo.dao.UserDemoDAOImpl;
import com.poc.userdemo.model.User;
import com.poc.userdemo.request.UserRequest;

@RunWith(MockitoJUnitRunner.class)
public class UserDemoDAOImplMockTest {
	
	@Mock
	UserDemoDAOCache userDemoDAOCacheMock;
	
	@InjectMocks
	UserDemoDAOImpl userDemoDAOImpl;
	
	@Test
	public void testGetUserCount() {
		List<User> users = prepareTestData();
		when(userDemoDAOCacheMock.getAllUsers()).thenReturn(users);
		assertEquals(4,userDemoDAOImpl.getUsersCount());
	}
	
	@Test
	public void testGetUserCount_Empty() {
		List<User> users = new ArrayList<User>();
		when(userDemoDAOCacheMock.getAllUsers()).thenReturn(users);
		assertEquals(0,userDemoDAOImpl.getUsersCount());
	}
	
	@Test
	public void testGetUserCount_Null() {
		List<User> users = null;
		when(userDemoDAOCacheMock.getAllUsers()).thenReturn(users);
		assertEquals(0,userDemoDAOImpl.getUsersCount());
	}
	
	@Test
	public void testUpdateUserDetails() {
		List<User> users = prepareTestData();
		int itemId = 4;
		UserRequest request = new UserRequest("1-800 Flower Title","1-800 Flower Body");
		when(userDemoDAOCacheMock.getAllUsers()).thenReturn(users);
		User actUser = userDemoDAOImpl.updateUserDetails(itemId, request);
		assertEquals("1-800 Flower Title", actUser.getTitle());
		assertEquals("1-800 Flower Body", actUser.getBody());
	}
	
	@Test
	public void testUpdateUserDetails_Empty() {
		List<User> users = new ArrayList<User>();
		int itemId = 4;
		UserRequest request = new UserRequest("1-800 Flower Title","1-800 Flower Body");
		when(userDemoDAOCacheMock.getAllUsers()).thenReturn(users);
		User actUser = userDemoDAOImpl.updateUserDetails(itemId, request);
		assertEquals(null, actUser);
		assertEquals(null, actUser);
	}
	
	@Test
	public void testUpdateUserDetails_Null() {
		List<User> users = null;
		int itemId = 4;
		UserRequest request = new UserRequest("1-800 Flower Title","1-800 Flower Body");
		when(userDemoDAOCacheMock.getAllUsers()).thenReturn(users);
		User actUser = userDemoDAOImpl.updateUserDetails(itemId, request);
		assertEquals(null, actUser);
		assertEquals(null, actUser);
	}
	
	public List<User> prepareTestData() {
		List<User> users = new ArrayList<User>();
		users.add(new User(1, 20, "Title1", "Body1"));
		users.add(new User(2, 20, "Title2", "Body2"));
		users.add(new User(3, 20, "Title3", "Body3"));
		users.add(new User(4, 30, "Title4", "Body4"));
		users.add(new User(5, 30, "Title5", "Body5"));
		users.add(new User(6, 30, "Title6", "Body6"));
		users.add(new User(7, 40, "Title7", "Body7"));
		users.add(new User(8, 40, "Title8", "Body8"));
		users.add(new User(9, 40, "Title9", "Body9"));
		users.add(new User(10, 50, "Title10", "Body10"));
		users.add(new User(11, 50, "Title11", "Body11"));
		users.add(new User(12, 50, "Title12", "Body12"));
		return users;
	}

}
