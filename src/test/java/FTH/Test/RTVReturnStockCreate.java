package FTH.Test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import FTH.BaseComponent.BaseClass;
import FTH.PageObject.RTVReturnStock;
import FTH.Util.RetryAnalyzer;

public class RTVReturnStockCreate extends BaseClass{

	RTVReturnStock rtvReturnStock ;
	
	

	@Test(groups = {"sanity", "order"})
	public void test_TC001_RTVReturnStock_menu_is_displayed() throws IOException
	{
		rtvReturnStock = new RTVReturnStock(driver);
		rtvReturnStock.login();
		Assert.assertTrue(rtvReturnStock.rtvMenuDisplayed());
	}

	
	@Test(groups = {"sanity", "order"})
	public void test_TC002_RTVReturn_stock_page_opened() throws InterruptedException
	{
		Assert.assertTrue(rtvReturnStock.selectRTVMenu());
	}
	
	
	
	@Test(groups = {"sanity", "order"})
	public void test_TC003_create_vendor_return_button_displayed()
	{
		Assert.assertTrue(rtvReturnStock.createVendorReturnBtnDisplayed());
	}
	

	@Test(groups = {"sanity", "order"})
	public void test_TC004_create_return_return_page_opened()
	{
		Assert.assertTrue(rtvReturnStock.isCreateVendorReturnPageOpened());
	}
	
	@Test
	public void test_TC005_vendor_return_Page_Title()
	{
		Assert.assertTrue(rtvReturnStock.checkPageTitle());
	}

	
	@Test
	public void test_TC006_vendor_field_displayed()
	{
		Assert.assertTrue(rtvReturnStock.vendorFieldDisplayed());
	}
	
	@Test
	public void test_TC007_vendors_are_getting_listed()
	{
		Assert.assertTrue(rtvReturnStock.isVendorList());
	}
	
	@Test
	public void test_TC008_vendor_pickup_field_displayed()
	{
		Assert.assertTrue(rtvReturnStock.pickHubDisplayed());
	}
	
	
	@Test
	public void test_TC009_pickup_store_listed()
	{
		Assert.assertTrue(rtvReturnStock.isPickupStoreListed());
	}
	
	
	@Test
	public void test_TC010_drop_hub_field_displayed()
	{
		Assert.assertTrue(rtvReturnStock.dropHubDisplayed());
	}
	
	
	@Test
	public void test_TC011_drop_hub_listed()
	{
		Assert.assertTrue(rtvReturnStock.isDropStoreListed());
	}
	
	
	
	@Test
	public void test_TC012_partner_reference_id_field_displayed()
	{
		Assert.assertTrue(rtvReturnStock.referenceIDDisplayed());
	}
	
	
	@Test
	public void test_TC013_partner_reference_id_empty_by_default()
	{
		Assert.assertTrue(rtvReturnStock.checkReferenceIDDefaultValueEmpty());
	}
	
	@Test
	public void test_TC014_return_date_field_displayed()
	{
		Assert.assertTrue(rtvReturnStock.returnDateFieldDisplayed());
	}
	
	@Test
	public void test_TC015_return_date_is_empty()
	{
		Assert.assertTrue(rtvReturnStock.checkReturnDateDefaultValueEmpty());
	}
	
	@Test
	public void test_TC016_date_picker_displayed_in_return_date()
	{
		Assert.assertTrue(rtvReturnStock.datePickerDisplayed());
	}
	
	@Test
	public void test_TC017_notes_field_displayed()
	{
		Assert.assertTrue(rtvReturnStock.notesFieldDisplayed());
	}
	
	@Test
	public void test_TC018_notes_field_is_empty_by_default()
	{
		Assert.assertTrue(rtvReturnStock.checkNotesDefaultValueEmpty());
	}
	
	@Test
	public void test_TC019_file_upload_button_displayed()
	{
		Assert.assertTrue(rtvReturnStock.fileUploadButtonDisplayed());
	}
	
	@Test
	public void test_TC020_sample_file_format_download_displayed()
	{
		Assert.assertTrue(rtvReturnStock.sampleTemplateDisplayed());
	}
	
	@Test
	public void test_TC021_goback_button_displayed()
	{
		Assert.assertTrue(rtvReturnStock.goBackBtnDisplayed());
	}
	

