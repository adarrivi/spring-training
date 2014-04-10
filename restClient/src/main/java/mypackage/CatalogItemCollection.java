package mypackage;

import java.util.Collection;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="items")
public class CatalogItemCollection {

	private Collection<CatalogItem> items;
	
	public CatalogItemCollection() 
	{}
	
	public CatalogItemCollection(Collection<CatalogItem> items) {
		this.items = items;
	}
	
	public Collection<CatalogItem> getCatalogItems() {
		return items;
	}

	public void setCatalogItems(Collection<CatalogItem> items) {
		this.items = items;
	}
}
