package com.dxc.testekesley.steps;

import com.dxc.testekesley.pages.*;
import org.apache.log4j.Logger;
import org.jbehave.core.annotations.*;
import org.jbehave.web.selenium.WebDriverProvider;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

import static com.dxc.testekesley.utils.Utils.*;

@Component
public class AdvantageDemoSteps extends AbstractSteps {
    protected Logger LOG = Logger.getLogger(this.getClass());
    final
    AdvantageDemoHomePage advantageDemoHomePage;
    final
    AdvantageDemoLoginPopUp advantageDemoLoginPopUp;
    final
    AdvantageDemoCreateNewAccountPage advantageDemoCreateNewAccountPage;
    final
    AdvantageDemoMyAccount advantageDemoMyAccount;
    final
    AdvantageDemoProductPage advantageDemoProductPage;
    final
    AdvantageDemoShoppingCartPage advantageDemoShoppingCartPage;
    final
    AdvantageDemoOrderPaymentPage advantageDemoOrderPaymentPage;
    final
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
    String quantityOfItem;
    double priceOfItem;
    private ArrayList<HashMap<String, String>> dataset;

    public AdvantageDemoSteps(AdvantageDemoHomePage advantageDemoHomePage, AdvantageDemoLoginPopUp advantageDemoLoginPopUp, AdvantageDemoCreateNewAccountPage advantageDemoCreateNewAccountPage, AdvantageDemoMyAccount advantageDemoMyAccount, AdvantageDemoProductPage advantageDemoProductPage, AdvantageDemoShoppingCartPage advantageDemoShoppingCartPage, AdvantageDemoOrderPaymentPage advantageDemoOrderPaymentPage, WebDriverProvider webDriverProvider) {
        this.advantageDemoHomePage = advantageDemoHomePage;
        this.advantageDemoLoginPopUp = advantageDemoLoginPopUp;
        this.advantageDemoCreateNewAccountPage = advantageDemoCreateNewAccountPage;
        this.advantageDemoMyAccount = advantageDemoMyAccount;
        this.advantageDemoProductPage = advantageDemoProductPage;
        this.advantageDemoShoppingCartPage = advantageDemoShoppingCartPage;
        this.advantageDemoOrderPaymentPage = advantageDemoOrderPaymentPage;
        this.webDriverProvider = webDriverProvider;
        dataset = readDataFromDatasheet("src/main/resources/Data.xlsx");

    }

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

    @When("i fill the users $id information from spreadsheet on the new account page")
    public void iFillTheUsersInformationFromSpreadSheetOnTheNewAccountPage(@Named("id") int id) {
        waitUntilClickable(advantageDemoCreateNewAccountPage.usernameField);
        setText(advantageDemoCreateNewAccountPage.usernameField, dataset.get(id).get("username"));
        setText(advantageDemoCreateNewAccountPage.passwordField, dataset.get(id).get("password"));
        setText(advantageDemoCreateNewAccountPage.confirmPasswordField, dataset.get(id).get("password"));
        setText(advantageDemoCreateNewAccountPage.emailField, dataset.get(id).get("email"));
        click(advantageDemoCreateNewAccountPage.allowOffersCheckbox);
    }

    @Then("i should verify if the required fields are filled")
    public void iShouldVerifyIfTheRequiredFieldsAreFilled() {
        Assert.assertEquals(username, getTextFromInput(advantageDemoCreateNewAccountPage.usernameField));
        Assert.assertEquals(userPassword, getTextFromInput(advantageDemoCreateNewAccountPage.passwordField));
        Assert.assertEquals(userPassword, getTextFromInput(advantageDemoCreateNewAccountPage.confirmPasswordField));
        Assert.assertEquals(userEmail, getTextFromInput(advantageDemoCreateNewAccountPage.emailField));
    }

    @Then("i should verify if the required fields are filled wth data from the $id at the spreadsheet")
    public void iShouldVerifyIfTheRequiredFieldsAreFilledWithDataFromTheSpreadsheet(@Named("id") int id) {
        Assert.assertEquals(dataset.get(id).get("username"), getTextFromInput(advantageDemoCreateNewAccountPage.usernameField));
        Assert.assertEquals(dataset.get(id).get("password"), getTextFromInput(advantageDemoCreateNewAccountPage.passwordField));
        Assert.assertEquals(dataset.get(id).get("password"), getTextFromInput(advantageDemoCreateNewAccountPage.confirmPasswordField));
        Assert.assertEquals(dataset.get(id).get("email"), getTextFromInput(advantageDemoCreateNewAccountPage.emailField));
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
        Assert.assertEquals(username, waitForElement(By.xpath("//a[@id='menuUserLink' and span[contains(.,'" + username + "')]]")).getText());
    }

