package com.test.qa.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.test.qa.data.Product;
import com.test.qa.utils.PageElements;

public class WMProductSearch {

	private static Logger logger = Logger.getLogger(WMProductSearch.class);
	private WMShopingCart wsc = new WMShopingCart();
	private WebDriver driver;
	private static List<Product> productInfo = new ArrayList<Product>();

	public WMProductSearch() {
		driver =  wsc.loadHomePage();
	}

	public void fetchProductList() {
		try {
			productInfo = wsc.readDataFromJSON("jsondata/products.json");
			Product prd = productInfo.get(2);
			wsc.searchProductByKeyWord(prd.getSearchKeyWord());
		} catch (Exception ex) {
			logger.info(ex.getMessage());
		}

	}
	public void selectShippingAndPickupRadio() throws Exception{
		String after_resultCount  = null;
		String before_resultCount = driver.findElement(PageElements.resultSummary).getText();
		System.out.println(before_resultCount);
		
		List<WebElement>  option = driver.findElements(PageElements.divShippingPickup);
		for(WebElement ele : option){
			String opt=ele.getText();
			if(opt.equalsIgnoreCase("Show all")){
				WebElement el1 = driver.findElement(PageElements.showAllRadio);
				((JavascriptExecutor)driver).executeScript("arguments[0].checked = false;", el1);
			}else if(opt.equalsIgnoreCase("2-day shipping")){
				JavascriptExecutor jse = (JavascriptExecutor)driver;
				jse.executeScript("window.scrollBy(0,650)", "");
				WebElement lbl = driver.findElement(PageElements.shippingLabel);
				lbl.click();
				Thread.sleep(1000);
				after_resultCount = driver.findElement(PageElements.resultSummary).getText();
			}
		}
		System.out.println(after_resultCount);
	}
	
	public void clickPriceRange(){
		int resultCnt =0;
		WebElement elePrice= driver.findElement(PageElements.priceLink);
		elePrice.click();
		driver.findElement(PageElements.inputMinPrice).sendKeys("200");
		driver.findElement(PageElements.inputMaxPrice).sendKeys("300");
		driver.findElement(PageElements.btnPriceRangeGo).click();
		
		List<WebElement> results = driver.findElements(PageElements.divSearchResult);
		if(results != null){
			for(WebElement ele : results){
				String str = ele.getText();
				String[] reslts = str.split("\n");
				for(int i =0;i<reslts.length;i++){
					if(reslts[i].equalsIgnoreCase("Current Price")){
						resultCnt = resultCnt+1;
					}
				}
				
			}
			System.out.println(resultCnt);
		}
		System.out.println(elePrice.getText());
	}
	public void quitBrowser(){
		if(driver !=null){
			driver.quit();
		}
	}
	public static void main(String agr[]) {
		try {
			WMProductSearch wps = new WMProductSearch();
			wps.fetchProductList();
			wps.selectShippingAndPickupRadio();
			wps.clickPriceRange();
			//wps.quitBrowser();
		} catch (Exception ex) {
			logger.info(ex.getMessage());
		}
	}
}
