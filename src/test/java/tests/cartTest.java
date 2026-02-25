package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import dataprovider.TestDataProviders;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductPage;
import utils.AssertionUtils;

public class cartTest extends BaseTest{
	
	
	@Test(dataProvider = "loginData",dataProviderClass = TestDataProviders.class,retryAnalyzer = utils.RetryAnalyzer.class)
	public void addSingleProductTest(String username , String password) {
		ProductPage productPage =
                new LoginPage(getDriver())
                        .login(username, password);

        productPage
                .addProduct("Sauce Labs Backpack");
	}
	
	
	@Test(dataProvider = "loginData",dataProviderClass = TestDataProviders.class,retryAnalyzer = utils.RetryAnalyzer.class)
    public void addMultipleProductsTest(String username,String password) {

        ProductPage productPage =
                new LoginPage(getDriver())
                        .login(username, password);

        productPage
                .addProduct("Sauce Labs Backpack")
                .addProduct("Sauce Labs Bike Light");

       
        		String cartCount =
                new CartPage(getDriver()).getCartBadge();

        		AssertionUtils.assertContains(cartCount, "2");
                AssertionUtils.assertAll();
    }
	
	
    @Test(dataProvider = "loginData",dataProviderClass = TestDataProviders.class,retryAnalyzer = utils.RetryAnalyzer.class)
    public void removeProductFromCartTest(String username , String password) {

        CartPage cart =
                new LoginPage(getDriver())
                        .login(username, password)
                        .addProduct("Sauce Labs Backpack")
                        .goToCart();

        cart.removeProduct();

        Assert.assertFalse(cart.isProductPresent());
    }

}
