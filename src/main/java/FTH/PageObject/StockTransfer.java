package FTH.PageObject;

import java.io.IOException;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import FTH.Abstractcomponent.AbstractComponent;

public class StockTransfer extends AbstractComponent{

	WebDriver driver ;
	public double totalWeight = 0;
	public int productCount;
	
	public StockTransfer(WebDriver driver) throws IOException {
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
	
	@FindBy(xpath="//span[text()='Catalog']")
	WebElement catalog;
	
	@FindBy(xpath="//span[text()='Stock Movement']")
	WebElement stockMovement;
	
	@FindBy(xpath="//span[text()='Manage Stock Move']")
	WebElement stockMove;
	
	@FindBy(xpath="//*[@class='icon-head head-products']")
	WebElement title ;
	
	@FindBy(xpath="//*[@id='sourceInventory']")
	WebElement source ;
	
	@FindBy(xpath="//*[@id='loading_mask_loader']")
	WebElement loading;
	
	@FindBy(xpath="//*[@id='sourceInventory']/optgroup/option")
	WebElement sourceStoreList ;
	
	@FindBy(xpath="//*[@placeholder='Search by product name/id...']")
	WebElement search;
	
	@FindBy(xpath="//*[@class='grid availableProduct']/table/tbody/tr")
	WebElement product;
	
	@FindBy(xpath="//span[@class='textColor']")
	WebElement itemCount;
	
	@FindBy(xpath="//label[@class='textColor']")
	WebElement selectedSourceStore ;
	
	@FindBy(xpath="//*[@class='grid col-sm-12 cartProduct']/table/tbody/tr/td[4]")
	List<WebElement> weightColumn;
	
	@FindBy(xpath="//span[2][@class='textColor']")
	WebElement weight;
	
	@FindBy(xpath="//button[contains(text(),'Continue')]")
	WebElement continueBtn;
	
	@FindBy(xpath="//*[@id='destInventory']")
	WebElement destination;
	
	@FindBy(xpath="//*[text()='Continue']")
	WebElement destinationContinueBtn;
	
	@FindBy(xpath="//span[contains(text(),'Stock Movement Summary')]")
	WebElement reviewText;
	
	@FindBy(xpath="(//*[@class='itemValue'])[2]")
	WebElement reviewWeight;
	
	@FindBy(xpath="(//*[@class='itemValue'])[1]")
	WebElement reviewCount;
	
	@FindBy(xpath="//span[text()='Source:']/following-sibling::label")
	WebElement reviewSource ;
	
	@FindBy(xpath="//span[text()='Destination: ']/following-sibling::label")
	WebElement reviewDestination ;
	
	@FindBy(xpath="//b[text()='Description']/following-sibling::input")
	WebElement reviewCommand ;
	
	@FindBy(xpath="//button[text()='Submit']")
	WebElement submitBtn;
	
	@FindBy(xpath="//input[@type='radio' and @value='same']")
	WebElement sameasSource;
	
	@FindBy(xpath="//input[@type='radio' and @value='different']")
	WebElement differentasSource;
	
	JSONObject data = getJsonObject(System.getProperty("user.dir")+"\\src\\test\\java\\FTH\\Data\\StockMovement.json" );
	
	public void login() throws IOException
	{
		visibilityOfElementLocated(username);
		username.sendKeys(getGlobalProperties().getProperty("username"));
		password.sendKeys(getGlobalProperties().getProperty("password"));
		loginBtn.click();
		
	}
	
	public Boolean getStockMovement()
	{
		visibilityOfElementLocated(catalog);
		moveToElement(catalog);
		elementToBeClickable(stockMovement);
		moveToElement(stockMovement);
		elementToBeClickable(stockMove);
		return stockMove.isDisplayed();
	
	}
	
	public String selectStockMovement()
	{
		elementToBeClickable(stockMove);
		stockMove.click();
		return title.getText();
	}

	public String selectSourceStore()
	{
		String sourceStore = data.getString("source");
		elementToBeClickable(source);
		source.click();
		return getSelectedTest(source,sourceStore);
	}
	
	public int searchProduct()
	{
		loading.click();
		invisibilityOfElement(loading);
		JSONArray products = data.getJSONArray("products");
		for(Object productID : products)
		{
			search.click();
			search.sendKeys(productID.toString());
			elementToBeClickable(product);
			product.click();
			search.clear();
		}
		productCount = Integer.parseInt(itemCount.getText());
		return productCount;
	}
	
	public double getCalculatedWeight()
	{
		
		for(WebElement weight : weightColumn)
		{
			totalWeight = totalWeight + Double.parseDouble(weight.getText());
		}
		return totalWeight ;
	}
	
	public double getTotalWeight()
	{
		return Double.parseDouble(weight.getText());
	}

	public String selectDestintion()
	{
		String destinationStore = data.getString("destination");
		elementToBeClickable(continueBtn);
		continueBtn.click();
		elementToBeClickable(destination);
		return getSelectedTest(destination, destinationStore);
	}
	
	public Boolean checkInvalidProductID()
	{
		elementToBeClickable(destinationContinueBtn);
		destinationContinueBtn.click();
		String alertmsg = driver.switchTo().alert().getText();
		int count = 0;
		JSONArray invalidProductID = data.getJSONArray("invalid_products");
		for(Object invalidProduct : invalidProductID )
		{
			if(alertmsg.contains(invalidProduct.toString()))
			{
				count+=1;
			}
		}
		
		return  count==invalidProductID.length()?true:false; 
		
	}
	
	public String getReviewScreen()
	{
		driver.switchTo().alert().accept();
		totalWeight= 0;
		getCalculatedWeight();
		productCount = Integer.parseInt(itemCount.getText());
		elementToBeClickable(continueBtn);
		continueBtn.click();
		elementToBeClickable(destinationContinueBtn);
		destinationContinueBtn.click();
		return reviewText.getText();
	}

	public double getReviewWeight()
	{
		elementToBeClickable(reviewWeight);
		return Double.parseDouble(reviewWeight.getText());
	}
	
	public String getReviewSource()
	{
		elementToBeClickable(reviewSource);
		return reviewSource.getText();
	}
	
	public String getReviewDestination()
	{
		elementToBeClickable(reviewDestination);
		return reviewDestination.getText();
	}
	
	public Double getReviewProductCount()
	{
		elementToBeClickable(reviewCount);
		return Double.parseDouble(reviewCount.getText());
	}
	
	public void createStockMovement()
	{
		
		elementToBeClickable(reviewCommand);
		reviewCommand.sendKeys("Stock Movement Created by Automation");
		elementToBeClickable(submitBtn);
		submitBtn.click();
		driver.switchTo().alert().accept();
		
		
	}
	
	public void createHubtoHubTransfer()
	{
	    driver.navigate().refresh();
		String sourceStore = data.getString("hubsource");
		Alert alert = isAlertPresent();
		alert.accept();
		elementToBeClickable(source);
		source.click();
		selectByVisibleText(source,sourceStore);
		loading.click();
		invisibilityOfElement(loading);
		JSONArray products = data.getJSONArray("hubproducts");
		for(Object productID : products)
		{
			search.click();
			search.sendKeys(productID.toString());
			elementToBeClickable(product);
			product.click();
			search.clear();
		}
		
		String destinationStore = data.getString("hubdestination");
		elementToBeClickable(continueBtn);
		continueBtn.click();
		elementToBeClickable(destination);
		selectByVisibleText(destination, destinationStore);
		elementToBeClickable(destinationContinueBtn);
		destinationContinueBtn.click();
		elementToBeClickable(submitBtn);
		submitBtn.click();
		driver.switchTo().alert().accept();
		visibilityOfElementLocated(loading);
		alert = isAlertPresent();
		alert.accept();
	}
	
	public void sameAsSourceStore()
	{
		Alert alert = isAlertPresent();
		alert.accept();
		driver.navigate().refresh();
		String sourceStore = data.getString("differentsource");
		driver.navigate().refresh();
		elementToBeClickable(source);
		source.click();
		selectByVisibleText(source,sourceStore);
		loading.click();
		invisibilityOfElement(loading);
		JSONArray products = data.getJSONArray("hubproducts");
		for(Object productID : products)
		{
			search.click();
			search.sendKeys(productID.toString());
			elementToBeClickable(product);
			product.click();
			search.clear();
		}
		
		elementToBeClickable(continueBtn);
		continueBtn.click();
		elementToBeClickable(destination);
		selectByVisibleText(destination, sourceStore);
		elementToBeClickable(destinationContinueBtn);
		destinationContinueBtn.click();
		elementToBeClickable(sameasSource);
		sameasSource.click();
		elementToBeClickable(continueBtn);
		continueBtn.click();
		elementToBeClickable(reviewCommand);
		reviewCommand.sendKeys("same as destination Created by Automation");
		elementToBeClickable(submitBtn);
		submitBtn.click();
		//driver.switchTo().alert().accept();
		//driver.switchTo().alert().accept();
	}
	
	public void differentAsSourceStore()
	{
	
		driver.navigate().refresh();
		String sourceStore = data.getString("differentsource");
		//driver.switchTo().alert().accept();
		elementToBeClickable(source);
		source.click();
		selectByVisibleText(source,sourceStore);
		loading.click();
		invisibilityOfElement(loading);
		JSONArray products = data.getJSONArray("differentproducts");
		for(Object productID : products)
		{
			search.click();
			search.sendKeys(productID.toString());
			elementToBeClickable(product);
			product.click();
			search.clear();
		}
		
		elementToBeClickable(continueBtn);
		continueBtn.click();
		elementToBeClickable(destination);
		selectByVisibleText(destination, sourceStore);
		elementToBeClickable(destinationContinueBtn);
		destinationContinueBtn.click();
		elementToBeClickable(differentasSource);
		differentasSource.click();
		elementToBeClickable(continueBtn);
		continueBtn.click();
		JSONArray byproducts = data.getJSONArray("byproduct");
		for(Object productID : byproducts)
		{
			search.clear();
			search.click();
			search.sendKeys(productID.toString());
			elementToBeClickable(product);
			product.click();
			search.clear();
		}
		elementToBeClickable(continueBtn);
		continueBtn.click();
		elementToBeClickable(reviewCommand);
		reviewCommand.sendKeys("differnt as destination Created by Automation");
		elementToBeClickable(submitBtn);
		submitBtn.click();
		driver.switchTo().alert().accept();
		visibilityOfElementLocated(loading);
		//driver.switchTo().alert().accept();
	}
}
