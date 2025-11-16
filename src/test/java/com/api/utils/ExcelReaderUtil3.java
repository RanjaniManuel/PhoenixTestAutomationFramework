package com.api.utils;

import java.util.Iterator;

import com.dataproviders.api.bean.CreateJobBean;

public class ExcelReaderUtil3 {
	public static void main(String[] args) {
		Iterator<CreateJobBean> data = ExcelReaderUtil2.loadExcel("TestData/PhoenixTestData.xlsx", "CreatJobAPI",CreateJobBean.class);
		
		while(data.hasNext())
			System.out.println(data.next());
	}
}
