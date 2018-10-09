package com.poc.userdemo.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserRequest {
	
	private String title;
	
	private String body;
	
	public UserRequest() {}
	
	public UserRequest(String pTitle, String pBody) {
		this.title = pTitle;
		this.body = pBody;
	}

}
