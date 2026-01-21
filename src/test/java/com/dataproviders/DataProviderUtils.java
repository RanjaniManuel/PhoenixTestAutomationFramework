package com.dataproviders;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;

import com.api.request.model.CreateJobPayload;
import com.api.utils.CSVReaderUtil;
import com.api.utils.CreateJobMapper;
import com.api.utils.ExcelReaderUtil;
import com.api.utils.FakerDataGenerator;
import com.api.utils.JsonReaderUtil;
import com.database.dao.CreateJobPayLoadDataDao;
import com.dataproviders.api.bean.CreateJobBean;
import com.dataproviders.api.bean.UserBean;

public class DataProviderUtils {
	private static final Logger LOGGER=LogManager.getLogger(DataProviderUtils.class);

	@DataProvider(name="LoginAPIDataProvider", parallel = true)
	public static Iterator<UserBean> LoginAPIDataProvider() {
		LOGGER.info("Loading Login Data from the CSV file TestData/LoginCreds.csv ");
		return CSVReaderUtil.loadCsv("TestData/LoginCreds.csv",UserBean.class);
	}
	
	
	@DataProvider(name="CreateJobDataProvider", parallel = true)
	public static Iterator<CreateJobPayload>  CreateJobDataProvider() {
		LOGGER.info("Loading Create Job payload Data from the CSV file TestData/CreateJobData.csv ");
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
		LOGGER.info("Generating fake Creae job data with the faker count {}",fakerCount);
		Iterator<CreateJobPayload> fakeCreateJobData = FakerDataGenerator.generateFakeCreateJobData(fakerCountInt);
		return fakeCreateJobData;
		
	}
	
	@DataProvider(name="LoginAPIJsonDataProvider",parallel = true)
	public static Iterator<UserBean> LoginAPIJsonDataProvider() {
		LOGGER.info("Loading Login Data  from the JSON file TestData/LoginAPITestData.json ");
		return JsonReaderUtil.loadJSON("TestData/LoginAPITestData.json",UserBean[].class);
	}
	
	@DataProvider(name="CreateJobAPIJsonDataProvider",parallel = true)
	public static Iterator<CreateJobPayload> CreateJobAPIJsonDataProvider() {
		LOGGER.info("Loading Data for Create Job from the JSON file TestData/CreateJobAPIData.json ");
		return JsonReaderUtil.loadJSON("TestData/CreateJobAPIData.json",CreateJobPayload[].class);
	}
	
	@DataProvider(name="LoginAPIExcelnDataProvider",parallel = true)
	public static Iterator<UserBean> LoginAPIExcelnDataProvider() {
		LOGGER.info("Loading Data for login data from the xlsx file TestData/PhoenixTestData.xlsx");
		return ExcelReaderUtil.loadExcel("TestData/PhoenixTestData.xlsx","LoginData",UserBean.class);
	}
	
	
	@DataProvider(name="CreateJobAPIExcelDataProvider",parallel = true)
	public static Iterator<CreateJobPayload> CreateJobAPIExcelDataProvider() {
		LOGGER.info("Loading Data for Create Job  from the xlsx file TestData/PhoenixTestData.xlsx ");
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
		LOGGER.info("Loading Data for Create Job From the DB");
		List<CreateJobBean> createjobBeanList = CreateJobPayLoadDataDao.getCreateJobPayloadData();
		List<CreateJobPayload> payloadList=new ArrayList<CreateJobPayload>();
		
		for(CreateJobBean bean:createjobBeanList) {
			CreateJobPayload payload = CreateJobMapper.mapper(bean);
			payloadList.add(payload);
		}

		return payloadList.iterator();
	}
	
}
