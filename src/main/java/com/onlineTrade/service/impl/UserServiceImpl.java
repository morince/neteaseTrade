package com.onlineTrade.service.impl;


import org.springframework.beans.factory.annotation.Autowired;

import com.onlineTrade.dao.UserValidateDao;
import com.onlineTrade.entity.Person;
import com.onlineTrade.service.UserService;

public class UserServiceImpl implements UserService {
	
@Autowired
	private UserValidateDao uvDao;
	
	private String userName;
	
	private String password;

	public UserServiceImpl() {

	}

	public UserServiceImpl(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	public boolean login(String userName, String password) {

		Person user = uvDao.loginVerify("buyer","37254660e226ea65ce6f1efd54233424");

		if (user.getUserName() != null && user.getPassword() != null) {
			return true;
		}
		return false;
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
