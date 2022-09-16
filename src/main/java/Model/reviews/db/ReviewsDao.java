package Model.reviews.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ReviewsDao {

	public List<ReviewsDto> doSelect(int id) {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<ReviewsDto> twoDto = new ArrayList<>();

		try {
			String jdbcURL = "jdbc:mysql://localhost/netshop?characterEncoding=UTF-8&serverTimezone=Japan&useSSL=false";
			String name = "root";
			String pass = "root";

			con = DriverManager.getConnection(jdbcURL, name, pass);

			StringBuffer buf = new StringBuffer();

			buf.append("SELECT ");
			buf.append("r.comment, r.star, r.created, u.nickname ");
			buf.append("FROM ");
			buf.append("reviews as r ");
			buf.append("LEFT JOIN users as u ");
			buf.append("ON r.user_id = u.id ");
			buf.append("WHERE r.item_id = ? ");
			buf.append("ORDER BY r.id DESC ");
			buf.append("LIMIT 2; ");

			ps = con.prepareStatement(buf.toString());
			ps.setInt(1, id);

			rs = ps.executeQuery();

			while (rs.next()) {
				ReviewsDto dto = new ReviewsDto();

				dto.setComment(rs.getString("r.comment"));
				dto.setStar(rs.getInt("r.star"));
				dto.setNickname(rs.getString("u.nickname"));
				dto.setCreated(String.valueOf(rs.getTimestamp("r.created")));
				twoDto.add(dto);

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

		return twoDto;
	}

	//	内容ほぼ一緒なのでどうにかしたい
	public List<ReviewsDto> doAllSelect(int id) {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<ReviewsDto> dtoList = new ArrayList<>();

		try {
			String jdbcURL = "jdbc:mysql://localhost/netshop?characterEncoding=UTF-8&serverTimezone=Japan&useSSL=false";
			String name = "root";
			String pass = "root";

			con = DriverManager.getConnection(jdbcURL, name, pass);

			StringBuffer buf = new StringBuffer();

			buf.append("SELECT ");
			buf.append("r.id, r.comment, r.star, r.user_id, r.created, u.nickname ");
			buf.append("FROM ");
			buf.append("reviews as r ");
			buf.append("LEFT JOIN users as u ");
			buf.append("ON r.user_id = u.id ");
			buf.append("WHERE r.item_id = ? ");
			buf.append("ORDER BY r.star DESC; ");

			ps = con.prepareStatement(buf.toString());
			ps.setInt(1, id);

			rs = ps.executeQuery();

			while (rs.next()) {
				ReviewsDto dto = new ReviewsDto();
				
				dto.setId(rs.getInt("id"));
				dto.setComment(rs.getString("r.comment"));
				dto.setStar(rs.getInt("r.star"));
				dto.setUser_id(rs.getInt("user_id"));
				dto.setNickname(rs.getString("u.nickname"));
				dto.setCreated(String.valueOf(rs.getTimestamp("r.created")));
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

	public boolean doInsert(ReviewsDto dto) {

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
			buf.append("reviews ");
			buf.append("(comment, star, item_id, user_id, created) ");
			buf.append("values( ");
			buf.append("?, ?, ?, ?, ? ");
			buf.append("); ");

			ps = con.prepareStatement(buf.toString());
			ps.setString(1, dto.getComment());
			ps.setInt(2, dto.getStar());
			ps.setInt(3, dto.getItem_id());
			ps.setInt(4, dto.getUser_id());
			ps.setTimestamp(5, new Timestamp(System.currentTimeMillis()));

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

	public boolean doDelete(int reviewId, int userId) {

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
			buf.append("reviews ");
			buf.append("WHERE ");
			buf.append("id = ? and  ");
			buf.append("user_id = ?; ");

			ps = con.prepareStatement(buf.toString());
			ps.setInt(1, reviewId);
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
