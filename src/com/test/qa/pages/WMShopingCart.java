package com.test.qa.pages;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.test.qa.data.Product;
import com.test.qa.resource.BaseWebDriver;
import com.test.qa.utils.GlobalConstants;
import com.test.qa.utils.PageElements;


public class WMShopingCart extends BaseWebDriver{
	
	private static Logger logger =Logger.getLogger(WMShopingCart.class);
	private WebDriver driver = initBrowser("chrome");
	private static List<Product> productInfo = new ArrayList<Product>();
	
	
	public WebDriver loadHomePage(){
		try{
			String baseURL = initProperties().getProperty(GlobalConstants.TEST_BASE_URL);
			driver.get(baseURL);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.MILLISECONDS);
			logger.info("Home page loaded successfully");
			return driver;
		}catch(Exception ex){
			logger.info(ex.getMessage());
		}
		return null;
	}
	
	public void enterSearchString()throws Exception{
		
		for(Product pinfo : productInfo){
			WebDriverWait wait=new WebDriverWait(driver, 10);
			String searchKeyWord = pinfo.getSearchKeyWord();
			
			driver.findElements(PageElements.inputSearch).clear();
			
			driver.findElement(PageElements.inputSearch).sendKeys(searchKeyWord);
			driver.findElement(PageElements.inputSearch).sendKeys(Keys.ENTER);

			String windowTitle = driver.getTitle();
			logger.info("Entered into prodcuts "+windowTitle);	
			
			List<WebElement> products = driver.findElements(PageElements.productsDiv);
			
			String productTitle = null;
			for(WebElement product : products){
				productTitle = product.getText();
			}
			String[] prs = productTitle.split("\n");
			String product_Title = pinfo.getProudctName();
			if(prs!=null && prs.length>0){
				for(int i=0;i<prs.length;i++){
					if(prs[i].equalsIgnoreCase("Product Title")){
						if(prs[i+1].equalsIgnoreCase(product_Title)){

							WebElement productTitleLink;
							productTitleLink = wait.until(ExpectedConditions.visibilityOfElementLocated((By.linkText(product_Title))));
							productTitleLink.click();
							addProductToCart(product_Title);
						}
					}
					
				}
			}
			
		}
		
	}
	
	public void searchProductByKeyWord(String productText){
		try{
			WebDriverWait wait=new WebDriverWait(driver, 10);
			boolean isVisible = driver.findElement(PageElements.inputSearch).isDisplayed();
			if(isVisible){
			driver.findElements(PageElements.inputSearch).clear();
			
			driver.findElement(PageElements.inputSearch).sendKeys(productText);
			driver.findElement(PageElements.inputSearch).sendKeys(Keys.ENTER);

			String windowTitle = driver.getTitle();
			logger.info("Entered into prodcuts "+windowTitle);
			}else{
				logger.info("Search Input is not visible");
			}
				
		}catch(Exception ex){
			logger.info(ex.getMessage());
		}
		
	}
	
	public void addProductToCart(String productTitle){
		driver.findElement(PageElements.btnAddToCart).click();
		driver.switchTo().activeElement().click();
		
		WebDriverWait wait=new WebDriverWait(driver, 20);
		
		WebElement el1;
		el1= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='modal-content']//div[@class='Cart-PACModal-Body']//div[@class='Grid']/child::div[2]//div//following::button[2]")));
		logger.info("Button you clicked is: "+el1.getText());
		el1.click();

	}
	

	
	
	
	public void closeBrowser(){
		if(driver!=null){
			driver.quit();
		}
	}
	public static void main(String ag[]) throws Exception{
		WMShopingCart wsc = new WMShopingCart();
		productInfo = wsc.readDataFromJSON("jsondata/products.json");
		wsc.loadHomePage();
		wsc.enterSearchString();
		wsc.closeBrowser();
	}
	
//	public void displayProducts(){
//	List<WebElement> products = driver.findElements(By.xpath("//div[@id='searchProductResult']"));
//	String productTitle = null;
//	for(WebElement product : products){
//		productTitle = product.getText();
//	}
//	String[] prs = productTitle.split("\n");
//	String product_Title = BaseWebDriver.initProperties().getProperty(GlobalConstants.TEST_SEARCH_PRODUCT);
//	if(prs!=null && prs.length>0){
//		for(int i=0;i<prs.length;i++){
//			if(prs[i].equalsIgnoreCase("Product Title")){
//				
//				if(prs[i+1].equals(product_Title)){
//					System.out.println("Product Title:"+product_Title);
//					driver.findElement(By.linkText(product_Title)).click();
//					
//				}
//			}
//			
////			if(prs[i].equalsIgnoreCase("Current Price")){
////				System.out.println("Product Price:"+prs[i+1]);
////			}
//		}
//	}
//}
}
