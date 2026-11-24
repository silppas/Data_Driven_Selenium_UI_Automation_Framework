package FTH.PageObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import FTH.Abstractcomponent.AbstractComponent;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Registration extends AbstractComponent
{
    
    WebDriver driver ;

public Registration(WebDriver driver) throws IOException 
{

        super(driver);
        // TODO Auto-generated constructor stub
        this.driver = driver ;
        PageFactory.initElements(driver, this);
    
}


@FindBy (className ="ico-register")
WebElement regMenu ;

@FindBy(xpath="//*[@id=\"gender\"]//input")
List<WebElement> gender;

@FindBy(xpath="//*[@id='FirstName']")
WebElement firstName;

@FindBy(xpath="//*[@id='LastName']")
WebElement lastName;

@FindBy(xpath="//*[@type='email' and @id='Email']")
WebElement email;

@FindBy(xpath="//*[@id='Company']")
WebElement company;

@FindBy(xpath="//*[@id='Password']")
WebElement password;

@FindBy(xpath="//*[@id='ConfirmPassword']")
WebElement confirmPassword;

@FindBy(xpath="//*[@id='register-button']")
WebElement registerButton;


public void selectRegistrationMenu()
{
    visibilityOfElementLocated(regMenu);
    regMenu.click();
}

public void fillRegistrationForm() throws IOException
{
List<HashMap<String,String>> data = getJsonData(System.getProperty("user.dir")+"\\src\\test\\java\\FTH\\Data\\RegisterData.json");
for (HashMap<String,String> formData : data)
{
    for(WebElement genderOption : gender)
    {
        if (genderOption.getText().equalsIgnoreCase(formData.get("gender")))
        {
            genderOption.click();
            break;
        }
    }
    firstName.sendKeys(formData.get("firstName"));
    lastName.sendKeys(formData.get("lastName"));
    email.sendKeys(formData.get("email"));
    company.sendKeys(formData.get("company"));
    password.sendKeys(formData.get("password"));
    confirmPassword.sendKeys(formData.get("confirmPassword"));
}
}

public void submitRegistrationForm()
{
    registerButton.click();
}
}