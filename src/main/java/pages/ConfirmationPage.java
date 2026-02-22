package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfirmationPage extends BasePage {

    WebDriver driver;

   private By confirmation = By.cssSelector(".complete-header");

    public ConfirmationPage(WebDriver driver) {
        super(driver);
    }

    public String getConfirmationText() {
        return getText(confirmation);
    }
}

