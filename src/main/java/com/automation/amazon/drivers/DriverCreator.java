package com.automation.amazon.drivers;

import org.openqa.selenium.WebDriver;

import java.util.Objects;

public class DriverCreator {
    public WebDriver create(String browser) {
        browser = setDefaultBrowser(browser);
        switch (browser.toLowerCase()) {
            case "firefox":
                return new FireFoxDriverManager().create();
            case "edge":
                return new EdgeDriverManager().create();
            default:
                return new ChromeDriverManager().create();


        }

    }
    private String setDefaultBrowser(String browser) {
        if(Objects.isNull(browser) || browser.isEmpty()) {
            browser = "chrome";
        }
        return browser;
    }
}
