package org.example.factory;

import com.microsoft.playwright.*;

public class PlaywrightFactory {
    Playwright playwright;
    Browser browser;
    BrowserContext browserContext;
    Page page;
    public Page initBrowser(String browserName){
        playwright = Playwright.create();

        switch(browserName.toLowerCase()){
            case("chromium"):
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(100));
                break;
            case("firefox"):
                browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(100));
                break;
            case("safari"):
                browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(100));
                break;
            case("chrome"):
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false).setSlowMo(100));
                break;
            default:
                System.out.println("Unsupported browser name provided. Please provide correct browser name.\n");
                break;
        }
        browserContext = browser.newContext();
        page = browserContext.newPage();
        page.navigate("https://naveenautomationlabs.com/opencart/");
        return page;
    }

    public void closeConnection() {
        page.close();
        browserContext.close();
        browser.close();
        playwright.close();
    }
}
