package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import library.DatabaseConnection;
import model.bean.Advertisements;

public class AdvertisementsDao {
	private Connection conn;
	private PreparedStatement pst;
	private ResultSet rs;
	private Statement st;
	
	public ArrayList<Advertisements> getItems() {
		ArrayList<Advertisements> items = new ArrayList<>();
		
		conn = DatabaseConnection.getConnectMySql();
		String sql = "SELECT * FROM ads ORDER BY id_ads ASC";
		
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				Advertisements item = new Advertisements(rs.getInt("id_ads"), rs.getInt("id_location"), rs.getString("name"), rs.getString("picture"), rs.getString("link"));
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

	public Advertisements getItem(int adsId) {
		Advertisements item = null;
		conn =  DatabaseConnection.getConnectMySql();
		String sql = "SELECT * FROM ads WHERE id_ads = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, adsId);
			rs = pst.executeQuery();
			if(rs.next()){
				item =  new Advertisements(rs.getInt("id_ads"), rs.getInt("id_location"), rs.getString("name"), rs.getString("picture"), rs.getString("link"));
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

	public int editItem(Advertisements itemAds) {
		int result = 0;
		conn = DatabaseConnection.getConnectMySql();
		String sql = "UPDATE ads set id_location = ?, name = ?, picture = ?, link = ? WHERE id_ads = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, itemAds.getLocation());
			pst.setString(2, itemAds.getNameAds());
			pst.setString(3, itemAds.getPicture());
			pst.setString(4, itemAds.getLink());
			pst.setInt(5, itemAds.getIdAds());
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

	public int delItem(int aid) {
		int result = 0;
		conn = DatabaseConnection.getConnectMySql();
		String sql = "DELETE FROM ads WHERE id_ads = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, aid);
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

	public int addItem(Advertisements objAds) {
		int result = 0;
		conn = DatabaseConnection.getConnectMySql();
		String sql = "INSERT INTO ads (id_location,name, picture, link) VALUES(?,?,?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, objAds.getLocation());
			pst.setString(2, objAds.getNameAds());
			pst.setString(3, objAds.getPicture());
			pst.setString(4, objAds.getLink());
			result = pst.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pst != null && conn != null) {
				try {
					st.close();
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
