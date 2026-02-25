package tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.BaseTest;
import pages.LoginPage;
import pages.ProductPage;
import dataprovider.TestDataProviders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortValidationTest extends BaseTest {


    //Price Low to High
    @Test(dataProvider = "loginData",
          dataProviderClass = TestDataProviders.class)
    public void validatePriceLowToHigh(String username, String password) {

        SoftAssert soft = new SoftAssert();

        ProductPage productPage =
                new LoginPage(getDriver()).login(username, password);

        productPage.selectSortOption("lohi");

        List<Double> actual = productPage.getProductPrices();
        List<Double> expected = new ArrayList<>(actual);
        Collections.sort(expected);

        soft.assertEquals(actual, expected,
                "Price Low to High sorting failed");

        soft.assertAll();
    }

    
    
    //Price High to Low
    @Test(dataProvider = "loginData",
          dataProviderClass = TestDataProviders.class)
    public void validatePriceHighToLow(String username, String password) {

        SoftAssert soft = new SoftAssert();

        ProductPage productPage =
                new LoginPage(getDriver()).login(username, password);

        productPage.selectSortOption("hilo");

        List<Double> actual = productPage.getProductPrices();
        List<Double> expected = new ArrayList<>(actual);
        expected.sort(Collections.reverseOrder());

        soft.assertEquals(actual, expected,
                "Price High to Low sorting failed");

        soft.assertAll();
    }

    
    
    //Name A to Z
    @Test(dataProvider = "loginData",
          dataProviderClass = TestDataProviders.class)
    public void validateNameAToZ(String username, String password) {

        SoftAssert soft = new SoftAssert();

        ProductPage productPage =
                new LoginPage(getDriver()).login(username, password);

        productPage.selectSortOption("az");

        List<String> actual = productPage.getProductNames();
        List<String> expected = new ArrayList<>(actual);
        Collections.sort(expected);

        soft.assertEquals(actual, expected,
                "Name A to Z sorting failed");

        soft.assertAll();
    }

    

    //Name Z to A
    @Test(dataProvider = "loginData",
          dataProviderClass = TestDataProviders.class)
    public void validateNameZToA(String username, String password) {

        SoftAssert soft = new SoftAssert();

        ProductPage productPage =
                new LoginPage(getDriver()).login(username, password);

        productPage.selectSortOption("za");

        List<String> actual = productPage.getProductNames();
        List<String> expected = new ArrayList<>(actual);
        expected.sort(Collections.reverseOrder());

        soft.assertEquals(actual, expected,
                "Name Z to A sorting failed");

        soft.assertAll();
    }
}
