package com.hrm.core;

public class TestRetryAnalyzer implements IRetryAnalyzer{
	private static final int MAX_RETRY_COUNT = 3;
    private int count = MAX_RETRY_COUNT;

    public int getCount() {
        return count;
    }

    @Override
    public boolean retry(ITestResult result) {
        boolean retryAgain = false;
        if (count > 0) {
            System.out.println("Going to retry test case: " + result.getMethod() + ", " + ((
                    (MAX_RETRY_COUNT - count) + 1)) + " out of " + MAX_RETRY_COUNT);
            retryAgain = true;
            --count;
        }
        return retryAgain;
    }
}
