package com.poc.userdemo.response;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.poc.userdemo.model.User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonInclude(Include.NON_NULL)
public class UserResponse {
	
	private int uniqueUsers;
	
	private User userDetails;
	
	private Map<String, String> error;
	
	public UserResponse() { }
	
	public UserResponse(int uniqueUsers, User userDetails) {
		this.uniqueUsers = uniqueUsers;
		this.userDetails = userDetails;
	}
}
