package org.example.factory;

import com.microsoft.playwright.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Properties;

public class PlaywrightFactory {
    Playwright playwright;
    Browser browser;
    BrowserContext browserContext;
    Page page;
    Properties properties = new Properties();

    private static ThreadLocal<Playwright> tlPlaywright = new ThreadLocal<>();
    private static ThreadLocal<Browser> tlBrowser = new ThreadLocal<>();
    private static ThreadLocal<BrowserContext> tlBrowserContext = new ThreadLocal<>();
    private static ThreadLocal<Page> tlPage = new ThreadLocal<>();

    public static Playwright getPlaywright(){
        return tlPlaywright.get();
    }

    public static Browser getBrowser(){
        return tlBrowser.get();
    }

    public static BrowserContext getBrowserContext(){
        return tlBrowserContext.get();
    }

    public static Page getPage(){
        return tlPage.get();
    }

    public Page initBrowser(Properties properties) {
        //playwright = Playwright.create();
        tlPlaywright.set(Playwright.create());
        String browserName = properties.getProperty("browser");
        String headlessProperty = properties.getProperty("headless");
        boolean headless = true;
        if (headlessProperty.equalsIgnoreCase("false")) headless = false;

        switch (browserName.toLowerCase()) {
            case ("chromium"):
                //browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(headless).setSlowMo(100));
                tlBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(headless).setSlowMo(100)));
                break;
            case ("firefox"):
                //browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(headless).setSlowMo(100));
                tlBrowser.set(getPlaywright().firefox().launch(new BrowserType.LaunchOptions().setHeadless(headless).setSlowMo(100)));
                break;
            case ("safari"):
                //browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(headless).setSlowMo(100));
                tlBrowser.set(getPlaywright().webkit().launch(new BrowserType.LaunchOptions().setHeadless(headless).setSlowMo(100)));
                break;
            case ("chrome"):
                //browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(headless).setSlowMo(100));
                tlBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(headless).setSlowMo(100)));
                break;
            default:
                System.out.println("Unsupported browser name provided. Please provide correct browser name.\n");
                break;
        }
        tlBrowserContext.set(getBrowser().newContext());
        tlPage.set(getBrowserContext().newPage());
        getPage().navigate(properties.getProperty("url").trim());
        return getPage();
    }

    public Properties initProperties(){
        try {
            FileInputStream propertyFile = new FileInputStream("./src/test/resources/config/config.properties");
            properties.load(propertyFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }

    public static String takeScreenshot(){
        String path = System.getenv("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
        byte[] buffer = getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));
        String base64path = Base64.getEncoder().encodeToString(buffer);
        return base64path;
    }
}
