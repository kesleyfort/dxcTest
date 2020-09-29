package com.dxc.testekesley.utils;

import org.apache.poi.ss.usermodel.*;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
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
     * Makes the execution halt until the condition of the element being invisible is met
     * Default timeout: 15 seconds
     */
    public static void waitUntilInvisibility(WebElement webElement) {
        new WebDriverWait(webDriverProvider.get(), Duration.ofSeconds(30))
                .until(ExpectedConditions.invisibilityOf(webElement));
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
     * 1
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
        int position = ThreadLocalRandom.current().nextInt(1, webElementList.size());
        scrollIntoView(By.xpath("//div[contains(@name,'popular_item_')]"));
        String xpathForViewDetails = "//div[contains(@name,'popular_item_')]" + "[" + position + "]" + "//a";
        String xpathForSelectedItemName = "//div[contains(@name,'popular_item_')]" + "[" + position + "]" + "//p";
        String selectedItemName = webElementList.get(position).findElement(By.xpath(xpathForSelectedItemName)).getText();
        if (selectedItemName.equals("HP ELITEBOOK FOLIO")) selectedItemName = "HP CHROMEBOOK 14 G1(ES)";
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

    public static double getProductValue(WebElement webElement) throws ParseException {
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        Number number;
        number = nf.parse(webElement.getText().trim().strip());
        return number.doubleValue();
    }

    public static String getProductValueInCurrency(Double value) {
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        String number;
        number = nf.format(value);
        return number;
    }

    public static ArrayList<HashMap<String, String>> readDataFromDatasheet(String path) {
        ArrayList<HashMap<String, String>> dataset = new ArrayList<>();
        HashMap<String, String> data = new HashMap<>();
        try (InputStream inp = new FileInputStream(path)) {
            Workbook wb = WorkbookFactory.create(inp);
            Sheet sheet = wb.getSheetAt(0);
            sheet.removeRow(sheet.getRow(0));
            for (Row row : sheet) {
                for (Cell cell : row) {
                    if (cell.getColumnIndex() > 6)
                        break;
                    switch (cell.getCellType()) {
                        case STRING:
                            if (cell.getColumnIndex() == 0) {
                                data.put("username", cell.getStringCellValue());
                            } else if (cell.getColumnIndex() == 1) {
                                data.put("email", cell.getStringCellValue());
                            } else if (cell.getColumnIndex() == 2) {
                                data.put("password", cell.getStringCellValue());
                            } else if (cell.getColumnIndex() == 3) {
                                data.put("popularItem", cell.getStringCellValue());
                            } else if (cell.getColumnIndex() == 5) {
                                data.put("out _tracking_number", cell.getStringCellValue());
                            } else if (cell.getColumnIndex() == 6) {
                                data.put("out_order_number", cell.getStringCellValue());
                            }
                            break;
                        case NUMERIC:
                            data.put("quantity", String.valueOf(cell.getNumericCellValue()));
                            System.out.println(cell.getNumericCellValue());
                            break;
                    }
                }
                dataset.add(data);
            }
            System.out.println(dataset.toString());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return dataset;
    }

}
