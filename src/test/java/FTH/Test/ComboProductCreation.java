package FTH.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import FTH.BaseComponent.BaseClass;
import FTH.PageObject.ComboCreation;

public class ComboProductCreation extends BaseClass{
	
	ComboCreation comboPageObj;
	
	@Test
	public void comboProductCreateTest() throws IOException, InterruptedException
	{
		comboPageObj = new ComboCreation(driver);
		comboPageObj.login();
		comboPageObj.selectManageProductPage();
		comboPageObj.productCreate();
		comboPageObj.updateProductInfo();
		
		
		
		
	}
	

}
