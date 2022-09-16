package Model.inquiry.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class InquiryDao {

	public boolean doInsert(InquiryDto dto) {

		Connection con = null;
		PreparedStatement ps = null;

		boolean isSuccess = true;

		try {

			String jdbcURL = "jdbc:mysql://localhost/netshop?characterEncoding=UTF-8&serverTimezone=Japan&useSSL=false";
			String name = "root";
			String pass = "root";

			con = DriverManager.getConnection(jdbcURL, name, pass);

			StringBuffer buf = new StringBuffer();
			buf.append("INSERT INTO ");
			buf.append("inquiry ");
			buf.append("(email, inquery_post, name, created, kenmei) ");
			buf.append("values( ");
			buf.append("?, ?, ?, ?, ? ");
			buf.append("); ");

			ps = con.prepareStatement(buf.toString());

			ps.setString(1, dto.getEmail());
			ps.setString(2, dto.getInquery_post());
			ps.setString(3, dto.getName());
			ps.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
			ps.setString(5, dto.getKenmei());

			ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();

			isSuccess = false;
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

		return isSuccess;
	}
}
