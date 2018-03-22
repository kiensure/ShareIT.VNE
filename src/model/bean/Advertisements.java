package model.bean;

public class Advertisements {
	private int idAds;
	private int location;
	private String nameAds;
	private String picture;
	private String link;

	public Advertisements() {
		// TODO Auto-generated constructor stub
	}

	public Advertisements(int idAds, int location, String nameAds,
			String picture, String link) {
		super();
		this.idAds = idAds;
		this.location = location;
		this.nameAds = nameAds;
		this.picture = picture;
		this.link = link;
	}

	public int getIdAds() {
		return idAds;
	}

	public void setIdAds(int idAds) {
		this.idAds = idAds;
	}

	public String getNameAds() {
		return nameAds;
	}

	public void setNameAds(String nameAds) {
		this.nameAds = nameAds;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}
	
	
}
