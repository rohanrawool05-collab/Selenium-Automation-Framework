package pages;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class ProductPage extends BasePage{



    By products = By.cssSelector(".inventory_item");
    By cart = By.cssSelector(".shopping_cart_link");
    By menuBtn = By.id("react-burger-menu-btn");
    By logoutLink = By.id("logout_sidebar_link");
    private By sortDropdown = By.className("product_sort_container");
    private By productNames = By.className("inventory_item_name");
    private By productPrices = By.className("inventory_item_price");

    public LoginPage logout() {
        click(menuBtn);
        click(logoutLink);
        return new LoginPage(driver);
    }

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public ProductPage addProduct(String productName) {

        List<WebElement> items = driver.findElements(products);

        WebElement prod = items.stream()
                .filter(p -> p.findElement(productNames)
                        .getText().equals(productName))
                .findFirst().orElse(null);

        if (prod != null) {
            prod.findElement(By.cssSelector(".btn")).click();
        }
        return this;
    }

    public CartPage goToCart() {
        click(cart);
        return new CartPage(driver);
    }
    
    public void selectSortOption(String value) {
        Select select = new Select(driver.findElement(sortDropdown));
        select.selectByValue(value);
    }

    public List<String> getProductNames() {
        return driver.findElements(productNames)
                .stream()
                .map(e -> e.getText())
                .collect(Collectors.toList());
    }

    public List<Double> getProductPrices() {
        return driver.findElements(productPrices)
                .stream()
                .map(e -> Double.parseDouble(e.getText().replace("$", "")))
                .collect(Collectors.toList());
    }
}


