import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SomeSiteTest {

	private static WebDriver driver;
	WebElement element;

	@BeforeClass
	public static void driverSetup() {
		// ChromeDrirver, FireforxDriver, ...
		System.setProperty("webdriver.chrome.driver", "D:\\Programowanie\\Java\\selenium-2.53.0\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void homePage(){
		driver.get("https://bitbucket.org/account/signin/?next=/");
		
		//element =
		driver.findElement(By.id("js-email-field")).clear();
		driver.findElement(By.id("js-email-field")).sendKeys("dawidwdvd@yahoo.com");

		driver.findElement(By.id("js-password-field")).clear();
		driver.findElement(By.id("js-password-field")).sendKeys("DVD1303");

		driver.findElement(By.xpath("//*[@value='Log in']")).click();
		//assertNotNull(element);
	}
	
//	@Test
//	public void polsatPage(){
//		driver.get("http://www.teleman.pl/");
//		driver.findElement(By.linkText("Polsat")).click();
//		element = driver.findElement(By.linkText("Polsat"));
//		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//	    assertNotNull(screenshot);
//
//		try {
//			FileUtils.copyFile(screenshot, new File("/Users/Vuko/Tmp/polsat.png"));
//		} catch (IOException e) {
//			e.printStackTrace();
//			assertTrue(false);
//		}
//
//	}

	@AfterClass
	public static void cleanp() {
		//driver.quit();
	}
}


/*
public class SomeSiteTest {

	private static WebDriver driver;
	WebElement element;

	@BeforeClass
	public static void driverSetup() {
		// ChromeDrirver, FireforxDriver, ...
		System.setProperty("webdriver.chrome.driver", "D:\\Programowanie\\Java\\selenium-2.53.0\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void homePage(){
		driver.get("http://www.teleman.pl");

		element = driver.findElement(By.linkText("Polsat"));
		assertNotNull(element);
	}

	@Test
	public void polsatPage(){
		driver.get("http://www.teleman.pl/");
		driver.findElement(By.linkText("Polsat")).click();
		element = driver.findElement(By.linkText("Polsat"));
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    assertNotNull(screenshot);

		try {
			FileUtils.copyFile(screenshot, new File("/Users/Vuko/Tmp/polsat.png"));
		} catch (IOException e) {
			e.printStackTrace();
			assertTrue(false);
		}

	}

	@AfterClass
	public static void cleanp() {
		driver.quit();
	}
}
 */