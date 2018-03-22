package model.bean;

import java.sql.Timestamp;

public class Contact {
	private int idContact;
	private String name;
	private String phone;
	private String email;
	private Timestamp date;
	private String content;
	private boolean status;
	
	public Contact() {
		// TODO Auto-generated constructor stub
	}
	
	public Contact(int idContact, String name, String phone, String email,
			Timestamp date, String content, boolean status) {
		super();
		this.idContact = idContact;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.date = date;
		this.content = content;
		this.status = status;
	}

	public int getIdContact() {
		return idContact;
	}

	public void setIdContact(int idContact) {
		this.idContact = idContact;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
