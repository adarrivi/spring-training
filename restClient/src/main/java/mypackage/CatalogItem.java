package mypackage;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="mypackage.CatalogItem")
public class CatalogItem {

	private int id;
	private String who;
	private String what;
	
	public CatalogItem() {
	}
	
	public CatalogItem(String who, String what) {
		this.who = who;
		this.what = what;
	}
	
	public void Update(CatalogItem item) {
		if (item != null) {
			this.who  = item.who;
			this.what = item.what;
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getWho() {
		return who;
	}

	public void setWho(String who) {
		this.who = who;
	}

	public String getWhat() {
		return what;
	}

	public void setWhat(String what) {
		this.what = what;
	}
	
	@Override
	public String toString() {
		return "CatalogItem [id=" + id + ", what=" + what + ", who=" + who
				+ "]";
	}
}
