package FTH.PageObject;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;

import FTH.Abstractcomponent.AbstractComponent;
import FTH.Util.ExcelReaderUtil;


public class LoginPage extends AbstractComponent
{
    WebDriver driver ;
    public LoginPage(WebDriver driver) throws IOException
    {
        super(driver);
        this.driver = driver ;
        PageFactory.initElements(driver ,this);
    }

@FindBy(xpath="//*[@name='username']")
WebElement userName ; 

@FindBy(xpath="//*[@name='password']")
WebElement password ;

@FindBy(xpath="//*[@type='submit']")
WebElement loginBtn ;

@FindBy(xpath="//*[text()='Invalid credentials']")
WebElement invalidLoginMsg;

public void loginApplication(String name, String pswrd)
{
    visibilityOfElementLocated(userName);
    userName.sendKeys(name);
    password.sendKeys(pswrd);
   loginBtn.click();
}

public String checkInvalidLoginMsg()
{
    return invalidLoginMsg.getText();
}




}