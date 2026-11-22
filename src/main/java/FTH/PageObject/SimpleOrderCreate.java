package FTH.PageObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;

import FTH.Abstractcomponent.AbstractComponent;

public class SimpleOrderCreate extends AbstractComponent{
	
	WebDriver driver ;
	
	public SimpleOrderCreate(WebDriver driver) throws IOException
	{
		super(driver);
		this.driver = driver ;
		PageFactory.initElements(driver, this);
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
	
	@FindBy(xpath = "//input[@type ='number']")
	WebElement mobileNumber ;

	@FindBy(xpath = "//*[contains(text(),\"Select From Existing Customers\")]")
	WebElement existingCustomerList;
	
	@FindBy(xpath= "//*[@id='customerTable']/tbody")
	WebElement selectCustomer;
	
	@FindBy(xpath = "//*[@id='customerTable']/tbody/tr/td[1]")
	WebElement customerID;
	
	@FindBy(css= ".customerEdit tr")
	List<WebElement> existingAddress;
	
	@FindBy(xpath= "//*[@class='customerEdit']/tr")
	WebElement customerAddress;
	
	@FindBy(xpath= "//*[contains(text(),'Continue')]")
	WebElement continueBtn;
	
	@FindBy(css =".sub-menu li")
	List<WebElement> storesList ;
	
	@FindBy(xpath= "//li[@class='sub-menu']/ul/li")
	List<WebElement> storeSelect;
			
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
	
	@FindBy(xpath="//*[@class='grid col-sm-12 cartProduct']/table/tbody/tr/td[4]")
	List<WebElement> priceColumn;
	
	@FindBy(xpath ="//*[@class='text-right']/span")
	WebElement cartTotal;
	
	@FindBy(xpath= "//*[contains(text(),'Create Cart')]")
    WebElement createCart;
			
	@FindBy(xpath= "//*[contains(text(),'Proceed To Checkout')]")
	WebElement proceedToCheckout;
			
	@FindBy(css= "#shippingMethod")
	WebElement shippingMethod;
	
	@FindBy(xpath= "//select")
	WebElement selectDeliverySlot;
			
	@FindBy(xpath= "//Select/option[2]")
	WebElement deliverySlot ;
	
	@FindBy(xpath= "//button[contains(text(),'Continue')]")
	WebElement cartContinueBtn;
	
	@FindBy(xpath = "//*[@class='template_body']/b")
	WebElement reviewText;
	
	@FindBy(xpath="//*[@class='template_body']/div[2]/table/tr/td[2]")
	WebElement reviewTotal;
			
	@FindBy(xpath= "//*[contains(text(),'Place Order')]")
	WebElement placeOrderBtn;
	
	@FindBy(xpath ="//*[contains(text(),'Creating new  order')]")
	WebElement createNewOrderText;
	
	@FindBy(xpath="//a[contains(text(),' View Order')]")
	WebElement viewOrder;
	
	JSONObject data ;
	
	public void login() throws IOException
	{
		visibilityOfElementLocated(username);
		username.sendKeys(getGlobalProperties().getProperty("username"));
		password.sendKeys(getGlobalProperties().getProperty("password"));
		loginBtn.click();
		
	}
	
	public void selectSimpleOrderCreate()
	{
		salesMenu.click();
		simpleOrderCreate.click();
	}
	
	public String getNewOrderText()
	{
		visibilityOfElementLocated(createNewOrderText);
		return createNewOrderText.getText();
	}
	
	public String getExistingCustomer() throws InterruptedException
	{
		
		
		JSONObject data = getJsonObject(System.getProperty("user.dir")+"\\src\\test\\java\\FTH\\Data\\SimpleOrderCreateTestData.json");
		//JSONObject data = getJsonObject("C:\\Users\\silppa\\eclipse-workspace\\Regression\\src\\test\\java\\FTH\\Data\\SimpleOrderCreateTestDataUAE.json");
		visibilityOfElementLocated(mobileNumber);
		mobileNumber.sendKeys(data.getString("mobile"));
		//mobileNumber.sendKeys("shilpa");
		visibilityOfElementLocated(existingCustomerList);
		existingCustomerList.click();
		elementToBeClickable(customerID);
        return customerID.getText();
	}
	
	public void selectCustomer() 
	{
		elementToBeClickable(customerID);
		selectCustomer.click();
	}
	
	public void selectCustomerAddress() throws InterruptedException
	{
		scrollUp();
		elementToBeClickable(customerAddress);
		moveToElement(customerAddress);
		Thread.sleep(20);
		customerAddress.click();
		scrollDown();
		elementToBeClickable(continueBtn);
		continueBtn.click();
	}
	
	public void selectStore()
	{
		scrollUp();
		JSONObject data = getJsonObject(System.getProperty("user.dir")+"\\src\\test\\java\\FTH\\Data\\SimpleOrderCreateTestData.json");
    	for (WebElement store : storesList)
    	{
    		if(store.getText().equals(data.getString("orderstore")))
    		{
    			store.click();
    			break;
    		}
    	}
	
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
	
	public int getExistingCustomerAddress()
	{
		return existingAddress.size();
	}
	
    public int checkStoreList()
    {
    	JSONObject data = getJsonObject(System.getProperty("user.dir")+"\\src\\test\\java\\FTH\\Data\\SimpleOrderCreateTestData.json");
    	int count = 0 ;
		JSONArray storelist = data.getJSONArray("storelist");
    	for (Object store: storelist)
    	{
    		if (count < 2)
    		{
    		for (WebElement storename : storesList)
    		{
    			if(storename.getText().trim().equals(store.toString().trim()))
    					{   
    				count = count +1 ;
    					}
    		}
    		}
    	}
    	return count ;
    }

    public int getProductsList()
    {
    	invisibilityOfElement(loadingSymbol);
    	return products.size();
    	
    }
    
    public JSONArray productSearch(JSONArray productID)
    {
    	JSONArray productids = new JSONArray() ;
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
    	}
    	bulkProductAdd.click();
    	return Integer.parseInt(cartItem.getText());
    }
    
    public double calculateCartTotal()
    {
    	invisibilityOfElement(loadingSymbol);
    	double sum = 0 ;
    	for (WebElement price : priceColumn)
    	{
    		sum = sum + Double.parseDouble(price.getText());
    	}
		return sum;
    	
    }
    
    public String getCartTotal()
    {
    	invisibilityOfElement(loadingSymbol);
    	elementToBeClickable(createCart);
    	createCart.click();
    	return cartTotal.getText().split("₹")[1].trim();
    }
    
    public String proceedtoCheckout()
    {
    	
    	elementToBeClickable(proceedToCheckout);
    	proceedToCheckout.click();
    	elementToBeClickable(shippingMethod);
    	shippingMethod.click();
    	selectByIndex(selectDeliverySlot,1);
    	invisibilityOfElement(loadingSymbol);
    	elementToBeClickable(cartContinueBtn);
    	cartContinueBtn.click();
    	elementToBeClickable(reviewText);
    	return reviewText.getText();
    }
    
    public double getSubTotal()
    {
    	visibilityOfElementLocated(reviewTotal);
    	String total = reviewTotal.getText().split("₹")[1].toString().replace(",", "");
    	return Double.parseDouble(total);
    }
    public boolean placeOrder()
    {
    	invisibilityOfElement(loadingSymbol);
    	elementToBeClickable(placeOrderBtn);
    	placeOrderBtn.click();
    	return viewOrder.isDisplayed();
    }
}
