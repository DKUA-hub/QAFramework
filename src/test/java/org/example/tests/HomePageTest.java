package org.example.tests;

import org.example.base.BaseTest;
import org.example.constants.AppConstants;
import org.testng.Assert;
import org.testng.annotations.*;

public class HomePageTest extends BaseTest {

    @Test(priority = 1)
    public void verifyHomePageUrl() {
        String actualUrl = homePage.getHomePageUrl();
        Assert.assertEquals(actualUrl,
                properties.getProperty("url").trim(),
                "HomePage URL is incorrect");
    }

    @Test(priority = 1)
    public void verifyHomePageTitle() {
        String actualTitle = homePage.getHomePageTitle();
        Assert.assertEquals(actualTitle, AppConstants.HOME_PAGE_TITLE, "HomePage title is incorrect");
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
        Assert.assertEquals(searchResult,
                "Search - "+productName,
                "Search Results page has incorrect title");
    }
}
