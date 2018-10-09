package com.poc.userdemo.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.poc.userdemo.dao.UserDemoDAO;
import com.poc.userdemo.model.User;
import com.poc.userdemo.request.UserRequest;
import com.poc.userdemo.services.UserDemoServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class UserDemoServicesMockTest {

	@Mock
	UserDemoDAO userDAOMock;
	
	@InjectMocks
	UserDemoServiceImpl userDemoServiceImpl;
	
	@Test
	public void testGetUsersCount() {
		when(userDAOMock.getUsersCount()).thenReturn(10);
		assertEquals(10, userDemoServiceImpl.getUsersCount());
	}
	
	@Test
	public void testGetUsersCount_wrongValue() {
		when(userDAOMock.getUsersCount()).thenReturn(-1);
		assertNotEquals(10, userDemoServiceImpl.getUsersCount());
	}
	
	@Test
	public void testUpdateUserDetails_WithOverride() {
		int itemId = 4;
		UserRequest request = new UserRequest("Text from client Title","Text from client Body");
		Optional<UserRequest> optionalRequest = Optional.of(request);
		User user = new User();
		user.setId(itemId);
		user.setUserId(1);
		user.setTitle("Text from client Title");
		user.setBody("Text from client Body");
		when(userDAOMock.updateUserDetails(itemId, request)).thenReturn(user);
		User actUser = userDemoServiceImpl.updateUserDetails(itemId, optionalRequest);
		assertEquals("Text from client Title", actUser.getTitle());
		assertEquals("Text from client Body", actUser.getBody());
	}
	
	@Test
	public void testUpdateUserDetails_WithoutOverride() {
		int itemId = 4;
		UserRequest request = new UserRequest();
		Optional<UserRequest> optionalRequest = Optional.of(request);
		User user = new User();
		user.setId(itemId);
		user.setUserId(1);
		user.setTitle("1-800 Flower Title");
		user.setBody("1-800 Flower Body");
		when(userDAOMock.updateUserDetails(itemId, request)).thenReturn(user);
		User actUser = userDemoServiceImpl.updateUserDetails(itemId, optionalRequest);
		assertEquals("1-800 Flower Title", actUser.getTitle());
		assertEquals("1-800 Flower Body", actUser.getBody());
	}
	
	@Test
	public void testUpdateUserDetails_WrongInput() {
		int itemId = 0;
		UserRequest request = new UserRequest();
		Optional<UserRequest> optionalRequest = Optional.of(request);
		when(userDAOMock.updateUserDetails(itemId, request)).thenReturn(null);
		User actUser = userDemoServiceImpl.updateUserDetails(itemId, optionalRequest);
		assertEquals(null, actUser);
	}
	
}
