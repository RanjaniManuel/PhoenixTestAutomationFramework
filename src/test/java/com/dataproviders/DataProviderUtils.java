package com.dataproviders;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.api.request.model.CreateJobPojo;
import com.api.utils.CSVReaderUtil;
import com.api.utils.CreateJobMapper;
import com.dataproviders.api.bean.CreateJobBean;
import com.dataproviders.api.bean.UserBean;

public class DataProviderUtils {

	@DataProvider(name="LoginAPIDataProvider", parallel = true)
	public static Iterator<UserBean> LoginAPIDataProvider() {
		return CSVReaderUtil.loadCsv("TestData/LoginCreds.csv",UserBean.class);
	}
	
	
	@DataProvider(name="CreateJobDataProvider", parallel = true)
	public static Iterator<CreateJobPojo>  CreateJobDataProvider() {
		Iterator<CreateJobBean> beanIterator= CSVReaderUtil.loadCsv("TestData/CreateJobData.csv",CreateJobBean.class);
		
		List<CreateJobPojo> payLoadList=new ArrayList<CreateJobPojo>();
		while(beanIterator.hasNext()) {
			CreateJobBean next = beanIterator.next();
			CreateJobPojo mapper = CreateJobMapper.mapper(next);
			
			payLoadList.add(mapper);
		}
		return payLoadList.iterator();
		
	}
}
