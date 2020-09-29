package com.dxc.testekesley.pages;

import com.dxc.testekesley.configuration.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

@Component
@PageObject
public class AdvantageDemoShoppingCartPage {
    @FindBy(id = "checkOutButton")
    public WebElement checkout;
    @FindBy(xpath = "//div[@id='Description']//h1")
    public WebElement productTitle;
    public String productTitleXpath = "//div[@id='Description']//h1";
    @FindBy(id = "toolTipCart")
    public WebElement toolTipCart;

}
