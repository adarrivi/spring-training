package mypackage;

import java.util.Collection;

public interface ItemService {

	CatalogItem getItem(int id);
	Collection<CatalogItem> getItems();

	void insert(CatalogItem item);
	void update(CatalogItem item);
	void delete(int id);
}
