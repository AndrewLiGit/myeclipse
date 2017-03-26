package com.briup.homework;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Test {
	public static void main(String[] args) {
		BookManager bManager = new BookManager();
//		Book book1 = new Book(1,"java",23,new Date());
//		Book book2 = new Book(2,"oracle",24,new Date());
//		Book book3 = new Book(3,"web",25,new Date());
		
		//bManager.addBook(book1);
		//bManager.addBook(book2);
		//bManager.deleteBookById(1);
		//bManager.saveOrupdateBook(book3);
		//System.out.println(bManager.findBookById(2));
		
//		Iterator<Book> iter = bManager.findAll().iterator();
//		while(iter.hasNext()){
//			System.out.println(iter.next());
//		}
		
		File file = new File("src/com/briup/homework/date.txt");
		List<Book> list = new ArrayList<Book>();
		BufferedReader bis =null;
		try {
			bis = new BufferedReader(new FileReader(file));
			String string = null;
			while((string=bis.readLine())!=null){
				String[] str = string.split(":");
				Book book = new Book();
				book.setId(Long.parseLong(str[0]));
				book.setName(str[1]);
				book.setPrice(Double.parseDouble(str[2]));
//				SimpleDateFormat s = new SimpleDateFormat("dd-MM-yy");
				String [] strings=str[3].split("-");
				int year=Integer.parseInt(strings[2]);
				int month=Integer.parseInt(strings[1]);
				int day=Integer.parseInt(strings[0]);
				java.sql.Date date=new java.sql.Date(year, month, day);
				//System.out.println(date);
				book.setPublishDate(date);
				
				list.add(book);
				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		try {
			if(bis!=null) bis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
//		Iterator<Book> iter = list.iterator();
//		while(iter.hasNext()){
//			System.out.println(iter.next());
//		}
		
		bManager.addListBook(list);
	}
}
