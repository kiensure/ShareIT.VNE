package model.bean;

public class UserType {
	private int idType;
	private String nameType;
	
	public UserType() {
		// TODO Auto-generated constructor stub
	}

	public UserType(int idType, String nameType) {
		super();
		this.idType = idType;
		this.nameType = nameType;
	}

	public int getIdType() {
		return idType;
	}

	public void setIdType(int idType) {
		this.idType = idType;
	}

	public String getNameType() {
		return nameType;
	}

	public void setNameType(String nameType) {
		this.nameType = nameType;
	}
	
	
}
