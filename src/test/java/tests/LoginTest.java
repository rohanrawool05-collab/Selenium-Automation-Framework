package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import dataprovider.TestDataProviders;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test(dataProvider = "loginData",dataProviderClass = TestDataProviders.class,retryAnalyzer = utils.RetryAnalyzer.class)
    public void loginTest(String username, String password) {

        LoginPage login = new LoginPage(getDriver());
        login.login(username, password);

        String currentUrl = getDriver().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("inventory"));
    }
    
    
    @Test(dataProvider = "invalidloginData",dataProviderClass = TestDataProviders.class,retryAnalyzer = utils.RetryAnalyzer.class)
    public void invalidPasswordTest(String username,String password) {

        new LoginPage(getDriver())
                .login(username , password);

        String error =
                getDriver()
                        .findElement(By.cssSelector("[data-test='error']"))
                        .getText();

        Assert.assertTrue(error.contains("Username and password"));
    }
    

    @Test(retryAnalyzer = utils.RetryAnalyzer.class)
    public void lockedUserTest() {

        new LoginPage(getDriver())
                .login("locked_out_user", "secret_sauce");

        String error =
                getDriver()
                        .findElement(By.cssSelector("[data-test='error']"))
                        .getText();

        Assert.assertTrue(error.contains("locked out"));
    }

 
}

