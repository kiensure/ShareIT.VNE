package model.bean;

public class User {
	private int idUser;
	private String name;
	private String password;
	private String fullname;
	private String email;
	private int idTypeUser;
	private String typeUser;
	private boolean active;
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(int idUser, String name, String password, String fullname,
			String email, int idTypeUser, String typeUser, boolean active) {
		super();
		this.idUser = idUser;
		this.name = name;
		this.password = password;
		this.fullname = fullname;
		this.email = email;
		this.idTypeUser = idTypeUser;
		this.typeUser = typeUser;
		this.active = active;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getIdTypeUser() {
		return idTypeUser;
	}

	public void setIdTypeUser(int idTypeUser) {
		this.idTypeUser = idTypeUser;
	}

	public String getTypeUser() {
		return typeUser;
	}

	public void setTypeUser(String typeUser) {
		this.typeUser = typeUser;
	}


}
