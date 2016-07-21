package com.onlineTrade.entity;

public class Trx {
	private int id;
	private int contentId;
	private int personId;
	private int price;
	private long time;
	private int number;
	private boolean isBuy;
	private boolean isSell;
	
	public Trx(){
		
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



	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getContentId() {
		return contentId;
	}

	public void setContentId(int contentId) {
		this.contentId = contentId;
	}

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
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



	public Trx(int id, int contentId, int personId, int price, long time, int number, boolean isBuy, boolean isSell) {
		this.id = id;
		this.contentId = contentId;
		this.personId = personId;
		this.price = price;
		this.time = time;
		this.number = number;
		this.isBuy = isBuy;
		this.isSell = isSell;
	}
	public Trx(int id, int contentId, int personId, int price, long time, int number) {
		this.id = id;
		this.contentId = contentId;
		this.personId = personId;
		this.price = price;
		this.time = time;
		this.number = number;
	}
	@Override
	public String toString() {
		return "Trx [id=" + id + ", contentId=" + contentId + ", personId=" + personId + ", price=" + price + ", time="
				+ time + ", number=" + number + ", isBuy=" + isBuy + ", isSell=" + isSell + "]";
	}


}

