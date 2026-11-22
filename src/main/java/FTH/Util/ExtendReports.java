package FTH.Util;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtendReports {
	    private static ExtentReports extent;

	    public static ExtentReports getReportInstance() {
	        if (extent == null) {
	            ExtentSparkReporter htmlReporter = new ExtentSparkReporter("test-output/ExtentReport.html");
	            htmlReporter.config().setDocumentTitle("Automation Report");
	            htmlReporter.config().setReportName("Selenium Test Execution Report");
	            htmlReporter.config().setTheme(Theme.DARK);

	            extent = new ExtentReports();
	            extent.attachReporter(htmlReporter);
	        }
	        return extent;
	    }
	}


