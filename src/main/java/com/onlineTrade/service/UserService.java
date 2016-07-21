package com.onlineTrade.service;

public interface UserService {
	//验证登录信息返回布尔值
	boolean login(String userName, String password);
}
