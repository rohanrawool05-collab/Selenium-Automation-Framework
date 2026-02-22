package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {



    By username = By.id("user-name");
    By password = By.id("password");
    By loginBtn = By.cssSelector(".submit-button");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public ProductPage login(String user, String pass) {
        type(username,user);
        type(password,pass);
        click(loginBtn);
        return new ProductPage(driver);
    }
}

