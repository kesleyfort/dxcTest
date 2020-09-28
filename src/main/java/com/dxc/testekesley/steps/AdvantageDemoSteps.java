package com.dxc.testekesley.steps;

import com.dxc.testekesley.pages.*;
import org.apache.log4j.Logger;
import org.jbehave.core.annotations.*;
import org.jbehave.web.selenium.WebDriverProvider;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static com.dxc.testekesley.utils.Utils.*;

@Component
public class AdvantageDemoSteps extends AbstractSteps {
    protected Logger LOG = Logger.getLogger(this.getClass());
    @Autowired
    AdvantageDemoHomePage advantageDemoHomePage;
    @Autowired
    AdvantageDemoLoginPopUp advantageDemoLoginPopUp;
    @Autowired
    AdvantageDemoCreateNewAccountPage advantageDemoCreateNewAccountPage;
    @Autowired
    AdvantageDemoMyAccount advantageDemoMyAccount;
    @Autowired
    AdvantageDemoProductPage advantageDemoProductPage;
    @Autowired
    WebDriverProvider webDriverProvider;
    @Value("${home.url}")
    private String advantageDemoURL;
    @Value("${username}")
    private String username;
    @Value("${email}")
    private String userEmail;
    @Value("${password}")
    private String userPassword;

    /**
     * Function created to delete the user account after the scenario is finished
     */
    @AfterScenario
    public void deleteAccount() {
        click(advantageDemoHomePage.userAccount);
        click(advantageDemoHomePage.myAccount);
        waitForPageLoad();
        click(advantageDemoMyAccount.deleteAccount);
        waitUntilVisibility(advantageDemoMyAccount.yesButtonDeleteAccount);
        click(advantageDemoMyAccount.yesButtonDeleteAccount);
        waitForPageLoad();
    }

    @Given("the customer access the store's home page")
    public void givenCustomerAccessHomePage() {
        LOG.info("Navigating user to page: " + advantageDemoURL);
        navigateTo(advantageDemoURL);
        waitForPageLoad();
    }

    @When("i click the account button")
    public void whenIClickTheAccountButton() {
        waitUntilClickable(advantageDemoHomePage.userAccount);
        click(advantageDemoHomePage.userAccount);
    }

    @Then("i should see the login popup")
    public void thenIShouldSeeTheLoginPopup() {
        Assert.assertTrue("Login modal is visible", waitForElement(By.xpath(advantageDemoHomePage.loginModalXpath)).isDisplayed());

    }

    @When("i click on the Create New Account link")
    public void iClickOnTheCreateNewAccountLink() {
        waitUntilClickable(advantageDemoLoginPopUp.createNewAccount);
        click(advantageDemoLoginPopUp.createNewAccount);
    }

    @When("i fill the users information on the new account page")
    public void iFillTheUsersInformationOnTheNewAccountPage() {
        waitUntilClickable(advantageDemoCreateNewAccountPage.usernameField);
        setText(advantageDemoCreateNewAccountPage.usernameField, username);
        setText(advantageDemoCreateNewAccountPage.passwordField, userPassword);
        setText(advantageDemoCreateNewAccountPage.confirmPasswordField, userPassword);
        setText(advantageDemoCreateNewAccountPage.emailField, userEmail);
        click(advantageDemoCreateNewAccountPage.allowOffersCheckbox);
    }

    @Then("i should verify if the required fields are filled")
    public void iShouldVerifyIfTheRequiredFieldsAreFilled() {
        Assert.assertEquals(username, getTextFromInput(advantageDemoCreateNewAccountPage.usernameField));
        Assert.assertEquals(userPassword, getTextFromInput(advantageDemoCreateNewAccountPage.passwordField));
        Assert.assertEquals(userPassword, getTextFromInput(advantageDemoCreateNewAccountPage.confirmPasswordField));
        Assert.assertEquals(userEmail, getTextFromInput(advantageDemoCreateNewAccountPage.emailField));
    }

    @When("i click on the 'Agree to terms and conditions' checkbox")
    public void iClickOnTheAgreeToTermsAndConditionsCheckbox() {
        click(advantageDemoCreateNewAccountPage.agreeToConditionsCheckbox);
    }

    @Then("i should verify if the 'Agree to terms and conditions' checkbox is selected")
    public void iShouldVerifyIfTheAgreeToTermsAndConditionsCheckboxIsSelected() {
        Assert.assertTrue("The checkbox is selected", advantageDemoCreateNewAccountPage.agreeToConditionsCheckbox.isSelected());
    }

    @When("I click on the register button")
    public void iClickOnTheRegisterButton() {
        click(advantageDemoCreateNewAccountPage.registerButton);
    }

    @Then("I should verify if the user's logged in")
    public void iShouldVerifyIfTheUsersLoggedIn() {
        waitUntilClickable(advantageDemoHomePage.userAccount);
        Assert.assertEquals(username, waitForElement(By.xpath("//a[@id='menuUserLink' and span[contains(.,'" + username + "')]]")).getText());
    }

    @When("i click on any of the products displayed at the popular items section")
    public void iClickOnAnyOfTheProductsDisplayedAtThePopularItemsSection() {
        randomClick(advantageDemoHomePage.popularItems);
    }

    @Then("the product page should load")
    public void theProductPageShouldLoad() {
        waitUntilClickable(advantageDemoProductPage.addToCart);
        Assert.assertTrue(advantageDemoProductPage.addToCart.isDisplayed());
    }

    @When("i select a new quantity of $numberOfItems items")
    public void iSelectANewQuantityOfItems(@Named("numberOfItems") String quantity) {
        setText(advantageDemoProductPage.quantityOfItem, quantity);
    }
}
