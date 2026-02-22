package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import dataprovider.TestDataProviders;
import pages.LoginPage;

public class LogoutTest extends BaseTest {

    @Test(dataProvider = "loginData",dataProviderClass = TestDataProviders.class,retryAnalyzer = utils.RetryAnalyzer.class)
    public void logoutTest(String username , String password) {

        new LoginPage(getDriver())
                .login(username, password);

        getDriver().findElement(org.openqa.selenium.By.id("react-burger-menu-btn")).click();
        getDriver().findElement(org.openqa.selenium.By.id("logout_sidebar_link")).click();

        String currentUrl = getDriver().getCurrentUrl();

        Assert.assertTrue(currentUrl.contains("saucedemo"));
    }
}

