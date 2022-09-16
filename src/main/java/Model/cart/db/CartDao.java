package Model.cart.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CartDao {
	
	public boolean doInsert(CartDto dto) {
		
		Connection con = null;
		PreparedStatement ps = null;

		boolean isSuccess = true;
		
		try {
			
			String jdbcURL = "jdbc:mysql://localhost/netshop?characterEncoding=UTF-8&serverTimezone=Japan&useSSL=false";
			String name = "root";
			String pass = "root";
			
			con = DriverManager.getConnection(jdbcURL, name, pass);
			
			StringBuffer buf = new StringBuffer();
			buf.append("INSERT into ");
			buf.append("cart ");
			buf.append("(user_id, item_id, number, created) ");
			buf.append("values( ");
			buf.append("?, ?, ?, ? ");
			buf.append("); ");
			
			ps = con.prepareStatement(buf.toString());
			ps.setInt(1, dto.getUser_id());
			ps.setInt(2, dto.getItem_id());
			ps.setInt(3, dto.getNumber());
			ps.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
			
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
	
	public List<CartDto> doAllSelect (int id) {
		
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<CartDto> dtoList = new ArrayList<>();
		
		try {
			
			String jdbcURL = "jdbc:mysql://localhost/netshop?characterEncoding=UTF-8&serverTimezone=Japan&useSSL=false";
			String name = "root";
			String pass = "root";
			
			con = DriverManager.getConnection(jdbcURL, name, pass);
			
			StringBuffer buf = new StringBuffer();
			buf.append("SELECT ");
			buf.append("c.id, c.user_id, c.item_id, c.number, i.name, i.price, i.picture, i.stock ");
			buf.append("FROM ");
			buf.append("cart as c ");
			buf.append("LEFT JOIN items as i ");
			buf.append("ON c.item_id = i.id ");
			buf.append("WHERE ");
			buf.append("user_id = ?; ");
			
			ps = con.prepareStatement(buf.toString());
			ps.setInt(1, id);
	
			rs = ps.executeQuery();
			
			while (rs.next()) {
				CartDto dto = new CartDto();
				
				dto.setId(rs.getInt("c.id"));
				dto.setItem_id(rs.getInt("c.item_id"));
				dto.setUser_id(rs.getInt("c.user_id"));
				dto.setNumber(rs.getInt("c.number"));
				dto.setName(rs.getString("i.name"));
				dto.setPicture(rs.getString("i.picture"));
				dto.setPrice(rs.getInt("i.price"));
				dto.setStock(rs.getInt("i.stock"));
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
	
	public boolean doUpdate (int itemId, int number, int userId) {
		
		
		Connection con = null;
		PreparedStatement ps = null;
		
		boolean isSuccess = true;
		
		try {
			
			String jdbcURL = "jdbc:mysql://localhost/netshop?characterEncoding=UTF-8&serverTimezone=Japan&useSSL=false";
			String name = "root";
			String pass = "root";
			
			con = DriverManager.getConnection(jdbcURL, name, pass);
			
			StringBuffer buf = new StringBuffer();
			buf.append("UPDATE ");
			buf.append("cart ");
			buf.append("SET ");
			buf.append("number = ? ");
			buf.append("where ");
			buf.append("item_id = ? and ");
			buf.append("user_id = ? ");
			
			
			ps = con.prepareStatement(buf.toString());
			ps.setInt(1, number);
			ps.setInt(2, itemId);
			ps.setInt(3, userId);
	
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
	
	public boolean doDelete (int cartId, int userId) {
		
		
		Connection con = null;
		PreparedStatement ps = null;
		
		boolean isDelete = true;
		
		try {
			
			String jdbcURL = "jdbc:mysql://localhost/netshop?characterEncoding=UTF-8&serverTimezone=Japan&useSSL=false";
			String name = "root";
			String pass = "root";
			
			con = DriverManager.getConnection(jdbcURL, name, pass);
			
			StringBuffer buf = new StringBuffer();
			buf.append("DELETE ");
			buf.append("FROM ");
			buf.append("cart ");
			buf.append("WHERE ");
			buf.append("id = ? and ");
			buf.append("user_id = ?; ");
			
			ps = con.prepareStatement(buf.toString());
			ps.setInt(1, cartId );
			ps.setInt(2, userId);
	
			ps.execute();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			
			isDelete = false;
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
		
		return isDelete;
	}

}
