package com.dxc.testekesley.pages;

import org.apache.log4j.Logger;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class AbstractPage {

    protected Logger LOG = Logger.getLogger(this.getClass());

    @Autowired
    protected WebDriverProvider webDriverProvider;

    public void waitPageLoad() {
        WebDriverWait wait = new WebDriverWait(webDriverProvider.get(), 30);
        wait.until(ExpectedConditions.visibilityOfAllElements(elementsToWait()));
    }

    protected List<WebElement> elementsToWait() {
        return null;
    }
}