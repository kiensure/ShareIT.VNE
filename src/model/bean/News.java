package model.bean;

import java.sql.Timestamp;

public class News {
	private int idNews;
	private String name;
	private String preview;
	private String detail;
	private Timestamp date_Create;
	private boolean hotNew;
	private int idUser;
	private String picture;
	private int idCat;
	private String catName;
	private boolean is_Slide;
	private int views;
	private boolean active;
	
	public News() {
		// TODO Auto-generated constructor stub
	}

	public News(int idNews, String name, String preview, String detail,
			Timestamp date_Create, boolean hotNew, int idUser, String picture,
			int idCat, String catName, boolean is_Slide, int views,
			boolean active) {
		super();
		this.idNews = idNews;
		this.name = name;
		this.preview = preview;
		this.detail = detail;
		this.date_Create = date_Create;
		this.hotNew = hotNew;
		this.idUser = idUser;
		this.picture = picture;
		this.idCat = idCat;
		this.catName = catName;
		this.is_Slide = is_Slide;
		this.views = views;
		this.active = active;
	}

	public int getIdNews() {
		return idNews;
	}

	public void setIdNews(int idNews) {
		this.idNews = idNews;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPreview() {
		return preview;
	}

	public void setPreview(String preview) {
		this.preview = preview;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Timestamp getDate_Create() {
		return date_Create;
	}

	public void setDate_Create(Timestamp date_Create) {
		this.date_Create = date_Create;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public int getIdCat() {
		return idCat;
	}

	public void setIdCat(int idCat) {
		this.idCat = idCat;
	}

	public boolean isIs_Slide() {
		return is_Slide;
	}

	public void setIs_Slide(boolean is_Slide) {
		this.is_Slide = is_Slide;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public boolean isHotNew() {
		return hotNew;
	}

	public void setHotNew(boolean hotNew) {
		this.hotNew = hotNew;
	}
	
}
