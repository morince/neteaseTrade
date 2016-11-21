package com.onlineTrade.service;

import com.onlineTrade.entity.Person;

/**
 * 用户登录验证
 * 
 * @author kilido
 *
 */
public interface UserService {
	/**
	 * 验证登录信息返回布尔值
	 * @param userName
	 * @param password
	 * @return
	 */
	boolean login(String userName, String password);
	
	
	/**
	 * 获取当前登录用户的详细信息，将信息保存在session中
	 * @param userName
	 * @return
	 */
	Person getUserInfo(String userName);
}
