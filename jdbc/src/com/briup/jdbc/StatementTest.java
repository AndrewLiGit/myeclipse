package com.briup.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StatementTest {
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
	private String username = "test";
	private String password = "test";
	
	//连接数据库的对象
	private Connection conn;
	//执行sql语句的对象
	private Statement stmt;
	//查询的结果集
	private ResultSet rs;
	
	public static void main(String[] args) {
		
		StatementTest st = new StatementTest();
		//st.insert("zhangsan3",3000);
		//st.delete(5);
		//st.update(1,"lisi");
		st.select();
		
		//st.insert4();		
	}	
	/*
	create table test(
		id number primary key,
		name varchar2(100),
		salary number
	); 
	 */
	//插入操作  自动提交事务的方式
	public void insert(){
		try {
			//第一步 注册驱动
			Class.forName(driver);
			
			//第二步 获得连接数据库的对象
			conn = DriverManager.getConnection(url, username, password);
			
			//第三步 获得Statement对象 作用:执行sql语句
			stmt = conn.createStatement();
			
			//第四步 使用Statement对象执行sql语句
			String sql = "insert into test(id,name,salary) values(1,'tom',1000)";
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
	
	//插入操作  手动提交事务的方式
	public void insert2(){
			try {
				//第一步 注册驱动
				Class.forName(driver);
				
				//第二步 获得连接数据库的对象
				conn = DriverManager.getConnection(url, username, password);
				//设置自动提交为false就得手动提交    默认是true
				conn.setAutoCommit(false);
				
				//第三步 获得Statement对象 作用:执行sql语句
				stmt = conn.createStatement();
				
				//第四步 使用Statement对象执行sql语句
				String sql = "insert into test(id,name,salary) values(1,'tom',1000)";
				stmt.execute(sql);
				//手动提交事务
				conn.commit();
				
			} catch (Exception e) {
				e.printStackTrace();
				//如果代码执行的时候有异常,可以在这回滚事务
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
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

	//插入操作  sql语句中拼接参数
	public void insert(int id,String name,double salary){
		try {
			//第一步 注册驱动
			Class.forName(driver);
			
			//第二步 获得连接数据库的对象
			conn = DriverManager.getConnection(url, username, password);
			
			//第三步 获得Statement对象 作用:执行sql语句
			stmt = conn.createStatement();
			
			//第四步 使用Statement对象执行sql语句
			String sql = "insert into test(id,name,salary) values("+id+",'"+name+"',"+salary+")";
			System.out.println(sql);
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
	
	//插入操作  id值使用需要生产 
	//create sequence my_seq;
	public void insert(String name,double salary){
		try {
			//第一步 注册驱动
			Class.forName(driver);
			
			//第二步 获得连接数据库的对象
			conn = DriverManager.getConnection(url, username, password);
			
			//第三步 获得Statement对象 作用:执行sql语句
			stmt = conn.createStatement();
			
			//第四步 使用Statement对象执行sql语句
			String sql = "insert into test(id,name,salary) values(my_seq.nextval,'"+name+"',"+salary+")";
			System.out.println(sql);
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
	
	//删除操作
	public void delete(int id){
		
		try {
			//第一步 注册驱动
			Class.forName(driver);
			
			//第二步 获得连接数据库的对象
			conn = DriverManager.getConnection(url, username, password);
			
			//第三步 获得Statement对象 作用:执行sql语句
			stmt = conn.createStatement();
			
			//第四步 使用Statement对象执行sql语句
			String sql = "delete from test where id="+id;
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
	
	//更新操作
	public void update(int id,String newName){
		
		try {
			//第一步 注册驱动
			Class.forName(driver);
			
			//第二步 获得连接数据库的对象
			conn = DriverManager.getConnection(url, username, password);
			
			//第三步 获得Statement对象 作用:执行sql语句
			stmt = conn.createStatement();
			
			//第四步 使用Statement对象执行sql语句
			String sql = "update test set name='"+newName+"' where id="+id;
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
	
	//查询
	public void select(){
		
		try {
			//第一步 注册驱动
			Class.forName(driver);
			
			//第二步 获得连接数据库的对象
			conn = DriverManager.getConnection(url, username, password);
			
			//第三步 获得Statement对象 作用:执行sql语句
			stmt = conn.createStatement();
			
			//第四步 使用Statement对象执行sql语句 如果是查询语句,需要接收结果集
			String sql = "select * from test";
			rs = stmt.executeQuery(sql);
			//遍历结果集 拿到数据
			//rs.next()返回true或者false
			//true表示结果集中还有数据
			//false表示结果集中没有数据了
			while(rs.next()){
				//如果rs中还有数据 就可以拿到这一行数据中的每一列的值
				
				int id = rs.getInt("id");
				String name = rs.getString("name");
				double salary = rs.getDouble("salary");
				
				System.out.println(id+" "+name+" "+salary);				
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
	/*
	 * 新建一直表
	create table test2(
		id number primary key,
		name varchar2(100),
		birthday date
	); 
	 */
	
	//插入操作  插入日期
	public void insert4(){
		try {
			//第一步 注册驱动
			Class.forName(driver);
			
			//第二步 获得连接数据库的对象
			conn = DriverManager.getConnection(url, username, password);
			
			//第三步 获得Statement对象 作用:执行sql语句
			stmt = conn.createStatement();
			
			//第四步 使用Statement对象执行sql语句
			//这个sql是正确的
//			String sql = "insert into test2(id,name,birthday) values(1,'tom',sysdate)";
			//这个sql是错误的
//			String sql = "insert into test2(id,name,birthday) values(3,'tom','01-05-99')";
			String sql = "insert into test2(id,name,birthday) values(2,'tom',to_date('01-05-99','dd-mm-yy'))";
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
}
