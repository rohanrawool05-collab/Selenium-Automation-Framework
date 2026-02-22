package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import dataprovider.TestDataProviders;
import pages.*;

public class CompletePlacingOrderTest extends BaseTest {

    @Test(dataProvider = "loginData",dataProviderClass = TestDataProviders.class,retryAnalyzer = utils.RetryAnalyzer.class)
    public void placeOrderTest(String username , String password) {

        String confirmationMessage =
                new LoginPage(getDriver())
                        .login(username,password)
                        .addProduct("Sauce Labs Backpack")
                        .goToCart()
                        .clickCheckout()
                        .fillDetails("Rohan", "Rawool", "400612")
                        .finishOrder()
                        .getConfirmationText();

        Assert.assertTrue(confirmationMessage.contains("Thank you"));
    }
}


