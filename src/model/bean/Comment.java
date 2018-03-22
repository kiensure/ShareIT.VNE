package model.bean;

import java.sql.Timestamp;

public class Comment {
	private int idComment;
	private String fullName;
	private String email;
	private String website;
	private String content;
	private int idUser;
	private Timestamp date_Create;
	private int idParent;
	private int idNews;
	private boolean active;
	
	public Comment() {
		// TODO Auto-generated constructor stub
	}

	public Comment(int idComment, String fullName, String email,
			String website, String content, int idUser, Timestamp date_Create,
			int idParent, int idNews, boolean active) {
		super();
		this.idComment = idComment;
		this.fullName = fullName;
		this.email = email;
		this.website = website;
		this.content = content;
		this.idUser = idUser;
		this.date_Create = date_Create;
		this.idParent = idParent;
		this.idNews = idNews;
		this.active = active;
	}

	public int getIdComment() {
		return idComment;
	}

	public void setIdComment(int idComment) {
		this.idComment = idComment;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public Timestamp getDate_Create() {
		return date_Create;
	}

	public void setDate_Create(Timestamp date_Create) {
		this.date_Create = date_Create;
	}

	public int getIdParent() {
		return idParent;
	}

	public void setIdParent(int idParent) {
		this.idParent = idParent;
	}

	public int getIdNews() {
		return idNews;
	}

	public void setIdNews(int idNews) {
		this.idNews = idNews;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}
	
}
