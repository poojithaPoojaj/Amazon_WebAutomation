package com.automation.amazon.pages;

import com.automation.amazon.exceptions.ElementExceptionHandler;
import com.automation.amazon.models.FilterName;
import com.automation.amazon.models.ItemDetail;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ProductsPage extends BasePage{
    final String ITEM_TYPES=".a-unordered-list>:nth-child";
    By text=By.className("a-color-base");
    By productTitle=By.cssSelector(".a-color-base.a-text-normal");
    By types=By.cssSelector("span[data-csa-c-type='element']>li");
    By productRating=By.cssSelector(" .a-icon-star-small>[class='a-icon-alt']");
    By productContainer=By.cssSelector("div[data-component-type='s-search-result']");
    By customerReviewFilter=By.id("reviewsRefinements");
    By brandFilter=By.id("brandsRefinements");
    By brandNames=By.cssSelector("li[id^='p_89']");
    By checkBox=By.className("a-checkbox");
    By seeMoreOrLess=By.className("a-expander-prompt");







//    By product=By.cssSelector(".a-section:not([class*=\" \"])>div.sg-row>div:nth-child(2)");
    public ProductsPage(WebDriver webDriver) {
        super(webDriver);
    }
    public List<String> getProductsDetail(ItemDetail itemDetails){
//       List<WebElement> elements=waits.waitForElementsToBeVisible(waits.waitForElementToBePresent(brandFilter,brandNames))
        List<WebElement> elements=waits.waitUntilAllElementsAreVisible(productContainer);
        By locatorValue;
        switch (itemDetails){
            case RATING ->locatorValue=productRating;

            default -> locatorValue=productTitle;
        }

        List<String> list= new ArrayList<>();
        for(WebElement element : elements) {
            String text=actions.getText(element,locatorValue);
           if(text.isEmpty()){
              list.add(actions.getJsText(element,locatorValue));
           }
           else{
               list.add(text);
           }

        }
        return list;

    }
    public String selectFilterAndGetSelectedItem(int value,FilterName filterName) throws InterruptedException {
        By locatorValue=By.cssSelector(ITEM_TYPES+"("+value+")");
        switch (filterName){
            case CUSTOMER_RATINGS :{
                actions.clickThroughJs(waits.waitForElementToBePresent(customerReviewFilter,locatorValue));
                return String.valueOf(value);
            }
            case BRAND :{
                if(elementExceptionHandler.isElementPresent(waits.waitForElementToBePresent(brandFilter,seeMoreOrLess))){
                    waits.waitForElementToBePresent(brandFilter,seeMoreOrLess).click();
                    waits.waitForElementToBePresent(brandFilter,seeMoreOrLess).getText().equalsIgnoreCase("SEE LESS");
                }
//                List<WebElement> list= waits.waitForElementToBeVisible(waits.waitForElementToBePresent(brandFilter,brandNames));
//                actions.clickThroughJs(list);
//                return actions.getText(element);
            }
            default:
                return null;
        }

    }
    public int getParticularFilterCount(FilterName filterName){
        switch (filterName){
            case CUSTOMER_RATINGS :
                return actions.getListOfElements(waits.waitForElementToBePresent(customerReviewFilter),types).size();
            case BRAND:
                return actions.getListOfElements(waits.waitForElementToBePresent(brandFilter),types).size();
            default:
                return 0;
        }
    }

}
