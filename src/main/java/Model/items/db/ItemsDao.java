package Model.items.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ItemsDao {
	
	
	public List<ItemsDto> allSelect() {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<ItemsDto> dtoList = new ArrayList<>();
		
		String jdbcURL = "jdbc:mysql://localhost/netshop?characterEncoding=UTF-8&serverTimezone=Japan&useSSL=false";
		String name = "root";
		String pass = "root";
		
		try {
			
			con = DriverManager.getConnection(jdbcURL, name, pass);
			
			StringBuffer buf = new StringBuffer();
			
			buf.append("SELECT ");
			buf.append("* ");
			buf.append("FROM ");
			buf.append("items; ");
			
			ps = con.prepareStatement(buf.toString());
			
			rs = ps.executeQuery();
			

			
			while(rs.next()) {
				
				ItemsDto dto = new ItemsDto();
				
				dto.setId(rs.getInt("id"));
				dto.setName(rs.getString("name"));
				dto.setPrice(rs.getInt("price"));
				dto.setRetention_stock(rs.getInt("retention_stock"));
				dto.setStock(rs.getInt("stock"));
				dto.setSetumei(rs.getString("setumei"));
				dto.setSyousai(rs.getString("syousai"));
				dto.setPicture(rs.getString("picture"));
				dto.setCreated(String.valueOf(rs.getTimestamp("created")));
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
	
	public List<ItemsDto> searchSelect(String itemName) {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<ItemsDto> dtoList = new ArrayList<>();
		
		String jdbcURL = "jdbc:mysql://localhost/netshop?characterEncoding=UTF-8&serverTimezone=Japan&useSSL=false";
		String name = "root";
		String pass = "root";
		
		try {
			
			con = DriverManager.getConnection(jdbcURL, name, pass);
			
			StringBuffer buf = new StringBuffer();
			
			buf.append("SELECT ");
			buf.append("* ");
			buf.append("FROM ");
			buf.append("items ");
			buf.append("WHERE name LIKE ?; ");
			
			ps = con.prepareStatement(buf.toString());
			ps.setString(1, "%" +itemName + "%");
			
			rs = ps.executeQuery();
			

			
			while(rs.next()) {
				
				ItemsDto dto = new ItemsDto();
				
				dto.setId(rs.getInt("id"));
				dto.setName(rs.getString("name"));
				dto.setPrice(rs.getInt("price"));
				dto.setRetention_stock(rs.getInt("retention_stock"));
				dto.setStock(rs.getInt("stock"));
				dto.setSetumei(rs.getString("setumei"));
				dto.setSyousai(rs.getString("syousai"));
				dto.setPicture(rs.getString("picture"));
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
	
	
	public ItemsDto doSelect(int id) {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		ItemsDto dto = new ItemsDto();
		
		try {
			
			String jdbcURL = "jdbc:mysql://localhost/netshop?characterEncoding=UTF-8&serverTimezone=Japan&useSSL=false";
			String name = "root";
			String pass = "root";
			
			con = DriverManager.getConnection(jdbcURL, name, pass);
			
			StringBuffer buf = new StringBuffer();
			
			buf.append("SELECT ");
			buf.append("i.id, i.name, i.price, i.stock, i.setumei, i.syousai, i.picture, ");
			buf.append("f.user_id, f.item_id ");
			buf.append("FROM ");
			buf.append("items as i ");
			buf.append("LEFT join favorite as f ");
			buf.append("ON i.id = f.item_id ");
			buf.append("WHERE i.id = ?;");
			
			ps = con.prepareStatement(buf.toString());
			
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				dto.setId(rs.getInt("i.id"));
				dto.setName(rs.getString("i.name"));
				dto.setPrice(rs.getInt("i.price"));
				dto.setStock(rs.getInt("i.stock"));
				dto.setSetumei(rs.getString("i.setumei"));
				dto.setSyousai(rs.getString("i.syousai"));
				dto.setPicture(rs.getString("i.picture"));
				dto.setFavorite_item_id(rs.getInt("f.item_id"));
				dto.setFavorite_user_id(rs.getInt("f.user_id"));
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
		
		return dto;
	}
	
	public boolean doDelete(int itemId) {

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
			buf.append("items ");
			buf.append("WHERE ");
			buf.append("id = ?;  ");

			ps = con.prepareStatement(buf.toString());
			ps.setInt(1, itemId);
			
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
	
	public boolean doInsert(ItemsDto dto) {

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
			buf.append("items ");
			buf.append("(name, price, retention_stock, stock, setumei, syousai, picture, created) ");
			buf.append("values( ");
			buf.append("?, ?, ?, ?, ?, ?, ?, ? ");
			buf.append("); ");

			ps = con.prepareStatement(buf.toString());
			ps.setString(1, dto.getName());
			ps.setInt(2, dto.getPrice());
			ps.setInt(3, dto.getRetention_stock());
			ps.setInt(4, dto.getStock());
			ps.setString(5, dto.getSetumei());
			ps.setString(6, dto.getSyousai());
			ps.setString(7, dto.getPicture());
			ps.setTimestamp(8, new Timestamp(System.currentTimeMillis()));

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
