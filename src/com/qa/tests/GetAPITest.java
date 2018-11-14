package com.qa.tests;

import com.macys.test.utils.RestClient;

public class GetAPITest extends TestBase{
	TestBase testBase;
	public void setUp() throws Exception{
		testBase = new TestBase();
		String baseUrl = testBase.initProperties().getProperty("URL");
		String apiUrl = testBase.initProperties().getProperty("serviceURL");
		RestClient restClient = new RestClient();
		//restClient.get(baseUrl+apiUrl);
	}
	
	public static void main(String ag[]) throws Exception{
		GetAPITest gat = new GetAPITest();
		gat.setUp();
	}
}
