package com.dataproviders;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.api.request.model.CreateJobPayload;
import com.api.utils.CSVReaderUtil;
import com.api.utils.CreateJobMapper;
import com.api.utils.FakerDataGenerator;
import com.dataproviders.api.bean.CreateJobBean;
import com.dataproviders.api.bean.UserBean;

public class DataProviderUtils {

	@DataProvider(name="LoginAPIDataProvider", parallel = true)
	public static Iterator<UserBean> LoginAPIDataProvider() {
		return CSVReaderUtil.loadCsv("TestData/LoginCreds.csv",UserBean.class);
	}
	
	
	@DataProvider(name="CreateJobDataProvider", parallel = true)
	public static Iterator<CreateJobPayload>  CreateJobDataProvider() {
		Iterator<CreateJobBean> beanIterator= CSVReaderUtil.loadCsv("TestData/CreateJobData.csv",CreateJobBean.class);
		
		List<CreateJobPayload> payLoadList=new ArrayList<CreateJobPayload>();
		while(beanIterator.hasNext()) {
			CreateJobBean next = beanIterator.next();
			CreateJobPayload mapper = CreateJobMapper.mapper(next);
			
			payLoadList.add(mapper);
		}
		return payLoadList.iterator();
		
	}
	@DataProvider(name="CreateJobAPIFakerDataProvider", parallel = true)
	public static Iterator<CreateJobPayload>  CreateJobAPIFakerDataProvider() {
		String fakerCount=System.getProperty("fakerCount", "5");
		int fakerCountInt=Integer.parseInt(fakerCount);
		Iterator<CreateJobPayload> fakeCreateJobData = FakerDataGenerator.generateFakeCreateJobData(fakerCountInt);
		return fakeCreateJobData;
		
	}
}
