package com.automation.amazon.exceptions;


import com.automation.amazon.pages.BasePage;
import com.automation.amazon.pages.PageWaits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementExceptionHandler {
private WebDriver webDriver;
private   PageWaits waits;
    public ElementExceptionHandler(WebDriver webDriver) {
       this.webDriver=webDriver;
       this.waits=new PageWaits(webDriver);

    }
    public boolean isElementPresent(By by) {

        WebElement element=waits.waitForElementToBePresent(by);
        return isElementPresent(element);
    }
    public boolean isElementPresent(WebElement webElement) {

        try {
            waits.waitForElementToBePresent(webElement).isDisplayed();
            return true;
        }
        catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

}
