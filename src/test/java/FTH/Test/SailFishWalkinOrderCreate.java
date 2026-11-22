package FTH.Test;

import java.io.IOException;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import FTH.BaseComponent.BaseClass;
import FTH.PageObject.SailFishWalkinOrder;


public class SailFishWalkinOrderCreate extends BaseClass{
	
	SailFishWalkinOrder sailFishWalkinOrder ;
	
	@Test 
	public void test_TC001_order_create_menu_displayed() throws IOException
	{
		
		sailFishWalkinOrder = new SailFishWalkinOrder(driver);
		sailFishWalkinOrder.login();
		Assert.assertTrue(sailFishWalkinOrder.createOrderMenuDisplayed());
	}
	
	@Test
	public void test_TC002_order_create_page_opened()
	{
		Assert.assertTrue(sailFishWalkinOrder.openOrderCreateOld());
	}
	
	@Test
	public void test_TC003_walkin_order_displayed()
	{
		Assert.assertTrue(sailFishWalkinOrder.isWalkinOrderDisplayed());
	}
	
	@Test
	public void test_TC005_select_walkin_order() throws InterruptedException
	{
		Assert.assertTrue(sailFishWalkinOrder.selectWalkingOrder());
	}
	
	@Test
	public void test_TC006_products_listed()
	{
		Assert.assertTrue(sailFishWalkinOrder.productsListed());
	}

	@Test
	public void test_TC007_products_search_and_product_add()
	{
		sailFishWalkinOrder.productSearchandAdd();
	}
	
	@Test
	public void test_TC008_cart_product_count()
	{
		Assert.assertTrue(sailFishWalkinOrder.checkCartItemCount());
	}
	

	public void test_TC009_cart_sub_total()
	{
		Assert.assertTrue(sailFishWalkinOrder.checkCartSubTotal());
	}
	
	@Test
	public void test_TC010_cart_total()
	{
		Assert.assertTrue(sailFishWalkinOrder.checkCartTotal());
	}
	
	
	@Test
	public void test_TC011_proceed_to_checkout()
	{
		Assert.assertTrue(sailFishWalkinOrder.proceedToCheckout());
	}
	
	@Test
	public void test_TC012_select_mobile_number()
	{
		Assert.assertTrue(sailFishWalkinOrder.selectMobileNumber());
	}
	
	@Test
	public void test_TC013_proceed_to_review_screen()
	{
		Assert.assertTrue(sailFishWalkinOrder.proceedToReview());
	}
	
	@Test
	public void test_TC014_place_order()
	{
		Assert.assertTrue(sailFishWalkinOrder.placeOrder());
	}
}
