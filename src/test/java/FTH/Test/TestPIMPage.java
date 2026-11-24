package FTH.Test;

import org.testng.annotations.Test;
import org.testng.internal.BaseClassFinder;

import FTH.BaseComponent.BaseClass;
import FTH.PageObject.LoginPage;
import FTH.PageObject.PIMPage;

public class TestPIMPage extends BaseClass
{

    LoginPage loginPage ;
    PIMPage pimPage ;

    @Test 
    public void testAddEmployee() throws Exception
    {
        loginPage = new LoginPage(driver);
        pimPage = new PIMPage(driver);
        
        loginPage.loginApplication("Admin", "admin123");
        pimPage.selectPIMMenu();
        pimPage.clickAddEmployeeBtn();
        pimPage.enterEmployeeDetails();
    }


} 
