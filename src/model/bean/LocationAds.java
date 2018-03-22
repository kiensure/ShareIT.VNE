package model.bean;

public class LocationAds {
	private int idLocal;
	private String name;
	public LocationAds(int idLocal, String name) {
		super();
		this.idLocal = idLocal;
		this.name = name;
	}
	public int getIdLocal() {
		return idLocal;
	}
	public void setIdLocal(int idLocal) {
		this.idLocal = idLocal;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
