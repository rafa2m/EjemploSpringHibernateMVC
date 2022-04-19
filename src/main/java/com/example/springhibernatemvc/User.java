package com.example.springhibernatemvc;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	private int id;
	
	private String userName;
	
	private String userMesagge;
	
	public User() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserMesagge() {
		return userMesagge;
	}

	public void setUserMesagge(String userMesagge) {
		this.userMesagge = userMesagge;
	}
	
	
}
