package FTH.Test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import FTH.BaseComponent.BaseClass;
import FTH.PageObject.Registration;

public class TestRegistration extends BaseClass {
    
Registration regObject ;

@Test 
public void test_registration_page() throws IOException
{
    regObject = new Registration(driver);
    regObject.selectRegistrationMenu();
    Assert.assertEquals(driver.getCurrentUrl(), "https://demo.nopcommerce.com/register?returnUrl=%2F");

}

@Test public void test_register_user() throws IOException
{
    regObject.fillRegistrationForm();
    regObject.submitRegistrationForm();
}
} 
