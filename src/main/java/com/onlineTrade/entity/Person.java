package com.onlineTrade.entity;

import org.springframework.stereotype.Component;

@Component
public class Person {
	private int id;
	private String userName;
	private String password;
	private String nickName;
	private int usertype;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public int getUserType() {
		return usertype;
	}

	public void setUserType(int userType) {
		this.usertype = userType;
	}

	public Person(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	public Person(String userName, String nickName, int userType) {
		super();
		this.userName = userName;
		this.nickName = nickName;
		this.usertype = userType;
	}

	public boolean isEmpty() {
		if(this.userName !=null&& this.password!=null){
			return true;
		}
		return false;
	}
	
	public  Person() {

	}
	@Override
	public String toString() {
		return "Person [userName=" + userName + ", nickName=" + nickName
				+ ", userType=" + usertype + "]";
	}

}
