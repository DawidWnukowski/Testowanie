package com.example.webguidemo;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.By;

import static org.junit.Assert.*;

public class Steps {
	
	private final Pages pages;

	public Steps(Pages pages) {
		this.pages = pages;
	}
	
	@Given("user is on Home page")
    public void userIsOnHomePage(){        
        pages.home().open();        
    }
 
    @When("user opens Login link and fill input with data")
    public void userClicksOnLink(){
        pages.home().clickLink();
        try {
            Thread.sleep(500L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pages.login().fillData();
    }
 
    @Then("login page is shown")
    public void loginPageIsShown(){
        assertEquals("Zaloguj się - PS Site Polska", pages.login().getTitle());
    }

    @Then("login failed")
    public void loginFailed(){
        assertEquals("Błąd logowania", pages.login().getError());
    }

    @When("user trying to subscribe")
    public void userSubscribe(){
        pages.login().Sub();
        try {
            Thread.sleep(1500L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Then("subscribe failed")
    public void subscribeSuccess(){
        assertEquals("Błąd! Ten email jest już w bazie subskrybentów.", pages.login().getSubSuccess());
    }
}
