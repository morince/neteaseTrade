package com.onlineTrade.entity;
import org.springframework.stereotype.Component;

@Component
public class Product {

	/**
	 * 产品ID
	 */
	private int id;
	/**
	 * 产品名称
	 */
	private String title;
	/**
	 * 摘要，前台名称summary
	 */
	private String abStract;
	/**
	 * 详细内容
	 */
	private byte[] text;
	/**
	 * 图片地址
	 */
	private byte[] image;
	/**
	 * 产品的价格（根据表的不同可分为购买价格和当前产品价格）
	 */
	private int price;
	/**
	 * 13位时间戳
	 */
	private long time;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAbStract() {
		return abStract;
	}
	public void setAbStract(String abStract) {
		this.abStract = abStract;
	}
	public byte[] getText() {
		return text;
	}
	public void setText(byte[] text) {
		this.text = text;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	
}
