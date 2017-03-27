package com.augmentum.util;

import com.augmentum.excel.Exporter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

public class SQLServerReaderUtil {
	
    static Logger log = Logger.getLogger(SQLServerReaderUtil.class.getClass());  
	
	public final static String DRIVERNAME = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	public final static String URL = "jdbc:sqlserver://192.168.179.103:1433;DatabaseName=zipcode";
	public final static String USERNAME = "sa";
	public final static String PASSWORD = "abc123_";
	public final static String QUERY_STRING = "select distinct stateFIPS , countyFIPS, County from [zipcode].[dbo].[ZipCode2] " +
			"where State = 'NY'";

	public static Map<String, String> getCountyCodeMap() {
		Map<String, String> zipCodeMap = new HashMap<String, String>();
		Connection con = null;
		Statement stmt = null;
		try {
			Class.forName(DRIVERNAME);
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery(QUERY_STRING);
			int count = 0;
			String county = "";
			String countyId = "";
			while (rs.next()) {
				if (StringUtils.isNotBlank(rs.getString(2))) {
					count++;
					county = rs.getString(3).toLowerCase();
					countyId = StringUtils.normalizeSpace(rs.getString(1) + rs.getString(2));
					zipCodeMap.put(county, countyId);
				}
			}
			log.info("--------------------total count : " + count + "-------------");
			log.info("--------------------zipCodeMap : " + zipCodeMap + "-------------");
		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return zipCodeMap;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
			getCountyCodeMap();
	}

}
