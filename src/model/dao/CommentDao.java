package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import library.DatabaseConnection;
import model.bean.Comment;

public class CommentDao {
	private Connection conn;
	private PreparedStatement pst;
	private ResultSet rs;
	private Statement st;
	
	public ArrayList<Comment> getItems() {
		ArrayList<Comment> items = new ArrayList<>();
		conn = DatabaseConnection.getConnectMySql();
		String sql = "SELECT id_comment, c.name, email, website, content, c.id_user, c.date_create, c.id_parent, "
				+ "c.id_news, c.active FROM comment AS c INNER JOIN news AS n ON c.id_news = n.id_news ORDER BY id_comment DESC";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Comment item = new Comment(rs.getInt("id_comment"), rs.getString("c.name"), rs.getString("email"), rs.getString("website"), rs.getString("content"), rs.getInt("c.id_user"), rs.getTimestamp("c.date_create"), rs.getInt("c.id_parent"), rs.getInt("c.id_news"), rs.getBoolean("c.active"));
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

	public int editItemByStatus(Comment cmt, boolean status) {
		int result = 0;
		conn =  DatabaseConnection.getConnectMySql();
		String sql = "UPDATE comment SET active = ? WHERE id_comment = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setBoolean(1, status);
			pst.setInt(2, cmt.getIdComment());
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

	public Comment getItem(int cmtID) {
		Comment item = null;
		conn = DatabaseConnection.getConnectMySql();
		String query = "SELECT id_comment, c.name, email, website, content, c.id_user, c.date_create, c.id_parent, c.id_news, c.active FROM comment AS c INNER JOIN news AS n ON c.id_news = n.id_news WHERE id_comment = ?";
		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, cmtID);
			rs = pst.executeQuery();
			if (rs.next()) {
				item = new Comment(rs.getInt("id_comment"), rs.getString("c.name"), rs.getString("email"), rs.getString("website"), rs.getString("content"), rs.getInt("c.id_user"), rs.getTimestamp("c.date_create"), rs.getInt("c.id_parent"), rs.getInt("c.id_news"), rs.getBoolean("c.active"));
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

	public int delItem(int cmtID) {
		int result = 0;
		conn = DatabaseConnection.getConnectMySql();
		String sql = "DELETE FROM comment WHERE id_comment = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, cmtID);
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

	public ArrayList<Comment> getItemsParent() {
		ArrayList<Comment> items = new ArrayList<>();
		conn = DatabaseConnection.getConnectMySql();
		String sql = "SELECT id_comment, c.name, email, website, content, c.id_user, c.date_create, c.id_parent, c.id_news, c.active FROM comment AS c INNER JOIN news AS n ON c.id_news = n.id_news WHERE c.id_parent = 0 AND c.active = true";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Comment item = new Comment(rs.getInt("id_comment"), rs.getString("c.name"), rs.getString("email"), rs.getString("website"), rs.getString("content"), rs.getInt("c.id_user"), rs.getTimestamp("c.date_create"), rs.getInt("c.id_parent"), rs.getInt("c.id_news"), rs.getBoolean("c.active"));
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

	public int countCmtByNews(int nid) {
		int result = 0;
		conn = DatabaseConnection.getConnectMySql();
		String sql = "SELECT COUNT(*) AS countCmt FROM comment WHERE id_news ="+nid+" AND active = true";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				result = rs.getInt("countCmt");
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

	public ArrayList<Comment> getItemsParentByIdNews(int idNews) {
		ArrayList<Comment> items = new ArrayList<>();
		conn = DatabaseConnection.getConnectMySql();
		String sql = "SELECT id_comment, c.name, email, website, content, c.id_user, c.date_create, "
				+ "c.id_parent, c.id_news, c.active FROM comment AS c INNER JOIN news AS n "
				+ "ON c.id_news = n.id_news WHERE c.id_news ="+idNews+" AND c.id_parent = 0 AND c.active = true";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Comment item = new Comment(rs.getInt("id_comment"), rs.getString("c.name"), rs.getString("email"), rs.getString("website"), rs.getString("content"), rs.getInt("c.id_user"), rs.getTimestamp("c.date_create"), rs.getInt("c.id_parent"), rs.getInt("c.id_news"), rs.getBoolean("c.active"));
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

	public ArrayList<Comment> getItemsByIdNews(int nid) {
		ArrayList<Comment> items = new ArrayList<>();
		conn = DatabaseConnection.getConnectMySql();
		String sql = "SELECT id_comment, c.name, email, website, content, c.id_user, c.date_create, c.id_parent, c.id_news, c.active FROM comment AS c INNER JOIN news AS n ON c.id_news = n.id_news WHERE c.id_news ="+nid+" AND c.active = true";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Comment item = new Comment(rs.getInt("id_comment"), rs.getString("c.name"), rs.getString("email"), rs.getString("website"), rs.getString("content"), rs.getInt("c.id_user"), rs.getTimestamp("c.date_create"), rs.getInt("c.id_parent"), rs.getInt("c.id_news"), rs.getBoolean("c.active"));
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

	public int addItem(Comment objCmt) {
		int result = 0;
		conn = DatabaseConnection.getConnectMySql();
		String sql = "INSERT INTO comment (id_comment,name,email,website,content,id_user,date_create,id_parent,id_news,active) VALUES(?,?,?,?,?,?,?,?,?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, objCmt.getIdComment());
			pst.setString(2, objCmt.getFullName());
			pst.setString(3, objCmt.getEmail());
			pst.setString(4, objCmt.getWebsite());
			pst.setString(5, objCmt.getContent());
			pst.setInt(6, objCmt.getIdUser());
			pst.setTimestamp(7, objCmt.getDate_Create());
			pst.setInt(8, objCmt.getIdParent());
			pst.setInt(9, objCmt.getIdNews());
			pst.setBoolean(10, objCmt.isActive());
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
	
}
