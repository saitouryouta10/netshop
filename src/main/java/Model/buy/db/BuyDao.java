package Model.buy.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import Model.cart.db.CartDto;

public class BuyDao {

	public boolean doBuy(List<CartDto> dtoList) {

		Connection con = null;
		PreparedStatement ps = null;

		boolean isInsert = true;
		boolean isDelete = false;

		int userId = 0;

		try {

			String jdbcURL = "jdbc:mysql://localhost/netshop?characterEncoding=UTF-8&serverTimezone=Japan&useSSL=false";
			String name = "root";
			String pass = "root";

			con = DriverManager.getConnection(jdbcURL, name, pass);

			con.setAutoCommit(false);

			StringBuffer buf = new StringBuffer();

			buf.append("INSERT into ");
			buf.append("history ");
			buf.append("(name, price, user_id, item_id, created) ");
			buf.append("values ");
			for (int i = 0; i < dtoList.size(); i++) {
				buf.append("( ");
				buf.append("?, ?, ?, ?, ? ");
				buf.append(") ");
				if (i == dtoList.size() - 1) {
					buf.append("; ");
				} else {
					buf.append(", ");
				}
			}

			ps = con.prepareStatement(buf.toString());

			for (int i = 0; i < dtoList.size(); i++) {
				CartDto dto = dtoList.get(i);
				userId = dto.getUser_id();
				ps.setString(i * 5 + 1, dto.getName());
				ps.setInt(i * 5 + 2, dto.getPrice());
				ps.setInt(i * 5 + 3, dto.getUser_id());
				ps.setInt(i * 5 + 4, dto.getItem_id());
				ps.setTimestamp(i * 5 + 5, new Timestamp(System.currentTimeMillis()));

				doBuyUpdate(dto);

			}

			ps.execute();
			isDelete = doBuyDelete(userId);

		} catch (SQLException e) {
			e.printStackTrace();
			isDelete = false;
			isInsert = false;

		} finally {

			if (isDelete && isInsert) {
				try {
					con.commit();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				try {
					con.rollback();
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

		return isDelete;
	}

	private boolean doBuyDelete(int userId) {

		Connection con = null;
		PreparedStatement ps = null;

		boolean isSuccess = true;

		try {

			String jdbcURL = "jdbc:mysql://localhost/netshop?characterEncoding=UTF-8&serverTimezone=Japan&useSSL=false";
			String name = "root";
			String pass = "root";

			con = DriverManager.getConnection(jdbcURL, name, pass);

			con.setAutoCommit(false);

			StringBuffer buf = new StringBuffer();

			buf.append("DELETE ");
			buf.append("FROM ");
			buf.append("cart ");
			buf.append("WHERE ");
			buf.append("user_id = ?; ");

			ps = con.prepareStatement(buf.toString());

			ps.setInt(1, userId);

			ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			isSuccess = false;
		} finally {

			if (isSuccess) {
				try {
					con.commit();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				try {
					con.rollback();
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

		return isSuccess;
	}

	private boolean doBuyUpdate(CartDto dto) {

		Connection con = null;
		PreparedStatement ps = null;

		boolean isSuccess = true;

		try {

			String jdbcURL = "jdbc:mysql://localhost/netshop?characterEncoding=UTF-8&serverTimezone=Japan&useSSL=false";
			String name = "root";
			String pass = "root";

			con = DriverManager.getConnection(jdbcURL, name, pass);

			con.setAutoCommit(false);

			StringBuffer buf = new StringBuffer();

			buf.append("UPDATE ");
			buf.append("items ");
			buf.append("SET ");
			buf.append("stock = ? ");
			buf.append("WHERE ");
			buf.append("id = ?; ");

			ps = con.prepareStatement(buf.toString());

			ps.setInt(1, dto.getStock() - dto.getNumber());
			ps.setInt(2, dto.getItem_id());

			ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			isSuccess = false;
		} finally {

			if (isSuccess) {
				try {
					con.commit();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				try {
					con.rollback();
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

		return isSuccess;
	}



}
