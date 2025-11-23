package com.dataproviders;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.api.request.model.CreateJobPayload;
import com.api.request.model.UserCredential;
import com.api.utils.CSVReaderUtil;
import com.api.utils.CreateJobMapper;
import com.api.utils.ExcelReaderUtil;
import com.api.utils.FakerDataGenerator;
import com.api.utils.JsonReaderUtil;
import com.database.dao.CreateJobPayLoadDataDao;
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
	
	@DataProvider(name="LoginAPIJsonDataProvider",parallel = true)
	public static Iterator<UserCredential> LoginAPIJsonDataProvider() {
		return JsonReaderUtil.loadJSON("TestData/LoginAPITestData.json",UserCredential[].class);
	}
	
	@DataProvider(name="CreateJobAPIJsonDataProvider",parallel = true)
	public static Iterator<CreateJobPayload> CreateJobAPIJsonDataProvider() {
		return JsonReaderUtil.loadJSON("TestData/CreateJobAPIData.json",CreateJobPayload[].class);
	}
	
	@DataProvider(name="LoginAPIExcelnDataProvider",parallel = true)
	public static Iterator<UserBean> LoginAPIExcelnDataProvider() {
		return ExcelReaderUtil.loadExcel("TestData/PhoenixTestData.xlsx","LoginData",UserBean.class);
	}
	
	
	@DataProvider(name="CreateJobAPIExcelDataProvider",parallel = true)
	public static Iterator<CreateJobPayload> CreateJobAPIExcelDataProvider() {

		Iterator<CreateJobBean> beanIterator = ExcelReaderUtil.loadExcel("TestData/PhoenixTestData.xlsx", "CreatJobAPI", CreateJobBean.class);
		
		List<CreateJobPayload> payLoadList=new ArrayList<CreateJobPayload>();
		while(beanIterator.hasNext()) {
			CreateJobBean next = beanIterator.next();
			CreateJobPayload mapper = CreateJobMapper.mapper(next);
			
			payLoadList.add(mapper);
		}
		return payLoadList.iterator();
	}
	@DataProvider(name="CreateJobAPIDBDataProvider",parallel = true)
	public static Iterator<CreateJobPayload> CreateJobAPIDBDataProvider() {
		List<CreateJobBean> createjobBeanList = CreateJobPayLoadDataDao.getCreateJobPayloadData();
		List<CreateJobPayload> payloadList=new ArrayList<CreateJobPayload>();
		
		for(CreateJobBean bean:createjobBeanList) {
			CreateJobPayload payload = CreateJobMapper.mapper(bean);
			payloadList.add(payload);
		}

		return payloadList.iterator();
	}
	
}
