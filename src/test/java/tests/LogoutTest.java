package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import dataprovider.TestDataProviders;
import pages.LoginPage;
import pages.ProductPage;

public class LogoutTest extends BaseTest {

    @Test(dataProvider = "loginData",
          dataProviderClass = TestDataProviders.class,
          retryAnalyzer = utils.RetryAnalyzer.class,
          )
    public void logoutTest(String username, String password) {

        ProductPage productPage =
                new LoginPage(getDriver())
                        .login(username, password);

        LoginPage loginPage = productPage.logout();

        // Validation 1 – URL check
        Assert.assertTrue(getDriver().getCurrentUrl().contains("saucedemo"));

        // Validation 2 – Login button visible
        Assert.assertTrue(loginPage.isLoginButtonDisplayed(),
                "Login button should be visible after logout");
    }
}