	@Test
	public void test_TC022_goback_button_is_working()
	{
		Assert.assertTrue(rtvReturnStock.checkGoBack());
	}
	
	
	@Test
	public void test_TC023_vendor_field_is_mandatory()
	{
		Assert.assertTrue(rtvReturnStock.checkVendorValidation());
	}
	
	
	
	@Test
	public void test_TC024_return_date_field_is_mandatory()
	{
		Assert.assertTrue(rtvReturnStock.checkReturnDateValidation());
		
	}
	
	@Test(retryAnalyzer = RetryAnalyzer.class)
	public void test_TC025_file_upload_validation() throws InterruptedException
	{
		Assert.assertTrue(rtvReturnStock.checkFileUploadValidation());
	}
	@Test(groups = {"sanity", "order"})
	public void test_TC026_select_ventor()
	{
		Assert.assertTrue(rtvReturnStock.isVendorSelected());
	}
	
	@Test(groups = {"sanity", "order"})
	public void test_TC027_select_pickup_hub()
	{
		Assert.assertTrue(rtvReturnStock.isPickupHubSelected());
	}
	
	@Test(groups = {"sanity", "order"})
	public void test_TC028_select_drop_hub()
	{
		Assert.assertTrue(rtvReturnStock.isDropHubSelected());
	}
	
	@Test(groups = {"sanity", "order"})
	public void test_TC029_enter_reference_ID()
	{
		Assert.assertTrue(rtvReturnStock.enterReferenceID());
	}
	
	@Test(groups = {"sanity", "order"})
	public void test_TC030_select_return_date()
	{
		rtvReturnStock.selectReturnDate();
		Assert.assertTrue(true);
	}
	
	@Test(groups = {"sanity", "order"})
	public void test_TC031_enter_notes()
	{
		Assert.assertTrue(rtvReturnStock.enterNotes());
	}
	
	
	@Test
	public void test_TC032_select_invalid_file_type()
	{
		Assert.assertTrue(rtvReturnStock.invalidFileUploads());
	}
	
	
	@Test
	public void test_TC033_upload_empty_file_upload()
	{
		Assert.assertTrue(rtvReturnStock.emptyFileUpload());
	}
	
	@Test
	public void test_TC034_duplicate_product_file_upload()
	{
		Assert.assertTrue(rtvReturnStock.duplicateProductFileUpload());
	}
	
	@Test
	public void test_TC035_incorrect_productID_file_upload()
	{
		Assert.assertTrue(rtvReturnStock.incorrectProductIDFileUpload());
	}
	
	@Test
	public void test_TC036_product_with_incorrect_quantity_file_upload()
	{
		Assert.assertTrue(rtvReturnStock.invalidProductQtyFileUpload());
	}
	
	@Test
	public void test_TC037_product_with_zero_quantity_file_upload()
	{
		Assert.assertTrue(rtvReturnStock.zeroQtyFileUpload());
	}
	
	@Test
	public void test_TC038_product_with_invalid_price_file_upload()
	{
		Assert.assertTrue(rtvReturnStock.invalidPriceFileUpload());
	}
	
	
	@Test
	public void test_TC040_empty_product_ID_file_upload()
	{
		Assert.assertTrue(rtvReturnStock.emptyProductIDFileUpload());
	}
	
	
	@Test(groups = {"sanity", "order"})
	public void test_TC041_valid_file_upload()
	{
		Assert.assertTrue(rtvReturnStock.validFileUpload());
	}
	
    @Test(groups = "sanity")
	public void test_TC042_sku_details_()
	{
		Assert.assertTrue(rtvReturnStock.checkSkuDetails());
	}
	
	
	@Test
	public void test_TC043_download_template() throws InterruptedException
	{
		Assert.assertTrue(rtvReturnStock.downloadTemplate());
	}
	
	@Test(groups = {"sanity", "order"})
	public void test_TC044_create_return_stock()
	{
		Assert.assertTrue(rtvReturnStock.createReturnStock());
	}
	
	

	@Test(groups = "sanity")
	public void test_TC045_vendor_details_in_edit_page()
	{
		Assert.assertTrue(rtvReturnStock.checkVendorInEdit());
	}
	
    @Test(groups = "sanity")
	public void test_TC046_pickup_hub_In_edit_page()
	{
		Assert.assertTrue(rtvReturnStock.checkPickUpHubInEdit());
		
	}
    
