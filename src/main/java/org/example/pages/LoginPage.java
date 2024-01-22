package org.example.pages;

import com.microsoft.playwright.Page;

public class LoginPage {
    Page page;
    private String myAccountLink = "//div/h2[text()='My Account']";
    private String loginField = "#input-email";
    private String passwordField = "#input-password";
    private String loginButton = "input[value='Login']";
    private String forgetPasswordLink = "//div[@class='form-group']//a[normalize-space()='Forgotten Password']";

    public LoginPage(Page page) {
        this.page = page;
    }

    public String getLoginPageTitle() {
        return page.title();
    }

    public boolean isLogged() {
        return page.isVisible(myAccountLink);
    }

    public boolean isForgetPasswordLinkPresent() {
        return page.isVisible(forgetPasswordLink);
    }

    public boolean doLogin(String userName, String userPassword) {
        System.out.println("Log in with following credentials: username - "
                + userName + " password - "
                + userPassword);
        page.fill(loginField, userName);
        page.fill(passwordField, userPassword);
        page.click(loginButton);
        if (page.isVisible(myAccountLink)){
            System.out.println("User logged in successfully/n");
            return true;
        }
        return false;
    }
}
