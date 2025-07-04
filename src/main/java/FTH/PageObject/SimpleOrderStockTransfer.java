
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

public class SimpleOrderStockTransfer extends AbstractComponent{

WebDriver driver ;
	
	public SimpleOrderStockTransfer(WebDriver driver) throws IOException
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
	
	@FindBy(xpath ="//span[contains(text(),'Stock Transfer Order')]")
	public
	WebElement stockTransfer;
	
	@FindBy(xpath="//div[contains(text(),'Creating new stock transfer order')]")
	WebElement stockTransferText;
	
	@FindBy(xpath="//td[@class='value']/select")
	WebElement source;
	
	@FindBy(xpath ="//td[@class='value'][2]/select/option")
	List<WebElement> destinationList;
	
	@FindBy(xpath="//td[@class='value'][2]/select")
	WebElement destination;
	
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
	
	@FindBy(xpath="//*[@class='grid col-sm-12 cartProduct']/table/tbody/tr/td[4]")
	List<WebElement> priceColumn;
	
	@FindBy(xpath ="//*[contains(text(),'Subtotal')]/span")
	WebElement cartTotal;
	
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
	
	@FindBy(xpath = "//*[@class='template_body']/b")
	WebElement reviewText;
	
	@FindBy(xpath="//*[@class='template_body']/div[2]/table/tr/td[2]")
	WebElement reviewTotal;
			
	@FindBy(xpath= "//*[contains(text(),'Place Order')]")
	WebElement placeOrderBtn;
	
	@FindBy(xpath="//a[contains(text(),' View Order')]")
	WebElement viewOrder;
	
	JSONObject data = getJsonObject(System.getProperty("user.dir")+"\\src\\test\\java\\FTH\\Data\\SimpleOrderStockTransfer.json");
	
	public void login() throws IOException
	{
		visibilityOfElementLocated(username);
		username.sendKeys(getGlobalProperties().getProperty("username"));
		password.sendKeys(getGlobalProperties().getProperty("password"));
		loginBtn.click();
		
	}
	
	public void selectSimpleOrderStockTransfer()
	{
		elementToBeClickable(salesMenu);
		salesMenu.click();
		simpleOrderCreate.click();
		elementToBeClickable(stockTransfer);
		
	}
	
	public String selectStore()
	{
	
		String sourceStore = data.getString("source");
		return getSelectedTest(source,sourceStore);
	    
	}
	
	public int checkDestinationStore(JSONArray storeList)
	{
		int count = 0;
		for (Object store : storeList)
		{
			for (WebElement hub : destinationList)
			{
				if(store.toString().trim().equals(hub.getText().trim()))
				{
					count+=1;
				}
			}
		}
		
		return count ;
	}
	
	public String selectDestination()
	{
		String destinationStore = data.getString("destination");
		return getSelectedTest(destination,destinationStore);
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
    		driver.switchTo().alert().accept();
    	}
    	bulkProductAdd.click();
    	return Integer.parseInt(cartItem.getText());
    }
    
    public double calculateCartTotal()
    {
    	double sum = 0 ;
    	for (WebElement price : priceColumn)
    	{
    		sum = sum + Double.parseDouble(price.getText());
    	}
		return sum;
    	
    }
    
    public String getCartTotal()
    {
    	return cartTotal.getText().split("₹")[1].trim();
    }
    public String proceedtoCheckout()
    {
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
    	return reviewText.getText();
    }
    
    public double getSubTotal()
    {
    	visibilityOfElementLocated(reviewTotal);
    	String total = reviewTotal.getText().split("₹")[1].replace(",", "");
    	return Double.parseDouble(total);
    }
    public boolean placeOrder()
    {
    	invisibilityOfElement(loadingSymbol);
    	elementToBeClickable(placeOrderBtn);
    	placeOrderBtn.click();
    	return viewOrder.isDisplayed();
    }

    public Boolean getInternalWareHouseandWareHouse()
    {
    	Boolean flag = false ; 
    	flag = ! internalWarehouse.getText().isEmpty();
    	flag = ! warehouseAddress.getText().isEmpty();
    	return flag ;
    	
    }
    
    public boolean getstockTransferText()
    {
    	stockTransfer.click();
    	visibilityOfElementLocated(stockTransferText);
    	return stockTransferText.isDisplayed();
    }

	
	
}
