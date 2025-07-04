package FTH.PageObject;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import FTH.Abstractcomponent.AbstractComponent;

public class ComboCreation extends AbstractComponent {
	
	WebDriver driver;
	JavascriptExecutor js;
	List<HashMap<String , String>> data ;
	AbstractComponent abstractObj = new AbstractComponent(driver);
	Properties pro = abstractObj.getGlobalProperties();
	String server = pro.getProperty("server");
	String url = pro.getProperty("url");
	
	public ComboCreation(WebDriver driver) throws IOException
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		js = (JavascriptExecutor)driver;
		
	}
	
	@FindBy(id="username")
	WebElement username ;
	
	@FindBy(id="login")
	WebElement password ;
	
	@FindBy(id="authme")
	WebElement loginBtn ;
	
	@FindBy(xpath="//*[text()='Catalog']")
	WebElement catalogMenu;
	
	@FindBy(xpath="//*[text()='Manage Products']")
	WebElement manageProduct;
	
	@FindBy(xpath="//*[text()='Add Product']")
	WebElement addProduct;
	
	@FindBy(xpath="//*[@title='Attribute Set']")
	WebElement attributeSet ;
	
	@FindBy(xpath="//*[@id='product_type']")
	WebElement productType;
	
	@FindBy(xpath="//*[text()='Continue']")
	WebElement continueBtn;
	
	@FindBy(xpath="//*[@id='name']")
	WebElement productName;
	
	@FindBy(xpath="//*[@id='short_description']")
	WebElement shortDesc;
	
	@FindBy(xpath="//*[@id='sku']")
	WebElement sku ;
	
	@FindBy(xpath="//*[@id='visible_on_homepage']")
	WebElement visibleOnHomePage ;
	
	@FindBy(xpath="//*[text()='Save and Continue Edit']")
	WebElement saveAndContinue ;
	
	@FindBy(xpath="//*[@id='price']")
	WebElement price ;
	
	@FindBy(xpath="//*[@id='mrp']")
	WebElement mrp ;
	
	@FindBy(xpath="//*[@id='tax_class_id']")
	WebElement taxClass ;
	
	@FindBy(xpath="//*[@id='product_info_tabs_inventory']")
	WebElement inventoryTab ;
	
	@FindBy(xpath="//*[text()='Websites']")
	WebElement webSitesTabs;
	
	@FindBy(xpath="//*[@id='product_website_1']")
	WebElement websiteCheckBox;
	
	@FindBy(xpath="//*[text()='Combo Items']")
	WebElement comboItems;
	
	@FindBy(xpath="//*[@id='super_product_grid_filter_entity_id']")
	WebElement idFilter ;
	
	@FindBy(xpath="//span[text()='Search']")
	WebElement search ;
	
	@FindBy(xpath="//input[@name='qty']")
	WebElement defaultQty ;
	
	@FindBy(xpath="//*[@id='store_switcher']")
	WebElement storeSelect;
	
	@FindBy(xpath="//*[text()='General']")
	WebElement generalTab;
	
	@FindBy(xpath="//*[@id='sell_by']")
	WebElement sellBy;
	
	
	@FindBy(xpath="//*[@id='inventory_qty']")
	WebElement qty;
	
	@FindBy(xpath="//*[@id='inventory_min_sale_qty']")
	WebElement minQy;
	
	@FindBy(xpath="//*[@id='inventory_is_qty_decimal']")
	WebElement allowDecimal ;
	
	@FindBy(xpath="//*[@id='inventory_qty_increments']")
	WebElement qtyIncrement ;
	
	@FindBy(xpath="//*[@id='inventory_stock_availability']")
	WebElement availability ;
	
	@FindBy(xpath="//*[@title='Back']")
	WebElement backBtn ;
	
	
	
	/*
	
	@FindBy(xpath="//*[text()='Duplicate']")
	WebElement duplicateBtn
	
	@FindBy(xpath="")
	WebElement

	@FindBy(xpath="")
	WebElement
	
	@FindBy(xpath="")
	WebElement
	
	@FindBy(xpath="")
	WebElement
	
	@FindBy(xpath="")
	WebElement
	
	@FindBy(xpath="")
	WebElement
	
	@FindBy(xpath="")
	WebElement
	*/
	
	public void login() throws IOException
	{
		visibilityOfElementLocated(username);
		username.sendKeys(getGlobalProperties().getProperty("username"));
		password.sendKeys(getGlobalProperties().getProperty("password"));
		loginBtn.click();
		
	}
	
	public void selectManageProductPage()
	{
		catalogMenu.click();
		visibilityOfElementLocated(manageProduct);
		manageProduct.click();
	}
	
	public void productCreate()
	{
		//visibilityOfElementLocated(addProduct);
		addProduct.click();
		selectByVisibleText(attributeSet, "Fish");
		selectByVisibleText(productType, "Virtual Product");
		continueBtn.click();
	}
	
	public void updateProductInfo() throws IOException, InterruptedException
	{
		data = getJsonData(System.getProperty("user.dir")+"\\\\src\\\\test\\\\java\\\\FTH\\\\Data\\\\ComboConfigData.json");
		for(int i = 0 ;i< data.size();i++)
		{
		productName.sendKeys(data.get(i).get("name"));
		shortDesc.sendKeys(data.get(i).get("name"));
		String skuz = UUID.randomUUID().toString();
		sku.sendKeys(data.get(i).get("name")+skuz);
		selectByVisibleText(visibleOnHomePage, "Yes");
		saveAndContinue.click();
		price.sendKeys(data.get(i).get("price"));
		mrp.clear();
		mrp.sendKeys(data.get(i).get("mrp"));
		selectByVisibleText(taxClass, "None");
		js.executeScript("window.scrollBy(0,-550)");
		webSitesTabs.click();
		elementToBeClickable(websiteCheckBox);
		websiteCheckBox.click();
		comboItems.click();
		idFilter.sendKeys(data.get(i).get("comolineitem1"));
		search.click();
		String productID =data.get(i).get("comolineitem1");
		elementToBeClickable(driver.findElement(By.cssSelector("td input[value='" + productID + "']")));
		driver.findElement(By.cssSelector("td input[value='" + productID + "']")).click();
		defaultQty.sendKeys(data.get(i).get("defaultqty1"));
		idFilter.clear();
		idFilter.sendKeys(data.get(i).get("comolineitem2"));
		search.click();
		productID =data.get(i).get("comolineitem2");
		elementToBeClickable(driver.findElement(By.cssSelector("td input[value='" + productID + "']")));
		driver.findElement(By.cssSelector("td input[value='" + productID + "']")).click();
		defaultQty.sendKeys(data.get(i).get("defaultqty2"));
		saveAndContinue.click();
		visibilityOfElementLocatedLongTime(storeSelect);
		//Thread.sleep(3i);
		selectByValue(storeSelect, "1");
		driver.switchTo().alert().accept();
		generalTab.click();
		sellBy.sendKeys(data.get(i).get("sellby"));
		js.executeScript("window.scrollBy(0,-250)");
		inventoryTab.click();
		qty.clear();
		qty.sendKeys(data.get(i).get("qty"));
		minQy.clear();
		minQy.sendKeys(data.get(i).get("minqty"));
		if(data.get(i).get("sellby").equals("kg"))
		{
		selectByVisibleText(allowDecimal, "Yes");	
		}
		qtyIncrement.clear();
		qtyIncrement.sendKeys(data.get(i).get("incrqty"));
		selectByValue(availability, "1");
		saveAndContinue.click();
		driver.get("https://" + server + url);
		selectManageProductPage();
		productCreate();
		}
	
	
	}
		
	
	

}