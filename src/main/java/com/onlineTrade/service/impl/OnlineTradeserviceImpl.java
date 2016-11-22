package com.onlineTrade.service.impl;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.onlineTrade.dao.ProductDao;
import com.onlineTrade.entity.Person;
import com.onlineTrade.entity.Product;
import com.onlineTrade.service.OnlineTradeService;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 * 网络商城service层实现类
 * 
 * @author kilido
 *
 */
@Service
public class OnlineTradeserviceImpl implements OnlineTradeService {

	@Resource
	private ProductDao dao;

	private Blob bImage;

	private Blob bDetail;

	@Override
	public JSONArray showIndex(Person user) throws JSONException {

		JSONArray showProduct = new JSONArray();

		if (!StringUtils.isEmpty(user)) {

			for (Product p : dao.productList()) {
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("id", p.getId());
				int count = dao.queryTrx(p.getId());
				if (user.getUserType() == 0) {
					boolean isBuy = (count == 0 ? false : true);
					jsonObj.put("isBuy", isBuy);
				} else {
					boolean isSell = (count == 0 ? false : true);
					jsonObj.put("isSell", isSell);
				}
				jsonObj.put("price", p.getPrice());
				jsonObj.put("title", p.getTitle());
				jsonObj.put("image", new String(p.getImage()));
				showProduct.add(jsonObj);
			}

		} else {
			for (Product p : dao.productList()) {
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("id", p.getId());
				jsonObj.put("price", p.getPrice());
				jsonObj.put("title", p.getTitle());
				jsonObj.put("image", new String(p.getImage()));
				showProduct.add(jsonObj);
			}
		}

		return showProduct;
	}

	@Override
	public JSONObject showProductDetail(Integer id) throws JSONException {
		List<Product> pdetail = dao.showDetail(id);

		JSONObject productObj = new JSONObject();

		for (Product p : pdetail) {
			productObj.put("id", p.getId());
			productObj.put("price", p.getPrice());
			productObj.put("title", p.getTitle());
			productObj.put("image", new String(p.getImage()));
			productObj.put("summary", p.getAbStract());
			productObj.put("detail", new String(p.getText()));

		}
		return productObj;
	}

	@Override
	public JSONObject publicSubmit(String title, String detail, String image, long price, String summary, String url) {

		try {
			// 抓取前台链接，若有URL则保存，否则保存本地已上传的图片路径
			// 代码涉及的所有图片均保存在服务器中，清除后图源不可见
			if (url != null) {
				// 将前台的String类型转换为后台的Blob类型
				bImage = new SerialBlob(url.getBytes());
			} else {
				bImage = new SerialBlob(image.getBytes());
			}
			bDetail = new SerialBlob(detail.getBytes());
		} catch (SerialException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		int i = dao.publish(title, bDetail, bImage, price, summary);
		if (i == 1) {
			return showProductDetail(dao.productQueryByNEWID());
		}
		return null;
	}

	public JSONObject editSubmit(Integer id, String title, String detail, String image, long price, String summary,
			String url) {

		try {
			// 抓取前台链接，若有URL则保存，否则保存本地已上传的图片路径
			// 代码涉及的所有图片均保存在服务器中，清除后图源不可见
			if (url != null) {
				// 将前台的String类型转换为后台的Blob类型
				bImage = new SerialBlob(url.getBytes());
			} else {
				bImage = new SerialBlob(image.getBytes());
			}
			bDetail = new SerialBlob(detail.getBytes());
		} catch (SerialException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		int i = dao.rePublish(id, title, bDetail, bImage, price, summary);
		if (i == 1) {
			return showProductDetail(id);
		}
		return null;
	}

	@Override
	@Transactional(value= "txManager" ,propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int delete(Integer id) {
		return dao.delete(id);
	}

	@Override
	public JSONArray account(Integer userId) {

		List<Product> m = dao.queryTrxByUserId(userId);
		JSONArray productsArray = new JSONArray();
		for (Product p : m) {
			JSONObject productObj = new JSONObject();
			productObj.put("id", p.getId());
			productObj.put("buyTime", p.getTime());
			productObj.put("buyPrice", p.getPrice());
			productObj.put("title", p.getTitle());
			productObj.put("image", new String(p.getImage()));
			productsArray.add(productObj);

		}
		return productsArray;
	}

	@Override
	public void buy(int contentId, int userId) {
		long time = System.currentTimeMillis();
		Product product = dao.productQueryById(contentId);
		int buyPrice = product.getPrice();
		dao.buy(contentId, userId, buyPrice, time);

	}

}