    @Test(groups = "sanity")
   	public void test_TC047_drop_hub_In_edit_page()
   	{
   		
   		Assert.assertTrue(rtvReturnStock.checkDropHubInEdit());
   	}
   	
	@Test(groups = "sanity")
	public void test_TC048_file_upload_details_in_edit_page()
	{
		Assert.assertTrue(rtvReturnStock.checkFileUploadHistoryDisplayed());
		Assert.assertTrue(rtvReturnStock.checkRequestcsvHistory());
		Assert.assertTrue(rtvReturnStock.checkCSVFilePathDisplayed());
		Assert.assertTrue(rtvReturnStock.checkCreatedDateDisplayed());
		Assert.assertTrue(rtvReturnStock.checkUserDetails());
		Assert.assertTrue(rtvReturnStock.checkCreatedDate());
	}
	

	public void test_TC049_uploaded_csv_showing_in_edit()
	{
		Assert.assertTrue(rtvReturnStock.checkRequestcsvHistory());
		
	}
	
	@Test(groups = "sanity")
	public void test_TC050_shipment_created_showing_in_history()
	{
		Assert.assertTrue(rtvReturnStock.shipmentCreated());
	}
	
	@Test
	public void test_TC051_upload_file_is_getting_opened()
	{
		Assert.assertTrue(rtvReturnStock.checkUploadedcsvLink());
	}
	
	@Test
	public void test_TC052_vendor_is_not_editable()
	{
		Assert.assertTrue(rtvReturnStock.checkVendorNotEditable());
	}
	
	
	
	
	@Test
	public void test_TC054_pickup_hub_is_not_editable()
	{
		Assert.assertTrue(rtvReturnStock.checkPickupHubNotEditable());
	}
	
	@Test
	public void test_TC055_drop_hub_is_not_editable()
	{
		Assert.assertTrue(rtvReturnStock.checkDropHubNotEditable());
	}


	@Test
	public void test_TC056_partner_reference_number_is_not_editable()
	{
		Assert.assertTrue(rtvReturnStock.checkPartnerIdNotEditable());
	}

	
	@Test
	public void test_TC057_return_date_is_not_editable()
	{
		Assert.assertTrue(rtvReturnStock.checkReturnDateNotEditable());
	}

	
	@Test
	public void test_TC058_notes_is_not_editable()
	{
		Assert.assertTrue(rtvReturnStock.checkDropHubNotEditable());
	}

	@Test
	public void test_TC059_sku_details_in_edit_page()
	{
		Assert.assertTrue(rtvReturnStock.checkSkuDetailsInEditPage());
	}

	
	@Test
	public void test_TC060_partner_referenceID_in_edit_page()
	{
		Assert.assertTrue(rtvReturnStock.checkPartnerReferenceNumber());
	}


	@Test
	public void test_TC061_notes_in_edit_page()
	{
		Assert.assertTrue(rtvReturnStock.checkNotesInEditPage());
	}
	
	@Test
	public void test_TC062_add_document_btn_displayed_in_edit_page()
	{
		Assert.assertTrue(rtvReturnStock.checkAddDocumentBtnDisplayed());
	}

	
	@Test
	public void test_TC063_return_order_is_listing_in_order_list()
	{
		Assert.assertTrue(rtvReturnStock.checkReturnOrderIDListing());
	}


	
	@Test
	public void test_TC064_search_option_displayed()
	{
		Assert.assertTrue(rtvReturnStock.searchOptionDisplayed());
	}

	
	@Test
	public void test_TC065_return_order_ID_search()
	{
		Assert.assertTrue(rtvReturnStock.searchByReturnOrderID());
	}

	
	
	@Test(groups = "sanity")
	public void test_TC066_return_stock_displayed_in_shipment() throws IOException
	{
		
		Assert.assertTrue(rtvReturnStock.checkReturnStockDisplayed());
	}

	
	@Test(groups = "sanity")
	public void test_TC067_shipment_source()
	{
		Assert.assertTrue(rtvReturnStock.checkShipmentSource());
	}

	@Test(groups = "sanity")
	public void test_TC068_shipment_status()
	{
		Assert.assertTrue(rtvReturnStock.checkShipmentStatus());
	}
	
