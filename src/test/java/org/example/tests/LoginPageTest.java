package org.example.tests;

import org.example.base.BaseTest;
import org.example.constants.AppConstants;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {

    @Test(priority = 1)
    public void testNavigationToLoginPage(){
        loginPage = homePage.navigateToLoginPage();
        String actualLoginPageTitle = loginPage.getLoginPageTitle();
        Assert.assertEquals(actualLoginPageTitle, AppConstants.LOGIN_PAGE_TITLE, "Login page title missmatch");
    }

    @Test(priority = 2)
    public void verifyForgetPasswordLink(){
        Assert.assertTrue(loginPage.isForgetPasswordLinkPresent(),
                "Forget Password link missing");
    }

    @Test(priority = 3)
    public void testLoginProcess(){
        Assert.assertTrue(loginPage.doLogin(properties.getProperty("username"),
                        properties.getProperty("password")),
                "Problem encountered during Log in process"
        );
    }
}
