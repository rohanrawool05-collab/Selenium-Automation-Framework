package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import dataprovider.TestDataProviders;
import pages.LoginPage;
import utils.AssertionUtils;

public class LoginTest extends BaseTest {

    @Test(dataProvider = "loginData",dataProviderClass = TestDataProviders.class,retryAnalyzer = utils.RetryAnalyzer.class)
    public void loginTest(String username, String password) {

        LoginPage login = new LoginPage(getDriver());
        login.login(username, password);

        String currentUrl = getDriver().getCurrentUrl();
        AssertionUtils.assertContains(currentUrl, "inventory");
        AssertionUtils.assertAll();
    }
    
    
    @Test(dataProvider = "invalidloginData",dataProviderClass = TestDataProviders.class,retryAnalyzer = utils.RetryAnalyzer.class)
    public void invalidPasswordTest(String username,String password) {

    	LoginPage loginPage = new LoginPage(getDriver());
    	loginPage.login(username, password);

        String error =loginPage.getLoginValidationText();

        AssertionUtils.assertContains(error, "Username and password");
        AssertionUtils.assertAll();
    }
    

    @Test(retryAnalyzer = utils.RetryAnalyzer.class)
    public void lockedUserTest() {

    	LoginPage loginPage = new LoginPage(getDriver());
    	loginPage.login("locked_out_user", "secret_sauce");

    	 String error =loginPage.getLoginValidationText();

    	 AssertionUtils.assertContains(error, "locked out");
         AssertionUtils.assertAll();
    }

 
}

