package com.test.qa.pages;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.test.qa.resource.BaseWebDriver;

public class DragNDrop extends BaseWebDriver{

	private static Logger logger =Logger.getLogger(DragNDrop.class);
	private WebDriver driver = initBrowser("chrome");
	
	public void loadHomePage(){
		try{
			driver.get("http://locahost:8080/drag_drop.html");
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.MILLISECONDS);
			logger.info("Home page loaded successfully");
			
			WebElement From=driver.findElement(By.xpath("//*[@id='credit2']/a"));
			
			WebElement To=driver.findElement(By.xpath("//*[@id='bank']/li"));
			
			Actions act=new Actions(driver);
			
			act.dragAndDrop(From, To).build().perform();	
		}catch(Exception ex){
			logger.info(ex.getMessage());
		}
	}
	
	public static void main(String agr[]){
		DragNDrop dnd = new DragNDrop();
		dnd.loadHomePage();
		
	}
}
