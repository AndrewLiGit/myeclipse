package com.lzjtu.bookstore.model;

public class BookOrder {

	private int id;
	private int number;
	private int orderId;
	private Book book;
	private int bookId;
	
	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getNumber() {
		return number;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	
	public int getOrderId() {
		return orderId;
	}
	
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	@Override
	public String toString() {
		return "BookOrder [id=" + id + ", number=" + number + ", orderId="
				+ orderId + ", book=" + book + ", bookId=" + bookId + "]";
	}
	
	
}
