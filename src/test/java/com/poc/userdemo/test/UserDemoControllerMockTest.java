package com.poc.userdemo.test;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.userdemo.UserDemoApplication;
import com.poc.userdemo.request.UserRequest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = UserDemoApplication.class)
@SpringBootTest
public class UserDemoControllerMockTest {

	private MockMvc mockMvc;
	
	@Autowired
    private WebApplicationContext webContext;
	
	@Before
	public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();
	}
	
	@Test
	public void testUsersCount() throws Exception {
	
		mockMvc.perform(get("/v1/users/count").accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$.uniqueUsers", is(10)));
	}
	
	@Test
	public void testUpdateUserDetails_default() throws Exception {

		mockMvc.perform(put("/v1/users/{itemId}", 4).
				contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$.userDetails.id", is(4)))
			.andExpect(jsonPath("$.userDetails.title", is("1-800 Flower Title")))
			.andExpect(jsonPath("$.userDetails.body", is("1-800 Flower Body")));
	}
	
	@Test
	public void testUpdateUserDetails() throws Exception {
		UserRequest userRequest = new UserRequest("Client set Title","Client set Body");
		ObjectMapper mapper = new ObjectMapper();
	    String json = mapper.writeValueAsString(userRequest);
		
		mockMvc.perform(put("/v1/users/{itemId}", 4).content(json)
				.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$.userDetails.id", is(4)))
			.andExpect(jsonPath("$.userDetails.title", is("Client set Title")))
			.andExpect(jsonPath("$.userDetails.body", is("Client set Body")))
			.andExpect(jsonPath("$.error").doesNotExist());
	}
	
	@Test
	public void testUpdateUserDetails_wronginput() throws Exception {
		UserRequest userRequest = new UserRequest("Client set Title","Client set Body");
		ObjectMapper mapper = new ObjectMapper();
	    String json = mapper.writeValueAsString(userRequest);
		
		mockMvc.perform(put("/v1/users/{itemId}", 0).content(json)
				.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$.error").exists())
			.andExpect(jsonPath("$.error.ERROR1", is("User not available")));
	}
}
