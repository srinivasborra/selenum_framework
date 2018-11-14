package com.macys.test.utils;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class RestClient {
	
	public void get() {
		try{
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		String url = "https://reqres.in/api/users";
		HttpGet request = new HttpGet(url);
		//configure(request);
		CloseableHttpResponse response = httpClient.execute(request);
		System.out.println("Response :"+response.getStatusLine().getStatusCode());
		System.out.println(response.getEntity().getContentType().getValue());
		
	}catch(Exception ex){
		System.out.println(ex.getMessage());
		}
	}
	
	private void configure(HttpRequestBase request) {
		request.addHeader("content-type", "application/json");
		request.addHeader("Accept", "application/json");
		request.addHeader("data-context","{'shoppingMode':'SITE','regionCode':'US','application':'SITE','deviceType':'DESKTOP','navigationType':'SEARCH','viewType':'Adaptive','domestic':true,'currencyCode':'USD'}");
		
		request.addHeader("data-uri", "/xapi/navigate/v1/header");
		request.addHeader("data-bagserviceproxy", "/api/shopping-bag/v1/bags/");
//		request.addHeader("X-OpenIDM-Password", "openidm-admin");
	}
	
	public static void main(String agr[]){
		RestClient rc = new RestClient();
		rc.get();
	}
}
