package com.automation.amazon.pages;

import com.automation.amazon.exceptions.ElementExceptionHandler;
import com.automation.amazon.internal.DriverStore;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class BasePage {
    protected PageWaits waits;
    protected WebDriver webDriver;
    protected PageActions actions;
    protected ElementExceptionHandler elementExceptionHandler;

    public BasePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.waits = new PageWaits(webDriver);
        this.actions = new PageActions(webDriver);
        this.elementExceptionHandler=new ElementExceptionHandler(webDriver);

        this.webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    protected synchronized WebDriver getDriver() {
        return new DriverStore().getWebDriver();
    }
}
