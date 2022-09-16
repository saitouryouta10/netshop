package Model.history.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HistoryDao {
	
	public List<HistoryDto> doSelect(int userId) {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<HistoryDto> dtoList = new ArrayList<>();

		try {

			String jdbcURL = "jdbc:mysql://localhost/netshop?characterEncoding=UTF-8&serverTimezone=Japan&useSSL=false";
			String name = "root";
			String pass = "root";

			con = DriverManager.getConnection(jdbcURL, name, pass);

			con.setAutoCommit(false);

			StringBuffer buf = new StringBuffer();

			buf.append("SELECT ");
			buf.append("h.id, h.name, h.price, h.user_id, h.item_id, h.created, i.picture ");
			buf.append("FROM ");
			buf.append("history as h ");
			buf.append("LEFT JOIN items as i ");
			buf.append("ON h.item_id = i.id ");
			buf.append("WHERE ");
			buf.append("h.user_id = ? ");
			buf.append("ORDER BY id desc");

			ps = con.prepareStatement(buf.toString());
			ps.setInt(1, userId);
			
			rs = ps.executeQuery();

			while (rs.next()) {
				HistoryDto dto = new HistoryDto();

				dto.setId(rs.getInt("h.id"));
				dto.setName(rs.getString("h.name"));
				dto.setPrice(rs.getInt("h.price"));
				dto.setUser_id(rs.getInt("h.user_id"));
				dto.setItem_id(rs.getInt("h.item_id"));
				dto.setCreated(String.valueOf(rs.getTimestamp("h.created")));
				dto.setPicture(rs.getString("i.picture"));
				dtoList.add(dto);
			}


		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
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
	
	public List<HistoryDto> doSelectConstrict(String time, String money, int userId) {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<HistoryDto> dtoList = new ArrayList<>();

		try {

			String jdbcURL = "jdbc:mysql://localhost/netshop?characterEncoding=UTF-8&serverTimezone=Japan&useSSL=false";
			String name = "root";
			String pass = "root";

			con = DriverManager.getConnection(jdbcURL, name, pass);

			con.setAutoCommit(false);

			StringBuffer buf = new StringBuffer();

			buf.append("SELECT ");
			buf.append("h.id, h.name, h.price, h.user_id, h.item_id, h.created, i.picture ");
			buf.append("FROM ");
			buf.append("history as h ");
			buf.append("LEFT JOIN items as i ");
			buf.append("ON h.item_id = i.id ");
			buf.append("WHERE ");
			buf.append("h.user_id = ? ");
			if (money != null && money.matches("^[4-8]$")) {
				buf.append("and ");
				if (money.equals("4")) {
					buf.append("h.price <= 1000 ");
				} else if (money.equals("5")) {
					buf.append("h.price > 1000 and h.price <= 5000 ");
				} else if (money.equals("6")) {
					buf.append("h.price > 5000 and h.price <= 10000 ");
				} else if (money.equals("7")) {
					buf.append("h.price > 10000 and h.price <= 50000 ");
				} else if (money.equals("8")){
					buf.append("h.price > 50000 ");
				}
			} 
			
			if (time != null && time.matches("^[1-2]$")) {
				if (time.equals("1")) {
					buf.append("ORDER BY h.created desc ");
				} else if (time.equals("2")) {
					buf.append("ORDER BY h.created ASC ");
				}
			} else {
				buf.append("ORDER BY h.id desc ");
			}
			
			buf.append("; ");
			

			ps = con.prepareStatement(buf.toString());
			ps.setInt(1, userId);
			
			rs = ps.executeQuery();

			while (rs.next()) {
				HistoryDto dto = new HistoryDto();

				dto.setId(rs.getInt("h.id"));
				dto.setName(rs.getString("h.name"));
				dto.setPrice(rs.getInt("h.price"));
				dto.setUser_id(rs.getInt("h.user_id"));
				dto.setItem_id(rs.getInt("h.item_id"));
				dto.setCreated(String.valueOf(rs.getTimestamp("h.created")));
				dto.setPicture(rs.getString("i.picture"));
				dtoList.add(dto);
			}


		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
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
	
	
}
