package model.bean;

public class Category {
	private int idCat;
	private String name;
	private int idParent;
	private int displayIndex;
	private boolean active;
	
	public Category() {
		// TODO Auto-generated constructor stub
	}

	public Category(int idCat, String name, int idParent, int displayIndex,
			boolean active) {
		super();
		this.idCat = idCat;
		this.name = name;
		this.idParent = idParent;
		this.displayIndex = displayIndex;
		this.active = active;
	}

	public int getIdCat() {
		return idCat;
	}

	public void setIdCat(int idCat) {
		this.idCat = idCat;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIdParent() {
		return idParent;
	}

	public void setIdParent(int idParent) {
		this.idParent = idParent;
	}

	public int getDisplayIndex() {
		return displayIndex;
	}

	public void setDisplayIndex(int displayIndex) {
		this.displayIndex = displayIndex;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
}
