package FTH.Util;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer  {

	private int count = 0;
    private static final int MAX_RETRY_COUNT = 3; // Max retries for each test case

    @Override
    public boolean retry(ITestResult result) {
        if (count < MAX_RETRY_COUNT) {
            count++;
            System.out.println("Retrying test " + result.getName() + " for the " + count + " time(s).");
            return true; // Return true to retry the test
        }
        return false; // If retries are exhausted, don't retry
    }
}
