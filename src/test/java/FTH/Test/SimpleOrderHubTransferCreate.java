package FTH.Test;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import FTH.Abstractcomponent.AbstractComponent;
import FTH.BaseComponent.BaseClass;
import FTH.PageObject.SimpleOrderHubTransfer;

public class SimpleOrderHubTransferCreate extends BaseClass {
	
	SimpleOrderHubTransfer simpleOrderHubTransfer ; 
	AbstractComponent abstractcomponet;
	double calculatedCartTotal;
	JSONObject data ;
	
	
	@Test
	public void test_TC001_simpleordercreate_hubtransfer_menu_displayed() throws IOException
	{
		simpleOrderHubTransfer = new SimpleOrderHubTransfer(driver);
		simpleOrderHubTransfer.login();
		abstractcomponet = new AbstractComponent(driver);
		data=  abstractcomponet.getJsonObject(System.getProperty("user.dir")+"\\src\\test\\java\\FTH\\Data\\\\SimpleOrderHubTransfer.json");
		simpleOrderHubTransfer.selectSimpleOrderhubTransfer();
		Assert.assertTrue(simpleOrderHubTransfer.hubTransferOrderMenu.isDisplayed());
	}
	
    @Test
    public void test_TC002_hubtransfer_page_opened()
    {
    	Assert.assertTrue(simpleOrderHubTransfer.getHubTransferText());
    }
	
	
	@Test
	public void test_TC003_select_source_store() throws IOException
	{
		String selectedStore = simpleOrderHubTransfer.selectStore();
		String expectedStore = data.getString("source");
		Assert.assertEquals(selectedStore, expectedStore);
	}
	
	@Test
	public void test_TC004_destination_store_listing() throws IOException
	{
		JSONArray storeList = data.getJSONArray("storelist");
		int actualCount = simpleOrderHubTransfer.checkDestinationStore(storeList);
		int expectedCount = storeList.length();
		Assert.assertEquals(actualCount, 2);
		
	}
	
	@Test
	public void test_TC005_select_destination_store() 
	{
		String actualDestinationHub = simpleOrderHubTransfer.selectDestination();
		String expectedDestinationHub = data.getString("destination");
		Assert.assertEquals(actualDestinationHub.trim(),expectedDestinationHub.trim());
	}
	
	@Test
	public void test_TC006_internal_warehouse_and_warehouse_displayed()
	{
		Assert.assertTrue(simpleOrderHubTransfer.getInternalWareHouseandWareHouse());
	}
	
	@Test
	public void test_TC007_products_are_getting_listed()
	{
	  int count = simpleOrderHubTransfer.getProductsList();
	  Assert.assertTrue(count>5);
	}

	@Test
	public void test_TC008_products_search()
	{
		
		JSONArray productsID = data.getJSONArray("products");
		JSONArray actualProductID = (JSONArray) simpleOrderHubTransfer.productSearch(productsID);
		Assert.assertEquals(productsID, actualProductID);
	}
	
	
	@Test
	public void test_TC009_bulk_add() throws InterruptedException
	{
		
		JSONArray bulkaddproducts = data.getJSONArray("bulkaddproducts");
		JSONArray products = data.getJSONArray("products");
		int count = (int) simpleOrderHubTransfer.bulkAddProducts(bulkaddproducts);
		int actualcount = bulkaddproducts.length() +  products.length() ;
		Assert.assertEquals(count, actualcount);
	}
	
	@Test
	public void test_TC010_cart_total() throws InterruptedException
	{
		calculatedCartTotal = simpleOrderHubTransfer.calculateCartTotal();
		double actualCartTotal = Double.parseDouble(simpleOrderHubTransfer.getCartTotal());
		Assert.assertEquals(calculatedCartTotal, actualCartTotal);
	}
	
	
	@Test
	public void test_TC011_procced_to_checkout()
	{
		String actualText = simpleOrderHubTransfer.proceedtoCheckout();
		Assert.assertEquals(actualText, "Review");
		
	}
	
	@Test
	public void test_TC012_subtotal_total()
	{
		double subtotal = simpleOrderHubTransfer.getSubTotal();
		Assert.assertEquals(subtotal, calculatedCartTotal);
	}

	@Test
	public void test_TC013_order_create()
	{
		Assert.assertTrue(simpleOrderHubTransfer.placeOrder());
	}
	

	

}
