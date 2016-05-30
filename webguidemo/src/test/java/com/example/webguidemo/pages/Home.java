package com.example.webguidemo.pages;

import java.util.concurrent.TimeUnit;

import org.jbehave.web.selenium.WebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Home extends WebDriverPage {

	public Home(WebDriverProvider driverProvider) {
		super(driverProvider);
	}

	private final static String LINK = "//*[@id=\"options\"]/ul/li[1]/a";
	public static WebDriver driver;
	JavascriptExecutor js;

	public void open() {
		manage().window().maximize();
		get("http://www.pssite.com/");

		driver = getDriverProvider().get();
		js = (JavascriptExecutor) driver;
		//js.executeScript("document.body.style.zoom='80%'");

		manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	public void clickLink(){
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement el = findElement(By.xpath(LINK));
		Actions actions = new Actions(driver);

		actions.moveToElement(el).perform();

		//js.executeScript("arguments[0].scrollIntoView(true);", el);
		el.click();
	}
	
}
