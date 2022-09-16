package Model.favorite.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class FavoriteDao {

	public boolean doInsert(int userId, int itemId) {
		
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
			buf.append("favorite ");
			buf.append("(user_id, item_id, created) ");
			buf.append("values( ");
			buf.append("?, ?, ? ");
			buf.append("); ");

			ps = con.prepareStatement(buf.toString());
			
			ps.setInt(1, userId);
			ps.setInt(2, itemId);
			ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));

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
	
	public List<FavoriteDto> doSelect(int userId) {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<FavoriteDto> dtoList = new ArrayList<>();

		try {

			String jdbcURL = "jdbc:mysql://localhost/netshop?characterEncoding=UTF-8&serverTimezone=Japan&useSSL=false";
			String name = "root";
			String pass = "root";

			con = DriverManager.getConnection(jdbcURL, name, pass);

			StringBuffer buf = new StringBuffer();
			buf.append("SELECT ");
			buf.append("f.id, f.user_id, f.item_id, i.picture, i.name, i.price ");
			buf.append("FROM ");
			buf.append("favorite as f ");
			buf.append("INNER JOIN items as i ");
			buf.append("ON f.item_id = i.id ");
			buf.append("WHERE  ");
			buf.append("f.user_id = ?; ");

			ps = con.prepareStatement(buf.toString());
			
			ps.setInt(1, userId);

			rs = ps.executeQuery();
			
			while(rs.next()) {
				FavoriteDto dto = new FavoriteDto();
				dto.setId(rs.getInt("f.id"));
				dto.setUser_id(rs.getInt("f.user_id"));
				dto.setItem_id(rs.getInt("f.item_id"));
				dto.setPicture(rs.getString("i.picture"));
				dto.setName(rs.getString("i.name"));
				dto.setPrice(rs.getInt("i.price"));
				dtoList.add(dto);
			}

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

		return dtoList;
	}
	
	public boolean doDelete(int userId, int itemId) {
		
		Connection con = null;
		PreparedStatement ps = null;

		boolean isSuccess = true;

		try {

			String jdbcURL = "jdbc:mysql://localhost/netshop?characterEncoding=UTF-8&serverTimezone=Japan&useSSL=false";
			String name = "root";
			String pass = "root";

			con = DriverManager.getConnection(jdbcURL, name, pass);

			StringBuffer buf = new StringBuffer();
			buf.append("DELETE ");
			buf.append("FROM ");
			buf.append("favorite ");
			buf.append("WHERE ");
			buf.append("user_id = ? and ");
			buf.append("item_id = ?; ");

			ps = con.prepareStatement(buf.toString());
			
			ps.setInt(1, userId);
			ps.setInt(2, itemId);

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
