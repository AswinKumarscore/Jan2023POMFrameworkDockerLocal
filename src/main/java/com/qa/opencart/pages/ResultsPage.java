package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;

public class ResultsPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By resultsProductXPath = By.xpath("//div[@class='row']/div[contains(@class,'product-layout')]");
	
	public ResultsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}
	
	//Page actions:
	public String getResultsPageTitle(String searchKey)
	{
	return eleUtil.waitForTitleIsAndCapture(searchKey, 5);	
	} 
	 
	public int getProductResultsCount()
	{
		int resultCount = eleUtil.waitForElementsVisible(resultsProductXPath,10).size();
		System.out.println("Product Result Count is =====>"+resultCount);
		return resultCount;
	
	}
	public ProductInfoPage selectProduct(String productName)
	{
		//a[contains(text(),'iPhone')]
		By productNameLocator = By.linkText(productName);
		eleUtil.doClick(productNameLocator);
		return new ProductInfoPage(driver);
		//TestDrivenDevelopment
		
	}

}
