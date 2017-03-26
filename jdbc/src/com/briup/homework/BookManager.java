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
	
	//连接数据库的对象
	private Connection conn;
	//执行sql语句的对象
	private Statement stmt;
	PreparedStatement ps;
	//查询的结果集
	private ResultSet rs;
	
	public Book book = new Book();
	public List<Book> list = new ArrayList<Book>();
	//添加书籍
	public void addBook(Book book){
		try {
			//第一步 注册驱动
			Class.forName(driver);
			
			//第二步 获得连接数据库的对象
			conn = DriverManager.getConnection(url, username, password);
			
			//第三步 获得Statement对象 作用:执行sql语句
			stmt = conn.createStatement();
			
			//第四步 使用Statement对象执行sql语句
			String sql = "insert into book values("+book.getId()+",'"+book.getName()
										+"',"+book.getPrice()+","+book.getPublishDate()+")";
			//sql语句执行完自动提交事务
			stmt.execute(sql);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//第五步 关闭连接数据库的各种资源
			try {
				//先创建的对象最后关闭
				if(stmt!=null)stmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
			
	}
	//根据id删除书籍
	public void deleteBookById(long id){
		try {
			//第一步 注册驱动
			Class.forName(driver);
			
			//第二步 获得连接数据库的对象
			conn = DriverManager.getConnection(url, username, password);
			
			//第三步 获得Statement对象 作用:执行sql语句
			stmt = conn.createStatement();
			
			//第四步 使用Statement对象执行sql语句
			String sql = "delete from book where id="+id;
			stmt.execute(sql);
			//stmt.executeUpdate(sql);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//第五步 关闭连接数据库的各种资源
			try {
				//先创建的对象最后关闭
				if(stmt!=null)stmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}	
	}
	//保存或者更新书籍
	//如果book的id值在数据库中存在了,那么就更新
	//如果book的id值在数据库中不存在了,则插入新书籍
	public void saveOrupdateBook(Book book){
		try {
			//第一步 注册驱动
			Class.forName(driver);
			
			//第二步 获得连接数据库的对象
			conn = DriverManager.getConnection(url, username, password);
			
			//第三步 获得Statement对象 作用:执行sql语句
			stmt = conn.createStatement();
			
			//第四步 使用Statement对象执行sql语句
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
			//第五步 关闭连接数据库的各种资源
			try {
				//先创建的对象最后关闭
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
	}
	//根据id查找书籍
	public Book findBookById(long id){
		try {
			//第一步 注册驱动
			Class.forName(driver);
			
			//第二步 获得连接数据库的对象
			conn = DriverManager.getConnection(url, username, password);
			
			//第三步 获得Statement对象 作用:执行sql语句
			stmt = conn.createStatement();
			
			//第四步 使用Statement对象执行sql语句 如果是查询语句,需要接收结果集
			String sql = "select * from book where id="+id;
			rs = stmt.executeQuery(sql);
			//遍历结果集 拿到数据
			//rs.next()返回true或者false
			//true表示结果集中还有数据
			//false表示结果集中没有数据了
			while(rs.next()){
				//如果rs中还有数据 就可以拿到这一行数据中的每一列的值
				
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
			//第五步 关闭连接数据库的各种资源
			try {
				//先创建的对象最后关闭
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
		return book;
	}
	//查找所有书籍
	public List<Book> findAll(){
		try {
			//第一步 注册驱动
			Class.forName(driver);
			
			//第二步 获得连接数据库的对象
			conn = DriverManager.getConnection(url, username, password);
			
			//第三步 获得Statement对象 作用:执行sql语句
			stmt = conn.createStatement();
			
			//第四步 使用Statement对象执行sql语句 如果是查询语句,需要接收结果集
			String sql = "select * from book";
			rs = stmt.executeQuery(sql);
			//遍历结果集 拿到数据
			//rs.next()返回true或者false
			//true表示结果集中还有数据
			//false表示结果集中没有数据了
			while(rs.next()){
				//如果rs中还有数据 就可以拿到这一行数据中的每一列的值
				
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
			//第五步 关闭连接数据库的各种资源
			try {
				//先创建的对象最后关闭
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
		return list;
	}
		
	//这个方法在第二个作业中实现完成
	public void addListBook(List<Book> list){
		try {
			//第一步 注册驱动
			Class.forName(driver);
			
			//第二步 获得连接数据库的对象
			conn = DriverManager.getConnection(url, username, password);
			
			//第三步 获得Statement对象 作用:执行sql语句
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
				//第四步 使用Statement对象执行sql语句 如果是查询语句,需要接收结果集
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
			//第五步 关闭连接数据库的各种资源
			try {
				//先创建的对象最后关闭
				if(ps!=null)ps.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
