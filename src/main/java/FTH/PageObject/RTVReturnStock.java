package FTH.PageObject;

import java.io.IOException;
import java.text.Collator;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.json.JSONObject;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import FTH.Abstractcomponent.AbstractComponent;
import FTH.Util.CSVReaderUtil;

public class RTVReturnStock  extends AbstractComponent{

	
	WebDriver driver;
	
	public RTVReturnStock(WebDriver driver) throws IOException {
		super(driver);
		this.driver = driver ;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//*[@name='email']")
	WebElement username ;
	
	@FindBy(xpath="//*[@name='password']")
	WebElement password ;
	
	@FindBy(xpath="//*[@type='submit']")
	WebElement loginBtn ;
	
	@FindBy(xpath="//*[@class='sidebar-toggle']")
	WebElement menuBar;
	
	@FindBy(xpath="//*[text()='Purchase']")
	WebElement purchaseMenu;
	
	@FindBy(xpath="//*[text()='RTV Requests']")
	WebElement rtvRequestMenu;
	
	@FindBy(xpath="//*[text()='Create Vendor Return']")
	WebElement createVendorReturn;
	
	@FindBy(xpath="//*[@id='vendor']")
	WebElement vendorDropDown;
	
	@FindBy(xpath="//*[@id='partner_hub']")
	WebElement vendorPickUp;
	
	@FindBy(xpath="//*[@id='partner_hub']/option")
	List<WebElement> vendorPickupOptions;
	
	@FindBy(xpath="//*[@id='hub_code']/option")
	WebElement vendorDropHubOptions;
	
	@FindBy(xpath="//*[@id='hub_code']")
	WebElement dropHub;
	
	@FindBy(xpath="//*[@id='partner_ref_id']")
	WebElement partnerRef;
	
	@FindBy(xpath="//*[@id='return_date']")
	WebElement returnDate;
	
	@FindBy(xpath="//*[@id='notes']")
	WebElement notes;
	
	@FindBy(xpath="//*[@id='upload_file']")
	WebElement uploadFile;
	
	@FindBy(xpath="//*[text()='Download sample file']")
	WebElement downloadSampleFile;
	
	@FindBy(xpath="//*[@id='productsTable']/tbody/tr/td")
	WebElement producTable;
	
	@FindBy(xpath="//*[@id='productsTable']/tbody/tr/td")
	List<WebElement> skus;
	
	@FindBy(xpath="//*[@id='submitBtn']")
	WebElement submitBtn;
	
	@FindBy(xpath="//*[contains(text(),'Sync to Magento')]")
	WebElement syncToMagento;
	
	@FindBy(xpath="//*[@id='rtvTable']/tbody/tr")
	WebElement existingShipments;
	
	@FindBy(xpath="//*[@class='title']")
	WebElement pageTitle ;
	
	@FindBy(xpath="//*[@for='vendor']")
	WebElement selectVendorText;
	
	@FindBy(xpath="//*[@for='partner_hub']")
	WebElement selectPickUpText;
	
	@FindBy(xpath="//*[@for='hub_code']")
	WebElement selectDrophubText;
	
	@FindBy(xpath="//*[@for='partner_ref_id']")
	WebElement referenceIDText;
	
	@FindBy(xpath="//*[@for='return_date']")
	WebElement returnDateText;
	
	@FindBy(xpath="//*[@for='notes' and contains(text(),'Notes')]")
	WebElement notesText;
	
	@FindBy(xpath="//*[@class='datepicker datepicker-dropdown dropdown-menu datepicker-orient-left datepicker-orient-top']")
	WebElement datePicker;
	
	@FindBy(xpath="//*[@for='upload_file']")
	WebElement fileUploadText ;
	
	@FindBy(xpath="//*[@for=\"notes\" and contains(text(),'Sample File Format')]")
	WebElement sampleFileText;
	
	@FindBy(xpath="//*[@class='btn btn-default pull-right']")
	WebElement goBackBtn ;
	
	@FindBy(xpath="//*[@class='day']")
	List<WebElement> day;
	
	@FindBy(xpath="//*[contains(text(),'Return Order -')]")
	WebElement returnOrder ;
	
	@FindBy(xpath="(//*[@id='productsTable'])[1]/tbody/tr/td[2]")
	WebElement fileType;
	
	@FindBy(xpath="//strong[text()='File Upload']")
	WebElement fileUploadHistory ;
	
	@FindBy(xpath="(//*[@id='productsTable'])[1]/tbody/tr/td[3]")
	WebElement filePath;
	
	@FindBy(xpath="(//*[@id='productsTable'])[1]/tbody/tr/td[4]")
	WebElement createdDate ;
	
	@FindBy(xpath="(//*[@id='productsTable'])[1]/tbody/tr/td[5]")
	WebElement createdUser;
	
	@FindBy(xpath="//th[text()='History']")
	WebElement history;
	
	@FindBy(xpath="(//*[@class='table table-striped'])[3]/tbody/tr/td/p")
	List<WebElement> historyData;
	
	@FindBy(xpath="//button[text()='Add document']")
	WebElement addDocument ;
	
	@FindBy(xpath="//*[@id='rtvTable']/tbody/tr/td")
	WebElement returnOrderId;
	
	@FindBy(xpath="//*[@name='search']")
	WebElement searchTextBox ;
	
	@FindBy(xpath="//*[@type=\"submit\"]")
	WebElement searchbtn ;
	
	@FindBy(xpath="(//*[@id='productsTable'])[2]/tbody/tr/td[2]")
	List<WebElement> editSkuColumn;
	
	@FindBy(xpath="(//*[@id='productsTable'])[2]/tbody/tr/td[4]")
	List<WebElement> editReqQtyColumn;
	
	@FindBy(xpath="(//*[@id='productsTable'])/tbody/tr/td[1]")
	List<WebElement> skuIDColumn ;
	
	@FindBy(xpath="(//*[@id='productsTable'])/tbody/tr/td[2]")
	List<WebElement> qtyColumn ;
	
	@FindBy(id="username")
	WebElement magentousername ;
	
	@FindBy(id="login")
	WebElement magentopassword ;
	
	@FindBy(id="authme")
	WebElement magentologinBtn ;
	
	@FindBy(xpath="//span[text()='Catalog']")
	WebElement catalog;
	
	@FindBy(xpath="//*[text()='Manage Shipments']")
	WebElement manageShipment ; 
	
	@FindBy(xpath="//*[@class='  level2']/a/span[text()='Shipments']")
	WebElement shipment;
	
	@FindBy(xpath="//select[@id='stock_id']")
	WebElement shipmentFor;
	
	@FindBy(xpath="//*[@id='dbaux_cataloginventoryadmin_shipment_grid_filter_notes']")
	WebElement notesSearch;
	
	@FindBy(xpath="//*[@id='dbaux_cataloginventoryadmin_shipment_grid_table']/tbody")
	WebElement shipmentRecord;
	
	@FindBy(xpath="//*[@id='dbaux_cataloginventoryadmin_shipment_grid_table']/tbody/tr/td[4]")
	WebElement shipmentSource;
	
	@FindBy(xpath="//*[@id='dbaux_cataloginventoryadmin_shipment_grid_table']/tbody/tr/td[5]")
	WebElement shipmentNotes;
	
	@FindBy(xpath="//*[@id='dbaux_cataloginventoryadmin_shipment_grid_table']/tbody/tr/td[6]")
	WebElement shipmentStatus;
	
	@FindBy(xpath="//table[@id='attribute-options-table']/tbody/tr/td[3]")
	List<WebElement> shipmentProductID;
	
	@FindBy(xpath="//table[@id='attribute-options-table']/tbody/tr/td[6]")
	List<WebElement> shipmentProductQty;
	
	@FindBy(xpath="//span[text()='Add Shipment To Inventory']")
	WebElement addShipmentToInventoryBtn;
	
	@FindBy(xpath="//span[text()='Sales']")
	WebElement salesMenu;
	
	@FindBy(xpath="//span[text()='Orders']")
	WebElement orderMenu;
	
	@FindBy(xpath="//*[@id='edit_order_status']")
	WebElement orderStatus;
	
	@FindBy(xpath="//tr[td[@class='label']/label[contains(text(),'Source')]]/td/strong")
	WebElement source;
	
	@FindBy(xpath="//*[@id='sales_order_grid_filter_real_order_id']")
	WebElement orderIdSearch;
	
	@FindBy(xpath="//*[@class='item-text']/following-sibling::div")
	List<WebElement> orderProductID; 
	
	@FindBy(xpath="//table[@id='sales_order_grid_table']/tbody/tr/td[2]")
	WebElement OrderIncrementID;
	
	@FindBy(xpath="//table[@id='sales_order_grid_table']/tbody/tr/td[3]")
	WebElement purchasedFrom;
	
	@FindBy(xpath="//*[@class='qty-table']/tbody/tr/td[text()='Ordered']/following-sibling::td")
	List<WebElement> orderedQty;
	
	@FindBy(xpath="//*[@class='qty-table']/tbody/tr/td[text()='Invoiced']/following-sibling::td")
	List<WebElement> invoicedQty;
	
	@FindBy(xpath="//*[@class='qty-table']/tbody/tr/td[text()='Shipped']/following-sibling::td")
	List<WebElement> shippedQty;
	
	@FindBy(xpath="//*[@class='md-icon md-icon-font md-size-3x md-theme-default' and text()='archive']")
	WebElement incomingStock ;
	
	@FindBy(xpath="//*[contains(text(),'Shipment ID:')]")
	List<WebElement> shipmentList;
	
	@FindBy(xpath="//*[contains(text(),'rtv_request')]")
	List<WebElement> hubshipmentSource;
	
	@FindBy(xpath="//*[@class='count-items']")
	List<WebElement> itemcount;
	
	@FindBy(xpath="//*[@class='return-pickup']")
	List<WebElement> returnPickupBtn;
	
	@FindBy(xpath="//*[text()='Return PickUp']")
	WebElement returnpickupTitle;
	
	@FindBy(xpath="//*[@class='md-card-content padding-card-content p-add']/div")
	WebElement pickUpAddress;
	
	@FindBy(xpath="//*[contains(text(),'Source:')]")
	WebElement hubshipmentdetailsSource;
	
	@FindBy(xpath="//*[contains(text(),'Total Shipment Items :')]")
	WebElement totalShipmentItem;
	
	@FindBy(xpath="//*[text()='add_to_queue']")
	WebElement confirmBtn;
	
	@FindBy(xpath="//*[@class='md-button-content' and text()='Confirm']")
	WebElement confirmShipmentBtn;
	
	@FindBy(xpath="//*[text()='chevron_left']")
	WebElement backBtn;
	
	@FindBy(xpath="//*[@class='col-sm-1 count text-center item-index-container-green']/following-sibling::div[1]")
	List<WebElement> hubProductID;
	
	
	@FindBy(xpath="//*[@class='product-id product-group border-to-top']/span")
	List<WebElement> returnProductID;
	
	@FindBy(xpath="//*[@class='text-center product-group text-center border-to-left border-to-top']")
	List<WebElement> centralStock ;
	
	@FindBy(xpath="//*[@class='text-center td-green-clr text-center border-to-left']")
	List<WebElement> currentStock ;
	
	@FindBy(xpath="//*[contains(text(),'Shipment added to hub inventory')]")
	WebElement shipmentSuccessMsg;

	@FindBy(id="user-name")
	WebElement hubuserName ;
	
	@FindBy(id="password")
	WebElement hubpassword ;
	
	@FindBy(xpath="//div[@class='md-button-content' and text()='Login']")
	WebElement hubloginBtn ;
	
	@FindBy(xpath="//h3[@class='icon-head head-shipment']")
	WebElement magentoShipmentID;
	
	@FindBy(xpath="//a[contains(text(),'request_csv_')]")
	WebElement uploadedcsv;
	
	@FindBy(xpath="//h1[text()='Whoops, looks like something went wrong.']")
	WebElement errorMsg;
	
	CSVReaderUtil csvReader ;
	JSONObject data = getJsonObject(System.getProperty("user.dir")+"\\src\\test\\java\\FTH\\Data\\RTVTestData.json");
	Properties prop = getGlobalProperties();
	Alert alert ;
    String returnOrderIDs ;
    String fileUploadURL;
    public int partnerID ;
    List<String> csvproductID;
    List<String> csvproductQty;
    String OrderID;
    String RTV_Ref;
    String address ;
    int index;
    String magentoShipmentIDs ;
	public void login()
	{
		String url = prop.getProperty("blackbuckurl");
		String usernametext =prop.getProperty("busername");
		String passwordtext =prop.getProperty("bpassword");
		driver.get(url);
		elementToBeClickable(username);
		username.sendKeys(usernametext);
		password.sendKeys(passwordtext);
		loginBtn.click();
		
		
	}
	
	public boolean rtvMenuDisplayed()
	{
		menuBar.click();
		elementToBeClickable(purchaseMenu);
		purchaseMenu.click();
		elementToBeClickable(rtvRequestMenu);
		return rtvRequestMenu.isDisplayed();
	}
	
	public boolean selectRTVMenu()
	{
		String url = prop.getProperty("blackbuckurl")+"rtvs";
		rtvRequestMenu.click();
		return driver.getCurrentUrl().equalsIgnoreCase(url);
	}
	
	public boolean checkPageTitle()
	{
		elementToBeClickable(pageTitle);
		return pageTitle.getText().equalsIgnoreCase("Create Return Order");
	}
	
	
	public boolean vendorFieldDisplayed()
	{
		elementToBeClickable(vendorDropDown);
		return vendorDropDown.isDisplayed() && selectVendorText.getText().equalsIgnoreCase("Select Vendor *") ;
	}
	
	
	public boolean isVendorList()
	{
		List<WebElement> vendors = getAllSelectOptions(vendorDropDown);
		return vendors.size()>1;
	}
	

	public boolean pickHubDisplayed()
	{
		elementToBeClickable(vendorPickUp);
		return vendorPickUp.isDisplayed() && selectPickUpText.getText().equalsIgnoreCase("Select Vendor Pickup Hub *");
	}
	
	
	public boolean isPickupStoreListed()
	{
		
		String vendorName = data.getString("vendor");
		selectByVisibleText(vendorDropDown, vendorName);
		List<WebElement> vendors = getAllSelectOptions(vendorPickUp);
		return vendors.size()>=1;
	}
	
	public boolean dropHubDisplayed()
	{
		elementToBeClickable(dropHub);
		return dropHub.isDisplayed() && selectDrophubText.getText().equalsIgnoreCase("Select Drop Hub *");
	}
	
	
	public boolean isDropStoreListed()
	{
		List<WebElement> vendors = getAllSelectOptions(dropHub);
		return vendors.size()>=1;
	}
	
	public boolean createVendorReturnBtnDisplayed()
	{
		elementToBeClickable(createVendorReturn);
		return createVendorReturn.isDisplayed();
	}

	public boolean isCreateVendorReturnPageOpened()
	{
		createVendorReturn.click();
		String url = prop.getProperty("blackbuckurl")+"rtvs/create";
		return driver.getCurrentUrl().equalsIgnoreCase(url);
	}
	
	public boolean referenceIDDisplayed()
	{
		elementToBeClickable(partnerRef);
		return partnerRef.isDisplayed() && referenceIDText.getText().equalsIgnoreCase("Partner Ref#");
	}
	
	public boolean returnDateFieldDisplayed()
	{
		elementToBeClickable(returnDate);
		return returnDate.isDisplayed() && returnDateText.getText().equalsIgnoreCase("Return Date *");
	}
	
	public boolean notesFieldDisplayed()
	{
		elementToBeClickable(notes);
		return notes.isDisplayed() && notesText.getText().equalsIgnoreCase("Notes");
	}
	
	public boolean checkReferenceIDDefaultValueEmpty()
	{
		return partnerRef.getText().isEmpty();
	}
	public boolean checkReturnDateDefaultValueEmpty()
	{
		return returnDate.getText().isEmpty();
	}
	
	public boolean checkNotesDefaultValueEmpty()
	{
		return notes.getText().isEmpty();
	}
	
	public boolean datePickerDisplayed()
	{
		returnDate.click();
		visibilityOfElementLocated(datePicker);
		return datePicker.isDisplayed();
	}
	
	public boolean fileUploadButtonDisplayed()
	{
		return uploadFile.isDisplayed() && fileUploadText.getText().equalsIgnoreCase("Upload File *");
	}
	
	public boolean sampleTemplateDisplayed()
	{
		return downloadSampleFile.isDisplayed() && sampleFileText.getText().equalsIgnoreCase("Sample File Format");
	}
	
	public boolean goBackBtnDisplayed()
	{
		return goBackBtn.isDisplayed();
	}
	
	public boolean checkGoBack()
	{
		goBackBtn.click();
		String url = prop.getProperty("blackbuckurl")+"rtvs";
		elementToBeClickable(createVendorReturn);
		return driver.getCurrentUrl().equalsIgnoreCase(url);
		
	}
	
	public boolean checkVendorValidation()
	{
		createVendorReturn.click();
		elementToBeClickable(vendorDropDown);
		submitBtn.click();
		alert = isAlertPresent();
		String alertMsg = alert.getText();
		alert.accept();
		return alertMsg.equalsIgnoreCase("Please select the vendor.");
	}
	
	public boolean checkReferenceIDValidation()
	{
		String vendorName = data.getString("vendor");
		selectByVisibleText(vendorDropDown, vendorName);
		submitBtn.click();
		alert = isAlertPresent();
		String alertMsg = alert.getText();
		alert.accept();
		return alertMsg.equalsIgnoreCase("Please enter reference ID");
	}
	
	public boolean checkReturnDateValidation()
	{
		String vendorName = data.getString("vendor");
		selectByVisibleText(vendorDropDown, vendorName);
		submitBtn.click();
		alert = isAlertPresent();
		String alertMsg = alert.getText();
		alert.accept();
		return alertMsg.equalsIgnoreCase("Please select the return date.");
	}
	
	public void selectDate()
	{
		LocalDate currentDate = LocalDate.now();
        String today = String.valueOf(currentDate.getDayOfMonth());
        returnDate.click();
        visibilityOfElementLocated(day.get(1));
       day.stream().filter(day->day.getText().equalsIgnoreCase(today)).forEach(day-> day.click());;
    
	}
	
	public boolean checkFileUploadValidation() throws InterruptedException
	{
		isPickupHubSelected();
		isDropHubSelected();
		selectDate();
		notes.click();
		submitBtn.click();
		alert = isAlertPresent();
		String alertMsg = alert.getText();
		System.out.print(alertMsg);
		alert.accept();
		return alertMsg.equalsIgnoreCase("Please choose the request skus csv.");
	}
	
	public boolean isVendorSelected()
	{
		driver.navigate().refresh();
		String vendorName = data.getString("vendor");
		String vendor = getSelectedTest(vendorDropDown, vendorName);
		return vendor.equalsIgnoreCase(vendorName);
	}
	
	public boolean isPickupHubSelected()
	{
	
		String pickuphub = data.getString("pickup");
		String pickup = getSelectedTest(vendorPickUp, pickuphub);
		return pickup.equalsIgnoreCase(pickuphub);
	}
	
	
	public boolean isDropHubSelected()
	{
		String drophub = data.getString("drop");
		String drop = getSelectedTest(dropHub, drophub);
		return drop.equalsIgnoreCase(drophub);
	}
	
	
	public boolean enterReferenceID()
	{
		 Random rand = new Random();
	     partnerID = rand.nextInt(9000) + 1000;
	     partnerRef.sendKeys(String.valueOf(partnerID));
	     return !partnerRef.getAttribute("value").isEmpty();
	    
	}
	
	public boolean selectReturnDate()
	{
		selectDate();
		return returnDate.isDisplayed();
	}
	
	public boolean enterNotes()
	{
		notes.sendKeys("RTV Return Stock by Automation");
		return !notes.getAttribute("value").isEmpty();
	}
	
	public void fileUpload(String path)
	{
	  uploadFile.sendKeys(System.getProperty("user.dir")+ path);
	}
	
	public boolean invalidFileUploads()
	{
		fileUpload("\\src\\test\\java\\RTVTestData\\invalidfiletype.html");
		Boolean check = checkAlertPresent();
		alert = isAlertPresent();
		alert.accept();
        return check;
		
		
	}
	public boolean emptyFileUpload()
	{
		fileUpload("\\src\\test\\java\\RTVTestData\\emptyqty.csv");
		Boolean check = checkAlertPresent();
		alert = isAlertPresent();
		alert.accept();
        return check;
		
	}
	public boolean duplicateProductFileUpload()
	{
		fileUpload("\\src\\test\\java\\RTVTestData\\duplicateproductID.csv");
		Boolean check = checkAlertPresent();
		alert = isAlertPresent();
		alert.accept();
        return check;
		
	}
	public boolean incorrectProductIDFileUpload()
	{
		fileUpload("\\src\\test\\java\\RTVTestData\\invalidproductID.csv");
		Boolean check = checkAlertPresent();
		alert = isAlertPresent();
		alert.accept();
        return check;
		
	}
	public boolean emptyqtyFileUpload()
	{
		fileUpload("\\src\\test\\java\\RTVTestData\\emptyqty.csv");
		Boolean check = checkAlertPresent();
		alert = isAlertPresent();
		alert.accept();
        return check;
		
	}
	
	
	public boolean invalidProductQtyFileUpload()
	{
		fileUpload("\\src\\test\\java\\RTVTestData\\invalidproductqty.csv");
		Boolean check = checkAlertPresent();
		alert = isAlertPresent();
		alert.accept();
        return check;
	}
	
	
	
	public boolean zeroQtyFileUpload()
	{
		fileUpload("\\src\\test\\java\\RTVTestData\\zeroqty.csv");
		Boolean check = checkAlertPresent();
		alert = isAlertPresent();
		alert.accept();
        return check;
		
	}
	
	
	
	public boolean emptyPriceFileUpload()
	{
		fileUpload("\\src\\test\\java\\RTVTestData\\emptyprice.csv");
		Boolean check = checkAlertPresent();
		return !check;
	}
	
	
	
	public boolean emptyReferenceIDFileUpload()
	{
		fileUpload("\\src\\test\\java\\RTVTestData\\emptyreferenceid.csv");
		Boolean check = checkAlertPresent();
		return !check;
		
	}
	
	
	
	public boolean invalidPriceFileUpload()
	{
		fileUpload("\\src\\test\\java\\RTVTestData\\invalidprice.csv");
		Boolean check = checkAlertPresent();
		alert = isAlertPresent();
		alert.accept();
        return check;
		
	}
	
	
	
	public boolean emptyProductIDFileUpload()
	{
		fileUpload("\\src\\test\\java\\RTVTestData\\emptyProductID.csv");
		Boolean check = checkAlertPresent();
		alert = isAlertPresent();
		alert.accept();
        return check;
		
	}
	
	
	
	public boolean validFileUpload()
	{
		fileUpload("\\src\\test\\java\\RTVTestData\\validexcel.csv");
		elementToBeClickable(producTable);
		return skus.size()>=1;
		
	}
	
	
	
	public boolean invalidFileUpload()
	{
		fileUpload("\\src\\test\\java\\RTVTestData\\invalidfiletype.html");
		alert = isAlertPresent();
		String alertMsg = alert.getText();
		alert.accept();
		return alertMsg.equalsIgnoreCase("Please upload a valid CSV file");
		
	}
	
	
	
	public boolean decimalQtyFileUpload()
	{
		fileUpload("\\src\\test\\java\\RTVTestData\\invalidfiletype.html");
		alert = isAlertPresent();
		String alertMsg = alert.getText();
		alert.accept();
		return alertMsg.equalsIgnoreCase("Please upload a valid CSV file");
		
	}
	
	
	
	public boolean decimalPriceFileUpload()
	{
		fileUpload("\\src\\test\\java\\RTVTestData\\invalidfiletype.html");
		alert = isAlertPresent();
		String alertMsg = alert.getText();
		alert.accept();
		return alertMsg.equalsIgnoreCase("Please upload a valid CSV file");
		
	}
	
	
	
	public boolean bulkDataFileUpload()
	{
		fileUpload("\\src\\test\\java\\RTVTestData\\invalidfiletype.html");
		alert = isAlertPresent();
		String alertMsg = alert.getText();
		alert.accept();
		return alertMsg.equalsIgnoreCase("Please upload a valid CSV file");
		
	}
	
	
	public boolean downloadTemplate() throws InterruptedException
	{
		downloadSampleFile.click();
		String downloadPath = "C:\\Users\\silppa\\Downloads"; // Change this path as per your system
        String expectedFileName = "sample-request-sku.csv"; // Change based on expected file name
          Thread.sleep(3000);
        // Wait for the file to appear
        boolean isDownloaded = waitForFileDownload(downloadPath, expectedFileName, 30);
        return isDownloaded;
	}
	
	public boolean createReturnStock()
	{
		submitBtn.click();
		elementToBeClickable(returnOrder);
		returnOrderIDs = returnOrder.getText().split("-")[1].trim();
		return returnOrder.isDisplayed();
	}
	
	public boolean checkFileUploadHistoryDisplayed()
	{
		return fileUploadHistory.isDisplayed() && fileUploadHistory.getText().equalsIgnoreCase("File Upload");
	}
	
	public boolean checkRequestcsvHistory()
	{
		return fileType.getText().equalsIgnoreCase("request_csv");
	}

	public boolean checkCSVFilePathDisplayed()
	{
		fileUploadURL = filePath.getAttribute("href");
		return filePath.isDisplayed() && !filePath.getText().isEmpty() ;
	}
	
	public boolean checkCreatedDateDisplayed()
	{
		return createdDate.isDisplayed() && !createdDate.getText().isEmpty();
	}
	
	public boolean checkUserDetails()
	{
		String upload_user = data.getString("userRole");
		return createdUser.isDisplayed() && createdUser.getText().equalsIgnoreCase(upload_user);
	}
	
	public boolean checkCreatedDate()
	{
		String expectedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String displayedDate = createdDate.getText().trim(); 
        String extractedDate = displayedDate.split(" ")[0]; 
        return extractedDate.equals(expectedDate);
	}
	
	public boolean checkHistory()
	{		
		return historyData.stream().filter(data->data.getText().equalsIgnoreCase("RTV request created")).count() >=1;
	}
	
	public boolean shipmentCreated()
	{
		driver.navigate().back();
		historyData.stream().filter(data->data.getText().contains("Hub to hub order created on magento.")).forEach(System.out::print);
		return historyData.stream().filter(data->data.getText().contains("Hub to hub order created on magento.")).count() >=1;
	}
	
	public boolean syncMagentoBtnDisplayed()
	{
		return syncToMagento.isDisplayed();
	}
	
	public boolean checkVendorInEdit()
	{
		String vendor = data.getString("vendor");
		return vendorDropDown.getAttribute("value").equalsIgnoreCase(vendor);
	}
	public boolean checkVendorNotEditable()
	{
	
		return vendorDropDown.getAttribute("readonly").equalsIgnoreCase("true");
	}
	public boolean checkPickupHubNotEditable()
	{
		return vendorPickUp.getAttribute("readonly").equalsIgnoreCase("true");
	}
	public boolean checkDropHubNotEditable()
	{
		return dropHub.getAttribute("readonly").equalsIgnoreCase("true");
	}
	
	public boolean checkPickUpHubInEdit()
	{
		String pickup = data.getString("pickup");
		return vendorPickUp.getAttribute("value").equalsIgnoreCase(pickup);
	}
	
	public boolean checkDropHubInEdit()
	{
		String drop = data.getString("drop");
		
		return vendorDropDown.getAttribute("value").equalsIgnoreCase(drop);
	}
	
	public boolean checkPartnerIdNotEditable()
	{
		return partnerRef.getAttribute("readonly").equalsIgnoreCase("true");
	}
	
	
	public boolean checkReturnDateNotEditable()
	{
		return returnDate.getAttribute("readonly").equalsIgnoreCase("true");
	}
	
	
	public boolean checkNotesNotEditable()
	{
		return notes.getAttribute("readonly").equalsIgnoreCase("true");
	}
	
	public boolean checkAddDocumentBtnDisplayed()
	{
		return addDocument.isDisplayed() && addDocument.getText().equals("Add document");
	}
	
	public boolean checkPartnerReferenceNumber()
	{
		return partnerRef.getAttribute("value").equals(String.valueOf(partnerID));
	}
	
	public boolean checkNotesInEditPage()
	{
		return notes.getAttribute("value").equalsIgnoreCase("RTV Return Stock by Automation");
	}
	
	public boolean checkReturnOrderIDListing()
	{
		goBackBtn.click();
		elementToBeClickable(searchbtn);
		return returnOrderId.getText().equalsIgnoreCase(returnOrderIDs);
	}
	
	public boolean searchOptionDisplayed()
	{
		return searchTextBox.isDisplayed() && searchbtn.isDisplayed();
	}
	
	public boolean searchByReturnOrderID()
	{
		searchTextBox.sendKeys(String.valueOf(returnOrderIDs));
		searchbtn.click();
		return returnOrderId.getText().equalsIgnoreCase(returnOrderIDs);
	}
	
	public void getCSVProductID()
	{
		csvReader = new CSVReaderUtil();
		String filePath = System.getProperty("user.dir")+"\\src\\test\\java\\RTVTestData\\validexcel.csv" ;
		String columnName = "Sku Id";
		csvproductID = csvReader.readCSVColumn(filePath, columnName);
		
	}
	
	public void getCSVProductQty()
	{
		csvReader = new CSVReaderUtil();
		String filePath = System.getProperty("user.dir")+"\\src\\test\\java\\RTVTestData\\validexcel.csv" ;
		String columnName = "Return Quantity";
		csvproductQty = csvReader.readCSVColumn(filePath, columnName);
		
	}
	
	public boolean checkSkuDetails()
	{
		getCSVProductID();
		getCSVProductQty();
		List<String> actualProductID = skuIDColumn.stream().map(sku -> sku.getText()).collect(Collectors.toList()); 
		List<String> actualProductQty = qtyColumn.stream().map(sku -> sku.getText()).collect(Collectors.toList()); 
		return actualProductID.equals(csvproductID) && actualProductQty.equals(csvproductQty);
	}
	
	public boolean checkSkuDetailsInEditPage()
	{
		moveToElement(editSkuColumn.get(1));
		List<String> actualProductID = editSkuColumn.stream().map(sku -> sku.getText()).collect(Collectors.toList()); 
		List<String> actualProductQty = editReqQtyColumn.stream().map(sku -> sku.getText()).collect(Collectors.toList()); 
		
		return actualProductID.equals(csvproductID) && actualProductQty.equals(csvproductQty);
	}
	
	public void magentoLogin() throws IOException
	{
		String server = prop.getProperty("server");
		String url = prop.getProperty("url");
		driver.get("https://" + server + url);
		visibilityOfElementLocated(magentousername);
		magentousername.sendKeys(prop.getProperty("username"));
		magentopassword.sendKeys(prop.getProperty("password"));
		magentologinBtn.click();
	}
	
	public void selectShipmentPage()
	{
		visibilityOfElementLocated(catalog);
		moveToElement(catalog);
		elementToBeClickable(manageShipment);
		moveToElement(manageShipment);
		elementToBeClickable(shipment);
		shipment.click();
	}
	
	public void selectShipmentFor()
	{
		elementToBeClickable(shipmentFor);
		selectByValue(shipmentFor, data.getString("shipmentFor"));
	}
	
	public boolean checkReturnStockDisplayed() throws IOException
	{
		magentoLogin();
		selectShipmentPage();
		selectShipmentFor();
		elementToBeClickable(notesSearch);
		notesSearch.sendKeys(String.valueOf(partnerID));
		notesSearch.sendKeys(Keys.ENTER);
		return shipmentRecord.isDisplayed();
	}
	
	public boolean checkShipmentSource()
	{
		return shipmentSource.getText().equalsIgnoreCase(data.getString("source"));
	}
	
	public boolean checkShipmentStatus()
	{
		return shipmentStatus.getText().equalsIgnoreCase(data.getString("beforeShipmentAcceptStatus"));
	}
		
	public boolean checkShipmentProductID()
	{
		shipmentNotes.click();
		elementToBeClickable(addShipmentToInventoryBtn);
		magentoShipmentIDs=magentoShipmentID.getText().split("Edit Shipment - Id :")[1].split(",")[0].trim();
		List<String> actualProductID = shipmentProductID.stream().map(id->id.getText()).collect(Collectors.toList());
		return actualProductID.equals(csvproductID);
	}
	
	public boolean checkShipmentProductQty()
	{
		getCSVProductQty();
		List<String> actualProductQty = shipmentProductQty.stream().map(id->id.getText()).collect(Collectors.toList());
		return actualProductQty.equals(csvproductQty);
	}
	
	public void extractNotesData()
	{
		String[] notes = (shipmentNotes.getText()).split("\\|");
		for(int i=0 ;i< notes.length;i++)
		{
			if(notes[i].contains("Order"))
			{
				OrderID = notes[i].split("Order#")[1].trim();
			
			}
			else if(notes[i].contains("RTV Ref#"))
			{
				RTV_Ref = notes[i].split("RTV Ref#")[1].trim();
				
			}
			else if(notes[i].contains("Address:"))
			{
				address = notes[i].split("Address:")[1].trim();
			
			}
		}

	}
	
	public boolean checkShipmentPartnerRefID()
	{
		extractNotesData();
		return RTV_Ref.equalsIgnoreCase(String.valueOf(partnerID));
	}
	
	public boolean checkShipmentOrderID()
	{
		return OrderID.startsWith("222");
	}
	
	public boolean checkShipmentAddress()
	{
		return address.equalsIgnoreCase(data.getString("address"));
	}
	
	public void selectOrderpage()
	{
		elementToBeClickable(salesMenu);
		salesMenu.click();
		elementToBeClickable(orderMenu);
		orderMenu.click();
		elementToBeClickable(orderIdSearch);
	}
	
	public boolean orderDisplayed()
	{
		selectOrderpage();
		orderIdSearch.sendKeys(OrderID);
		orderIdSearch.sendKeys(Keys.ENTER);
		visibilityOfElementLocated(OrderIncrementID);
		return OrderIncrementID.isDisplayed() && OrderIncrementID.getText().equalsIgnoreCase(OrderID);
	}
	
	public boolean checkOrderSource()
	{
		elementToBeClickable(purchasedFrom);
		purchasedFrom.click();
		visibilityOfElementLocated(source);
		return source.getText().equalsIgnoreCase(data.getString("source"));
	}
	
	public boolean checkOrderStatus()
	{
		visibilityOfElementLocated(orderStatus);
		return orderStatus.getText().equalsIgnoreCase(data.getString("beforeShipmentAcceptStatus"));
	}
	
	public boolean checkOrderProductDetails()
	{
		List<String> actualProducts = orderProductID.stream().map(id->id.getText().split("Product Id: ")[1].trim()).collect(Collectors.toList());
		return actualProducts.equals(csvproductID);
	}
	
	public boolean checkOrderProductOrderQtyDetails()
	{
		List<String> actualOrdredQyt = orderedQty.stream().map(id->id.getText().split("Product Id: ")[1].trim()).collect(Collectors.toList());
		return actualOrdredQyt.equals(csvproductID);
	}
	
	public boolean checkOrderProductInvoiceQtyDetails()
	{

		List<String> actualInvoicedQty = invoicedQty.stream().map(id->id.getText().split("Product Id: ")[1].trim()).collect(Collectors.toList());
		return actualInvoicedQty.equals(csvproductID);
	}
	
	public boolean checkOrderProductShippedQtyDetails()
	{
		List<String> actualShippedQty = shippedQty.stream().map(id->id.getText().split("Product Id: ")[1].trim()).collect(Collectors.toList());
		return actualShippedQty.equals(csvproductQty);
	}
	
	 public void hublogin() throws IOException
	    {
	    	driver.get(getGlobalProperties().getProperty("huburl"));
	    	//clearLocalStorage();
	    	visibilityOfElementLocated(hubuserName);
	    	hubuserName.sendKeys(getGlobalProperties().getProperty("hubuser"));
	    	hubpassword.sendKeys(getGlobalProperties().getProperty("hubpwd"));
	    	hubloginBtn.click();
	    }
	    
	    public void selectIncomingStockMenu()
	    {
	    	visibilityOfElementLocated(incomingStock);
	    	incomingStock.click();
	    	visibilityOfElementLocated(shipmentList.get(1));
	    }
	    
	    public boolean checkReturnOrderShipmentListing() throws IOException, InterruptedException
	    {
	    	hublogin();
	    	selectIncomingStockMenu();
	    	Thread.sleep(5);
	    	//String shipmentID = "Shipment ID: HUB_TRANSFER-87b893260e4fdec2265a826c6ec5db16";
	    	index = IntStream.range(0, shipmentList.size()).filter(i -> shipmentList.get(i).getText().contains(magentoShipmentIDs)).findFirst().orElse(-1);
	    	return shipmentList.stream().filter(id->id.getText().contains(magentoShipmentIDs)).count()==1;
	    }
	    
	    public boolean checkHubShipmentSource()
	    {
	    	return hubshipmentSource.get(index).getText().equalsIgnoreCase(data.getString("source"));
	    }
	    
	    public boolean checkReturnPickupTitleShowing()
	    {
	    	return returnPickupBtn.get(index).isDisplayed() ;
	    }
	    
	    public boolean checkHubShipmentItemCount()
	    {
	    	int itemcounts = Integer.parseInt(itemcount.get(index).getText().split("Items")[0].trim());
	    	return itemcounts ==csvproductID.size();
	    }
	    
	    public boolean checkShipmentReturnPickUpAddress()
	    {
	    	moveToElement(returnPickupBtn.get(index));
	    	returnPickupBtn.get(index).click();
	    	visibilityOfElementLocated(returnpickupTitle);
	    	return pickUpAddress.getText().equalsIgnoreCase(data.getString("address"));
	    	
	    }
	    
	    public boolean checkShipmentDetailsSource()
	    {
	    	String source = hubshipmentdetailsSource.getText().split("Source:")[1].trim();
	    	return source.equalsIgnoreCase(data.getString("source"));
	    }
	    
	    public boolean checkTototalShipmentItemQty()
	    {
	    	int itemcounts = Integer.parseInt(totalShipmentItem.getText().split("Total Shipment Items :")[1].trim());
	    	return itemcounts == csvproductID.size();
	    }
	    
	    public boolean checkProductDetails()
	    {
	    	List<String> actualProductID = hubProductID.stream().map(id->id.getText()).collect(Collectors.toList());
	    	return actualProductID.equals(csvproductID);
	    }
	    
	    public boolean acceptShipment()
	    {
	    	confirmBtn.click();
	    	elementToBeClickable(confirmShipmentBtn);
	    	confirmShipmentBtn.click();
	    	visibilityOfElementLocated(shipmentSuccessMsg);
	    	return shipmentSuccessMsg.isDisplayed();
	    }
	    
	    public boolean shipmentStatusAfterInvoice() throws IOException
	    {
	    	String server = prop.getProperty("server");
			String url = prop.getProperty("url");
			driver.get("https://" + server + url);
			selectShipmentPage();
			selectShipmentFor();
			elementToBeClickable(notesSearch);
			notesSearch.clear();
			notesSearch.sendKeys(String.valueOf(partnerID));
			notesSearch.sendKeys(Keys.ENTER);
			return shipmentStatus.getText().equalsIgnoreCase(data.getString("afterShipmentAcceptStatus"));
	    }
	    
	    public boolean checkUploadedcsvLink()
	    {
	    	uploadedcsv.click();
	    	System.out.println(errorMsg.getText());
	    	return !errorMsg.isDisplayed();
	    }
}

