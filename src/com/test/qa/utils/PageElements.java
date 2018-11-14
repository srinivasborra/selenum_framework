package com.test.qa.utils;

import org.openqa.selenium.By;

public interface PageElements {
	By productsDiv = By.xpath("//div[@id='searchProductResult']");
	By inputSearch = By.id("global-search-input");
	
	By btnAddToCart = By.cssSelector("button[data-tl-id*='ProductPrimaryCTA-cta_add_to_cart_button']");
	
	
	//By Searching laptop result page elements
	By divShippingPickup = By.xpath("//div[@class='collapsible-content']//div[@class='radio-button-facet']//div[@class='validation-group']");
	
	By showAllRadio = By.xpath("//label[@class='radio display-block'][1]//div//input");
	By shippingRadio = By.xpath("//label[@class='radio display-block'][2]//div//input");
	By shippingLabel = By.xpath("//label[@class='radio display-block'][2]//div//span");
	
	By resultSummary = By.xpath("//div[@class='result-summary-container']");
	
	By priceLink = By.xpath("//div[@class='desktop-bar-left']//div//button[1]");
	By inputMinPrice = By.name("minPrice");
	By inputMaxPrice = By.name("maxPrice");
	By btnPriceRangeGo = By.xpath("//form[@class='PriceRange-form']//button[@type='submit']");
	By divSearchResult = By.xpath("//div[@class='search-result-listview-items']");
	
	
}
