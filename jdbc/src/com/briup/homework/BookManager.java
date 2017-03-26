package com.briup.homework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.text.DefaultEditorKit.InsertBreakAction;

public class BookManager implements IBookManager{
	
	private String driver="oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
	private String username = "test";
	private String password = "test";
	
	//�������ݿ�Ķ���
	private Connection conn;
	//ִ��sql���Ķ���
	private Statement stmt;
	PreparedStatement ps;
	//��ѯ�Ľ����
	private ResultSet rs;
	
	public Book book = new Book();
	public List<Book> list = new ArrayList<Book>();
	//����鼮
	public void addBook(Book book){
		try {
			//��һ�� ע������
			Class.forName(driver);
			
			//�ڶ��� ����������ݿ�Ķ���
			conn = DriverManager.getConnection(url, username, password);
			
			//������ ���Statement���� ����:ִ��sql���
			stmt = conn.createStatement();
			
			//���Ĳ� ʹ��Statement����ִ��sql���
			String sql = "insert into book values("+book.getId()+",'"+book.getName()
										+"',"+book.getPrice()+","+book.getPublishDate()+")";
			//sql���ִ�����Զ��ύ����
			stmt.execute(sql);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//���岽 �ر��������ݿ�ĸ�����Դ
			try {
				//�ȴ����Ķ������ر�
				if(stmt!=null)stmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
			
	}
	//����idɾ���鼮
	public void deleteBookById(long id){
		try {
			//��һ�� ע������
			Class.forName(driver);
			
			//�ڶ��� ����������ݿ�Ķ���
			conn = DriverManager.getConnection(url, username, password);
			
			//������ ���Statement���� ����:ִ��sql���
			stmt = conn.createStatement();
			
			//���Ĳ� ʹ��Statement����ִ��sql���
			String sql = "delete from book where id="+id;
			stmt.execute(sql);
			//stmt.executeUpdate(sql);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//���岽 �ر��������ݿ�ĸ�����Դ
			try {
				//�ȴ����Ķ������ر�
				if(stmt!=null)stmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}	
	}
	//������߸����鼮
	//���book��idֵ�����ݿ��д�����,��ô�͸���
	//���book��idֵ�����ݿ��в�������,��������鼮
	public void saveOrupdateBook(Book book){
		try {
			//��һ�� ע������
			Class.forName(driver);
			
			//�ڶ��� ����������ݿ�Ķ���
			conn = DriverManager.getConnection(url, username, password);
			
			//������ ���Statement���� ����:ִ��sql���
			stmt = conn.createStatement();
			
			//���Ĳ� ʹ��Statement����ִ��sql���
			String sql = "select id,name from book where id="+book.getId();
			//System.out.println(sql);
			rs = stmt.executeQuery(sql);
			if(rs.next()){
				String sqlString1 = "update book set name='"+book.getName()+"',price="
								+book.getPrice()+",publishDate="+book.getPublishDate()+" where id="+book.getId();
				//System.out.println(sqlString1);
				stmt.execute(sqlString1);
			}
			else{
				String sqlString2 = "insert into book values("+book.getId()+",'"+book.getName()
												+"',"+book.getPrice()+","+book.getPublishDate()+")";
				//System.out.println(sqlString2);
				stmt.execute(sqlString2);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//���岽 �ر��������ݿ�ĸ�����Դ
			try {
				//�ȴ����Ķ������ر�
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
	}
	//����id�����鼮
	public Book findBookById(long id){
		try {
			//��һ�� ע������
			Class.forName(driver);
			
			//�ڶ��� ����������ݿ�Ķ���
			conn = DriverManager.getConnection(url, username, password);
			
			//������ ���Statement���� ����:ִ��sql���
			stmt = conn.createStatement();
			
			//���Ĳ� ʹ��Statement����ִ��sql��� ����ǲ�ѯ���,��Ҫ���ս����
			String sql = "select * from book where id="+id;
			rs = stmt.executeQuery(sql);
			//��������� �õ�����
			//rs.next()����true����false
			//true��ʾ������л�������
			//false��ʾ�������û��������
			while(rs.next()){
				//���rs�л������� �Ϳ����õ���һ�������е�ÿһ�е�ֵ
				
				long id1 = rs.getLong("id");
				String name = rs.getString("name");
				double price = rs.getDouble("price");
				Date publishDate = rs.getDate("publishDate");
				
				book.setId(id1);
				book.setName(name);
				book.setPrice(price);
				//book.setPublishDate(publishDate);
				
				//System.out.println(id1+" "+name+" "+price+" "+publishDate);				
			}						
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//���岽 �ر��������ݿ�ĸ�����Դ
			try {
				//�ȴ����Ķ������ر�
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
		return book;
	}
	//���������鼮
	public List<Book> findAll(){
		try {
			//��һ�� ע������
			Class.forName(driver);
			
			//�ڶ��� ����������ݿ�Ķ���
			conn = DriverManager.getConnection(url, username, password);
			
			//������ ���Statement���� ����:ִ��sql���
			stmt = conn.createStatement();
			
			//���Ĳ� ʹ��Statement����ִ��sql��� ����ǲ�ѯ���,��Ҫ���ս����
			String sql = "select * from book";
			rs = stmt.executeQuery(sql);
			//��������� �õ�����
			//rs.next()����true����false
			//true��ʾ������л�������
			//false��ʾ�������û��������
			while(rs.next()){
				//���rs�л������� �Ϳ����õ���һ�������е�ÿһ�е�ֵ
				
				int id1 = rs.getInt("id");
				String name = rs.getString("name");
				double price = rs.getDouble("price");
				Date publishDate = rs.getDate("publishDate");
				
				Book book = new Book();
				
				book.setId(id1);
				book.setName(name);
				book.setPrice(price);
				//book.setPublishDate(publishDate);
//				System.out.println(book);
//				System.out.println("-------");
				list.add(book);
			}						
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//���岽 �ر��������ݿ�ĸ�����Դ
			try {
				//�ȴ����Ķ������ر�
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
		return list;
	}
		
	//��������ڵڶ�����ҵ��ʵ�����
	public void addListBook(List<Book> list){
		try {
			//��һ�� ע������
			Class.forName(driver);
			
			//�ڶ��� ����������ݿ�Ķ���
			conn = DriverManager.getConnection(url, username, password);
			
			//������ ���Statement���� ����:ִ��sql���
			//stmt = conn.createStatement();
			 ps=conn.prepareStatement("insert into book values(?,?,?,?)");
			 
			Iterator<Book> iter = list.iterator();
			
//			for(int i=0;i<list.size();i++){
//				Book book = list.get(i);
//				ps.setLong(1,  book.getId());
//				ps.setString(2, book.getName());
//				ps.setDouble(3, book.getPrice());
//				ps.setDate(4,book.getPublishDate());
//				//System.out.println(book.getId()+" "+book.getName()+" "+book.getPrice()+" "+book.getPublishDate());
//				ps.execute();
//			}
			
			while(iter.hasNext()){
				Book book=iter.next();
				//���Ĳ� ʹ��Statement����ִ��sql��� ����ǲ�ѯ���,��Ҫ���ս����
				ps.setLong(1,  book.getId());
				ps.setString(2, book.getName());
				ps.setDouble(3, book.getPrice());
				ps.setDate(4,book.getPublishDate());
				boolean d=ps.execute();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally{
			//���岽 �ر��������ݿ�ĸ�����Դ
			try {
				//�ȴ����Ķ������ر�
				if(ps!=null)ps.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
