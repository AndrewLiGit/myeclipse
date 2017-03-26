package com.lzjtu.bookstore.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.lzjtu.bookstore.exception.DBException;

public class DBUtil {

	public static Connection getConnection() {

        try {
            Class.forName(PropertyUtil.getProperty("jdbc.driver"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //get connection object
        String jdcUrl = PropertyUtil.getProperty("jdbc.url");
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(jdcUrl, PropertyUtil.getProperty("jdbc.user"), PropertyUtil.getProperty("jdbc.password"));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException();
        }
        return conn;
    }

    public static void close(ResultSet rs, PreparedStatement stmt, Connection conn) {

        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
         } catch (SQLException e) {
                 e.printStackTrace();
         }
    }
}
