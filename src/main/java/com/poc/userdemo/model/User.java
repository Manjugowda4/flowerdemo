package com.poc.userdemo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

	private int Id;
	
	private int UserId;
	
	private String Title;
	
	private String Body;
	
	public User() {}
	
	public User(int id, int userId, String title, String body) {
		super();
		this.Id = id;
		this.UserId = userId;
		this.Title = title;
		this.Body = body;
	}
	
	@Override
	public String toString() {
		return "User Details { Id:"+Id+", UserId:"+UserId+", \nTitle:"+Title+", \nBody:"+Body+" }";
	}
	
}
