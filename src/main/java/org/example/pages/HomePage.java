package org.example.pages;

import com.microsoft.playwright.Page;

public class HomePage {
    private String searchField = "input[placeholder='Search']";
    private String searchButton = "button[class='btn btn-default btn-lg']";
    private String resultTitle = "div[id='content'] h1";
    private String loginLink = "li[class='dropdown open'] li:nth-child(2) a:nth-child(1)";
    private String myAccountLnk = "a[title='My Account'] span[class='hidden-xs hidden-sm hidden-md']";
    Page page;

    public HomePage(Page page){
        this.page = page;
    }

    public String getHomePageTitle(){
        return page.title();
    }

    public String getHomePageUrl(){
        return page.url();
    }
    public String doSearch(String name){
        System.out.println("Seraching for: " + name);
        page.fill(searchField, name);
        page.click(searchButton);
        return page.textContent(resultTitle);
    }

    public LoginPage navigateToLoginPage() {
        page.click(myAccountLnk);
        page.click(loginLink);
        return new LoginPage(page);
    }
}
