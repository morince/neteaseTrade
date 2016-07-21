package com.onlineTrade.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.onlineTrade.entity.Product;
import com.onlineTrade.entity.Trx;

public interface ProductDao {

	boolean publish(@Param("title") String title, @Param("text") String detail, @Param("icon") String image,
			@Param("price") long price, @Param("abStract") String summary);

	boolean buy(@Param("contentId") int contentId, @Param("personId") int psersonId, @Param("number") int number,
			@Param("price") int price, @Param("time") long time);

	boolean delete(@Param("id") int contentId);

	boolean setbuyer(int contentId);

	boolean setSeller(int contentId);

	// 通过商品Id查找商品信息
	Product productQueryById(Integer contentId);

	Product productQueryByTitle(String title);

	List<Product> productQueryAll();

	List<Trx> trxQuery();

	// 通过商品Id查询该商品明细
	List<Product> showDetail(@Param("id") Integer id);

	// 查询所有商品明细
	List<Product> showAllDetail();

	// 通过用户Id获取购物车商品明细
	List<Product> queryTrxByUserId(int personId);

	// 获取首页商品展示
	List<Product> productList();

	String upload();

}
