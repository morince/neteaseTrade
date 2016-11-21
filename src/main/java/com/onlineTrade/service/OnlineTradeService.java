package com.onlineTrade.service;

import com.onlineTrade.entity.Person;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 * 网络商城service层
 * 
 * @author kilido
 *
 */
public interface OnlineTradeService {
	/**
	 * 
	 * 展示页（首页）
	 * 
	 * @param user
	 *            判断用户信息，展示是否包含隐藏页面
	 * @return
	 * @throws JSONException
	 */
	JSONArray showIndex(Person user) throws JSONException;

	/**
	 * 通过商品id展示单件商品细节
	 * 
	 * @param id
	 * @return
	 * @throws JSONException
	 */
	JSONObject showProductDetail(Integer id) throws JSONException;

	/**
	 * 将发布的商品信息保存在数据库中
	 * 
	 * @param title
	 * @param detail
	 * @param image
	 * @param price
	 * @param summary
	 * @param url
	 * @return
	 */
	JSONObject publicSubmit(String title, String detail, String image, long price, String summary, String url);

	/**
	 * 编辑商品数据,更新到数据库中
	 * 
	 * @param id
	 * @return
	 */
	JSONObject editSubmit(Integer id, String title, String detail, String image, long price, String summary,
			String url);

	/**
	 * 删除本条数据,注解开启事务
	 * 
	 * @param id
	 * @return
	 */
	int delete(Integer id);

	/**
	 * 根据用户id查询交易记录表，返回jsonarray，遍历插入前台
	 * 
	 * @param userId
	 * @return
	 */
	JSONArray account(Integer userId);

	/**
	 * 用户通过商品id购买商品
	 * 
	 * @param contentId
	 * @param userId
	 */
	void buy(int contentId, int userId);

}
