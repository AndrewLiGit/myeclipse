package com.briup.homework;

import java.util.List;

public interface IBookManager {
	//����鼮
	void addBook(Book book);
	//����idɾ���鼮
	void deleteBookById(long id);
	//������߸����鼮
	//���book��idֵ�����ݿ��д�����,��ô�͸���
	//���book��idֵ�����ݿ��в�������,��������鼮
	void saveOrupdateBook(Book book);
	//����id�����鼮
	Book findBookById(long id);
	//���������鼮
	List<Book> findAll();
	
	//��������ڵڶ�����ҵ��ʵ�����
	void addListBook(List<Book> list);
	
}
