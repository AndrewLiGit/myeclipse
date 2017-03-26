package com.lzjtu.bookstore.model;

import java.util.Date;

public class Book {

	private int id;
	private String name;
	private String description;
	private int hot;
	private Date hotTime;
	private float price;
	private String picture;
	private int isSpecialPrice;
	private float specialPrice;
	private Date specialPriceTime;
	private int amount;
	private int selled;
	private int bigCategoryId;
	private int smallCategoryId;
	private String writer;
	private String publish;
	private int page;
	private Date createTime;
	private Date lastUpdateTime;
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public int getIsSpecialPrice() {
		return isSpecialPrice;
	}

	public void setIsSpecialPrice(int isSpecialPrice) {
		this.isSpecialPrice = isSpecialPrice;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getHot() {
		return hot;
	}
	
	public void setHot(int hot) {
		this.hot = hot;
	}
	
	public Date getHotTime() {
		return hotTime;
	}
	public void setHotTime(Date hotTime) {
		this.hotTime = hotTime;
	}
	
	public float getPrice() {
		return price;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}
	
	public String getPicture() {
		return picture;
	}
	
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	public float getSpecialPrice() {
		return specialPrice;
	}
	
	public void setSpecialPrice(float specialPrice) {
		this.specialPrice = specialPrice;
	}
	
	public Date getSpecialPriceTime() {
		return specialPriceTime;
	}
	
	public void setSpecialPriceTime(Date specialPriceTime) {
		this.specialPriceTime = specialPriceTime;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public int getSelled() {
		return selled;
	}
	
	public void setSelled(int selled) {
		this.selled = selled;
	}
	
	public int getBigCategoryId() {
		return bigCategoryId;
	}
	
	public void setBigCategoryId(int bigCategoryId) {
		this.bigCategoryId = bigCategoryId;
	}
	
	public int getSmallCategoryId() {
		return smallCategoryId;
	}
	
	public void setSmallCategoryId(int smallCategoryId) {
		this.smallCategoryId = smallCategoryId;
	}
	
	public String getWriter() {
		return writer;
	}
	
	public void setWriter(String writer) {
		this.writer = writer;
	}
	
	public String getPublish() {
		return publish;
	}
	
	public void setPublish(String publish) {
		this.publish = publish;
	}
	
	public int getPage() {
		return page;
	}
	
	public void setPage(int page) {
		this.page = page;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", description="
				+ description + ", hot=" + hot + ", hotTime=" + hotTime
				+ ", price=" + price + ", picture=" + picture
				+ ", isSpecialPrice=" + isSpecialPrice + ", specialPrice="
				+ specialPrice + ", specialPriceTime=" + specialPriceTime
				+ ", amount=" + amount + ", selled=" + selled
				+ ", bigCategoryId=" + bigCategoryId + ", smallCategoryId="
				+ smallCategoryId + ", writer=" + writer + ", publish="
				+ publish + ", page=" + page + ", createTime=" + createTime
				+ ", lastUpdateTime=" + lastUpdateTime + "]";
	}
	
	
}