	@Test(groups = "sanity")
	public void test_TC069_partner_refence_id_in_notes()
	{
		Assert.assertTrue(rtvReturnStock.checkShipmentPartnerRefID());
	}
	
	@Test(groups = "sanity")
	public void test_TC070_hub_to_hub_order_ID_in_notes()
	{
		Assert.assertTrue(rtvReturnStock.checkShipmentOrderID());
	}
	
	
	@Test(groups = "sanity")
	public void test_TC071_shipment_address_in_notes()
	{
		Assert.assertTrue(rtvReturnStock.checkShipmentAddress());
	}
	
	
	@Test(groups = "sanity")
	public void test_TC072_product_ID_in_shipment()
	{
		Assert.assertTrue(rtvReturnStock.checkShipmentProductID());
	}
	
	@Test(groups = "sanity")
	public void test_TC073_product_qty_in_shipment()
	{
		Assert.assertTrue(rtvReturnStock.checkShipmentProductQty());
	}
	
	@Test(groups = "sanity")
	public void test_TC074_hub_to_hub_order_listing()
	{
		Assert.assertTrue(rtvReturnStock.orderDisplayed());
	}
	
	@Test(retryAnalyzer = RetryAnalyzer.class , groups = "sanity")
	public void test_TC075_order_source()
	{
		Assert.assertTrue(rtvReturnStock.checkOrderSource());
	}
	
	@Test(groups = "sanity")
	public void test_TC076_order_status()
	{
		Assert.assertTrue(rtvReturnStock.checkOrderStatus());
	}
	
	@Test(groups = "sanity")
	public void test_TC077_order_product_details()
	{
		Assert.assertTrue(rtvReturnStock.checkOrderProductDetails());
	}
	
	@Test(groups = "sanity")
	public void test_TC078_orderedQty()
	{
		Assert.assertTrue(rtvReturnStock.checkOrderProductDetails());
	}
	
	@Test(groups = "sanity")
	public void test_TC079_invoicedQty()
	{
		Assert.assertTrue(rtvReturnStock.checkOrderProductDetails());
	}
	
	@Test(groups = "sanity")
	public void test_TC080_shippedQty()
	{
		Assert.assertTrue(rtvReturnStock.checkOrderProductDetails());
	}
	
	@Test(retryAnalyzer = RetryAnalyzer.class , groups = "sanity")
	public void test_TC081_return_order_shipment_displayed() throws IOException, InterruptedException
	{
		Assert.assertTrue(rtvReturnStock.checkReturnOrderShipmentListing());
	}
	
	@Test(groups = "sanity")
	public void test_TC082_hub_shipment_source()
	{
		Assert.assertTrue(rtvReturnStock.checkHubShipmentSource());
	}
	
	@Test(groups = "sanity")
	public void test_TC083_hub_shipment_item_count()
	{
		Assert.assertTrue(rtvReturnStock.checkHubShipmentItemCount());
	}
	
	@Test(groups = "sanity")
	public void test_TC084_return_pickup_title_showing()
	{
		Assert.assertTrue(rtvReturnStock.checkReturnPickupTitleShowing());
	}
	
	@Test(groups = "sanity")
	public void test_TC085_return_pickup_address()
	{
		Assert.assertTrue(rtvReturnStock.checkShipmentReturnPickUpAddress());
	}
	
	@Test(groups = "sanity")
	public void test_TC086_shipment_details_source()
	{
		Assert.assertTrue(rtvReturnStock.checkShipmentDetailsSource());
	}
	
	@Test(groups = "sanity")
	public void test_TC087_shipment_details_item_count()
	{
		Assert.assertTrue(rtvReturnStock.checkTototalShipmentItemQty());
	}
	@Test(groups = "sanity")
	public void test_TC088_shipment_product_details()
	{
		Assert.assertTrue(rtvReturnStock.checkProductDetails());
	}
	
	
	@Test(groups = "sanity")
	public void test_TC089_confirm_shipment()
	{
		Assert.assertTrue(rtvReturnStock.acceptShipment());
	}
	
	@Test(retryAnalyzer = RetryAnalyzer.class , groups = "sanity")
	public void test_TC090_shipment_status_after_shipment_accept() throws IOException
	{
		Assert.assertTrue(rtvReturnStock.shipmentStatusAfterInvoice());
	}
	
}