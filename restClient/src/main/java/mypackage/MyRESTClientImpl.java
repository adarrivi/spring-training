package mypackage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component("MyRESTClient")
public class MyRESTClientImpl implements MyRESTClient {

	@Autowired
	private RestTemplate restTemplate;
	
	private String baseAddress = "http://localhost:8080/DemoRESTServices/services/itemManager";
	
	@Override
	public void doRESTCalls() {
	
		// GET an item (unmarshalled via JAXB).
		CatalogItem item2 = restTemplate.getForObject(baseAddress + "/item/2", CatalogItem.class);
		System.out.println("CatalogItem 2:\t" + item2);
	
		// GET ALL ITEMS and display them.
		System.out.println("All CatalogItems:");
		CatalogItemCollection coll = restTemplate.getForObject(baseAddress + "/items", CatalogItemCollection.class);
		for (CatalogItem item : coll.getCatalogItems()) {
			System.out.println("\tCatalogItem as obj:\t" + item);
		}
		
		// DELETE an item.
		restTemplate.delete(baseAddress + "/item/2");
		System.out.println("Deleted item 2");
	}
}
