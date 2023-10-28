package com.ssc.Entity;

public class UserRequest {
	
	public String username;
	
	public String password;

	
	
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		
	//	System.out.println("kkkk");
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @param username
	 * @param password
	 */
	public UserRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	/**
	 * 
	 */
	public UserRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

}
