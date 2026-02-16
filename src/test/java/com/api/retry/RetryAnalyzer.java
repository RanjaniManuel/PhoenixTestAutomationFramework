package com.api.retry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
	private static final Logger LOGGER=LogManager.getLogger(RetryAnalyzer.class);
	private static final int MAX_COUNT=2;
	private int count =1;

	@Override
	public boolean retry(ITestResult result) {
		LOGGER.info("Checking if the {} test can be reexecuted", result.getName());
		if(count<=MAX_COUNT) {
			LOGGER.warn("Executing the {} test, Current Attemp: {}/{} and the Reason {}",
					result.getName(),count,MAX_COUNT, result.getThrowable().getMessage());
			count++;
			return true;
		}		
		return false;
	}

}
