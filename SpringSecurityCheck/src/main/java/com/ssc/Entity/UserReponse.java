package com.ssc.Entity;

public class UserReponse {
	
	public String token;
	public String message;
	/**
	 * 
	 */
	public UserReponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param token
	 * @param message
	 */
	public UserReponse(String token, String message) {
		super();
		this.token = token;
		this.message = message;
	}
	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}
	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	} 

}
