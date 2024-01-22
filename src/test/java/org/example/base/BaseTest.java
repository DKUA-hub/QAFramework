package org.example.base;

import com.microsoft.playwright.Page;
import org.example.factory.PlaywrightFactory;
import org.example.pages.HomePage;
import org.example.pages.LoginPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.Properties;

public class BaseTest {
    PlaywrightFactory pf;
    Page page;
    protected HomePage homePage;
    protected LoginPage loginPage;
    protected Properties properties;

    @BeforeClass
    public void setup() {
        pf = new PlaywrightFactory();
        properties = pf.initProperties();
        page = pf.initBrowser(properties);
        homePage = new HomePage(page);
    }
    @AfterClass
    public void tearDown() {
        page.context().browser().close();
    }
}
