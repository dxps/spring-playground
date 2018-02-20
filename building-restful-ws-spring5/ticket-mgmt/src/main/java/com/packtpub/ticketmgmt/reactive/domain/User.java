package com.packtpub.ticketmgmt.reactive.domain;

/**
 * A simple user model.
 *
 * @author vision8
 */
public class User {
	
	private Integer userid;
	private String username;
	
	public User(Integer userid, String username) {
		this.userid = userid;
		this.username = username;
	}
	
	public Integer getUserid() {
		return userid;
	}
	
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
}
