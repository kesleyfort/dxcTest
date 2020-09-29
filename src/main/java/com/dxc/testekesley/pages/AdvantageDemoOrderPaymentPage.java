package com.dxc.testekesley.pages;

import com.dxc.testekesley.configuration.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

@Component
@PageObject
public class AdvantageDemoOrderPaymentPage {
    @FindBy(xpath = "//article/h3")
    public WebElement orderPaymentTitle;
    @FindBy(id = "next_btn")
    public WebElement next;
    @FindBy(name = "masterCredit")
    public WebElement masterCreditOption;
    @FindBy(id = "creditCard")
    public WebElement creditCardNumberField;
    @FindBy(name = "cvv_number")
    public WebElement cvvNumberField;
    @FindBy(name = "cardholder_name")
    public WebElement cardHolderName;
    @FindBy(id = "pay_now_btn_ManualPayment")
    public WebElement payNowButton;
    @FindBy(xpath = "//p[contains(text(),'Your tracking number is')]")
    public WebElement trackingNumberText;
    @FindBy(xpath = "//p[contains(text(),'Your order number is')]")
    public WebElement OrderNumberText;

}
