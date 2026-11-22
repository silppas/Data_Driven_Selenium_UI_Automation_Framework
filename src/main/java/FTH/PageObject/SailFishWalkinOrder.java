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

import FTH.Abstractcomponent.AbstractComponent;

public class SailFishWalkinOrder extends AbstractComponent {

	WebDriver driver;
	double cartTotal ;
	
	public SailFishWalkinOrder(WebDriver driver) throws IOException {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver = driver ;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="user-name")
	WebElement userName ;
	
	@FindBy(id="password")
	WebElement password ;
	
	@FindBy(xpath="//div[@class='md-button-content' and text()='Login']")
	WebElement loginBtn ;
	
	@FindBy(xpath="//*[text()='Order Create']")
	WebElement orderCreateOld;
	
	@FindBy(xpath="//*[contains(text(),'Walk in Customer')]")
	WebElement walkinCustomer ;
	
	@FindBy(id="search-scan-item-input")
	WebElement searchBar ;
	
	@FindBy(xpath="//*[@class='order-items-row item-row']/div/div")
	WebElement productImg ;
	
	@FindBy(xpath ="//*[text()='items in Cart']/preceding-sibling::span")
	WebElement itemCount ;
	
    @FindBy(xpath ="//*[contains(text(),'Checkout')]")
    WebElement checkoutBtn ;
    
    @FindBy(xpath="//input[@id='mobile']")
    WebElement mobileNo ;
    
    @FindBy(id="user-name")
    WebElement name ;
    
    @FindBy(xpath="//*[contains(text(),'Continue')]")
    WebElement continueBtn ;
    
    @FindBy(xpath="//*[contains(text(),'Place Order')]")
    WebElement placeOrderBtn ;
    
    @FindBy(xpath="//*[contains(text(),'Order Id')]")
    WebElement orderIDText ;
    
    @FindBy(xpath="//*[@class='item-img']")
    List<WebElement> productsList ;
    
    @FindBy(xpath="//*[@class='row order-items-row cart-items-row']/div[5]")
    List<WebElement> rowPrice ;
    
    @FindBy(xpath="//*[contains(text(),'Cart Total  :')]/span")
    WebElement cartTotalValue;
    
    @FindBy(xpath="//*[contains(text(),'Sub-total')]/following-sibling::div")
    WebElement cartSubTotal ;
    
    @FindBy(xpath="//*[@class='loader']")
    WebElement loader ;
    
    JSONObject data = getJsonObject(System.getProperty("user.dir")+"\\src\\test\\java\\FTH\\Data\\SailFishOrderCreate.json");
    JSONArray products;
    
    @FindBy(xpath="//*[@class='spinner spinner-gritcode spinner-xl']")
    WebElement loadingSymbol ;
    
    public void login() throws IOException
    {
    	driver.get(getGlobalProperties().getProperty("huburl"));
    	//clearLocalStorage();
    	visibilityOfElementLocated(userName);
    	userName.sendKeys(getGlobalProperties().getProperty("hubuser"));
    	password.sendKeys(getGlobalProperties().getProperty("hubpwd"));
    	loginBtn.click();
    }
    
    public boolean createOrderMenuDisplayed()
    {
    	visibilityOfElementLocated(orderCreateOld);
    	return orderCreateOld.getText().equalsIgnoreCase("Order Create");
    }
    
    public boolean openOrderCreateOld()
    {
    	orderCreateOld.click();
    	return driver.getCurrentUrl().equals("https://hub-mgr.qa.myfth.in/#/ordercreate");
    }
    
    public boolean isWalkinOrderDisplayed()
    {
    	invisibilityOfElement(loadingSymbol);
    	visibilityOfElementLocated(walkinCustomer);
    	return walkinCustomer.isDisplayed();
    }
    
    public boolean selectWalkingOrder() throws InterruptedException
    {
    	invisibilityOfElement(loadingSymbol); 
    	elementToBeClickable(walkinCustomer);
    	Thread.sleep(10);
    	 walkinCustomer.click();
    	 visibilityOfElementLocated(searchBar);
    	 return searchBar.isDisplayed();
    }
    
    public boolean productsListed()
    {
    	return productsList.size()>1;
    }
    
    public void productSearchandAdd()
    {
    	elementToBeClickable(productImg);
    	products = data.getJSONArray("products");
    	for(Object product: products)
    	{
    		searchBar.sendKeys(product.toString());
    		searchBar.sendKeys(Keys.ENTER);
    		elementToBeClickable(productImg);
    		productImg.click();
    		searchBar.clear();
    	}
    }
    
    public boolean checkCartItemCount()
    {
    	int count = Integer.parseInt(itemCount.getText().toString());
    	return count == products.length();
    }
    
    public boolean checkCartSubTotal()
    {
    	cartTotal = 0 ;
    	visibilityOfElementLocated(cartSubTotal);
    	for(WebElement rowprice : rowPrice)
    	{
    		invisibilityOfElement(loader);
    		
    		
    		cartTotal += Double.parseDouble(rowprice.getText().toString().split("delete")[0]);
    	}
    	
    	double actualCartTotal = Double.parseDouble(cartSubTotal.getText().toString().split("₹")[0]);
    	
    	return  actualCartTotal==cartTotal ;
    }
    
    public boolean checkCartTotal()
    {
    	return cartSubTotal.getText().toString().trim().equalsIgnoreCase(cartTotalValue.getText().toString().trim());
    }
    
    public boolean proceedToCheckout()
    {
    	checkoutBtn.click();
    	elementToBeClickable(mobileNo);
    	return mobileNo.isDisplayed();
    }
    
    public boolean selectMobileNumber()
    {
    	mobileNo.sendKeys(data.getString("mobile"));
    	mobileNo.sendKeys(Keys.ENTER);
    	invisibilityOfElement(loader);
    	elementToBeClickable(continueBtn);
    	System.out.println(name.getText().toString());
    	return  !name.getAttribute("readonly").isEmpty();
    }
    
    public boolean proceedToReview()
    {
    	continueBtn.click();
    	elementToBeClickable(placeOrderBtn);
    	return placeOrderBtn.isDisplayed();
    }
    
    public boolean placeOrder()
    {
    	placeOrderBtn.click();
    	invisibilityOfElement(loader);
    	visibilityOfElementLocated(orderIDText);
    	return orderIDText.isDisplayed();
    }
}
