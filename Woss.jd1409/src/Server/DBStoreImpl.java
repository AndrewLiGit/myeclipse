package Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Calendar;
import java.util.Collection;
import java.util.Properties;

import com.briup.util.BIDR;
import com.briup.woss.server.DBStore;

public class DBStoreImpl implements DBStore{

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	private String username = "test";
	private String password = "test";
	Connection conn;
	PreparedStatement pstmt;
	
	@Override
	public void init(Properties pro) {
		this.driver = pro.getProperty("driver");
		this.url = pro.getProperty("url");
		this.username = pro.getProperty("username");
		this.password = pro.getProperty("password");
	}

	@Override
	public void saveToDB(Collection<BIDR> coll) throws Exception {
		// TODO Auto-generated method stub
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
			String sql = "insert into t_detail_"+getDay()+" values(?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			Long index = 0l;
			for(BIDR bidr:coll){
				index++;
				pstmt.setString(1, bidr.getAAA_login_name());
				pstmt.setString(2, bidr.getLogin_ip());
				pstmt.setTimestamp(3, bidr.getLogin_date());
				pstmt.setTimestamp(4, bidr.getLogout_date());
				pstmt.setString(5, bidr.getNAS_ip());
				pstmt.setInt(6, bidr.getTime_deration());
				
				pstmt.addBatch();
				if(index%1000 == 0&&index!=0)
					pstmt.executeBatch();
			}
			pstmt.executeBatch();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (Exception e) {
				// TODO hAuto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public int getDay(){
		return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
	}

}
