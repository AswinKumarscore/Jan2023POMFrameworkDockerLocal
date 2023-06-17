package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.frameworkexception.FrameException;
//Driver factor method is used for providing the Driver
public class DriverFactory1 {
	WebDriver driver;
	OptionsManager optionsManager;
	public static String highlightElement;
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	
	
	public WebDriver initDriver(Properties prop)
	{
		String browserName = prop.getProperty("browser").trim();
		System.out.println("Browser name is :"+browserName);
		highlightElement = prop.getProperty("highlight");
		optionsManager = new OptionsManager(prop);
		switch (browserName.toLowerCase()) {
		case "chrome":
			
			if(Boolean.parseBoolean(prop.getProperty("remote")))
					{
				
					}
			System.out.println("options manager value is ::" +optionsManager.getChromeOptions().toString());
			
			//driver = new ChromeDriver();
			//driver = new ChromeDriver(optionsManager.getChromeOptions());
		//	driver = new ChromeDriver(optionsManager.getChromeOptions());
		
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			break;
		case "edge":
			//driver = new EdgeDriver(optionsManager.getEdgeOptions());
			//driver = new EdgeDriver(optionsManager.getEdgeOptions());
			//lDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			break;
		case "safari":
			//driver = new SafariDriver();
			tlDriver.set(new SafariDriver());
			break;
		case "firefox":
			//driver = new FirefoxDriver();
 			System.out.println("options manager value is ::" +optionsManager.getFirefoxOptions().toString() );
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			//driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			
			break;

		default:
			System.out.println("Please pass the right Browser..."+browserName);
			throw new FrameException("NOBROWSERFOUNDEXCEPTION");
			
		}
	
		
//		driver.manage().deleteAllCookies();
//		driver.manage().window().maximize();
//		driver.get(prop.getProperty("url"));
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		//return getDriver(); 
		return getDriver() ;
		
	}
	
	//returning thread local copy of driver
	// static keyword is important
	public synchronized static WebDriver getDriver()
	{
		return tlDriver.get(); 
	}
	
	public Properties initProp()
	{
		//mvn clean install -Denv="qa"
		//mvn clean install
		Properties prop = new Properties();
		FileInputStream ip= null;
		
		String envName = System.getProperty("env");
		System.out.println("Running testcases on env: "+envName);
		try
		{
			
		
		if(envName == null)
		{
			System.out.println("No Env is given..hence running it on QA Env....");
			
				ip = new FileInputStream(".\\src\\main\\resources\\config\\qa.config.properties");
			
		
		}
		else
		{
			System.out.println("Running testcases on env:  "+envName);
			switch (envName.toLowerCase().trim()) {
			case "qa":
				ip = new FileInputStream(".\\src\\main\\resources\\config\\qa.config.properties");
				break;
				
			case "dev":
				ip = new FileInputStream(".\\src\\main\\resources\\config\\dev.config.properties");
				break;
			case "stage":
				ip = new FileInputStream(".\\src\\main\\resources\\config\\stage.config.properties");
				break;
			case "uat":
				ip = new FileInputStream(".\\src\\main\\resources\\config\\uat.config.properties");
				break;
			case "prod":
				ip = new FileInputStream(".\\src\\main\\resources\\config\\config.properties");
				break;	

			default:
				System.out.println("Please pass the right environment name..."+envName);
				throw new FrameException("NOVALIDENVGIVEN");
				
			}
		}
		}
		 catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//FileInputStream helps in making connections with config.properties
		//to fetch data and values from config.properties
		try {
			prop.load(ip);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;

		
	}
	
	/**
	 * take screenshot
	 */
	public static String getScreenshot() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		//user.dir --> user.dir creates in Current Project/User directory
		//->/screenshot/==>>Creates Screenshot folder
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		
		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;
	}

}
