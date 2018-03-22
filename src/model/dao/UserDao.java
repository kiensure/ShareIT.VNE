package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import library.DatabaseConnection;
import model.bean.User;

public class UserDao {
	private Connection conn;
	private PreparedStatement pst;
	private ResultSet rs;
	private Statement st;
	public ArrayList<User> getItems() {
		ArrayList<User> items = new ArrayList<>();
		conn = DatabaseConnection.getConnectMySql();
		String sql = "SELECT id_user, username, password, fullname, email, ut.id_type AS idtype, name, active FROM user AS u INNER JOIN user_types AS ut ON u.id_type = ut.id_type ORDER BY id_user ASC";
		
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				User user = new User(rs.getInt("id_user"), rs.getString("username"), rs.getString("password"), rs.getString("fullname"), rs.getString("email"), rs.getInt("idtype"),rs.getString("name") ,rs.getBoolean("active"));
				items.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(rs != null && st != null && conn != null){
				try {
					rs.close();
					st.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return items;
	}
	public int addItem(User item) {
		int result = 0;
		conn =  DatabaseConnection.getConnectMySql();
		String sql = "INSERT INTO user(username, password, fullname, email, id_type, active) VALUES (?,?,?,?,?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, item.getName());
			pst.setString(2, item.getPassword());
			pst.setString(3, item.getFullname());
			pst.setString(4, item.getEmail());
			pst.setInt(5, item.getIdTypeUser());
			pst.setBoolean(6, item.isActive());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(pst != null && conn != null){
				try {
					pst.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	public User checkUser(String username){
		User item = null;
		conn = DatabaseConnection.getConnectMySql();
		String sql = "SELECT * FROM user WHERE username = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			rs =  pst.executeQuery();
			if(rs.next()){
				item = new User(rs.getInt("id_user"), rs.getString("username"), rs.getString("password"), rs.getString("fullname"), rs.getString("email"), rs.getInt("id_type"),"" ,rs.getBoolean("active"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(rs != null && pst != null && conn != null){
				try {
					rs.close();
					pst.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return item;
	}
	
	public User checkLogin(String username, String password) {
		User user = null;
		conn = DatabaseConnection.getConnectMySql();
		String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			pst.setString(2, password);
			rs = pst.executeQuery();
			if(rs.next()){
				user = new User(rs.getInt("id_user"), rs.getString("username"), rs.getString("password"), rs.getString("fullname"), rs.getString("email"), rs.getInt("id_type"),"" ,rs.getBoolean("active"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(rs != null && pst != null && conn != null){
				try {
					rs.close();
					pst.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return user;
	}

	public User getItem(int idUser) {
		User item = null;
		conn = DatabaseConnection.getConnectMySql();
		String sql = "SELECT * FROM user WHERE id_user = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idUser);
			rs = pst.executeQuery();
			if(rs.next()){
				item = new User(rs.getInt("id_user"), rs.getString("username"), rs.getString("password"), rs.getString("fullname"), rs.getString("email"), rs.getInt("id_type"),"" ,rs.getBoolean("active"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(rs != null && pst != null && conn != null){
				try {
					rs.close();
					pst.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return item;
	}

	public int delItem(int idUser) {
		int result = 0;
		conn =  DatabaseConnection.getConnectMySql();
		String sql = "DELETE FROM user WHERE id_user = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idUser);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(pst != null && conn != null){
				try {
					pst.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	public int editItem(User item) {
		int result = 0;
		conn =  DatabaseConnection.getConnectMySql();
		String sql = "UPDATE user SET password = ?, fullname = ?, email = ?, id_type = ? WHERE id_user = ?";
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, item.getPassword());
			pst.setString(2, item.getFullname());
			pst.setString(3, item.getEmail());
			pst.setInt(4, item.getIdTypeUser());
			pst.setInt(5, item.getIdUser());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(pst != null && conn != null){
				try {
					pst.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return result;
	}
}
