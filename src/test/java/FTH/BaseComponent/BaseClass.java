
package FTH.BaseComponent;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import FTH.Abstractcomponent.AbstractComponent;
import FTH.Util.ExtendReports;
import FTH.Util.ExtentReportListener;
import FTH.Util.RetryAnalyzer;

public class BaseClass {

	public WebDriver driver;
	AbstractComponent abstractObj;
	public ExtentReportListener reportListener;
	public Properties pro;
	public String server;
	public String url;


	public void initializeBrowser() throws IOException {
		ChromeOptions option = new ChromeOptions();
		option.setAcceptInsecureCerts(true);
		//option.addArguments("--headless");
		driver = new ChromeDriver(option);
		abstractObj = new AbstractComponent(driver);

	}

	@BeforeClass(alwaysRun = true)
	public void lunchBrowser() throws IOException {
		initializeBrowser();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		 pro = abstractObj.getGlobalProperties();
		url = pro.getProperty("url");
		driver.get(url);
	    waitForPageToLoad(driver);
	    PageFactory.initElements(driver, this);
	}

	public void visibilityOfElementLocated(By webelement) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(webelement));
	}
	
	public void waitForPageToLoad(WebDriver driver) {
	    new WebDriverWait(driver, Duration.ofSeconds(10)).until(
	        webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete")
	    );
	}
	
	@BeforeMethod
    public void setup(ITestContext context) {
        // ✅ Store WebDriver in TestNG Context
        context.setAttribute("WebDriver", driver);
    }
	
	//@AfterClass(alwaysRun = true)
	  public void tearDown() 
	  { 
		  if(driver!=null) 
		  { 
			  driver.quit(); 
		  } 
	  }
	 
}
