package com.iot.model;

/**
 * 登录用户实体
 * @author Z
 *
 * 创建于：2018年6月6日-上午9:30:31
 */
public class LoginUser {
	private String userName;
	private String password;
	
	
	
	public LoginUser() {
	}
	public LoginUser(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