    @Then("I should verify if the user $id is logged in")
    public void iShouldVerifyIfTheUserIsLoggedIn(@Named("id") int id) {
        Assert.assertEquals(dataset.get(id).get("username"), waitForElement(By.xpath("//a[@id='menuUserLink' and span[contains(.,'" + dataset.get(id).get("username") + "')]]")).getText());
    }

    @When("i click on any of the products displayed at the popular items section")
    public void iClickOnAnyOfTheProductsDisplayedAtThePopularItemsSection() {
        selectedPopularItem = randomClickOnPopularItem(advantageDemoHomePage.popularItems);
    }

    @When("i click on the product selected by user $id displayed at the popular items section")
    public void iClickOnAnyOfTheProductsDisplayedAtThePopularItemsSection(@Named("id") int id) {
        String popularItemXpath = "//div[contains(@name,'popular_item_') and p[text()='" + dataset.get(id).get("popularItem") + "']]//p";
        selectedPopularItem = webDriverProvider.get().findElement(By.xpath(popularItemXpath)).getText().trim().strip();
        webDriverProvider.get().findElement(By.xpath("//div[contains(@name,'popular_item_') and p[text()='" + dataset.get(id).get("popularItem") + "']]//a")).click();
    }

    @Then("the product page should load")
    public void theProductPageShouldLoad() {
        waitUntilVisibility(advantageDemoProductPage.addToCart);
        Assert.assertTrue("Product page is loaded", advantageDemoProductPage.addToCart.isDisplayed());
    }

