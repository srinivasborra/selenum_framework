package com.test.qa.resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriverService;

import com.test.qa.data.Product;
import com.test.qa.utils.GlobalConstants;

public class BaseWebDriver {
	private static Logger logger =Logger.getLogger(BaseWebDriver.class);
	
	private WebDriver driver;
	private static ChromeDriverService chromeService;
	private static GeckoDriverService firefoxService;
	private static SafariDriverService safariService;

	private static String chromepath;
	private static String safaripath;
	private static String firefoxpath;

	private static String chromebrowser;
	private static String firefoxbrowser;
	private static String safaribrowser;
	private static String iebrowser;
	
	private List<Product> productInfo= new ArrayList<Product>();

	static {
		initProperties();
	}

	public WebDriver initBrowser(String browser) {
		String brw = browser.toLowerCase();
		try {
			switch (brw) {
			case "chrome":
				chromeService = new ChromeDriverService.Builder()
						.usingDriverExecutable(new File("/usr/local/bin/chromedriver")).usingAnyFreePort().build();
				chromeService.start();
				DesiredCapabilities ch = DesiredCapabilities.chrome();
				ch.acceptInsecureCerts();
				ch.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
				ch.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				driver = new RemoteWebDriver(chromeService.getUrl(), ch);
				break;
			case "safari":
				safariService = new SafariDriverService.Builder().usingDriverExecutable(new File(safaripath))
						.usingAnyFreePort().build();
				safariService.start();
				driver = new RemoteWebDriver(safariService.getUrl(), DesiredCapabilities.safari());
				break;
			case "firefox":
				 firefoxService = new
				 GeckoDriverService.Builder().usingDriverExecutable(new
				 File("/Users/nborra/Documents/my_qa/geckodriver"))
				 .usingAnyFreePort().build();
				 firefoxService.start();
				 driver = new RemoteWebDriver(firefoxService.getUrl(),
				 DesiredCapabilities.firefox());
				 break;
			case "ie":
			default:

			}

			logger.info("Driver initiated");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return driver;
	}

	public static Properties initProperties() {
			Properties prop = new Properties();

			try {
				Path path = FileSystems.getDefault().getPath("").toAbsolutePath();
				prop.load(new FileInputStream(new File(path + "/configs/runtime.properties")));
				chromebrowser = prop.getProperty(GlobalConstants.TEST_CHROME_BROWSER);
				safaribrowser = prop.getProperty(GlobalConstants.TEST_SAFARI_BROWSER);
				firefoxbrowser = prop.getProperty(GlobalConstants.TEST_FIREFOX_BROWSER);
				iebrowser = prop.getProperty(GlobalConstants.TEST_IE_BROWSER);

				chromepath = prop.getProperty(GlobalConstants.TEST_CHROME_DRIVER_PATH);
				safaripath = prop.getProperty(GlobalConstants.TEST_SAFARI_DRIVER_PATH);
				firefoxpath = prop.getProperty(GlobalConstants.TEST_GECO_DRIVER_PATH);
				
				logger.info("Initial Properties are loaded");
		
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return prop;
	}

	public void quitBrowser() {
		if (driver != null) {
			driver.quit();
		}
	}

	public List<Product> readDataFromJSON(String jsonFilePath) throws Exception{
		//Object object = new JSONParser().parse(new FileReader("jsondata/products.json"));
		Object object = new JSONParser().parse(new FileReader(jsonFilePath));
		JSONObject jsonObject = (JSONObject) object;
		
		
		List<JSONObject> product = (List<JSONObject>)jsonObject.get("product");
		
		for(JSONObject obj : product){
			Product p = new Product();
			p.setSearchKeyWord(obj.get("searchkeyword").toString());
			p.setProudctName(obj.get("searchproduct").toString());
			productInfo.add(p);
		}
		return productInfo;
	}
}
