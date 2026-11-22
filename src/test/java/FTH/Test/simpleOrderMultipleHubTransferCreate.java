package FTH.Test;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import FTH.Abstractcomponent.AbstractComponent;
import FTH.BaseComponent.BaseClass;
import FTH.PageObject.SimpleOrderMultipleHubTransfer;

public class simpleOrderMultipleHubTransferCreate extends BaseClass {
	
	SimpleOrderMultipleHubTransfer simpleOrderMultipleHubTransfer ; 
	AbstractComponent abstractcomponet;
	double calculatedCartTotal;
	JSONObject data ;
	
	
	@Test
	public void test_TC001_simpleordercreate_hubtransfer_menu_displayed() throws IOException
	{
		simpleOrderMultipleHubTransfer = new SimpleOrderMultipleHubTransfer(driver);
		simpleOrderMultipleHubTransfer.login();
		abstractcomponet = new AbstractComponent(driver);
		data=  abstractcomponet.getJsonObject(System.getProperty("user.dir")+"\\src\\test\\java\\FTH\\Data\\simpleOrderMultipleHubTransfer.json");
		simpleOrderMultipleHubTransfer.selectSimpleOrderhubTransfer();
		Assert.assertTrue(simpleOrderMultipleHubTransfer.hubTransferOrderMenu.isDisplayed());
	}
	
    @Test
    public void test_TC002_hubtransfer_page_opened()
    {
    	Assert.assertTrue(simpleOrderMultipleHubTransfer.getHubTransferText());
    }
	
	
	@Test
	public void test_TC003_select_source_store() throws IOException
	{
		String selectedStore = simpleOrderMultipleHubTransfer.selectStore();
		String expectedStore = data.getString("source");
		Assert.assertEquals(selectedStore, expectedStore);
	}
	
	
	@Test
	public void test_TC004_multiple_hub_transfer_option_displayed()
	{
		Assert.assertTrue(simpleOrderMultipleHubTransfer.multipleHubTransferDisplayed());
	}
	
	
	@Test
	public void test_TC005_select_all_hub_store_option_displayed()
	{
		Assert.assertTrue(simpleOrderMultipleHubTransfer.selectAllHubDisplayed());
	}

	@Test
	public void test_TC006_select_destination_store() 
	{
		 Assert.assertTrue(simpleOrderMultipleHubTransfer.selectDestination());
	}
	
	@Test
	public void test_TC007_view_customer_address_is_displayed()
	{
		Assert.assertTrue(simpleOrderMultipleHubTransfer.viewCustomerAddressDisplayed());
	}
	

	@Test
	public void test_TC008_customer_address_popup_opened()
	{
		
		Assert.assertTrue(simpleOrderMultipleHubTransfer.customerAddressPopUpOpened());
	}
	
	
	@Test
	public void test_TC009_billing_shipping_address_displayed_in_customer_address_popup()
	{
		Assert.assertTrue(simpleOrderMultipleHubTransfer.internalWarehouseAddressDisplayed());
		Assert.assertTrue(simpleOrderMultipleHubTransfer.warehouseAddressDisplayed());
	}
	
	@Test
	public void test_TC010_products_are_getting_listed()
	{
	  int count = simpleOrderMultipleHubTransfer.getProductsList();
	  Assert.assertTrue(count>5);
	}

	@Test
	public void test_TC011_products_search()
	{
		
		JSONArray productsID = data.getJSONArray("products");
		JSONArray actualProductID = (JSONArray) simpleOrderMultipleHubTransfer.productSearch(productsID);
		Assert.assertEquals(productsID, actualProductID);
	}
	
	
	@Test
	public void test_TC012_bulk_add() throws InterruptedException
	{
		
		JSONArray bulkaddproducts = data.getJSONArray("bulkaddproducts");
		JSONArray products = data.getJSONArray("products");
		int count = (int) simpleOrderMultipleHubTransfer.bulkAddProducts(bulkaddproducts);
		int actualcount = bulkaddproducts.length() +  products.length() ;
		Assert.assertEquals(count, actualcount);
	}
	
	
	@Test
	public void test_TC013_cart_created_for_selected_store()
	{
		Assert.assertTrue(simpleOrderMultipleHubTransfer.cartCreatedForSelectedStore());
		Assert.assertTrue(simpleOrderMultipleHubTransfer.viewCartDisplayed());
		
	}
	@Test
	public void test_TC014_products_displayed_in_cart()
	{
		Assert.assertTrue(simpleOrderMultipleHubTransfer.checkViewCart());
	}
	@Test
	public void test_TC015_proceed_to_checkout()
	{
		Assert.assertTrue(simpleOrderMultipleHubTransfer.proceedtoCheckout());
	}
	@Test
	public void test_TC016_place_order()
	{
		Assert.assertTrue(simpleOrderMultipleHubTransfer.placeOrder());
	}
	

}
