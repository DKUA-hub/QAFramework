package org.example.factory;

import com.microsoft.playwright.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PlaywrightFactory {
    Playwright playwright;
    Browser browser;
    BrowserContext browserContext;
    Page page;
    Properties properties = new Properties();

    public Page initBrowser(Properties properties) {
        playwright = Playwright.create();
        String browserName = properties.getProperty("browser");
        String headlessProperty = properties.getProperty("headless");
        boolean headless = true;
        if (headlessProperty.equalsIgnoreCase("false")) headless = false;

        switch (browserName.toLowerCase()) {
            case ("chromium"):
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(headless).setSlowMo(100));
                break;
            case ("firefox"):
                browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(headless).setSlowMo(100));
                break;
            case ("safari"):
                browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(headless).setSlowMo(100));
                break;
            case ("chrome"):
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(headless).setSlowMo(100));
                break;
            default:
                System.out.println("Unsupported browser name provided. Please provide correct browser name.\n");
                break;
        }
        browserContext = browser.newContext();
        page = browserContext.newPage();
        page.navigate(properties.getProperty("url").trim());
        return page;
    }

    public Properties initProperties(){
        try {
            FileInputStream propertyFile = new FileInputStream("./src/test/resources/config.properties");
            properties.load(propertyFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }
}
