package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import library.DatabaseConnection;
import model.bean.Contact;

public class ContactDao {
	private Connection conn;
	private PreparedStatement pst;
	private ResultSet rs;
	private Statement st;
	
	public ArrayList<Contact> getItems() {
		ArrayList<Contact> items = new ArrayList<>();
		
		conn = DatabaseConnection.getConnectMySql();
		String sql = "SELECT * FROM contact ORDER BY id_contact DESC";
		
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				Contact item = new Contact(rs.getInt("id_contact"), rs.getString("name"), rs.getString("phone"), rs.getString("email"), rs.getTimestamp("date"), rs.getString("content"), rs.getBoolean("status"));
				items.add(item);
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

	public int addItem(Contact objContact) {
		int result = 0;
		conn = DatabaseConnection.getConnectMySql();
		String sql = "INSERT INTO contact (name,phone,email,date,content,status) VALUES(?,?,?,?,?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objContact.getName());
			pst.setString(2, objContact.getPhone());
			pst.setString(3, objContact.getEmail());
			pst.setTimestamp(4, objContact.getDate());
			pst.setString(5, objContact.getContent());
			pst.setBoolean(6, objContact.isStatus());
			result = pst.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pst != null && conn != null) {
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

	public Contact getItem(int conid) {
		Contact item = null;
		
		conn = DatabaseConnection.getConnectMySql();
		String sql = "SELECT * FROM contact WHERE id_contact = ?";
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, conid);
			rs = pst.executeQuery();
			if(rs.next()){
				item = new Contact(rs.getInt("id_contact"), rs.getString("name"), rs.getString("phone"), rs.getString("email"), rs.getTimestamp("date"), rs.getString("content"), rs.getBoolean("status"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(rs != null && conn != null && pst != null){
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

	public int editItemChangeStatus(Contact objContact) {
		int result = 0;
		conn = DatabaseConnection.getConnectMySql();
		String sql = "UPDATE contact SET status = true WHERE id_contact = ?";
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, objContact.getIdContact());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(conn != null && pst != null){
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
