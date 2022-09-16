package Model.users.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class LoginDao {

	private String jdbcURL = "jdbc:mysql://localhost/netshop?characterEncoding=UTF-8&serverTimezone=Japan&useSSL=false";
	
	private String name = "root";
	
	private String pass = "root";
	
	LoginDto dto = new LoginDto();
	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	
	public boolean doInsert(LoginDto dto) {
		
		Connection con = null;
		PreparedStatement ps = null;		
		boolean isSuccess = true;
		
		try {
			
			con = DriverManager.getConnection(jdbcURL, name, pass);
			
			StringBuffer buf = new StringBuffer();
			
			buf.append("insert into ");
			buf.append("users ");
			buf.append("(name, name_kana, nickname, sex, birthday, zipcode, address, ");
			buf.append("tell, email, pass, created) ");
			buf.append("values( ");
			buf.append("?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ");
			buf.append("); ");
			
			ps = con.prepareStatement(buf.toString());
			
			ps.setString(1, dto.getName());
			ps.setString(2, dto.getName_kana());
			ps.setString(3, dto.getNickname());
			ps.setInt(4, dto.getSex());
			ps.setString(5, dto.getBirthday());
			ps.setString(6, dto.getZipcode());
			ps.setString(7, dto.getAddress());
			ps.setString(8, dto.getTell());
			ps.setString(9, dto.getEmail());
			ps.setString(10, dto.getPass());
			ps.setTimestamp(11, timestamp);
			
			ps.execute();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			
			isSuccess = false;
		} finally {
			
			if (ps != null) {
				try {
					ps.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
			
			if (con != null) {
				try {
					con.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return isSuccess;
		
	}
	
	public LoginDto doLogin(String email, String pass) {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		LoginDto dto = new LoginDto();
		
		try {
			
			con = DriverManager.getConnection(jdbcURL, name, this.pass);
			
			StringBuffer buf = new StringBuffer();
			buf.append("SELECT ");
			buf.append("* ");
			buf.append("FROM ");
			buf.append("USERS ");
			buf.append("WHERE ");
			buf.append("email = ? and ");
			buf.append("pass = ?; ");
			
			ps = con.prepareStatement(buf.toString());
			ps.setString(1, email);
			ps.setString(2, pass);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				dto.setId(Integer.parseInt(rs.getString("id")));
				dto.setName(rs.getString("name"));
				dto.setName_kana(rs.getString("name_kana"));
				dto.setNickname(rs.getString("nickname"));
				
				if (rs.getString("sex") != null) {
					dto.setSex(Integer.parseInt(rs.getString("sex")));
				} else {
					dto.setSex(0);
				}
				
				if (rs.getString("birthday") != null) {
					dto.setBirthday(rs.getString("birthday"));
				} else {
					dto.setBirthday("0");
				}

				dto.setZipcode(rs.getString("zipcode"));
				dto.setAddress(rs.getString("address"));
				dto.setTell(rs.getString("tell"));
				dto.setEmail(rs.getString("email"));
				dto.setPass(rs.getString("pass"));
//				created
				
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
	
	public boolean doUpdate(LoginDto dto) {
		
		Connection con = null;
		PreparedStatement ps = null;		
		boolean isSuccess = true;
		
		try {
			
			con = DriverManager.getConnection(jdbcURL, name, pass);
			
			StringBuffer buf = new StringBuffer();
			
			buf.append("UPDATE ");
			buf.append("users ");
			buf.append("SET ");
			buf.append("name=?, name_kana=?, nickname=?, sex=?, birthday=?, zipcode=?, ");
			buf.append("address=?, tell=?, email=? ");
			buf.append("where ");
			buf.append("id = ?; ");
			
			ps = con.prepareStatement(buf.toString());
			
			ps.setString(1, dto.getName());
			ps.setString(2, dto.getName_kana());
			ps.setString(3, dto.getNickname());
			ps.setInt(4, dto.getSex());
			ps.setString(5, dto.getBirthday());
			ps.setString(6, dto.getZipcode());
			ps.setString(7, dto.getAddress());
			ps.setString(8, dto.getTell());
			ps.setString(9, dto.getEmail());
			ps.setInt(10, dto.getId());
			
			ps.execute();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			
			isSuccess = false;
		} finally {
			
			if (ps != null) {
				try {
					ps.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
			
			if (con != null) {
				try {
					con.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return isSuccess;
		
	}
	
public boolean doUpdatePass(String password, int id) {
		
		Connection con = null;
		PreparedStatement ps = null;		
		boolean isSuccess = true;
		
		try {
			
			con = DriverManager.getConnection(jdbcURL, name, pass);
			
			StringBuffer buf = new StringBuffer();
			
			buf.append("UPDATE ");
			buf.append("users ");
			buf.append("SET ");
			buf.append("pass = ? ");
			buf.append("where ");
			buf.append("id = ?; ");
			
			ps = con.prepareStatement(buf.toString());
			
			ps.setString(1, password);
			ps.setInt(2, id);
			
			ps.execute();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			
			isSuccess = false;
		} finally {
			
			if (ps != null) {
				try {
					ps.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
			
			if (con != null) {
				try {
					con.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return isSuccess;
		
	}

	public List<LoginDto> doSelect() {
		
		Connection con = null;
		PreparedStatemet ps = null;
		ResultSet rs = null;
		
		try {
			
			con = DriverManager.getConecction(jdbcURL, name, pass);
			
		} catch (SQLException e) {
			
		} finally {
			
		}
	}
}
