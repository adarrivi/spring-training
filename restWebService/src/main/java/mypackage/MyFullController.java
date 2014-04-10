package mypackage;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping(value="/itemManager")
public class MyFullController {

	@Autowired
	private ItemService service;
	
	
	// Get all items.
	@RequestMapping(method=RequestMethod.GET, 
			        value="/items", 
			        headers="Accept=application/json, application/xml, text/plain")
	public @ResponseBody CatalogItemCollection getItems() {
		Collection<CatalogItem> items = service.getItems();
		return new CatalogItemCollection(items);
	}


	// Get a specific item.
	@RequestMapping(method=RequestMethod.GET, 
			        value="/item/{id}", 
			        headers="Accept=application/json, application/xml, text/plain")
	public CatalogItem getItem(@PathVariable int id) {
		CatalogItem ret = service.getItem(id);
		return ret;
	}

	// Insert a new item.
	@RequestMapping(method=RequestMethod.POST, 
			        value="/item", 
			        headers={"Content-Type=application/json, application/xml", "Accept=application/json, application/xml" })
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody CatalogItem addItem(@RequestBody CatalogItem item) {
		service.insert(item);
		return item;
	}


	// Update an existing item.
	@RequestMapping(method=RequestMethod.PUT, 
			        value="/item", 
			        headers={"Content-Type=application/json, application/xml", "Accept=*/*" })
	public @ResponseBody void modifyItem(@RequestBody CatalogItem item) {
		System.out.println("Modifying item to " + item);
		service.update(item);
	}


	// Delete an existing item.
	@RequestMapping(method=RequestMethod.DELETE, 
			        value="/item/{id}",
			        headers="Accept=*/*")
	public @ResponseBody void deleteItem(@PathVariable int id) {
		service.delete(id);
	}
}
