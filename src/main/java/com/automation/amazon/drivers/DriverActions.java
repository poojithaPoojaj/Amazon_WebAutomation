package com.automation.amazon.drivers;

import org.openqa.selenium.WebDriver;

public class DriverActions {
    private WebDriver webDriver;

    public DriverActions(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void closeDriver(){
        this.webDriver.close();
    }


}
