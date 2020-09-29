package com.dxc.testekesley.utils;

import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class Utils {
    protected static WebDriverProvider webDriverProvider;

    public Utils(WebDriverProvider webDriverProvider) {
        Utils.webDriverProvider = webDriverProvider;
    }

    /**
     * Makes the execution halt until the element is found. Polling for the element every 3 seconds with timeout of 15 seconds.
     *
     * @param by Identificador de tipo de busca (By.xpath, By.id, By.css, etc)
     * @return
     */
    public static WebElement waitForElement(By by) {
        Wait<WebDriver> wait = new FluentWait<>(webDriverProvider.get())
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(3))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
        return wait.until(webDriver -> {
            if (webDriver == null) throw new AssertionError();
            return webDriver.findElement(by);
        });

    }

    /**
     * Navigates to a given URL
     *
     * @param url
     */
    public static void navigateTo(String url) {
        webDriverProvider.get().get(url);
    }

    /**
     * Clicks on a web element
     *
     * @param webElement
     */
    public static void click(WebElement webElement) {
        webElement.click();
    }

    /**
     * Makes the execution halt until the condition of the element being clickable is met
     *
     * @param webElement
     */
    public static void waitUntilClickable(WebElement webElement) {
        new WebDriverWait(webDriverProvider.get(), Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(webElement));
    }

    /**
     * Makes the execution halt until the condition of the element being visible is met
     * Default timeout: 15 seconds
     */
    public static void waitUntilVisibility(WebElement webElement) {
        new WebDriverWait(webDriverProvider.get(), Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOf(webElement));
    }

    /**
     * Set text inside a web element
     *
     * @param webElement
     * @param text
     */
    public static void setText(WebElement webElement, String text) {
        webElement.clear();
        webElement.sendKeys(text);
    }

    /**
     * Gets text from a web element
     *
     * @param webElement
     * @return
     */
    public static String getText(WebElement webElement) {
        return webElement.getText();
    }

    /**
     * Get text from anything that can be identified using By
     *
     * @param by
     * @return
     */
    public static String getText(By by) {
        return webDriverProvider.get().findElement(by).getText();
    }

    /**
     * Gets the value from an input
     *
     * @param webElement
     * @return value of the input
     */
    public static String getTextFromInput(WebElement webElement) {
        return webElement.getAttribute("value").trim();
    }

    public static Boolean validateTextExists(WebElement webElement) {
        return !getText(webElement).isEmpty() && !getText(webElement).isBlank() && getText(webElement) != null;
    }

    /**
     * Makes the test halt for 10 seconds before executing the next step. Useful when explicit, implicit or fluente wait
     * can't be used.
     */
    public static void waitForPageLoad() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Randomly clicks on an item from a List of web elements
     *
     * @return
     */
    public static String randomClickOnPopularItem(List<WebElement> webElementList) {
        int position = ThreadLocalRandom.current().nextInt(0, webElementList.size() - 1);
        scrollIntoView(By.xpath("//div[contains(@name,'popular_item_')]"));
        String xpathForViewDetails = "//div[contains(@name,'popular_item_')]//a[" + position + "]";
        String xpathForSelectedItemName = "//div[contains(@name,'popular_item_')]//p[" + position + "]";
        String selectedItemName = webElementList.get(position).findElement(By.xpath(xpathForSelectedItemName)).getText();
        webElementList.get(position).findElement(By.xpath(xpathForViewDetails)).click();
        return selectedItemName;
    }

    /**
     * Evaluates if the web element is selected.
     *
     * @param webElement
     * @return
     */
    public static Boolean isSelected(WebElement webElement) {
        return webElement.isSelected();
    }

    /**
     * Clear any text from any type of input/textarea.
     *
     * @param webElement
     */
    public static void clearText(WebElement webElement) {
        click(webElement);
        webElement.sendKeys(Keys.CONTROL + "a");
        webElement.sendKeys(Keys.DELETE);
    }

    public static void scrollIntoView(By by) {
        WebElement element = webDriverProvider.get().findElement(by);
        ((JavascriptExecutor) webDriverProvider.get()).executeScript("arguments[0].scrollIntoView(true);", element);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
