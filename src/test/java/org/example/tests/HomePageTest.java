package org.example.tests;

import com.microsoft.playwright.Page;
import org.example.factory.PlaywrightFactory;
import org.example.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.*;

public class HomePageTest {
    PlaywrightFactory pf;
    Page page;
    HomePage homePage;

    @BeforeClass
    public void setup() {
        pf = new PlaywrightFactory();
        page = pf.initBrowser("chromium");
        homePage = new HomePage(page);
    }

    @Test(priority = 1)
    public void verifyHomePageUrl() {
        String actualUrl = homePage.getHomePageUrl();
        Assert.assertEquals(actualUrl,
                "https://naveenautomationlabs.com/opencart/",
                "HomePage URL is incorrect");
    }

    @Test(priority = 1)
    public void verifyHomePageTitle() {
        String actualTitle = homePage.getHomePageTitle();
        Assert.assertEquals(actualTitle, "Your Store", "HomePage title is incorrect");
    }

    @DataProvider
    public Object[][] getProductName(){
        return new Object[][]{
                {"iPhone"},
                {"macbook"},
                {"Galaxy"}
        };
    }
    @Test(priority = 2, dataProvider = "getProductName")
    public void testSearch(String productName) {
        String searchResult = homePage.doSearch(productName);
        Assert.assertEquals(searchResult, "Search - "+productName, "Search Results page has incorrect title");
    }

    @AfterClass
    public void tearDown() {
        page.context().browser().close();
    }

}
