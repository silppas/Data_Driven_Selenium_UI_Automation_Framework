package FTH.Test;

import java.io.IOException;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import FTH.BaseComponent.BaseClass;
import FTH.PageObject.StockTransfer;
import FTH.Util.RetryAnalyzer;

public class StockMovement extends BaseClass {

	StockTransfer stockTransfer ;
	JSONObject data ;
	
	@Test(retryAnalyzer =RetryAnalyzer.class)
	public void test_TC001_stockmovement_page_displayed() throws IOException
	{
		stockTransfer = new StockTransfer(driver);
		stockTransfer.login();
		Assert.assertTrue(stockTransfer.getStockMovement());
	}
	
	@Test
	public void test_TC002_stockmovement_page_opened() throws IOException
	{
		
		String actualPageTitle = stockTransfer.selectStockMovement();
		String expectedPageTitle = "Manage Stock Move";
		Assert.assertEquals(actualPageTitle, expectedPageTitle);
	}
	
	@Test 
	public void test_TC003_select_source_store()
	{
		data = stockTransfer.getJsonObject(System.getProperty("user.dir")+"\\src\\test\\java\\FTH\\Data\\StockMovement.json" );
		String actualStoreSelected = stockTransfer.selectSourceStore().trim();
		String expectedStoreSelected = data.getString("source");
		Assert.assertEquals(actualStoreSelected, expectedStoreSelected);
	}
	
	@Test
	public void test_TC004_product_search_and_product_add()
	{
		int actualProductCount = stockTransfer.searchProduct();
		int expectedProductCount = data.getJSONArray("products").length();
		Assert.assertEquals(actualProductCount, expectedProductCount);
	}
	
	
	@Test 
	public void test_TC006_total_weight()
	{
		double actualWeight = stockTransfer.getTotalWeight();
		double expectedWeight = stockTransfer.getCalculatedWeight();
		Assert.assertEquals(actualWeight, expectedWeight);
	}
	
	
	@Test
	public void test_TC007_select_destination_store()
	{
		String destinationStore = data.getString("destination");
		String selectedDestinationStore = stockTransfer.selectDestintion();
		Assert.assertEquals(destinationStore, selectedDestinationStore);
	}

	@Test
	public void  test_TC008_invalid_products()
	{
		Assert.assertTrue(stockTransfer.checkInvalidProductID());
	}
	
	@Test
	public void test_TC009_proceed_to_review()
	{
		String actualReviewText = stockTransfer.getReviewScreen();
		String expectedReviewText = "Stock Movement Summary";
		Assert.assertEquals(actualReviewText, expectedReviewText);
	}
	
	@Test
	public void test_TC010_source_store()
	{
		String actualSourceStore = stockTransfer.getReviewSource();
		String expectedSourceStore = data.getString("source");
		Assert.assertEquals(actualSourceStore, expectedSourceStore);
	}
	
	@Test
	public void test_TC011_destination_store()
	{
		String actualDestinationStore = stockTransfer.getReviewDestination();
		String expectedDestinationStore = data.getString("destination");
		Assert.assertEquals(actualDestinationStore, expectedDestinationStore);
	}
	
	@Test
	public void test_TC012_product_count()
	{
		
		double actualProductCount= stockTransfer.getReviewProductCount();
		double expectedProductCount = stockTransfer.productCount; 
		Assert.assertEquals(actualProductCount, expectedProductCount);
	}
	
	@Test
	public void test_TC013_total_weight()
	{
		double actualWeight= stockTransfer.getReviewWeight();
		double expectedWeight = stockTransfer.totalWeight; 
		Assert.assertEquals(actualWeight, expectedWeight);
	}
	
	@Test
	public void test_TC014_create_stock_movement()
	{
		stockTransfer.createStockMovement();	
	}
	
	@Test
	public void test_TC015_create_hub_to_hub_transfer()
	{
		stockTransfer.createHubtoHubTransfer();
	}
	
	
	@Test
	public void test_TC016_create_hub_to_hub_transfer_different_as_source()
	{
		stockTransfer.differentAsSourceStore();
	}
	
	@Test
	public void test_TC016_create_hub_to_hub_transfer_same_as_source()
	{
		stockTransfer.sameAsSourceStore();
	}
	
}
