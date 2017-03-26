package com.briup.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import com.briup.jdbc.JDBCUtil;

public class StmtAndPsTest {
	
	private Statement stmt;
	private Connection conn;
	private PreparedStatement ps;
	
	public void stmtInsert(){
		
		try {
			conn = JDBCUtil.getConneciton();
			stmt = conn.createStatement();
			
			String sql = "";
			
			for(int i=1;i<=300;i++){
				
				sql = "insert into test(id,name,salary) values("+i+",'tom',1000)";
				
				stmt.execute(sql);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			try {
				JDBCUtil.close(stmt, conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public void psInsert(){
		
		try {
			conn = JDBCUtil.getConneciton();
			String sql = "insert into test(id,name,salary) values(?,'tom',1000)";
			ps = conn.prepareStatement(sql);
			
			for(int i=1;i<=300;i++){
				ps.setLong(1, i);
				ps.execute();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			try {
				JDBCUtil.close(ps, conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public void psInsertBatch(){
		
		try {
			conn = JDBCUtil.getConneciton();
			String sql = "insert into test(id,name,salary) values(?,'tom',1000)";
			ps = conn.prepareStatement(sql);
			
			for(int i=1;i<=300;i++){
				ps.setLong(1, i);
				ps.addBatch();
				if(i%100==0){
					ps.executeBatch();
				}
			}
			ps.executeBatch();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			try {
				JDBCUtil.close(ps, conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	public static void main(String[] args) {
		
		StmtAndPsTest t = new StmtAndPsTest();
		//stmt:573 543
		//ps: 519 475
		//batch 300�� 10������:357 302
		//batch 300�� 20������:333 261
		//batch 300�� 5������:386 412
		//batch 300�� 50������:302 260
		//batch 300�� 300������:252
		//batch 300�� 100������:249 254
		long start = System.currentTimeMillis();
//		t.stmtInsert();
//		t.psInsert();
		t.psInsertBatch();
		long end = System.currentTimeMillis();
		System.out.println("��ʱ"+(end-start)+"����");
		
	}
	
	
	
}
