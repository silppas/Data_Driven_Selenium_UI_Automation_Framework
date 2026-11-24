package FTH.Util;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import FTH.Abstractcomponent.AbstractComponent;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportListener implements ITestListener {

	    private static ExtentReports extent;
	    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>(); // Thread-safe test logging
	

	    @Override
	    public void onStart(ITestContext context) {
	        // ✅ Generate unique timestamp-based report
	        String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
	        String reportName = "test-output/ExtentReport_" + timeStamp + ".html";
	        
	        // ✅ Initialize Extent Report
	        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(reportName);
	        htmlReporter.config().setTheme(com.aventstack.extentreports.reporter.configuration.Theme.STANDARD);
	        htmlReporter.config().setDocumentTitle("Test Automation Report");
	        htmlReporter.config().setReportName("Test Execution Summary");

	        extent = new ExtentReports();
	        extent.attachReporter(htmlReporter);

	        // ✅ Fetch WebDriver from TestNG Context
	

	        // ✅ Add system info
	        extent.setSystemInfo("Environment", "QA");
	        extent.setSystemInfo("Browser", "Chrome");
	        extent.setSystemInfo("Executed By", "Automation Team");
	    }

	    @Override
	    public void onTestStart(ITestResult result) {
	        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
	        test.set(extentTest);
	    }

	    @Override
	    public void onTestSuccess(ITestResult result) {
			WebDriver driver = (WebDriver) result.getTestContext().getAttribute("WebDriver");
	        test.get().pass("✅ Test Passed: " + result.getMethod().getMethodName());
			if (driver != null) {
	        	String screenshotPath = captureScreenshot(driver, result.getMethod().getMethodName());
	            test.get().addScreenCaptureFromPath(screenshotPath);
	        } else {
	            test.get().fail("WebDriver instance is null !, cannot capture screenshot.");
	        }
	    }

	    @Override
	    public void onTestFailure(ITestResult result) {
	    	 WebDriver driver = (WebDriver) result.getTestContext().getAttribute("WebDriver");
	        test.get().fail("❌ Test Failed: " + result.getThrowable());

	        if (driver != null) {
	        	String screenshotPath = captureScreenshot(driver, result.getMethod().getMethodName());
	            test.get().addScreenCaptureFromPath(screenshotPath);
	        } else {
	            test.get().fail("WebDriver instance is null !, cannot capture screenshot.");
	        }
	    }

	    @Override
	    public void onTestSkipped(ITestResult result) {
	        test.get().skip("⏭ Test Skipped: " + result.getThrowable());
	    }

	    @Override
	    public void onFinish(ITestContext context) {
	        extent.flush();
	    }

	  
	    private String captureScreenshot(WebDriver driver, String testName) {
	        try {
	            TakesScreenshot ts = (TakesScreenshot) driver;
	            File source = ts.getScreenshotAs(OutputType.FILE);

	            // 📁 Ensure the screenshots directory exists
	            File screenshotsDir = new File(System.getProperty("user.dir") + "/test-output/screenshots");
	            if (!screenshotsDir.exists()) {
	                screenshotsDir.mkdirs(); // ✅ Use mkdirs() to create parent dirs if needed
	            }

	            // 🖼️ Define screenshot file path
	            String filePath = screenshotsDir.getAbsolutePath() + "/" + testName + "_" + System.currentTimeMillis() + ".png";
	            File finalDestination = new File(filePath);
	            FileUtils.copyFile(source, finalDestination);

	            return filePath; // ✅ Return absolute path
	        } catch (Exception e) {
	            return null; // ✅ Avoid IllegalArgumentException if screenshot fails
	        }
	    }

	}

    
