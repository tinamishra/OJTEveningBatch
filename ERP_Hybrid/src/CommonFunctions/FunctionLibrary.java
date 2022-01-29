package CommonFunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import Utilities.PropertyFileUtil;

public class FunctionLibrary {
	public static WebDriver driver;
	//method for launch browser
	public static WebDriver startBrowser()throws Throwable
	{
		if(PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "./CommonDrivers/chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();

		}
		else if(PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "./CommonDrivers/geckodriver.exe");
			driver = new FirefoxDriver();
			driver.manage().deleteAllCookies();

		}
		else
		{
			Reporter.log("Browser value Not Matching",true);
		}
		return driver;
	}
	//method for Url
	public static void openApplication(WebDriver driver)throws Throwable
	{
		driver.get(PropertyFileUtil.getValueForKey("Url"));
	}
	//method for wait for element
	public static void waitForElement(WebDriver driver,String locatortype,String locatorvalue,String testdata)
	{
		WebDriverWait myWait = new WebDriverWait(driver, Integer.parseInt(testdata));
		if(locatortype.equalsIgnoreCase("name"))
		{
			myWait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorvalue)));
		}
		else if(locatortype.equalsIgnoreCase("xpath"))
		{
			myWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorvalue)));
		}
		else if(locatortype.equalsIgnoreCase("id"))
		{
			myWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorvalue)));
		}
	}
	//method type action
	public static void typeAction(WebDriver driver,String locatortype,String locatorvalue,String testdata)
	{
		if(locatortype.equalsIgnoreCase("id"))
		{
			driver.findElement(By.id(locatorvalue)).clear();
			driver.findElement(By.id(locatorvalue)).sendKeys(testdata);
		}
		else if(locatortype.equalsIgnoreCase("xpath"))
		{
			driver.findElement(By.xpath(locatorvalue)).clear();
			driver.findElement(By.xpath(locatorvalue)).sendKeys(testdata);
		}
		else if(locatortype.equalsIgnoreCase("name"))
		{
			driver.findElement(By.name(locatorvalue)).clear();
			driver.findElement(By.name(locatorvalue)).sendKeys(testdata);
		}
	}
	//method for click action
	public static void clickAction(WebDriver driver,String locatortype,String locatorvalue)
	{
		if(locatortype.equalsIgnoreCase("xpath"))
		{
			driver.findElement(By.xpath(locatorvalue)).click();
		}
		else if(locatortype.equalsIgnoreCase("name"))
		{
			driver.findElement(By.name(locatorvalue)).click();
		}
		else if(locatortype.equalsIgnoreCase("id"))
		{
			driver.findElement(By.id(locatorvalue)).sendKeys(Keys.ENTER);
		}
	}
	public static void validateTitle(WebDriver driver,String expectedtitle)
	{
		String actualtile =driver.getTitle();
		try {
			Assert.assertEquals(actualtile, expectedtitle,"Title is Not Matching");
		}catch(Throwable t)
		{
			System.out.println(t.getMessage());
		}
	}
	//method for closing browser
	public static void closeBrowser(WebDriver driver)
	{
		driver.quit();
	}
}













