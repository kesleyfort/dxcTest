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
    String selectedPopularItem;
    /**
     * Function created to delete the user account after the scenario is finished
     */
    @AfterScenario
    public void deleteAccount() {
        waitForPageLoad();
        click(advantageDemoHomePage.userAccount);
        click(advantageDemoHomePage.myAccount);
        waitForPageLoad();
        click(advantageDemoMyAccount.deleteAccount);
        waitForElement(By.xpath(advantageDemoMyAccount.yesButtonDeleteAccountXpath)).click();
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
        if (!isSelected(advantageDemoCreateNewAccountPage.agreeToConditionsCheckbox))
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
//        waitUntilClickable(advantageDemoHomePage.userAccount);
        Assert.assertEquals(username, waitForElement(By.xpath("//a[@id='menuUserLink' and span[contains(.,'" + username + "')]]")).getText());
    }

    @When("i click on any of the products displayed at the popular items section")
    public void iClickOnAnyOfTheProductsDisplayedAtThePopularItemsSection() {
        selectedPopularItem = randomClickOnPopularItem(advantageDemoHomePage.popularItems);
        LOG.info("Texto " + selectedPopularItem);
    }

    @Then("the product page should load")
    public void theProductPageShouldLoad() {
        waitForElement(By.xpath(advantageDemoProductPage.productTitleXpath));
        LOG.info("Texto " + selectedPopularItem);
        Assert.assertEquals(selectedPopularItem, waitForElement(By.xpath(advantageDemoHomePage.popularItemsNameXpath)).getText().trim().strip());
    }

    @When("i select a new quantity of $numberOfItems items")
    public void iSelectANewQuantityOfItems(@Named("numberOfItems") String quantity) {
        waitUntilClickable(advantageDemoProductPage.quantityOfItem);
        clearText(advantageDemoProductPage.quantityOfItem);
        setText(advantageDemoProductPage.quantityOfItem, quantity);
    }

    @Then("i should verify if the quantity is $quantity")
    public void thenIShouldVerifyIfTheQuantityIs(@Named("quantity") String quantity) {
        Assert.assertEquals(quantity, getTextFromInput(advantageDemoProductPage.quantityOfItem));
    }

    @When("i click on the add to cart button")
    public void whenIClickOnTheAddToCartButton() {
        click(advantageDemoProductPage.addToCart);
    }

    @Then("i should verify if the selected item is in the cart")

    public void thenIShouldVerifyIfTheSelectedItemIsInTheCart() {
        // PENDING
    }

    @When("i click on the cart")
    public void whenIClickOnTheCart() {
        // PENDING
    }

    @Then("i should verify if the product, desired quantity and price are correct")
    public void thenIShouldVerifyIfTheProductDesiredQuantityAndPriceAreCorrect() {
        // PENDING
    }
}
