package com.example.webguidemo.pages;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import org.jbehave.web.selenium.WebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Login extends WebDriverPage{

	private final static String EMAIL = "//*[@id=\"luser-main\"]";
	private final static String PW = "//*[@id=\"lpass-main\"]";
	private final static String BTN = "//*[@id=\"content\"]/div[1]/div/div/form/div[8]/a";
	private final static String ERROR = "//*[@id=\"login_komunikat-main\"]/span";
	private final static String SUB_EMAIL = "//*[@id=\"newsletter-email\"]";
	private final static String SUB_OK = "//*[@id=\"newsletter-form\"]/input[2]";
	private final static String SUB_SUCCESS = "//*[@id=\"content\"]/div[1]/div";

//	WebDriver driver;
//	JavascriptExecutor js;

//	WebDriverWait wait;

	public Login(WebDriverProvider driverProvider) {
		super(driverProvider);
	}

//	public void open() {
//		get("http://www.pssite.com/loguj.html");
//		manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//	}

	public void fillData() {
//		driver = getDriverProvider().get();
//		//wait = new WebDriverWait(driver, 3);
//		js = ((JavascriptExecutor) driver);
		//js = ((JavascriptExecutor) getDriverProvider());
		sleep(1000);
		to(EMAIL).sendKeys("zlyemail@zly");
		to(PW).sendKeys("123");
		to(BTN).click();
		//findElement(By.xpath(EMAIL)).sendKeys("zlyemail@zly");
//		findElement(By.xpath(PW)).sendKeys("123");
//		findElement(By.xpath(BTN)).click();
	}

	public String getError() {
		return findElement(By.xpath(ERROR)).getText();
	}

	public void Sub() {
		WebElement el = to(SUB_EMAIL);
		el.clear();
		el.sendKeys("zlyemail@yahoo.com");
		to(SUB_OK).click();

//		findElement(By.xpath(SUB_EMAIL)).clear();
//		findElement(By.xpath(SUB_EMAIL)).sendKeys("zlyemail@yahoo.com");
//		findElement(By.xpath(SUB_OK)).click();
	}

	public String getSubSuccess() {
		return findElement(By.xpath(SUB_SUCCESS)).getText();
	}

	private WebElement to(String path) {

		WebElement element = findElement(By.xpath(path));
//		js.executeScript("arguments[0].scrollIntoView(true);", element);

		//Actions actions = new Actions(Home.driver);

//		actions.moveToElement(element).perform();

		return element;
	}

	private void sleep(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
