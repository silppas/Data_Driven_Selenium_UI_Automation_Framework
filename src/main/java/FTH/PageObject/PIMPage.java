package FTH.PageObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import FTH.Abstractcomponent.AbstractComponent;

public class PIMPage extends AbstractComponent
{

public PIMPage(WebDriver driver) throws IOException
{
    super(driver);
    PageFactory.initElements(driver, this);

}

@FindBy(xpath="//span[text()='PIM']")
WebElement pimMenu ;

@FindBy(xpath="//*[text()=' Add ']")
WebElement addBtn ;

@FindBy(xpath="//*[@name='firstName']")
WebElement firstName ;

@FindBy(xpath="//*[@name='middleName']")
WebElement middleName ;

@FindBy(xpath="//*[@name='lastName']")
WebElement lastName ;

@FindBy(xpath="//*[text()=' Save ']")
WebElement saveBtn;

public void selectPIMMenu()
{
    visibilityOfElementLocated(pimMenu);
    pimMenu.click();
}

public void clickAddEmployeeBtn()
{
    visibilityOfElementLocated(addBtn);
    addBtn.click();
}

public void enterEmployeeDetails() throws IOException
{
    visibilityOfElementLocated(firstName);
    List<HashMap<String , String>> empData = getJsonData(System.getProperty("user.dir") + "/src/test/java/FTH/Data/EmployeeData.json");
    for(HashMap<String,String> emp : empData)

        {
            firstName.sendKeys(emp.get("firstName"));
            middleName.sendKeys(emp.get("middleName"));
            lastName.sendKeys(emp.get("lastName"));
            saveBtn.click();
            
        }
}

} 
