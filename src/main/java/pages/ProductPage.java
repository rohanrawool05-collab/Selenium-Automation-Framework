package pages;

import java.util.List;
import org.openqa.selenium.*;

public class ProductPage extends BasePage{



    By products = By.cssSelector(".inventory_item");
    By cart = By.cssSelector(".shopping_cart_link");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public ProductPage addProduct(String productName) {

        List<WebElement> items = driver.findElements(products);

        WebElement prod = items.stream()
                .filter(p -> p.findElement(By.className("inventory_item_name"))
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
}

