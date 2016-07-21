package com.onlineTrade.entity;


public class Product {

	private int id;
	private String title;
	private String abStract;
	private String text;
	private String image;
	private long price;
	private long buyprice;
	private int buyNum;
	private int saleNum;
	private boolean isBuy;
	private boolean isSell;
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
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public long getBuyprice() {
		return buyprice;
	}
	public void setBuyprice(long buyprice) {
		this.buyprice = buyprice;
	}
	public int getBuyNum() {
		return buyNum;
	}
	public void setBuyNum(int buyNum) {
		this.buyNum = buyNum;
	}
	public int getSaleNum() {
		return saleNum;
	}
	public void setSaleNum(int saleNum) {
		this.saleNum = saleNum;
	}
	public boolean isBuy() {
		return isBuy;
	}
	public void setBuy(boolean isBuy) {
		this.isBuy = isBuy;
	}
	public boolean isSell() {
		return isSell;
	}
	public void setSell(boolean isSell) {
		this.isSell = isSell;
	}
	public Product(){}
	
	public Product(int id, String title, String abStract, String text, String image, long price, long buyprice,
			int buyNum, int saleNum, boolean isBuy, boolean isSell) {
		this.id = id;
		this.title = title;
		this.abStract = abStract;
		this.text = text;
		this.image = image;
		this.price = price;
		this.buyprice = buyprice;
		this.buyNum = buyNum;
		this.saleNum = saleNum;
		this.isBuy = isBuy;
		this.isSell = isSell;
	}
	public Product(int id, String title, String abStract, String text, String image, long price) {
		this.id = id;
		this.title = title;
		this.abStract = abStract;
		this.text = text;
		this.image = image;
		this.price = price;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", title=" + title + ", abStract=" + abStract + ", text=" + text + ", image="
				+ image + ", price=" + price + ", buyprice=" + buyprice + ", buyNum=" + buyNum + ", saleNum=" + saleNum
				+ ", isBuy=" + isBuy + ", isSell=" + isSell + "]";
	}




}
