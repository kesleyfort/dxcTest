package com.dxc.testekesley.utils;

import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class Utils {
    protected static WebDriverProvider webDriverProvider;

    public Utils(WebDriverProvider webDriverProvider) {
        Utils.webDriverProvider = webDriverProvider;
    }

    /** Função usada para fazer o teste esperar enquanto verifica se o webElement encontra-se presente na tela.
     * @param by Identificador de tipo de busca (By.xpath, By.id, By.css, etc)
     * @return
     */
    public static WebElement waitForElement(By by) {
        Wait<WebDriver> wait = new FluentWait<>(webDriverProvider.get())
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofSeconds(3))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
        return wait.until(webDriver -> {
            if (webDriver == null) throw new AssertionError();
            return webDriver.findElement(by);
        });

    }

    /**
     * Função usada para navegar até uma URL
     * @param url
     */
    public static void navigateTo(String url){
        webDriverProvider.get().get(url);
    }

    public static void click(WebElement webElement){
        webElement.click();
    }
}
