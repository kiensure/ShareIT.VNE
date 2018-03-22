package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import library.DatabaseConnection;
import model.bean.Category;

public class CategoryDao {
	private Connection conn;
	private PreparedStatement pst;
	private ResultSet rs;
	private Statement st;
	
	public ArrayList<Category> getItems() {
		ArrayList<Category> items = new ArrayList<>();
		
		conn = DatabaseConnection.getConnectMySql();
		String sql = "SELECT * FROM cat_list ORDER BY id_cat ASC";
		
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				Category item = new Category(rs.getInt("id_cat"), rs.getString("name"), rs.getInt("id_parent"), rs.getInt("display_index"), rs.getBoolean("active"));
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

	public ArrayList<Category> getParentItems() {
		ArrayList<Category> items = new ArrayList<>();
		
		conn = DatabaseConnection.getConnectMySql();
		String sql = "SELECT * FROM cat_list WHERE id_parent = 0 ORDER BY id_cat ASC";
		
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				Category item = new Category(rs.getInt("id_cat"), rs.getString("name"), rs.getInt("id_parent"), rs.getInt("display_index"), rs.getBoolean("active"));
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

	public int addItem(Category objCat) {
		int result = 0;
		conn = DatabaseConnection.getConnectMySql();
		String sql = "INSERT INTO cat_list (name,id_parent,display_index,active) VALUES (?,?,?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objCat.getName());
			pst.setInt(2, objCat.getIdParent());
			pst.setInt(3, objCat.getDisplayIndex());
			pst.setBoolean(4, objCat.isActive());
			result = pst.executeUpdate();
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
		return result;
	}

	public int editItem(Category objCat) {
		int result = 0;
		conn = DatabaseConnection.getConnectMySql();
		String sql = "UPDATE cat_list SET name = ?, id_parent = ?, display_index = ?, active = ? WHERE id_cat = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objCat.getName());
			pst.setInt(2, objCat.getIdParent());
			pst.setInt(3, objCat.getDisplayIndex());
			pst.setBoolean(4, objCat.isActive());
			pst.setInt(5, objCat.getIdCat());
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

	public Category getItem(int idCat) {
		Category item = null;
		
		conn = DatabaseConnection.getConnectMySql();
		String sql = "SELECT * FROM cat_list WHERE id_cat = ?";
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idCat);
			rs = pst.executeQuery();
			if(rs.next()){
				item = new Category(rs.getInt("id_cat"), rs.getString("name"), rs.getInt("id_parent"), rs.getInt("display_index"), rs.getBoolean("active"));
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

	public int delItem(int idCat) {
		int result = 0;
		conn = DatabaseConnection.getConnectMySql();
		String sql = "DELETE FROM cat_list WHERE id_cat = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idCat);
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

	public ArrayList<Category> getItemsIndex() {
		ArrayList<Category> items = new ArrayList<>();
		
		conn = DatabaseConnection.getConnectMySql();
		String sql = "SELECT * FROM cat_list WHERE name LIKE 'Tin ICT' || name LIKE '%Kham pha%' || name Like '%Thu thuat%' ORDER BY id_cat ASC";
		
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				Category item = new Category(rs.getInt("id_cat"), rs.getString("name"), rs.getInt("id_parent"), rs.getInt("display_index"), rs.getBoolean("active"));
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

	public ArrayList<Category> getItemsByID(int idCat, int idParent) {
		ArrayList<Category> items = new ArrayList<>();
		
		conn = DatabaseConnection.getConnectMySql();
		String sql = "SELECT * FROM cat_list WHERE id_parent = ? ORDER BY id_cat ASC";
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idCat);
			rs = pst.executeQuery();
			while(rs.next()){
				Category item = new Category(rs.getInt("id_cat"), rs.getString("name"), rs.getInt("id_parent"), rs.getInt("display_index"), rs.getBoolean("active"));
				items.add(item);
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
		return items;
	}

	public ArrayList<Category> getItemsEdit(int idCat) {
		ArrayList<Category> items =  new ArrayList<>();
		conn = DatabaseConnection.getConnectMySql();
		String query = "SELECT * FROM cat_list WHERE id_cat NOT IN(SELECT id_cat FROM cat_list WHERE id_parent = ? UNION SELECT id_cat FROM cat_list WHERE id_parent IN (SELECT id_cat FROM cat_list WHERE id_parent = ?)) AND id_cat != ?";
		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, idCat);
			pst.setInt(2, idCat);
			pst.setInt(3, idCat);
			rs = pst.executeQuery();
			while (rs.next()) {
				Category item = new Category(rs.getInt("id_cat"), rs.getString("name"), rs.getInt("id_parent"), rs.getInt("display_index"), rs.getBoolean("active"));
				items.add(item);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null && pst != null && conn != null) {
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
		return items;
	}

	public ArrayList<Category> getItemsByIdPaAndSortByDisplayIndex(int idPa) {
		ArrayList<Category> items = new ArrayList<>();
		
		conn = DatabaseConnection.getConnectMySql();
		String sql = "SELECT * FROM cat_list WHERE id_parent = ? ORDER BY display_index ASC";
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idPa);
			rs = pst.executeQuery();
			while(rs.next()){
				Category item = new Category(rs.getInt("id_cat"), rs.getString("name"), rs.getInt("id_parent"), rs.getInt("display_index"), rs.getBoolean("active"));
				items.add(item);
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
		return items;
	}

	public Category getItemByDisplayIndexAndParent(int displayIndex, int idParent) {
		Category item = null;
		
		conn = DatabaseConnection.getConnectMySql();
		String sql = "SELECT * FROM cat_list WHERE id_parent = ? && display_index = ?";
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idParent);
			pst.setInt(2, displayIndex);
			rs = pst.executeQuery();
			if(rs.next()){
				item = new Category(rs.getInt("id_cat"), rs.getString("name"), rs.getInt("id_parent"), rs.getInt("display_index"), rs.getBoolean("active"));
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

	public int editItemDisplayIndex(Category objCat) {
		int result = 0;
		conn = DatabaseConnection.getConnectMySql();
		String sql = "UPDATE cat_list SET display_index = ? WHERE id_cat = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, objCat.getDisplayIndex());
			pst.setInt(2, objCat.getIdCat());
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

	public int editItemChangeStatus(Category objCat, boolean status) {
		int result = 0;
		conn =  DatabaseConnection.getConnectMySql();
		String sql = "UPDATE cat_list SET active = ? WHERE id_cat = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setBoolean(1, status);
			pst.setInt(2, objCat.getIdCat());
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
	
	public ArrayList<Integer> getItemsChild(int idCat) {
		ArrayList<Integer> items = new ArrayList<>();
		
		conn = DatabaseConnection.getConnectMySql();
		String sql = "SELECT id_cat FROM cat_list WHERE id_parent = ? "
				+ "UNION SELECT id_cat FROM cat_list WHERE id_parent IN "
				+ "(SELECT id_cat FROM cat_list WHERE id_parent = ?)";
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idCat);
			pst.setInt(2, idCat);
			rs = pst.executeQuery();
			while(rs.next()){
				Integer item = rs.getInt("id_cat");
				items.add(item);
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
		return items;
	}

	public int getIndexMaxOfParent(int idPa) {
		int result = 0;
		
		conn = DatabaseConnection.getConnectMySql();
		String sql = "SELECT MAX(display_index) AS maxindex FROM cat_list WHERE id_parent = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idPa);
			rs = pst.executeQuery();
			if(rs.next()){
				result = rs.getInt("maxindex");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(pst != null && rs != null && conn != null){
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
		return result;
	}
}
