package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import constant.Define;
import library.DatabaseConnection;
import model.bean.News;

public class NewsDao {
	private Connection conn;
	private PreparedStatement pst;
	private ResultSet rs;
	private Statement st;

	public int countNews() {
		int result = 0;
		conn = DatabaseConnection.getConnectMySql();
		String sql = "SELECT COUNT(*) AS sumnews FROM news INNER JOIN cat_list ON news.id_cat = cat_list.id_cat";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				result = rs.getInt("sumNews");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null && st != null && conn != null) {
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
		return result;
	}

	public ArrayList<News> getItemsPagination(int offset) {
		ArrayList<News> items = new ArrayList<>();
		conn = DatabaseConnection.getConnectMySql();
		String query = "SELECT id_news, n.name AS nname, preview, "
				+ "detail, date_create, hotnew,id_user, picture, c.id_cat AS cat_id, "
				+ "c.name AS cname, is_slide, views , n.active AS nactive FROM news AS n INNER JOIN cat_list AS c "
				+ "ON n.id_cat = c.id_cat ORDER BY id_news DESC LIMIT "
				+ offset + "," + Define.ROW_COUNT_ADMIN;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				News item = new News(rs.getInt("id_news"),
						rs.getString("nname"), rs.getString("preview"),
						rs.getString("detail"), rs.getTimestamp("date_create"), 
						rs.getBoolean("hotnew"), rs.getInt("id_user"), 
						rs.getString("picture"), rs.getInt("cat_id"), 
						rs.getString("cname"), rs.getBoolean("is_slide"), 
						rs.getInt("views"), rs.getBoolean("nactive"));
				items.add(item);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null && st != null && conn != null) {
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

	public int addItem(News news) {
		int result = 0;
		conn = DatabaseConnection.getConnectMySql();
		String sql = "INSERT INTO news(name,preview,detail,date_create,hotnew,id_user,picture,id_cat,is_slide,views,active) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, news.getName());
			pst.setString(2, news.getPreview());
			pst.setString(3, news.getDetail());
			pst.setTimestamp(4, news.getDate_Create());
			pst.setBoolean(5, news.isHotNew());
			pst.setInt(6, news.getIdUser());
			pst.setString(7, news.getPicture());
			pst.setInt(8, news.getIdCat());
			pst.setBoolean(9, news.isIs_Slide());
			pst.setInt(10, news.getViews());
			pst.setBoolean(11, news.isActive());
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

	public News getItem(int idNews) {
		News item = null;
		conn = DatabaseConnection.getConnectMySql();
		String query = "SELECT id_news, n.name AS nname, preview, "
				+ "detail, date_create, hotnew, id_user, picture, c.id_cat AS cat_id, "
				+ "c.name AS cname, is_slide, views, n.active AS nactive FROM news AS n INNER JOIN cat_list AS c "
				+ "ON n.id_cat = c.id_cat WHERE id_news = ?";
		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, idNews);
			rs = pst.executeQuery();
			if (rs.next()) {
				item = new News(rs.getInt("id_news"),
						rs.getString("nname"), rs.getString("preview"),
						rs.getString("detail"), rs.getTimestamp("date_create"), 
						rs.getBoolean("hotnew"), rs.getInt("id_user"), 
						rs.getString("picture"), rs.getInt("cat_id"), 
						rs.getString("cname"), rs.getBoolean("is_slide"), 
						rs.getInt("views"), rs.getBoolean("nactive"));
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
		return item;
	}

	public int editItem(News news) {
		int result = 0;
		conn = DatabaseConnection.getConnectMySql();
		String sql = "UPDATE news SET name = ?,preview = ?, detail = ?,picture = ?,id_cat = ?, is_slide = ? WHERE id_news = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, news.getName());
			pst.setString(2, news.getPreview());
			pst.setString(3, news.getDetail());
			pst.setString(4, news.getPicture());
			pst.setInt(5, news.getIdCat());
			pst.setBoolean(6, news.isIs_Slide());
			pst.setInt(7, news.getIdNews());
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

	public int delItem(int idNews) {
		int result = 0;
		conn = DatabaseConnection.getConnectMySql();
		String sql = "DELETE FROM news WHERE id_news = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idNews);
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

	public ArrayList<News> getItemsSlide() {
		ArrayList<News> items = new ArrayList<>();
		conn = DatabaseConnection.getConnectMySql();
		String sql = "SELECT id_news, n.name AS nname, preview,  detail, date_create,hotnew,n.id_user, "
				+ "picture, c.id_cat AS cat_id, c.name AS cname, is_slide, views, n.active AS nactive, username "
				+ "FROM news AS n INNER JOIN cat_list AS c ON n.id_cat = c.id_cat "
				+ "INNER JOIN user AS u ON n.id_user = u.id_user WHERE is_slide = true && n.active = true && c.active = true ORDER BY date_create DESC LIMIT 4";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				News item = new News(rs.getInt("id_news"),
						rs.getString("nname"), rs.getString("preview"),
						rs.getString("detail"), rs.getTimestamp("date_create"), 
						rs.getBoolean("hotnew"), rs.getInt("id_user"), 
						rs.getString("picture"), rs.getInt("cat_id"), 
						rs.getString("cname"), rs.getBoolean("is_slide"), 
						rs.getInt("views"), rs.getBoolean("nactive"));
				items.add(item);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (rs != null && st != null && conn != null) {
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

	public ArrayList<News> getItemsICT() {
		ArrayList<News> items = new ArrayList<>();
		conn = DatabaseConnection.getConnectMySql();
		String sql = "SELECT id_news, n.name AS nname, preview,  detail, date_create, hotnew, n.id_user, picture, c.id_cat AS cat_id, c.name AS cname, is_slide, views, n.active AS nactive, username FROM news AS n INNER JOIN cat_list AS c ON n.id_cat = c.id_cat INNER JOIN user AS u ON n.id_user = u.id_user WHERE c.name LIKE '%Tin ICT%' ORDER BY id_news DESC LIMIT 4";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				News item = new News(rs.getInt("id_news"),
						rs.getString("nname"), rs.getString("preview"),
						rs.getString("detail"), rs.getTimestamp("date_create"), 
						rs.getBoolean("hotnew"), rs.getInt("id_user"), 
						rs.getString("picture"), rs.getInt("cat_id"), 
						rs.getString("cname"), rs.getBoolean("is_slide"), 
						rs.getInt("views"), rs.getBoolean("nactive"));
				items.add(item);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (rs != null && st != null && conn != null) {
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

	public ArrayList<News> getItemsByIDCat(int idCat) {
		ArrayList<News> items = new ArrayList<>();
		conn = DatabaseConnection.getConnectMySql();
		String sql = "SELECT id_news, n.name AS nname, preview,  detail, date_create, hotnew, n.id_user, "
				+ "picture, c.id_cat AS cat_id, c.name AS cname, is_slide, views, n.active AS nactive, username FROM "
				+ "news AS n INNER JOIN cat_list AS c ON n.id_cat = c.id_cat INNER JOIN user AS u ON n.id_user = u.id_user "
				+ "WHERE n.id_cat = ? && n.active = true && c.active = true ORDER BY id_news DESC LIMIT 4";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idCat);
			rs = pst.executeQuery();
			while (rs.next()) {
				News item = new News(rs.getInt("id_news"),
						rs.getString("nname"), rs.getString("preview"),
						rs.getString("detail"), rs.getTimestamp("date_create"), 
						rs.getBoolean("hotnew"), rs.getInt("id_user"), 
						rs.getString("picture"), rs.getInt("cat_id"), 
						rs.getString("cname"), rs.getBoolean("is_slide"), 
						rs.getInt("views"), rs.getBoolean("nactive"));
				items.add(item);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
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

	public int editItemByStatus(News news, boolean status) {
		int result = 0;
		conn =  DatabaseConnection.getConnectMySql();
		String sql = "UPDATE news SET active = ? WHERE id_news = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setBoolean(1, status);
			pst.setInt(2, news.getIdNews());
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

	public int countViews(int idNews) {
		int result = 0;
		conn = DatabaseConnection.getConnectMySql();
		String sql = "SELECT views FROM news WHERE id_news = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idNews);
			rs = pst.executeQuery();
			if(rs.next()){
				result = rs.getInt("views");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
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

	public int updateViews(int countViews, News objnews) {
		int result = 0;
		conn = DatabaseConnection.getConnectMySql();
		String sql = "UPDATE news SET views = ? WHERE id_news = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, countViews);
			pst.setInt(2, objnews.getIdNews());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
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

	public ArrayList<News> searchItem(String keyword) {
		ArrayList<News> items = new ArrayList<>();
		conn = DatabaseConnection.getConnectMySql();
		String query = "SELECT id_news, n.name AS nname, preview, "
				+ "detail, date_create, hotnew, id_user, picture, c.id_cat AS cat_id, "
				+ "c.name AS cname, is_slide, views , n.active AS nactive FROM news AS n INNER JOIN cat_list AS c "
				+ "ON n.id_cat = c.id_cat WHERE n.name LIKE '%"+keyword+"%' && n.active = true && c.active= true ORDER BY id_news DESC";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				News item = new News(rs.getInt("id_news"),
						rs.getString("nname"), rs.getString("preview"),
						rs.getString("detail"), rs.getTimestamp("date_create"), 
						rs.getBoolean("hotnew"), rs.getInt("id_user"), 
						rs.getString("picture"), rs.getInt("cat_id"), 
						rs.getString("cname"), rs.getBoolean("is_slide"), 
						rs.getInt("views"), rs.getBoolean("nactive"));
				items.add(item);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null && st != null && conn != null) {
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

	public ArrayList<News> getItemsPagination(String keyword, int offset) {
		ArrayList<News> items = new ArrayList<>();
		conn = DatabaseConnection.getConnectMySql();
		String query = "SELECT id_news, n.name AS nname, preview, "
				+ "detail, date_create, hotnew, id_user, picture, c.id_cat AS cat_id, "
				+ "c.name AS cname, is_slide, views , n.active AS nactive FROM news AS n INNER JOIN cat_list AS c "
				+ "ON n.id_cat = c.id_cat WHERE n.name LIKE '%"+keyword+"%' && active = true ORDER BY id_news DESC LIMIT "+offset+","+Define.ROW_COUNT_ADMIN;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				News item = new News(rs.getInt("id_news"),
						rs.getString("nname"), rs.getString("preview"),
						rs.getString("detail"), rs.getTimestamp("date_create"), 
						rs.getBoolean("hotnew"), rs.getInt("id_user"), 
						rs.getString("picture"), rs.getInt("cat_id"), 
						rs.getString("cname"), rs.getBoolean("is_slide"), 
						rs.getInt("views"), rs.getBoolean("nactive"));
				items.add(item);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null && st != null && conn != null) {
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

	public ArrayList<News> getItemsByIDCAT(int cid) {
		ArrayList<News> items = new ArrayList<>();
		conn = DatabaseConnection.getConnectMySql();
		String sql = "SELECT id_news, n.name AS nname, preview,  detail, date_create, hotnew, n.id_user, "
				+ "picture, c.id_cat AS cat_id, c.name AS cname, is_slide, views, n.active AS nactive, username "
				+ "FROM news AS n INNER JOIN cat_list AS c ON n.id_cat = c.id_cat "
				+ "INNER JOIN user AS u ON n.id_user = u.id_user WHERE n.id_cat = ? ORDER BY date_create DESC";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, cid);
			rs = pst.executeQuery();
			while (rs.next()) {
				News item = new News(rs.getInt("id_news"),
						rs.getString("nname"), rs.getString("preview"),
						rs.getString("detail"), rs.getTimestamp("date_create"), 
						rs.getBoolean("hotnew"), rs.getInt("id_user"), 
						rs.getString("picture"), rs.getInt("cat_id"), 
						rs.getString("cname"), rs.getBoolean("is_slide"), 
						rs.getInt("views"), rs.getBoolean("nactive"));
				items.add(item);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
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
	
	public ArrayList<News> getItemsNew() {
		ArrayList<News> items = new ArrayList<>();
		News item = null;
		conn = DatabaseConnection.getConnectMySql();
		String query = "SELECT id_news, n.name AS nname, preview, "
				+ "detail, date_create, hotnew, id_user, picture, c.id_cat AS cat_id, "
				+ "c.name AS cname, is_slide, views, n.active AS nactive FROM news AS n INNER JOIN cat_list AS c "
				+ "ON n.id_cat = c.id_cat WHERE n.active = true ORDER BY id_news DESC LIMIT 5";
		try {
			pst = conn.prepareStatement(query);
			rs = pst.executeQuery();
			while (rs.next()) {
				item = new News(rs.getInt("id_news"),
						rs.getString("nname"), rs.getString("preview"),
						rs.getString("detail"), rs.getTimestamp("date_create"), 
						rs.getBoolean("hotnew"), rs.getInt("id_user"), 
						rs.getString("picture"), rs.getInt("cat_id"), 
						rs.getString("cname"), rs.getBoolean("is_slide"), 
						rs.getInt("views"), rs.getBoolean("nactive"));
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

	public ArrayList<News> getItems(int start, int limit) {
		ArrayList<News> items =  new ArrayList<>();
		conn = DatabaseConnection.getConnectMySql();
		String query = "SELECT id_news, n.name AS nname, preview, "
				+ "detail, date_create, hotnew, id_user, picture, c.id_cat AS cat_id, "
				+ "c.name AS cname, is_slide, views ,n.active AS nactive FROM news AS n INNER JOIN cat_list AS c "
				+ "ON n.id_cat = c.id_cat ORDER BY id_news DESC LIMIT "
				+ start + "," + (limit+1);
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				News item = new News(rs.getInt("id_news"),
						rs.getString("nname"), rs.getString("preview"),
						rs.getString("detail"), rs.getTimestamp("date_create"), 
						rs.getBoolean("hotnew"), rs.getInt("id_user"), 
						rs.getString("picture"), rs.getInt("cat_id"), 
						rs.getString("cname"), rs.getBoolean("is_slide"), 
						rs.getInt("views"), rs.getBoolean("nactive"));
				items.add(item);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null && st != null && conn != null) {
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


	public int editItemActiveHotNews(News news, boolean ishotnews) {
		int result = 0;
		conn =  DatabaseConnection.getConnectMySql();
		String sql = "UPDATE news SET hotnew = ? WHERE id_news = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setBoolean(1, ishotnews);
			pst.setInt(2, news.getIdNews());
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

	public ArrayList<News> getItems(String name, String date, int status) {
		ArrayList<News> items =  new ArrayList<>();
		conn = DatabaseConnection.getConnectMySql();
		String where = "";
		if(!"".equals(name)){
			if(!"".equals(date)){
				if(status == 1){
					where = "WHERE n.name LIKE '%"+name+"%' AND DATE(date_create) LIKE '"+date+"' AND n.active = true ORDER BY date_create";
				}else if(status == 0){
					where = "WHERE n.name LIKE '%"+name+"%' AND DATE(date_create) LIKE '"+date+"' AND n.active = false ORDER BY date_create";
				}else{
					where = "WHERE n.name LIKE '%"+name+"%' AND DATE(date_create) LIKE '"+date+"' ORDER BY date_create";
				}
			}else{
				if(status == 1){
					where = "WHERE n.name LIKE '%"+name+"%' AND n.active = true ORDER BY date_create";
				}else if(status == 0){
					where = "WHERE n.name LIKE '%"+name+"%' AND n.active = false ORDER BY date_create";
				}else{
					where = "WHERE n.name LIKE '%"+name+"%' ORDER BY date_create";
				}
			}
		}
		
		if(!"".equals(date)){
			if(!"".equals(name)){
				if(status == 1){
					where = "WHERE n.name LIKE '%"+name+"%' AND DATE(date_create) LIKE '"+date+"' AND n.active = true ORDER BY date_create";
				}else if(status == 0){
					where = "WHERE n.name LIKE '%"+name+"%' AND DATE(date_create) LIKE '"+date+"' AND n.active = false ORDER BY date_create";
				}else{
					where = "WHERE n.name LIKE '%"+name+"%' AND DATE(date_create) LIKE '"+date+"' ORDER BY date_create";
				}
			}else{
				if(status == 1){
					where = "WHERE DATE(date_create) LIKE '"+date+"' AND active = true ORDER BY date_create";
				}else if(status == 0){
					where = "WHERE DATE(date_create) LIKE '"+date+"' AND active = false ORDER BY date_create";
				}else{
					where = "WHERE DATE(date_create) LIKE '"+date+"' ORDER BY date_create";
				}
			}
		}
		
		if(status == 1){
			if(!"".equals(name)){
				if(!"".equals(date)){
					where = "WHERE n.name LIKE '%"+name+"%' AND DATE(date_create) LIKE '"+date+"' AND active = true ORDER BY date_create";
				}else{
					where = "WHERE n.name LIKE '%"+name+"%' AND n.active = true ORDER BY date_create";
				}
			}else{
				if(!"".equals(date)){
					where = "WHERE DATE(date_create) LIKE '"+date+"' AND n.active = true ORDER BY date_create";
				}else{
					where = "WHERE n.active = true ORDER BY date_create";
				}
			}
		}else if(status == 0){
			if(!"".equals(name)){
				if(!"".equals(date)){
					where = "WHERE n.name LIKE '%"+name+"%' AND DATE(date_create) LIKE '"+date+"' AND active = false ORDER BY date_create";
				}else{
					where = "WHERE n.name LIKE '%"+name+"%' AND n.active = false ORDER BY date_create";
				}
			}else{
				if(!"".equals(date)){
					where = "WHERE DATE(date_create) LIKE '"+date+"' AND n.active = false ORDER BY date_create";
				}else{
					where = "WHERE n.active = false ORDER BY date_create";
				}
			}
		}else{
			if(!"".equals(name)){
				if(!"".equals(date)){
					where = "WHERE n.name LIKE '%"+name+"%' AND DATE(date_create) LIKE '"+date+"' ORDER BY date_create";
				}else{
					where = "WHERE n.name LIKE '%"+name+"%' ORDER BY date_create";
				}
			}else{
				if(!"".equals(date)){
					where = "WHERE DATE(date_create) LIKE '"+date+"' ORDER BY date_create";
				}else{
					where = "ORDER BY date_create";
				}
			}
		}
		
		String query = "SELECT id_news, n.name AS nname, preview, "
				+ "detail, date_create, hotnew, id_user, picture, c.id_cat AS cat_id, "
				+ "c.name AS cname, is_slide, views , n.active AS nactive FROM news AS n INNER JOIN cat_list AS c "
				+ "ON n.id_cat = c.id_cat "+where+" DESC";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				News item = new News(rs.getInt("id_news"),
						rs.getString("nname"), rs.getString("preview"),
						rs.getString("detail"), rs.getTimestamp("date_create"), 
						rs.getBoolean("hotnew"), rs.getInt("id_user"), 
						rs.getString("picture"), rs.getInt("cat_id"), 
						rs.getString("cname"), rs.getBoolean("is_slide"), 
						rs.getInt("views"), rs.getBoolean("nactive"));
				items.add(item);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null && st != null && conn != null) {
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

	public ArrayList<News> getItemsMostViews(int cid) {
		ArrayList<News> items =  new ArrayList<>();
		conn = DatabaseConnection.getConnectMySql();
		String query = "SELECT id_news, n.name AS nname, preview, detail, date_create, hotnew, n.id_user, "
				+ "picture, c.id_cat AS cat_id, c.name AS cname, is_slide, views, n.active AS nactive, "
				+ "username FROM news AS n INNER JOIN cat_list AS c ON n.id_cat = c.id_cat "
				+ "INNER JOIN user AS u ON n.id_user = u.id_user WHERE n.id_cat "
				+ "IN((SELECT id_cat FROM cat_list WHERE id_parent = ? || id_cat = ? && active = true "
				+ "UNION SELECT id_cat FROM cat_list WHERE id_parent IN "
				+ "(SELECT id_cat FROM cat_list WHERE id_parent = ? && active = true) && active = true))"
				+ " && n.active = true && c.active = true ORDER BY views DESC LIMIT 5";
		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, cid);
			pst.setInt(2, cid);
			pst.setInt(3, cid);
			rs = pst.executeQuery();
			while (rs.next()) {
				News item = new News(rs.getInt("id_news"),
						rs.getString("nname"), rs.getString("preview"),
						rs.getString("detail"), rs.getTimestamp("date_create"), 
						rs.getBoolean("hotnew"), rs.getInt("id_user"), 
						rs.getString("picture"), rs.getInt("cat_id"), 
						rs.getString("cname"), rs.getBoolean("is_slide"), 
						rs.getInt("views"), rs.getBoolean("nactive"));
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
	
	public ArrayList<News> getItemsHot(int cid) {
		ArrayList<News> items =  new ArrayList<>();
		conn = DatabaseConnection.getConnectMySql();
		String query = "SELECT id_news, n.name AS nname, preview, detail, date_create, hotnew, n.id_user, picture, c.id_cat AS cat_id, c.name AS cname, is_slide, views, n.active AS nactive, username FROM news AS n INNER JOIN cat_list AS c ON n.id_cat = c.id_cat INNER JOIN user AS u ON n.id_user = u.id_user WHERE n.id_cat IN(SELECT id_cat FROM cat_list WHERE id_parent = ? || id_cat = ? && active = true UNION SELECT id_cat FROM cat_list WHERE id_parent IN (SELECT id_cat FROM cat_list WHERE id_parent = ? & active = true) && active = true) AND hotnew = true AND n.active = true && c.active = true ORDER BY views DESC LIMIT 5";
		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, cid);
			pst.setInt(2, cid);
			pst.setInt(3, cid);
			rs = pst.executeQuery();
			while (rs.next()) {
				News item = new News(rs.getInt("id_news"),
						rs.getString("nname"), rs.getString("preview"),
						rs.getString("detail"), rs.getTimestamp("date_create"), 
						rs.getBoolean("hotnew"), rs.getInt("id_user"), 
						rs.getString("picture"), rs.getInt("cat_id"), 
						rs.getString("cname"), rs.getBoolean("is_slide"), 
						rs.getInt("views"), rs.getBoolean("nactive"));
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

	public ArrayList<News> getAllItemsByIdCat(int cid) {
		ArrayList<News> items =  new ArrayList<>();
		conn = DatabaseConnection.getConnectMySql();
		String query = "SELECT id_news, n.name AS nname, preview, detail, date_create, hotnew, "
				+ "n.id_user, picture, c.id_cat AS cat_id, c.name AS cname, is_slide, views, n.active AS nactive, "
				+ "username FROM news AS n INNER JOIN cat_list AS c ON n.id_cat = c.id_cat "
				+ "INNER JOIN user AS u ON n.id_user = u.id_user "
				+ "WHERE n.id_cat IN(SELECT id_cat FROM cat_list WHERE id_parent = ? || id_cat = ? && active = true "
				+ "UNION SELECT id_cat FROM cat_list WHERE id_parent IN "
				+ "(SELECT id_cat FROM cat_list WHERE id_parent = ? && active = true) && active = true) && n.active = true && c.active = true ORDER BY date_create DESC";
		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, cid);
			pst.setInt(2, cid);
			pst.setInt(3, cid);
			rs = pst.executeQuery();
			while (rs.next()) {
				News item = new News(rs.getInt("id_news"),
						rs.getString("nname"), rs.getString("preview"),
						rs.getString("detail"), rs.getTimestamp("date_create"), 
						rs.getBoolean("hotnew"), rs.getInt("id_user"), 
						rs.getString("picture"), rs.getInt("cat_id"), 
						rs.getString("cname"), rs.getBoolean("is_slide"), 
						rs.getInt("views"), rs.getBoolean("nactive"));
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

	public int editItemActiveSlide(News news, boolean aisSlide) {
		int result = 0;
		conn =  DatabaseConnection.getConnectMySql();
		String sql = "UPDATE news SET is_slide = ? WHERE id_news = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setBoolean(1, aisSlide);
			pst.setInt(2, news.getIdNews());
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

	public ArrayList<News> getItemsHot() {
		ArrayList<News> items = new ArrayList<>();
		conn = DatabaseConnection.getConnectMySql();
		String sql = "SELECT id_news, n.name AS nname, preview,  detail, date_create,hotnew,n.id_user, "
				+ "picture, c.id_cat AS cat_id, c.name AS cname, is_slide, views, n.active AS nactive, username "
				+ "FROM news AS n INNER JOIN cat_list AS c ON n.id_cat = c.id_cat INNER JOIN user AS u "
				+ "ON n.id_user = u.id_user WHERE date_create >= NOW() - INTERVAL 3 DAY && n.active = true && c.active = true ORDER BY views DESC LIMIT 5";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				News item = new News(rs.getInt("id_news"),
						rs.getString("nname"), rs.getString("preview"),
						rs.getString("detail"), rs.getTimestamp("date_create"), 
						rs.getBoolean("hotnew"), rs.getInt("id_user"), 
						rs.getString("picture"), rs.getInt("cat_id"), 
						rs.getString("cname"), rs.getBoolean("is_slide"), 
						rs.getInt("views"), rs.getBoolean("nactive"));
				items.add(item);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (rs != null && st != null && conn != null) {
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

	public ArrayList<News> getItemsScroll(int cid, int start, int limit) {
		limit++;
		ArrayList<News> items =  new ArrayList<>();
		conn = DatabaseConnection.getConnectMySql();
		String query = "SELECT id_news, n.name AS nname, preview, detail, date_create, hotnew, "
				+ "n.id_user, picture, c.id_cat AS cat_id, c.name AS cname, is_slide, views, n.active AS nactive, "
				+ "username FROM news AS n INNER JOIN cat_list AS c ON n.id_cat = c.id_cat "
				+ "INNER JOIN user AS u ON n.id_user = u.id_user "
				+ "WHERE n.id_cat IN(SELECT id_cat FROM cat_list WHERE id_parent = ? || id_cat = ? && active = true "
				+ "UNION SELECT id_cat FROM cat_list WHERE id_parent IN "
				+ "(SELECT id_cat FROM cat_list WHERE id_parent = ? && active = true) && active = true) && n.active = true && c.active = true ORDER BY date_create DESC LIMIT ?,?";
		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, cid);
			pst.setInt(2, cid);
			pst.setInt(3, cid);
			pst.setInt(4, start);
			pst.setInt(5, limit);
			rs = pst.executeQuery();
			while (rs.next()) {
				News item = new News(rs.getInt("id_news"),
						rs.getString("nname"), rs.getString("preview"),
						rs.getString("detail"), rs.getTimestamp("date_create"), 
						rs.getBoolean("hotnew"), rs.getInt("id_user"), 
						rs.getString("picture"), rs.getInt("cat_id"), 
						rs.getString("cname"), rs.getBoolean("is_slide"), 
						rs.getInt("views"), rs.getBoolean("nactive"));
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

	public ArrayList<News> getItemsByIdCat(int cid) {
		ArrayList<News> items =  new ArrayList<>();
		conn = DatabaseConnection.getConnectMySql();
		String query = "SELECT id_news, n.name AS nname, preview, detail, date_create, hotnew, "
				+ "n.id_user, picture, c.id_cat AS cat_id, c.name AS cname, is_slide, views, n.active AS nactive, "
				+ "username FROM news AS n INNER JOIN cat_list AS c ON n.id_cat = c.id_cat "
				+ "INNER JOIN user AS u ON n.id_user = u.id_user "
				+ "WHERE n.id_cat IN(SELECT id_cat FROM cat_list WHERE id_parent = ? || id_cat = ? && active = true "
				+ "UNION SELECT id_cat FROM cat_list WHERE id_parent IN "
				+ "(SELECT id_cat FROM cat_list WHERE id_parent = ? && active = true) && active = true) && n.active = true && c.active = true ORDER BY date_create DESC LIMIT 5";
		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, cid);
			pst.setInt(2, cid);
			pst.setInt(3, cid);
			rs = pst.executeQuery();
			while (rs.next()) {
				News item = new News(rs.getInt("id_news"),
						rs.getString("nname"), rs.getString("preview"),
						rs.getString("detail"), rs.getTimestamp("date_create"), 
						rs.getBoolean("hotnew"), rs.getInt("id_user"), 
						rs.getString("picture"), rs.getInt("cat_id"), 
						rs.getString("cname"), rs.getBoolean("is_slide"), 
						rs.getInt("views"), rs.getBoolean("nactive"));
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

	public ArrayList<News> getTinLienQuan(int cid, int nid) {
		ArrayList<News> items =  new ArrayList<>();
		conn = DatabaseConnection.getConnectMySql();
		String query = "SELECT id_news, n.name AS nname, preview, detail, date_create, hotnew, "
				+ "n.id_user, picture, c.id_cat AS cat_id, c.name AS cname, is_slide, views, n.active AS nactive, "
				+ "username FROM news AS n INNER JOIN cat_list AS c ON n.id_cat = c.id_cat "
				+ "INNER JOIN user AS u ON n.id_user = u.id_user "
				+ "WHERE id_news != ? && c.id_cat = ? && n.active = true ORDER BY date_create DESC LIMIT 3";
		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, nid);
			pst.setInt(2, cid);
			rs = pst.executeQuery();
			while (rs.next()) {
				News item = new News(rs.getInt("id_news"),
						rs.getString("nname"), rs.getString("preview"),
						rs.getString("detail"), rs.getTimestamp("date_create"), 
						rs.getBoolean("hotnew"), rs.getInt("id_user"), 
						rs.getString("picture"), rs.getInt("cat_id"), 
						rs.getString("cname"), rs.getBoolean("is_slide"), 
						rs.getInt("views"), rs.getBoolean("nactive"));
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

}
