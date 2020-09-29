package com.dxc.testekesley.pages;

import com.dxc.testekesley.configuration.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

@Component
@PageObject
public class AdvantageDemoProductPage {
    @FindBy(name = "save_to_cart")
    public WebElement addToCart;
    @FindBy(name = "quantity")
    public WebElement quantityOfItem;
    @FindBy(xpath = "//div[@id='Description']//h1")
    public WebElement productTitle;
    public String productTitleXpath = "//div[@id='Description']//h1";
    @FindBy(xpath = "//li//a[@id='shoppingCartLink']")
    public WebElement cart;
    @FindBy(xpath = "//div[@id='Description']/h2")
    public WebElement price;

}
