package FTH.Test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import FTH.BaseComponent.BaseClass;
import FTH.DataProvider.DataProviders;
import FTH.PageObject.LoginPage;

public class TestLoginPage extends BaseClass {


    LoginPage loginPageObject ;
    @Test(dataProvider ="getData", dataProviderClass = DataProviders.class)
    public void testLoginPage(String username , String password , String testcase) throws IOException
    {
        loginPageObject = new LoginPage(driver);

        if(testcase.equalsIgnoreCase("valid"))
        {
            loginPageObject.loginApplication(username, password);
            Assert.assertEquals(driver.getCurrentUrl(), "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
            
        }
        else
        {
             loginPageObject.loginApplication(username, password);
             Assert.assertEquals(loginPageObject.checkInvalidLoginMsg(),"Invalid credentials");
        }
    }
    
}
