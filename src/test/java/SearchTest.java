import com.automation.amazon.drivers.DriverActions;
import com.automation.amazon.drivers.DriverCreator;
import com.automation.amazon.models.Item;
import com.automation.amazon.models.ItemDetail;
import com.automation.amazon.pages.HomePage;
import com.automation.amazon.pages.LauncherPage;
import com.automation.amazon.pages.ProductsPage;
import org.openqa.selenium.WebDriver;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;


public class SearchTest {
    @Test(priority = 1)
    public void verifyIfSearchItemShowReleventResults() {

        String searchItem = "Jeans";
        String searchKey = "jeans";
        String browser = "chrome";
        WebDriver webDriver=new DriverCreator().create(browser);
        LauncherPage launcherPage = new LauncherPage(webDriver);
        launcherPage.navigateTo("https://www.amazon.com/");

        HomePage homePage=new HomePage(webDriver);
        homePage.search(searchItem);
        List<Item> resultItems=homePage.getSearchItems();
        Assert.assertTrue(resultItems.stream().allMatch(product -> product.getName().toLowerCase().contains(searchKey.toLowerCase())));
        new DriverActions(webDriver).closeDriver();
    }

 @Test
   public void verifyRecentlySearchedItemIsDisplayedInSearchResultContainer(){

     String searchItem = "Smart Phone";
     String browser = "chrome";
     WebDriver webDriver=new DriverCreator().create(browser);
     LauncherPage launcherPage = new LauncherPage(webDriver);
     launcherPage.navigateTo("https://www.amazon.com/");

     HomePage homePage=new HomePage(webDriver);
     homePage.search(searchItem);
     homePage.clickEnterToSearch();
     homePage.clearTextInSearchBar(searchItem);
     List<Item> searchedItems=homePage.getSearchedItems();

     String recentSearchedItem=searchedItems.get(0).getName().toLowerCase();
     System.out.println(recentSearchedItem);
     Assert.assertTrue(recentSearchedItem.toLowerCase().contains(searchItem.toLowerCase().replaceAll(" ","")));
     new DriverActions(webDriver).closeDriver();
 }
 @Test
    public void searchWithBrandNameAndVerifyIfReleventProductsShowedBasedOnBrandName() throws InterruptedException {
     String brandName = "samsung";
     String browser = "chrome";
     WebDriver webDriver=new DriverCreator().create(browser);
     LauncherPage launcherPage = new LauncherPage(webDriver);
     launcherPage.navigateTo("https://www.amazon.com/");

     HomePage homePage=new HomePage(webDriver);
     homePage.search(brandName);
     homePage.clickEnterToSearch();
     List<String> titles=new ProductsPage(webDriver).getProductsDetail(ItemDetail.TITLE);
     Assert.assertTrue(titles.stream().limit(5).allMatch(title->title.toLowerCase().contains(brandName.toLowerCase())));
     new DriverActions(webDriver).closeDriver();

 }

 }