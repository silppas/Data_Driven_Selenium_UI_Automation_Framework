package FTH.PageObject;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import FTH.Abstractcomponent.AbstractComponent;

public class SimpleOrderMultipleHubTransfer extends AbstractComponent {
	
WebDriver driver ;
JSONArray destinationList ;
	JSONArray productids;
	
	public SimpleOrderMultipleHubTransfer(WebDriver driver) throws IOException
	{
		super(driver);
		this.driver = driver ;
		PageFactory.initElements(driver, this);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	@FindBy(id="username")
	WebElement username ;
	
	@FindBy(id="login")
	WebElement password ;
	
	@FindBy(id="authme")
	WebElement loginBtn ;
	
	@FindBy(xpath="//span[contains(text(), 'Sales')]")
	WebElement salesMenu;
	
	@FindBy(xpath = "//span[contains(text(),'Simple Order Create')]")
	WebElement simpleOrderCreate ;
	
	@FindBy(xpath="//span[contains(text(),'Hub Transfer Order')]")
	public
	WebElement hubTransferOrderMenu;
	
	@FindBy(xpath="//div[contains(text(),'Creating new hub transfer order')]")
	WebElement hubTransferText;
	
	@FindBy(xpath="//td[@class='value']/select")
	WebElement selectStore;
	
	@FindBy(xpath ="//select[@id='selectHubStores']/option")
	List<WebElement> hubList;
	
	@FindBy(xpath="//*[@id='selectHubStores']")
	WebElement selectHub;
	
	@FindBy(xpath="//span[contains(text(),'Internal Warehouse Address')]")
	public WebElement internalWarehouse;
	
	@FindBy(xpath="//div[2]/span[contains(text(),' Warehouse Address: ')]")
	public WebElement warehouseAddress;
			
	@FindBy(css= ".inputType")
	WebElement search;
	
	@FindBy(id="loading_mask_loader")
	WebElement loadingSymbol;
			
	@FindBy(css= ".grid.availableProduct tr")
	List<WebElement> products;
	
	@FindBy(css =".grid.availableProduct tr td:nth-child(2)")
	WebElement productDetails;
	
	@FindBy(xpath ="//*[contains(text(),'Bulk Add Products')]")
	WebElement bulkAdd; 
	
	@FindBy(xpath ="//*[@class='modal-body']/div/table/tr[3]/td/textarea")
	WebElement enterProduct ;
	
	@FindBy(xpath = "//*[@class='btn btn-primary pull-right']")
	WebElement bulkProductAdd ;
	
	@FindBy(css =".text-left .textColor")
	WebElement cartItem;

	@FindBy(xpath= "//*[contains(text(),'Create Cart')]")
    WebElement createCart;
			
	@FindBy(xpath= "//*[contains(text(),'Proceed To Checkout')]")
	WebElement proceedToCheckout;
			
	@FindBy(css= "#shippingMethod")
	WebElement shippingMethod;
	
	@FindBy(xpath= "//*[@class='template_body']/div/select")
	WebElement selectDeliverySlot;
			
	@FindBy(xpath= "//Select/option[2]")
	WebElement deliverySlot ;
	
	@FindBy(xpath= "//button[contains(text(),'Continue')]")
	WebElement cartContinueBtn;
	
	@FindBy(xpath = "//h4[contains(text(),'Order review of ')]")
	WebElement reviewText;

			
	@FindBy(xpath= "//*[contains(text(),'Place Order')]")
	WebElement placeOrderBtn;
	
	@FindBy(xpath="//span[contains(text(),'Order created successfully')]")
	WebElement orderCreateMsg;
	
	@FindBy(xpath="//*[@type='radio' and @value='1']")
	WebElement multipleRadioBtn;
	
	@FindBy(xpath="//a[contains(text(),'Select All Hub Stores')]")
	WebElement selectAllHub ;
	
	@FindBy(xpath="//*[text()='Click here to view the customers address']")
	WebElement customerAddress ;
	
	@FindBy(xpath="//*[text()=' Hub Customers Billing/Shipping Address: ']")
	WebElement customerAddressPopUpTitle;
	
	@FindBy(xpath="//*[@class='multi-address-modal']/tr/td[2]")
	List<WebElement> internalWarehouseAddress;
	
	@FindBy(xpath="//*[@class='multi-address-modal']/tr/td[3]")
	List<WebElement> WarehouseAddress;
	
	@FindBy(xpath="//a[contains(text(),'X Close')]")
	WebElement closeBtn ;
	
	@FindBy(xpath="//h4[@class='text-left']")
	List<WebElement> cartList;
	
	@FindBy(xpath="//a[contains(text(),' View cart')]")
	List<WebElement> viewCart;
	
	@FindBy(xpath="//table[@class='fixed']/tbody/tr")
	List<WebElement> cartitems ;
	
	JSONObject data = getJsonObject(System.getProperty("user.dir")+"\\src\\test\\java\\FTH\\Data\\SimpleOrderMultipleHubTransfer.json");
	
	public void login() throws IOException
	{
		visibilityOfElementLocated(username);
		username.sendKeys(getGlobalProperties().getProperty("username"));
		password.sendKeys(getGlobalProperties().getProperty("password"));
		loginBtn.click();
		
	}
	
	public void selectSimpleOrderhubTransfer()
	{
		salesMenu.click();
		simpleOrderCreate.click();
		elementToBeClickable(hubTransferOrderMenu);
		
	}
	
	public String selectStore()
	{

		String sourceStore = data.getString("source");
		return getSelectedTest(selectStore,sourceStore);
	    
	}
	
	public boolean multipleHubTransferDisplayed()
	{
		invisibilityOfElement(loadingSymbol);
		elementToBeClickable(multipleRadioBtn);
		return multipleRadioBtn.isDisplayed();
	}
	
	public boolean selectAllHubDisplayed()
	{
		elementToBeClickable(multipleRadioBtn);
		multipleRadioBtn.click();
		elementToBeClickable(selectAllHub);
		return selectAllHub.isDisplayed();
	
	}

	public boolean viewCustomerAddressDisplayed()
	{
		elementToBeClickable(customerAddress);
		return customerAddress.isDisplayed();
	}

	public boolean customerAddressPopUpOpened()
	{
		customerAddress.click();
		elementToBeClickable(customerAddressPopUpTitle);
		return customerAddressPopUpTitle.getText().trim().equalsIgnoreCase("Hub Customers Billing/Shipping Address:") ? true : false ;
	}
	
	public boolean internalWarehouseAddressDisplayed()
	{
		
		return internalWarehouseAddress.size()==destinationList.length() ? true : false ;
	}
	
	public boolean warehouseAddressDisplayed()
	{
		return WarehouseAddress.size()==destinationList.length() ? true : false ;
	}
	
	public boolean selectDestination()
	{
		destinationList = data.getJSONArray("destination");
		pressKeys(Keys.SHIFT);
		for (Object destination : destinationList)
		{
			selectByVisibleText(selectHub,destination.toString());
			invisibilityOfElement(loadingSymbol);		}
		releaseKeys(Keys.SHIFT);
		
		return getAllSelectedValue(selectHub).size()==destinationList.length()? true : false;
	}

	public void selectProducts(List<String> product_list)
	{
		
		for(String productID:product_list )
		{
			search.click();
			search.sendKeys(productID);
			//product.click();
			
		}
	}
	
	
    public int getProductsList()
    {
    	closeBtn.click();
    	invisibilityOfElement(loadingSymbol);
    	return products.size();
    	
    }
    
    public JSONArray productSearch(JSONArray productID)
    {
    	productids = new JSONArray() ;
    	for(Object productid :productID )
    	{
    		search.click();
    		search.sendKeys(productid.toString());
    		elementToBeClickable(productDetails);
    		int product= Integer.parseInt(productDetails.getText().split("\\[|\\]")[1]);
    		productids.put(product);
    		productDetails.click();
    		search.clear();
    		
    	}
    	
    	return productids ;
    }
    
    public int bulkAddProducts(JSONArray bulkaddproducts ) throws InterruptedException
    {
    	bulkAdd.click(); 
    	elementToBeClickable(enterProduct);
    	
    	for(Object product: bulkaddproducts)
    	{
    		enterProduct.click();
    		elementToBeClickable(enterProduct);
    		enterProduct.sendKeys(product.toString());
    		enterProduct.sendKeys(Keys.ENTER);
    		driver.switchTo().alert().accept();
    	}
    	bulkProductAdd.click();
    	return Integer.parseInt(cartItem.getText());
    }
    

    public boolean cartCreatedForSelectedStore()
    {
    	return cartList.size()==destinationList.length()?true : false ;
    }
    
    public boolean viewCartDisplayed()
    {
    	return viewCart.size()==destinationList.length()?true : false ;
    }
    
    public boolean checkViewCart()
    {
    	viewCart.get(0).click();
    	elementToBeClickable(cartitems.get(0));
    	JSONArray bulkproducts = data.getJSONArray("bulkaddproducts");
    	int sum = productids.length() +bulkproducts.length() ;
    	return cartitems.size()==sum ? true : false ;
    }

    public Boolean getInternalWareHouseandWareHouse()
    {
    	Boolean flag = false ; 
    	flag = ! internalWarehouse.getText().isEmpty();
    	flag = ! warehouseAddress.getText().isEmpty();
    	return flag ;
    	
    }
    
    public boolean getHubTransferText()
    {
		hubTransferOrderMenu.click();
    	visibilityOfElementLocated(hubTransferText);
    	return hubTransferText.isDisplayed();
    }
    
    public boolean proceedtoCheckout()
    {
    	closeBtn.click();
    	elementToBeClickable(createCart);
    	createCart.click();
    	elementToBeClickable(proceedToCheckout);
    	proceedToCheckout.click();
    	elementToBeClickable(shippingMethod);
    	shippingMethod.click();
    	selectByIndex(selectDeliverySlot,1);
    	invisibilityOfElement(loadingSymbol);
    	elementToBeClickable(cartContinueBtn);
    	cartContinueBtn.click();
    	elementToBeClickable(reviewText);
    	return reviewText.isDisplayed();
    }
    
    public boolean placeOrder()
    {
    	invisibilityOfElement(loadingSymbol);
    	elementToBeClickable(placeOrderBtn);
    	placeOrderBtn.click();
    	return orderCreateMsg.isDisplayed();
    }

}
