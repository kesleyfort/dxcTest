package com.dxc.testekesley.steps;

import com.dxc.testekesley.pages.AdvantageDemoHomePage;
import org.apache.log4j.Logger;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.web.selenium.WebDriverProvider;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static com.dxc.testekesley.utils.Utils.*;

@Component
public class AdvantageDemoSteps extends AbstractSteps {
    @Autowired
    AdvantageDemoHomePage advantageDemoHomePage;
    @Value("${home.url}")
    private String advantageDemoURL;
    protected Logger LOG = Logger.getLogger(this.getClass());
    @Autowired
    WebDriverProvider webDriverProvider;

    @Given("the customer access the store's home page")
    public void givenCustomerAccessHomePage() {
        LOG.info("Navigating user to page: " + advantageDemoURL);
        navigateTo(advantageDemoURL);
        webDriverProvider.get().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @When("i click the account button")
    public void whenIClickTheAccountButton() {
        click(advantageDemoHomePage.userAccount);
    }

    @Then("i should see the login popup")
    public void thenIShouldSeeTheLoginPopup() {
        Assert.assertTrue("Login modal is visible", waitForElement(By.xpath(advantageDemoHomePage.loginModalXpath)).isDisplayed());

    }
}
