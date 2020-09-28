package com.dxc.testekesley.pages;

import com.dxc.testekesley.configuration.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

@Component
@PageObject
public class AdvantageDemoCreateNewAccountPage {
    @FindBy(name = "usernameRegisterPage")
    public WebElement usernameField;
    public String usernameXpath = "//input[@name='username']";
    @FindBy(name = "passwordRegisterPage")
    public WebElement passwordField;
    @FindBy(name = "confirm_passwordRegisterPage")
    public WebElement confirmPasswordField;
    @FindBy(name = "emailRegisterPage")
    public WebElement emailField;
    @FindBy(name = "first_nameRegisterPage")
    public WebElement firstNameField;
    @FindBy(name = "last_nameRegisterPage")
    public WebElement lastNameField;
    @FindBy(name = "phone_numberRegisterPage")
    public WebElement phoneNumberField;
    @FindBy(name = "countryListboxRegisterPage")
    public WebElement countryListBox;
    @FindBy(name = "cityRegisterPage")
    public WebElement cityField;
    @FindBy(name = "addressRegisterPage")
    public WebElement addressField;
    @FindBy(name = "state_/_province_/_regionRegisterPage")
    public WebElement stateProvinceRegionField;
    @FindBy(name = "postal_codeRegisterPage")
    public WebElement postalCodeField;
    @FindBy(name = "allowOffersPromotion")
    public WebElement allowOffersCheckbox;
    @FindBy(name = "i_agree")
    public WebElement agreeToConditionsCheckbox;
    @FindBy(id = "register_btnundefined")
    public WebElement registerButton;

}
