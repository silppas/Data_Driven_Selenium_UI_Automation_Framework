package FTH.Test;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import FTH.Abstractcomponent.AbstractComponent;
import FTH.BaseComponent.BaseClass;
import FTH.PageObject.SimpleOrderCreate;
import FTH.Util.RetryAnalyzer;

public class SimpleOrderCustomerCreate extends BaseClass{
	
	SimpleOrderCreate simpleordercreate ;
	AbstractComponent abstractcomponet ;
	double calculatedCartTotal;
	JSONObject data;
	
	@Test(retryAnalyzer = RetryAnalyzer.class)
	public void test_TC001_SimpleOrderCreateCustomerOrderMenu_is_displayed() throws IOException
	{
		
		simpleordercreate = new SimpleOrderCreate(driver);
		simpleordercreate.login();
		simpleordercreate.selectSimpleOrderCreate();
		String expected_text = simpleordercreate.getNewOrderText();
		String actual_text = "Creating new order";
		Assert.assertEquals(expected_text, actual_text);
		
		
	}
	
	@Test
	public void test_TC002_existing_customer_details() throws IOException, InterruptedException
	{
		String expectedResult = simpleordercreate.getExistingCustomer();
		abstractcomponet = new AbstractComponent(driver);
		data = abstractcomponet.getJsonObject(System.getProperty("user.dir")+"\\src\\test\\java\\FTH\\Data\\SimpleOrderCreateTestData.json");
		//data = abstractcomponet.getJsonObject(System.getProperty("user.dir")+"\\src\\test\\java\\FTH\\Data\\SimpleOrderCreateTestDataUAE.json.json");
		String actualResult = data.getString("customerid");
		Assert.assertEquals(expectedResult, actualResult);
		
	}
	
	@Test
	public void test_TC003_existing_customer_address_is_displayed() throws InterruptedException
	{
		simpleordercreate.selectCustomer();
		int count = simpleordercreate.getExistingCustomerAddress();
		Assert.assertTrue(count>2);
	}
	
	@Test(retryAnalyzer = RetryAnalyzer.class)
	public void test_TC004_stores_are_getting_listed() throws InterruptedException
	{
		simpleordercreate.selectCustomerAddress();
		int count = simpleordercreate.checkStoreList();
		simpleordercreate.selectStore();
		Assert.assertEquals(count, 2);
	}
	
	@Test
	public void test_TC005_products_are_getting_listed()
	{
	  int count = simpleordercreate.getProductsList();
	  Assert.assertTrue(count>5);
	}

	@Test
	public void test_TC006_products_search()
	{
		
		JSONArray productsID = data.getJSONArray("products");
		JSONArray actualProductID = (JSONArray) simpleordercreate.productSearch(productsID);
		Assert.assertEquals(productsID, actualProductID);
	}
	
	
	@Test
	public void test_TC007_bulk_add() throws InterruptedException
	{
		
		JSONArray bulkaddproducts = data.getJSONArray("bulkaddproducts");
		JSONArray products = data.getJSONArray("products");
		int count = (int) simpleordercreate.bulkAddProducts(bulkaddproducts);
		int actualcount = bulkaddproducts.length() +  products.length() ;
		Assert.assertEquals(count, actualcount);
	}
	
	@Test
	public void test_TC008_cart_total() throws InterruptedException
	{
		if (server.equals("qa1") || server.equals("qa2") )
		{
			return ;
		}
		else 
		{
		calculatedCartTotal = simpleordercreate.calculateCartTotal();
		double actualCartTotal = Double.parseDouble(simpleordercreate.getCartTotal());
		Assert.assertEquals(calculatedCartTotal, actualCartTotal);
		}
	}
	
	
	@Test
	public void test_TC009_procced_to_checkout()
	{
		String actualText = simpleordercreate.proceedtoCheckout();
		Assert.assertEquals(actualText, "Review");
		
	}
	
	@Test
	public void test_TC010_subtotal_total()
	{
		if (server.equals("qa1")|| server.equals("qa2"))
		{
			return ;
		}
		else 
		{
		Assert.assertEquals(simpleordercreate.getSubTotal(), calculatedCartTotal);
		}
	}
	@Test
	public void test_TC011_order_create()
	{
		Assert.assertTrue(simpleordercreate.placeOrder());
	}
	

	
	
}
