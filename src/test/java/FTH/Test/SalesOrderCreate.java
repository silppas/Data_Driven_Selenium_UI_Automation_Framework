package FTH.Test;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import FTH.BaseComponent.BaseClass;
import FTH.PageObject.SalesOrder;

public class SalesOrderCreate extends BaseClass {
	

	SalesOrder salesOrder ;
	
	@Test 
	public void test_TC001_sales_order_menu_displayed() throws IOException
	{
		salesOrder = new SalesOrder(driver);
		salesOrder.login();
		Assert.assertTrue(salesOrder.checkSalesOrderMenuDisplayed());
	}
	
	@Test
	public void test_TC002_select_sales_order()
	{
		String actualTitle = salesOrder.selectSalesOrderMenu();
		Assert.assertEquals(actualTitle, "Orders");
	}
	
	@Test
	public void test_TC003_orders_are_getting_listed()
	{
		Assert.assertTrue(salesOrder.checkExistingOrdersListed());
	}
	
	@Test
	public void test_TC004_create_new_order_btn_displayed()
	{
		Assert.assertTrue(salesOrder.customerOrderCreateBtnDisplayed());
	}

	@Test
	public void test_TC005_create_new_order_page_opened()
	{
		Assert.assertTrue(salesOrder.selectCustomerOrderBtn());
	}
	@Test
	public void test_TC006_existing_customer_are_listing()
	{
		Assert.assertTrue(salesOrder.checkExistingCustomerDisplayed());
	}
	
	@Test
	public void test_TC007_customer_search_by_customer_id()
	{
		Assert.assertTrue(salesOrder.searchCustomer());
	}

	
	@Test
	public void test_TC008_select_existing_customer()
	{
		Assert.assertTrue(salesOrder.selectCustomer());
	}

	
	@Test
	public void test_TC009_select_store()
	{
		salesOrder.selectStore();
	}

	
	@Test
	public void test_TC010_add_product_button_displayed() throws InterruptedException
	{
		Assert.assertTrue(salesOrder.addBtnDisplayed());
	}

	
	@Test
	public void test_TC011_product_displayed()
	{
		Assert.assertTrue(salesOrder.isProductDisplayed());
	}

	
	@Test
	public void test_TC012_product_search_and_product_add() throws InterruptedException
	{
		Assert.assertTrue(salesOrder.searchProductID());
	}

	
	@Test
	public void test_TC013_select_products()
	{
		Assert.assertTrue(salesOrder.addSelectedProducts());
	}
	

	@Test
	public void test_TC014_cart_sub_total()
	{
		Assert.assertTrue(salesOrder.checkCartTotal());
	}

	@Test
	public void test_TC015_customer_mobile_number()
	{
		Assert.assertTrue(salesOrder.checkMobileNo());
	}

	@Test
	public void test_TC016_payment_method_displayed()
	{
		Assert.assertTrue(salesOrder.paymentMethodDisplayed());
	}
	
	@Test
	public void test_TC017_select_payment_method() throws InterruptedException
	{
		Assert.assertTrue(salesOrder.selectPaymentMethod());
	}
	
	
	@Test
	public void test_TC018_shipping_method_displayed()
	{
		Assert.assertTrue(salesOrder.shippingMethodDisplayed());
	}

	@Test
	public void test_TC019_select_shipping_method()
	{
		Assert.assertTrue(salesOrder.selectShippingMethod());
	}


	@Test
	public void test_TC020_delivery_slot_displayed()
	{
		Assert.assertTrue(salesOrder.deliverySlotDisplayed());
	}
	
	@Test
	public void test_TC021_deliervy_slot_selected()
	{
		Assert.assertTrue(salesOrder.deliverySlotSelected());
	}
	
	@Test
	public void test_TC022_subtotal()
	{
		Assert.assertTrue(salesOrder.checkSubTotal());
	}

	@Test
	public void test_TC023_place_order()
	{
		Assert.assertTrue(salesOrder.placeOrder());
	}
	
	@Test
	public void test_TC024_check_order_status()
	{
		Assert.assertTrue(salesOrder.checkOrderSource());
	}

	@Test
	public void test_TC025_check_order_source()
	{
		Assert.assertTrue(salesOrder.checkOrderSource());
	}

	@Test
	public void test_TC026_check_customer()
	{
		Assert.assertTrue(salesOrder.checkCustomer());
	}
	

	@Test
	public void test_TC027_check_order_sub_total()
	{
		Assert.assertTrue(salesOrder.checkOrderSubTotal());
	}


	

}
