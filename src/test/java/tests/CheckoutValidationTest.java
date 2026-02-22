package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import dataprovider.TestDataProviders;
import pages.*;

public class CheckoutValidationTest extends BaseTest {
    
    @Test(dataProvider = "checkoutValidationWithLoginData",
    	      dataProviderClass = TestDataProviders.class,
    	      groups = {"regression"})
    	public void checkoutValidationTest(String username,
    	                                   String password,
    	                                   String firstName,
    	                                   String lastName,
    	                                   String postalCode,
    	                                   String expectedError) {

    	    CheckoutPage checkout =
    	            new LoginPage(getDriver())
    	                    .login(username, password)
    	                    .addProduct("Sauce Labs Backpack")
    	                    .goToCart()
    	                    .clickCheckout();

    	    checkout.fillDetails(firstName, lastName, postalCode);

    	    String error = checkout.erorrText();

    	    Assert.assertTrue(error.contains(expectedError));
    	}
    
    
}
