package com.onlineTrade.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.onlineTrade.dao.ProductDao;
import com.onlineTrade.entity.Product;
import com.onlineTrade.entity.Trx;
import com.onlineTrade.service.ShowPage;

public class ShowDetailImpl implements ShowPage{
	@Resource
	private ProductDao pdao;

	
	public void showDetail() {
		List<Trx> trx = pdao.trxQuery();
		List<Product> list = pdao.showAllDetail();
		for(int i=0;i<trx.size();i++){
			for(Product ps:list){
				for(Trx t:trx){
					if(ps.getId() == t.getContentId()){
						ps.setBuy(true);
						ps.setSell(true);
						t.setBuy(true);
						t.setSell(true);
					}
				}
			}
		}
	}

	
	
	
}
