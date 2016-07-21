package com.onlineTrade.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.onlineTrade.dao.ProductDao;
import com.onlineTrade.service.TradeService;
@Service
public class TradeServiceImpl implements TradeService{
	@Resource
	private ProductDao pdao;

	public void buy(int contentId, int number) {
		Date date = new Date();		
		int buyPrice = pdao.productQueryById(contentId).getBuyNum();
		pdao.buy(contentId,0, buyPrice,number,date.getTime());
		
	}

	
}
