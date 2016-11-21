package com.onlineTrade.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlineTrade.dao.UserValidateDao;
import com.onlineTrade.entity.Person;
import com.onlineTrade.service.UserService;

/**
 * 用户认证服务层实现类
 * 
 * @author kilid
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserValidateDao uvDao;

	public boolean login(String userName, String password) {

		Person user = uvDao.loginVerify(userName, password);

		if (user.getUserName() != null && user.getPassword() != null) {
			return true;
		}
		return false;
	}

	@Override
	public Person getUserInfo(String userName) {
		return uvDao.getUserInfo(userName);
	}

}
