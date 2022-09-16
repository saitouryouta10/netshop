package Config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class DBConnection {

	protected String jdbcURL = "jdbc:mysql://localhost/netshop?characterEncoding=UTF-8&serverTimezone=Japan&useSSL=false";
	protected String name = "root";
	protected String pass = "pass";
	
	protected Connection con = null;
	protected PreparedStatement ps = null;
	
	protected void doDB() {
		try {
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			
		}
		
	}
	
	
	
}
