package org.example.pages;

import com.microsoft.playwright.Page;

public class HomePage {
    private String searchField = "input[placeholder='Search']";
    private String searchButton = "button[class='btn btn-default btn-lg']";
    private String resultTitle = "div[id='content'] h1";
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
        page.fill(searchField, name);
        page.click(searchButton);
        return page.textContent(resultTitle);
    }
}
