package FTH.Test;


import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import FTH.Abstractcomponent.AbstractComponent;
import FTH.BaseComponent.BaseClass;
import FTH.PageObject.SimpleOrderStockTransfer;

public class SimpleOrderStockTransferCreate extends BaseClass{

	
	SimpleOrderStockTransfer simpleOrderStockTransfer ;
	JSONObject data ;
	AbstractComponent abstractComponent ;
	double calculatedCartTotal ;
	
	@Test
	public void test_TC001_stock_transfer_menu_displayed() throws IOException
	{
		simpleOrderStockTransfer = new SimpleOrderStockTransfer(driver);
		simpleOrderStockTransfer.login();
		abstractComponent = new AbstractComponent(driver);
		data = abstractComponent.getJsonObject(System.getProperty("user.dir")+"\\src\\test\\java\\FTH\\Data\\SimpleOrderStockTransfer.json");
		simpleOrderStockTransfer.selectSimpleOrderStockTransfer();
		Assert.assertTrue(simpleOrderStockTransfer.stockTransfer.isDisplayed());
		
	}
	
	@Test
	public void test_TC002_stock_transfer_page_is_opened()
	{
		
		Assert.assertTrue(simpleOrderStockTransfer.getstockTransferText());
	}
	
	@Test
	public void test_TC003_select_source_store() throws IOException
	{
		String selectedStore = simpleOrderStockTransfer.selectStore();
		String expectedStore = data.getString("source");
		Assert.assertEquals(selectedStore, expectedStore);
	}
	
	@Test
	public void test_TC004_destination_store_listing() throws IOException
	{
		JSONArray storeList = data.getJSONArray("storelist");
		int actualCount = simpleOrderStockTransfer.checkDestinationStore(storeList);
		int expectedCount = storeList.length();
		Assert.assertEquals(actualCount, expectedCount);
		
	}
	
	@Test
	public void test_TC005_select_destination_store() 
	{
		String actualDestinationHub = simpleOrderStockTransfer.selectDestination();
		String expectedDestinationHub = data.getString("destination");
		Assert.assertEquals(actualDestinationHub.trim(),expectedDestinationHub.trim());
	}
	
	@Test
	public void test_TC006_internal_warehouse_and_warehouse_displayed()
	{
		Assert.assertTrue(simpleOrderStockTransfer.getInternalWareHouseandWareHouse());
	}
	
	@Test
	public void test_TC007_products_are_getting_listed()
	{
	  int count = simpleOrderStockTransfer.getProductsList();
	  Assert.assertTrue(count>5);
	}

	@Test
	public void test_TC008_products_search()
	{
		
		JSONArray productsID = data.getJSONArray("products");
		JSONArray actualProductID = (JSONArray) simpleOrderStockTransfer.productSearch(productsID);
		Assert.assertEquals(productsID, actualProductID);
	}
	
	
	@Test
	public void test_TC009_bulk_add() throws InterruptedException
	{
		
		JSONArray bulkaddproducts = data.getJSONArray("bulkaddproducts");
		JSONArray products = data.getJSONArray("products");
		int count = (int) simpleOrderStockTransfer.bulkAddProducts(bulkaddproducts);
		int actualcount = bulkaddproducts.length() +  products.length() ;
		Assert.assertEquals(count, actualcount);
	}
	
	@Test
	public void test_TC010_cart_total() throws InterruptedException
	{
		calculatedCartTotal = simpleOrderStockTransfer.calculateCartTotal();
		double actualCartTotal = Double.parseDouble(simpleOrderStockTransfer.getCartTotal());
		Assert.assertEquals(calculatedCartTotal, actualCartTotal);
	}
	
	
	@Test
	public void test_TC011_procced_to_checkout()
	{
		String actualText = simpleOrderStockTransfer.proceedtoCheckout();
		Assert.assertEquals(actualText, "Review");
		
	}
	
	@Test
	public void test_TC012_subtotal_total()
	{
		double subtotal = simpleOrderStockTransfer.getSubTotal();
		Assert.assertEquals(subtotal, calculatedCartTotal);
	}

	@Test
	public void test_TC013_order_create()
	{
		Assert.assertTrue(simpleOrderStockTransfer.placeOrder());
	}
	

	

}



