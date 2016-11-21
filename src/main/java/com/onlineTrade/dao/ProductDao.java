package com.onlineTrade.dao;

import java.sql.Blob;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.onlineTrade.entity.Product;

/**
 * 在线商城dao层接口
 * 
 * @author kilido
 *
 */
public interface ProductDao {

	/**
	 * 获取首页商品展示
	 * 
	 * @return
	 */
	List<Product> productList();

	/**
	 * 通过商品Id查询该商品明细
	 * 
	 * @param id
	 * @return
	 */
	List<Product> showDetail(@Param("id") Integer id);

	/**
	 * 通过用户Id获取商品明细
	 * 
	 * @param personId
	 * @return
	 */
	List<Product> queryTrxByUserId(int personId);


	/**
	 * 通过商品Id查找商品信息
	 * 
	 * @param id
	 * @return
	 */
	Product productQueryById(Integer id);
	/**
	 * 新发布一件商品
	 * 
	 * @param title
	 * @param detail
	 * @param image
	 * @param price
	 * @param summary
	 * @return
	 */
	int publish(@Param("title") String title, @Param("text") Blob detail, @Param("icon") Blob image,
			@Param("price") long price, @Param("abStract") String summary);

	/**
	 * 编辑商品后发布
	 * 
	 * @param id
	 * @param title
	 * @param detail
	 * @param image
	 * @param price
	 * @param summary
	 * @return
	 */
	int rePublish(@Param("id") Integer id, @Param("title") String title, @Param("text") Blob detail,
			@Param("icon") Blob image, @Param("price") long price, @Param("abStract") String summary);

	/**
	 * 购买该商品，更新商品信息至交易表中
	 * 
	 * @param contentId
	 * @param psersonId
	 * @param price
	 * @param time
	 * @return
	 */
	int buy(@Param("contentId") int contentId, @Param("personId") int psersonId, @Param("price") int price,
			@Param("time") long time);

	/**
	 * 删除当前选中商品
	 * 
	 * @param contentId
	 * @return
	 */
	int delete(@Param("id") int contentId);



	/**
	 * 产品查询通过id倒叙取第一值
	 * 
	 * @return
	 */
	Integer productQueryByNEWID();
	List<Product> productQueryAll();

	/**
	 * 查询数据库中交易表中是否有该记录，有则返回具体条数，否则为0
	 * 
	 * @param contentId
	 * @return
	 */
	Integer queryTrx(int contentId);


}
