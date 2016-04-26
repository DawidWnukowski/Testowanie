package com.example.webguidemo.pages;

import java.util.concurrent.TimeUnit;

import org.jbehave.web.selenium.WebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;

public class Login extends WebDriverPage{

	private final static String EMAIL = "//*[@id=\"luser-main\"]";
	private final static String PW = "//*[@id=\"lpass-main\"]";
	private final static String BTN = "//*[@id=\"content\"]/div[1]/div/div/form/div[8]/a";
	private final static String ERROR = "//*[@id=\"login_komunikat-main\"]/span";
	private final static String SUB_EMAIL = "//*[@id=\"newsletter-email\"]";
	private final static String SUB_OK = "//*[@id=\"newsletter-form\"]/input[2]";
	private final static String SUB_SUCCESS = "//*[@id=\"content\"]/div[1]/div";

	public Login(WebDriverProvider driverProvider) {
		super(driverProvider);		
	}

//	public void open() {
//		get("http://www.pssite.com/loguj.html");
//		manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//	}

	public void fillData() {
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		findElement(By.xpath(EMAIL)).sendKeys("zlyemail@zly");
		findElement(By.xpath(PW)).sendKeys("123");
		findElement(By.xpath(BTN)).click();
	}

	public String getError() {
		return findElement(By.xpath(ERROR)).getText();
	}

	public void Sub() {
		findElement(By.xpath(SUB_EMAIL)).clear();
		findElement(By.xpath(SUB_EMAIL)).sendKeys("zlyemail@yahoo.com");
		findElement(By.xpath(SUB_OK)).click();
	}

	public String getSubSuccess() {
		return findElement(By.xpath(SUB_SUCCESS)).getText();
	}
}
