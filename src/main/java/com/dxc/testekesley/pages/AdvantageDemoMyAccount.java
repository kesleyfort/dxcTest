package com.dxc.testekesley.pages;

import com.dxc.testekesley.configuration.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

@Component
@PageObject
public class AdvantageDemoMyAccount {
    @FindBy(xpath = "//div[@class='deleteBtnText']")
    public WebElement deleteAccount;
    @FindBy(xpath = "//div[@class='deletePopupBtn deleteRed' and contains(.,'yes')]")
    public WebElement yesButtonDeleteAccount;
    public String yesButtonDeleteAccountXpath = "//div[@class='deletePopupBtn deleteRed' and contains(.,'yes')]";

}
