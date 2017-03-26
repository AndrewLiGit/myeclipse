package com.briup.homework;

import java.util.List;

public interface IBookManager {
	//添加书籍
	void addBook(Book book);
	//根据id删除书籍
	void deleteBookById(long id);
	//保存或者更新书籍
	//如果book的id值在数据库中存在了,那么就更新
	//如果book的id值在数据库中不存在了,则插入新书籍
	void saveOrupdateBook(Book book);
	//根据id查找书籍
	Book findBookById(long id);
	//查找所有书籍
	List<Book> findAll();
	
	//这个方法在第二个作业中实现完成
	void addListBook(List<Book> list);
	
}
