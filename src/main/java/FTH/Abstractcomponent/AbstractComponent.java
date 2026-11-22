package FTH.Abstractcomponent;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.awt.Desktop.Action;
import java.io.File;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Files;
import com.opencsv.CSVParser;

public class AbstractComponent {
	
	WebDriver driver;
	JavascriptExecutor js;
	WebDriverWait wait;
	Actions actions;
	public Select dropdown;

	public AbstractComponent(WebDriver driver) throws IOException {
		// TODO Auto-generated constructor stub
		this.driver = driver;
	
	}
	
	public Properties getGlobalProperties() throws IOException
	{
		Properties prop = new Properties();		
		FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\FTH\\resource\\GlobalData.properties");
		prop.load(file);
		return prop;
		
	}
	
	public String  getSelectedTest(WebElement webelement, String value)
	{
		dropdown = new Select(webelement);
		dropdown.selectByVisibleText(value);
		return dropdown.getFirstSelectedOption().getText();
		
		
	}
	
	public List<WebElement> getAllSelectOptions(WebElement webelement)
	{
		dropdown = new Select(webelement);
		return dropdown.getOptions();
	}

	public List<WebElement> getAllSelectedValue(WebElement webelement)
	{
		dropdown = new Select(webelement);
		return dropdown.getAllSelectedOptions();
	}
	
	
	public void  selectByVisibleText(WebElement webelement, String value)
	{
		dropdown = new Select(webelement);
		dropdown.selectByVisibleText(value);
		
	}
	public WebElement  getFirstSelectedOption(WebElement webelement)
	{
		dropdown = new Select(webelement);
		return dropdown.getFirstSelectedOption();
		
	}
	
	public Alert isAlertPresent()
	{
		wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		return  wait.until(ExpectedConditions.alertIsPresent());
	}
	public void  selectByValue(WebElement webelement, String value)
	{
		dropdown = new Select(webelement);
		dropdown.selectByValue(value);
		
	}
	
	public void  selectByIndex(WebElement webelement, int index)
	{
		dropdown = new Select(webelement);
		dropdown.selectByIndex(index);
		
	}
	
	public void visibilityOfElementLocated(WebElement webelement)
	{
		wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(webelement));
		}
	
	public void visibilityOfElementLocatedLongTime(WebElement webelement)
	{
		wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(webelement));
		}
	
	public void elementToBeClickable(WebElement webelement)
	{
		 wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(webelement));
		}
	
	public void invisibilityOfElement(WebElement webelement)
	{
		wait = new WebDriverWait(driver , Duration.ofSeconds(30));
		wait.until((ExpectedConditions.invisibilityOf(webelement)));
	}
	
	public List<HashMap<String, String>> getJsonData(String path) throws IOException
	{
		File file = new File(path);
		String jsonData = FileUtils.readFileToString(file , StandardCharsets.UTF_8); //json to string
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonData, new TypeReference<List<HashMap<String, String>>>() {});
		return data;
		
	}
	
	public JSONObject getJsonObject(String path)
	{
		try {
			File file = new File(path);
            String content = FileUtils.readFileToString(file , StandardCharsets.UTF_8);

            // Parse the content into a JSONObject and return it
            return new JSONObject(content);
        } catch (Exception e) {
            System.out.println("Error reading or parsing the file: " + e.getMessage());
            return null;
        }
	}
	
	

	public void moveToElement(WebElement element)
	{
		actions = new Actions(driver);
		actions.moveToElement(element).build().perform();
	}
	
	public void scrollUp()
	{
		js = (JavascriptExecutor)driver ;
		js.executeScript("window.scrollTo(0, 0);");
		
	}
	
	public void scrollDown()
	{
		js = (JavascriptExecutor)driver ;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}
	
	public void pressKeys(Keys key)
	{
		actions = new Actions(driver);
		actions.keyDown(key);
	}
	
	public void releaseKeys(Keys key)
	{

		actions = new Actions(driver);
		actions.keyUp(key);
	}

   public void clearLocalStorage()
   {
       js.executeScript("window.localStorage.clear();");
   }
   
   public static boolean waitForFileDownload(String downloadDir, String fileName, int timeoutSeconds) {
       File file = new File(downloadDir, fileName);
       int elapsed = 0;
       
       while (elapsed < timeoutSeconds) {
           if (file.exists()) {
               return true; // File found
           }
        
           elapsed++;
       }
       return false; // Timeout reached, file not found
   }
   
  public boolean checkAlertPresent()
  {
	 // adjust timeout if needed
      wait.until(ExpectedConditions.alertIsPresent());
      return true;
  }
}
