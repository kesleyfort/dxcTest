package com.dxc.testekesley.pages;

import com.dxc.testekesley.configuration.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@PageObject
public class AdvantageDemoHomePage {
    @FindBy(id = "menuUserLink")
    public WebElement userAccount;
    public String userAccountXpath = "//a[@id='menuUserLink']";
    @FindBy(xpath = "//login-modal")
    public WebElement loginModal;
    public String loginModalXpath = "//login-modal";
    @FindBy(xpath = "//div[@class='loader']")
    public WebElement loadingSpinner;
    public String loadingSpinnerXpath = "//div[@class='loader']";
    @FindBy(xpath = "//div[@id='loginMiniTitle']//label[contains(.,'My account')]")
    public WebElement myAccount;
    @FindBy(xpath = "//article[@id='popular_items']//a")
    public List<WebElement> popularItems;
}
