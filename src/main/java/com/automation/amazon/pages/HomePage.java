package com.automation.amazon.pages;

import com.automation.amazon.models.Item;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends BasePage{



    By searchBar=By.id("twotabsearchtextbox");

    By searchResults=By.cssSelector("div.s-suggestion-container");
    By searchResultsContainer=By.className("left-pane-results-container");
    By recentSearches=By.className("s-recentSearchDistinct");
    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public HomePage search(String searchItem){
        actions.click(searchBar);
        actions.type(searchBar, searchItem);
        return this;
    }
    public void clearTextInSearchBar(String value){
        actions.clickBackSpaceUntilTextToBeClear(searchBar,value);
    }
    public void clickEnterToSearch(){
        actions.enter(searchBar);
    }
    public List<Item> getSearchItems() {
        waits.waitForElementToBeClickable(searchResultsContainer);
        List<WebElement> elements = waits.waitUntilAllElementsAreVisible(searchResults);
        List<Item> items = new ArrayList<>();
        for(WebElement element : elements) {
            String name = actions.getText(element);
            Item item = new Item();
            item.setName(name);
            items.add(item);
        }
        return items;
    }
    public List<Item> getSearchedItems(){
        List<WebElement> elements = waits.waitUntilAllElementsAreVisible(recentSearches);
        List<Item> items = new ArrayList<>();
        for(WebElement element : elements) {
            String name = actions.getText(element);
            Item item = new Item();
            item.setName(name);
            items.add(item);
        }
        return items;
    }

}