    @When("i select a new quantity of $numberOfItems items")
    public void iSelectANewQuantityOfItems(@Named("numberOfItems") String quantity) {
        quantityOfItem = quantity;
        waitUntilClickable(advantageDemoProductPage.quantityOfItem);
        clearText(advantageDemoProductPage.quantityOfItem);
        setText(advantageDemoProductPage.quantityOfItem, quantity);
        try {
            priceOfItem = getProductValue(advantageDemoProductPage.price);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @When("i select a new quantity based on the user $id on spreadsheet")
    public void iSelectANewQuantityOfItemsBasedOnSpreadsheet(@Named("id") String id) {
        int userId = Integer.parseInt(id);
        quantityOfItem = dataset.get(userId).get("quantity");
        waitUntilClickable(advantageDemoProductPage.quantityOfItem);
        clearText(advantageDemoProductPage.quantityOfItem);
        setText(advantageDemoProductPage.quantityOfItem, quantityOfItem);
        try {
            priceOfItem = getProductValue(advantageDemoProductPage.price);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Then("i should verify if the quantity is $quantity")
    public void thenIShouldVerifyIfTheQuantityIs(@Named("quantity") String quantity) {
        Assert.assertEquals(quantity, getTextFromInput(advantageDemoProductPage.quantityOfItem));
    }

    @Then("i should verify if product quantity is the same as the $id on the spreadsheet")
    public void thenIShouldVerifyIfTheQuantityIsTheSameAsSpreadSheet(@Named("id") int id) {
        Assert.assertEquals(dataset.get(id).get("quantity"), getTextFromInput(advantageDemoProductPage.quantityOfItem));
    }

    @When("i click on the add to cart button")
    public void whenIClickOnTheAddToCartButton() {
        click(advantageDemoProductPage.addToCart);
    }

    @Then("i should verify if the selected item is in the cart")
    public void thenIShouldVerifyIfTheSelectedItemIsInTheCart() {
        String xpathExpression = "//tool-tip-cart//h3[contains(.,'" + selectedPopularItem + "')]";
        WebElement productTitle = waitForElement(By.xpath(xpathExpression));
        String actualTitle = productTitle.getText();
        Assert.assertEquals(selectedPopularItem, actualTitle);
    }

    @When("i click on the cart")
    public void whenIClickOnTheCart() {
        waitUntilClickable(advantageDemoProductPage.cart);
        click(advantageDemoProductPage.cart);
    }

    @Then("i should verify if the product, desired quantity and price are correct")
    public void thenIShouldVerifyIfTheProductDesiredQuantityAndPriceAreCorrect() {
        String productTitleXpath = "//div[@id='shoppingCart']//tbody//td//label[contains(.,'" + selectedPopularItem + "')]";
        String productQuantityXpath = "//div[@id='shoppingCart']//tbody//td//label[text()='" + quantityOfItem + "']";
        String productPriceXpath = "//div[@id='shoppingCart']//tbody//td//p";
        Double totalOfOrder = Integer.parseInt(quantityOfItem) * priceOfItem;
        String totalOfOrderInCurrency = getProductValueInCurrency(totalOfOrder);
        waitForElement(By.xpath(productTitleXpath));
        Assert.assertEquals(selectedPopularItem, getText(By.xpath(productTitleXpath)).strip().trim());
        Assert.assertEquals(quantityOfItem, getText(By.xpath(productQuantityXpath)).strip().trim());
        Assert.assertEquals(totalOfOrderInCurrency, getText(By.xpath(productPriceXpath)).strip().trim());
    }

    @When("i click on the checkout button")
    @Pending
    public void whenIClickOnTheCheckoutButton() {
        click(advantageDemoShoppingCartPage.checkout);
    }

    @Then("i should verify if the order payment screen has loaded")
    @Pending
    public void thenIShouldVerifyIfTheOrderPaymentScreenHasLoaded() {
        Assert.assertTrue("Page has loaded", advantageDemoOrderPaymentPage.orderPaymentTitle.isDisplayed());
    }

    @When("i click on the next button")
    @Pending
    public void whenIClickOnTheNextButton() {
        click(advantageDemoOrderPaymentPage.next);
    }

    @When("i select the MasterCredit payment option")
    @Pending
    public void whenISelectTheMasterCreditPaymentOption() {
        click(advantageDemoOrderPaymentPage.masterCreditOption);
    }

    @When("i fill the required fields for payment")
    @Pending
    public void whenIFillTheRequiredFieldsForPayment() {
        setText(advantageDemoOrderPaymentPage.creditCardNumberField, "1234 5678 9012");
        setText(advantageDemoOrderPaymentPage.cvvNumberField, "765");
        setText(advantageDemoOrderPaymentPage.cardHolderName, "Bam Adebayo");
    }

    @Then("i should verify if the required fields for the payment are filled")
    @Pending
    public void thenIShouldVerifyIfTheRequiredFieldsForThePaymentAreFilled() {
        Assert.assertNotNull("Verifying if field 'Credit Card number' is not null", getTextFromInput(advantageDemoOrderPaymentPage.creditCardNumberField));
        Assert.assertNotNull("Verifying if field 'CVV Number' is not null", getTextFromInput(advantageDemoOrderPaymentPage.cvvNumberField));
        Assert.assertNotNull("Verifying if field 'Cardholder Name' is not null", getTextFromInput(advantageDemoOrderPaymentPage.cardHolderName));
    }

    @When("i click on the pay now button")
    @Pending
    public void whenIClickOnThePayNowButton() {
        click(advantageDemoOrderPaymentPage.payNowButton);
    }

    @Then("i should verify if the order and tracking number are shown")
    @Pending
    public void thenIShouldVerifyIfTheOrderAndTrackingNumberAreShown() {
        waitUntilVisibility(advantageDemoOrderPaymentPage.trackingNumberText);
        Assert.assertTrue("Verifying if the text is displayed", advantageDemoOrderPaymentPage.trackingNumberText.isDisplayed());
        Assert.assertTrue("Verifying if the text is displayed", advantageDemoOrderPaymentPage.orderPaymentTitle.isDisplayed());
    }

    @When("i logout")
    @Pending
    public void whenILogout() {
        click(advantageDemoHomePage.userAccount);
        click(advantageDemoHomePage.logout);
    }

    @Then("i should be logged out")
    @Pending
    public void thenIShouldBeLoggedOut() {
        Assert.assertEquals("", waitForElement(By.xpath("//a[@id='menuUserLink']//span")).getText());
    }

}
