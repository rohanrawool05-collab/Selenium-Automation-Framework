package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {

    private By checkoutBtn = By.id("checkout");
    private By removeBtn = By.cssSelector(".cart_button");
    private By name = By.className("inventory_item_name");
    private By cartBadge = By.className("shopping_cart_badge");

    public CartPage(WebDriver driver) {
        super(driver);
    }
    
    public String getCartBadge() {
    	return driver.findElement(cartBadge).getText();
    }

    public CheckoutPage clickCheckout() {
        click(checkoutBtn);
        return new CheckoutPage(driver);
    }
    
    public void removeProduct() {
    	click(removeBtn);
    }
    
    public boolean isProductPresent() {
    	return driver.findElements(name).size() > 0;
    }
}

