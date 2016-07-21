package com.onlineTrade.dao;



import org.apache.ibatis.annotations.Param;

import com.onlineTrade.entity.Person;

public interface UserValidateDao {
	//验证用户
	Person loginVerify(@Param("userName")String userName,@Param("password")String password);
	//获取用户信息
	Person getUserInfo(@Param("userName")String userName);
	
}
