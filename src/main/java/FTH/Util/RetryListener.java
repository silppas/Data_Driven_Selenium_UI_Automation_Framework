package FTH.Util;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class RetryListener implements ITestListener{
	
	@Override
    public void onTestFailure(ITestResult result) {
        if (result.getMethod().getRetryAnalyzerClass() == null) {
            result.getMethod().setRetryAnalyzerClass(RetryAnalyzer.class);
        }
    }

    @Override
    public void onTestStart(ITestResult result) {}

    @Override
    public void onTestSuccess(ITestResult result) {}

    @Override
    public void onTestSkipped(ITestResult result) {}

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}

    @Override
    public void onStart(ITestContext context) {}

    @Override
    public void onFinish(ITestContext context) {}

}
