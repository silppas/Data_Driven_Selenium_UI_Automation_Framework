package FTH.PageObject;

import java.io.IOException;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import FTH.Abstractcomponent.AbstractComponent;

public class SalesOrder extends AbstractComponent {
	
	WebDriver driver ;
	double calculatedSubTotal = 0;
	
	
	public SalesOrder(WebDriver driver) throws IOException
	{
		super(driver);
		this.driver= driver ;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//span[text()='Sales']")
	WebElement salesMenu;
	
	@FindBy(xpath="//span[text()='Orders']")
	WebElement orderMenu;
	
	@FindBy(xpath="//*[@class='icon-head head-sales-order']")
	WebElement orderTitle;
	
	@FindBy(xpath="//span[text()='Create New Order']")
	WebElement createNewOrderBtn;
	
	@FindBy(xpath="//input[@name='entity_id']")
	WebElement customerID;
	
	@FindBy(xpath="//*[@class='even pointer']")
	WebElement customerRow;
	
	@FindBy(xpath="//input[@type = 'radio']")
	List<WebElement> storeRadioBtn;
	
	@FindBy(xpath="//*[@id='loading_mask_loader']")
	WebElement loading;
	
	@FindBy(xpath="//*[@id='sales_order_create_search_grid_filter_entity_id']")
	WebElement productIDSearch;
	
	@FindBy(xpath="//*[@id=\"order-items\"]/div/div[1]/div/button/span")
	WebElement addProductBtn;
	
	@FindBy(xpath="//*[@id='sales_order_create_search_grid_table']/tbody/tr/td")
	WebElement productID;
	
	@FindBy(xpath="//*[@id='sales_order_create_search_grid_table']/tbody/tr")
	List<WebElement> productRow;
	
	@FindBy(xpath="//td[@class='a-center ']/input[@type='checkbox']")
	WebElement productSelectCheckbox;
	
	@FindBy(xpath="//*[text()='Add Selected Product(s) to Order']")
	WebElement addSelectedProductBtn;
	
	@FindBy(xpath="//*[@class='payment-methods']/dt")
	List<WebElement> paymentMethods;
	
	@FindBy(xpath="//*[@for='p_method_cashondelivery']")
	WebElement codBtn;
	
	@FindBy(xpath="//a[contains(text(),'Get shipping methods and rates')]")
	WebElement getShippingLink;
	
	@FindBy(xpath="//*[@class='shipment-methods']/dd")
	List<WebElement> shippingMethod;
	
	@FindBy(css="#s_method_tablerate_bestway")
	WebElement homeDeliveryShippingBtn;
	
	@FindBy(xpath="(//*[text()='Submit Order'])[2]")
	WebElement submitBtn;
	
	@FindBy(xpath="//*[text()='Edit']")
	WebElement editBtn;
	
	@FindBy(xpath="//*[text()='Cancel']")
	WebElement cancelBtn ;
	
	@FindBy(xpath="//*[text()='Invoice']")
	WebElement invoiceBtn;
	
	@FindBy(xpath="//*[text()='Save Items']")
	WebElement saveItems ;
	
	@FindBy(xpath="//*[text()='Remove Item and Send Nil Notifications']")
	WebElement nilNotifactionBtn;
	
	@FindBy(xpath="//*[text()='Add Items']")
	WebElement addItems;
	
	@FindBy(xpath="//*[@id='sales_order_view_tabs_order_invoices']")
	WebElement invoiceTab;
	
	@FindBy(xpath="//*[@id='sales_order_view_tabs_order_creditmemos']")
	WebElement creditmemoTab;
	
	@FindBy(xpath="//*[@id='sales_order_view_tabs_order_shipments']")
	WebElement shipmentTab;
	
	@FindBy(xpath="//*[@class='icon-head head-account']/following-sibling::span")
	WebElement orderID;
	
	@FindBy(xpath="//*[@id='edit_order_status']")
	WebElement orderStatus;
	
	@FindBy(xpath="//td[text()=' Customer Id ']/following-sibling::td")
	WebElement orderedCustomerID ;
	
	@FindBy(xpath="//tr[td[@class='label']/label[contains(text(),'Source')]]/td/strong")
	WebElement source;
	
	@FindBy(id="username")
	WebElement username ;
	
	@FindBy(id="login")
	WebElement password ;
	
	@FindBy(id="authme")
	WebElement loginBtn ;
	
	@FindBy(xpath="//*[@name='order_ids']")
	List<WebElement> orderList;
	
	@FindBy(xpath="//*[@class='icon-head head-sales-order']")
	WebElement orderCreateTitle ;
	
	@FindBy(xpath ="//*[@id='sales_order_create_customer_grid_table']/tbody/tr")
	List<WebElement> existingCustomerList; 
	
	@FindBy(xpath ="//table[@id='sales_order_create_customer_grid_table']/tbody/tr/td")
	WebElement idColumn;
	
	@FindBy(xpath="//*[text()='Please Select a Store']")
	WebElement storePageTitle;
	
	@FindBy(xpath="//*[@id='mobile']")
	WebElement mobileNo;
	
	@FindBy(xpath ="//*[text()='Associated Products']")
	List<WebElement> configProductPopUp;
	
	@FindBy(xpath="//*[@class='required-entry super-attribute-select']")
	WebElement configurableProduct;
	
	@FindBy(xpath="//*[@id='product_composite_configure_input_qty']")
	WebElement configQty;
	
	@FindBy(xpath="//*[@type='submit']")
	WebElement okBtn ;
	
	@FindBy(xpath="//table[@class='data order-tables']/tbody")
	List<WebElement> selectedItems ;
	
	@FindBy(xpath="//table[@class='data order-tables']/tbody/tr/td[5]/span")
	List<WebElement> selectedProductPrice;
	
	@FindBy(xpath="(//strong/span[@class='price'])[1]")
	WebElement tableSubTotal;
	
	@FindBy(xpath="(//td[contains(text(),'Subtotal')]/following-sibling::td)[5]")
	WebElement orderSubTotal ;

	@FindBy(id="delivery-slots")
	WebElement deliveryslot;
	
	@FindBy(xpath ="//span[text()='The order has been created.']")
	WebElement successMessage ;
	
	@FindBy(xpath="//td[contains(text(),'Subtotal')]/following-sibling::td/span")
	WebElement orderedSubTotal ;
	
	JSONObject data = getJsonObject(System.getProperty("user.dir")+"\\src\\test\\java\\FTH\\Data\\SalesOrderTestData.json");
	
	public void login() throws IOException
	{
		visibilityOfElementLocated(username);
		username.sendKeys(getGlobalProperties().getProperty("username"));
		password.sendKeys(getGlobalProperties().getProperty("password"));
		loginBtn.click();
		
	}
	
	
	public Boolean checkSalesOrderMenuDisplayed()
	{
		elementToBeClickable(salesMenu);
		salesMenu.click();
		elementToBeClickable(orderMenu);
		return orderMenu.isDisplayed();
		
	}
	
	public boolean checkExistingOrdersListed()
	{
		elementToBeClickable(orderTitle);
		return orderList.size()> 1 ;
	}
	
	public String selectSalesOrderMenu()
	{
		orderMenu.click();
		elementToBeClickable(orderTitle);
		return orderTitle.getText();
		
	}
	
	public boolean customerOrderCreateBtnDisplayed()
	{
		elementToBeClickable(createNewOrderBtn);
		return createNewOrderBtn.isDisplayed();
	}
	
	public Boolean selectCustomerOrderBtn()
	{
		createNewOrderBtn.click();
		visibilityOfElementLocated(orderCreateTitle);
		return orderCreateTitle.getText().equalsIgnoreCase("Create New Order");
	}
	
	public Boolean checkExistingCustomerDisplayed()
	{
		return existingCustomerList.size()>1  ;
	}
	
	public boolean searchCustomer()
	{
		String customerid= data.getString("customerid");
		invisibilityOfElement(loading);
		elementToBeClickable(customerID);
		customerID.sendKeys(customerid);
		customerID.sendKeys(Keys.ENTER);
		invisibilityOfElement(loading);
		elementToBeClickable(idColumn);
		return idColumn.getText().equals(customerid) ;
		
	}
	
	public boolean selectCustomer()
	{
		invisibilityOfElement(loading);
		idColumn.click();
		invisibilityOfElement(loading);
	    visibilityOfElementLocated(storePageTitle);
	    return storePageTitle.getText().equalsIgnoreCase("Please Select a Store")? true : false ;
	}
	
	public void selectStore()
	{
		String storeName = data.getString("orderstore");
		for(WebElement store : storeRadioBtn)
		{
			store.getText().equalsIgnoreCase(storeName);
			store.click();
			break;
		}
	}
	
	public boolean addBtnDisplayed() throws InterruptedException
	{
		invisibilityOfElement(loading);
		elementToBeClickable(mobileNo);
		
		elementToBeClickable(addProductBtn);
		return addProductBtn.isDisplayed();
	}
	
	public boolean isProductDisplayed()
	{
		elementToBeClickable(addProductBtn);
		addProductBtn.click();
		elementToBeClickable(productSelectCheckbox);
		return productRow.size() > 1  ;
		
	}
	
	public boolean searchProductID() throws InterruptedException
	{
		int count =0 ;
		JSONArray products = data.getJSONArray("products");
		for( Object product : products)
		{
			productIDSearch.sendKeys(product.toString());
			Thread.sleep(2000);
			productIDSearch.sendKeys(Keys.ENTER);
			Thread.sleep(2000);
			invisibilityOfElement(loading);
			elementToBeClickable(productID);
			if(productID.getText().equalsIgnoreCase(product.toString()))
			{
				count++ ;
			}
			productSelectCheckbox.click();
			if(!configProductPopUp.isEmpty())
			{
				selectByIndex(configurableProduct, 1);
				configQty.sendKeys("1");
				okBtn.click();
				
			}
			productIDSearch.clear();
		}
		return count == products.length()  ;
		
	}
	
	public boolean addSelectedProducts()
	{
		elementToBeClickable(addSelectedProductBtn);
		addSelectedProductBtn.click();
		invisibilityOfElement(loading);
		JSONArray products = data.getJSONArray("products");
		return selectedItems.size()==products.length()  ;
		
	}
	
	
	public boolean checkCartTotal()
	{
		
	 for( WebElement price : selectedProductPrice)
	 {
		 
		 calculatedSubTotal= calculatedSubTotal + Double.parseDouble(price.getText().toString().replaceAll(",", "").split("₹")[1]);
	 }
	 
	 double subTotal = Double.parseDouble(tableSubTotal.getText().toString().replaceAll(",", "").split("₹")[1]);
	 return subTotal==calculatedSubTotal ;
		
	}
	
	public boolean checkMobileNo()
	{
		return mobileNo.getAttribute("value").equalsIgnoreCase(data.getString("mobile")) ;
	}
	
	public boolean paymentMethodDisplayed()
	{
		moveToElement(codBtn);
		return paymentMethods.size() > 1 ;
	}
	
	public boolean selectPaymentMethod() throws InterruptedException
	{
	
		codBtn.click();
		invisibilityOfElement(loading);
		return codBtn.getText().equalsIgnoreCase("Cash On Delivery");
	}
	
	public boolean shippingMethodDisplayed()
	{
		visibilityOfElementLocated(getShippingLink);
		getShippingLink.click();
		invisibilityOfElement(loading);
		return shippingMethod.size()> 0  ;
	}
	
	public boolean selectShippingMethod()
	{
		homeDeliveryShippingBtn.click();
		return homeDeliveryShippingBtn.isSelected();
	}
	
	public boolean deliverySlotDisplayed()
	{
		return deliveryslot.isDisplayed();
	}
	
	public boolean deliverySlotSelected()
	{
		dropdown = new Select(deliveryslot);
		return !dropdown.getFirstSelectedOption().getText().isEmpty();
		
	}
	
	public boolean checkSubTotal()
	{
		visibilityOfElementLocated(orderSubTotal);
		double subTotal = Double.parseDouble(orderSubTotal.getText().toString().replaceAll(",", "").split("₹")[1]);
		return calculatedSubTotal==subTotal ;
	}
	
	
	public boolean placeOrder()
	{
		
		try 
		{
			moveToElement(submitBtn);
			submitBtn.click();
			
		}
		catch(Exception e )
		{
			moveToElement(submitBtn);
			submitBtn.click();
		}
		
		invisibilityOfElement(loading);
		visibilityOfElementLocated(successMessage);
		return successMessage.isDisplayed();
	}
	
	public boolean checkOrderStatus()
	{
		visibilityOfElementLocated(orderStatus);
		return orderStatus.getText().equalsIgnoreCase("pending");
	}
	
	public boolean checkOrderSource()
	{
		visibilityOfElementLocated(source);
		return source.getText().equalsIgnoreCase("web");
	}
	
	public boolean checkCustomer()
	{
		
		visibilityOfElementLocated(orderedCustomerID);
		return orderedCustomerID.getText().equalsIgnoreCase(data.getString("customerid"));
	}
	
	public boolean checkOrderSubTotal()
	{
		moveToElement(orderedSubTotal);
		double subTotal = Double.parseDouble(orderedSubTotal.getText().toString().replaceAll(",", "").split("₹")[1]);
		return true;
	}
	
}

