package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import library.DatabaseConnection;
import model.bean.LocationAds;

public class LocalAdsDao {
	private Connection conn;
	private PreparedStatement pst;
	private ResultSet rs;
	private Statement st;
	
	public ArrayList<LocationAds> getItems() {
		ArrayList<LocationAds> items = new ArrayList<>();
		
		conn = DatabaseConnection.getConnectMySql();
		String sql = "SELECT * FROM ads_location ORDER BY id_location ASC";
		
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				LocationAds item = new LocationAds(rs.getInt("id_location"), rs.getString("name"));
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
	
	public LocationAds getItem(int idLoca) {
		LocationAds item = null;
		
		conn = DatabaseConnection.getConnectMySql();
		String sql = "SELECT * FROM ads_location WHERE id_location = ?";
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idLoca);
			rs = pst.executeQuery();
			if(rs.next()){
				item = new LocationAds(rs.getInt("id_location"), rs.getString("name"));
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
	
}
