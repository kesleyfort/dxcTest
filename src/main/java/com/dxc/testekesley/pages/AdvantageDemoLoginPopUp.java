package com.dxc.testekesley.pages;

import com.dxc.testekesley.configuration.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

@Component
@PageObject
public class AdvantageDemoLoginPopUp {
    @FindBy(name = "username")
    public WebElement usernameField;
    public String usernameXpath = "//input[@name='username']";
    @FindBy(name = "password")
    public WebElement passwordField;
    @FindBy(name = "remember_me")
    public WebElement rememberMeCheckBox;
    @FindBy(id = "sign_in_btnundefined")
    public WebElement signInButton;
    @FindBy(xpath = "//a[contains(.,'CREATE NEW ACCOUNT')]")
    public WebElement createNewAccount;
    public String createNewAccountXpath = "//a[contains(.,'CREATE NEW ACCOUNT')]";
}
