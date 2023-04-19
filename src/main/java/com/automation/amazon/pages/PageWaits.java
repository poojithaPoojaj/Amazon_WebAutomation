package com.automation.amazon.pages;

import com.automation.amazon.exceptions.ElementExceptionHandler;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.List;

public class PageWaits {

    private WebDriver webDriver;
    private FluentWait<WebDriver> wait;

    public PageWaits(WebDriver webDriver) {
        this.webDriver = webDriver;
        wait = initWait(webDriver);
    }

    private FluentWait<WebDriver> initWait(WebDriver webDriver) {
        return new FluentWait<>(webDriver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class);

    }
    public List<WebElement> waitUntilAllElementsAreVisible(By by) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }
    public List<WebElement> waitUntilAllElementsAreVisible(WebElement webElements) {
        return wait.until(ExpectedConditions.visibilityOfAllElements(webElements));
    }

    public WebElement waitForElementToBePresent(By by) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement waitForElementToBeClickable(By by) {
        return wait.until(ExpectedConditions.elementToBeClickable(by));
    }
    public WebElement waitForElementToBePresent(WebElement webElement){
        return wait.until(ExpectedConditions.visibilityOf(webElement));
    }
    public WebElement waitForElementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    public WebElement waitForElementToBePresent(By parentLocator,By childLocator){
        WebElement element=waitForElementToBePresent(parentLocator);
        return waitForElementToBePresent(element.findElement(childLocator));
    }


    public WebElement waitForElementToBeClickable(WebElement webElement, By by) {
     return waitForElementToBeClickable(webElement.findElement(by));
    }



}
