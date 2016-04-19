package com.example.webguidemo.pages;

import java.util.concurrent.TimeUnit;

import org.jbehave.web.selenium.WebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;

public class Login extends WebDriverPage{

	private final static String EMAIL = "//*[@id=\"luser-main\"]";
	private final static String PW = "//*[@id=\"lpass-main\"]";
	private final static String BTN = "//*[@title=\"Zaloguj się\"]"; //*[@id="content"]/div[1]/div/div/form/div[8]/a title="Zaloguj się"
	public final static String ERROR = "//*[@id=\"login_komunikat-main\"]/span";   //*[@id="login_komunikat-main"]/span

	public Login(WebDriverProvider driverProvider) {
		super(driverProvider);		
	}

	public void open() {
		get("http://www.pssite.com/loguj.html");
		manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	public void fillData() {
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		findElement(By.xpath(EMAIL)).sendKeys("zlyemail@zly");
		findElement(By.xpath(PW)).sendKeys("123");
		findElement(By.xpath(BTN)).click();
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String getError() {
		return findElement(By.xpath(ERROR)).getText();
	}

}
