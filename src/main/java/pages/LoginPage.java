package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BaseTest;

public class LoginPage extends BasePage {

    private static final Logger logger = LogManager.getLogger(LoginPage.class);

    private By username = By.id("user-name");
    private By password = By.id("password");
    private By loginBtn = By.cssSelector(".submit-button");
    private By logBtn = By.id("login-button");
    private By text = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public ProductPage login(String user, String pass) {
        logger.info("Entering username");
        type(username, user);

        logger.info("Entering password");
        type(password, pass);

        logger.info("Clicking login button");
        click(loginBtn);

        logger.info("Login successful");
        return new ProductPage(driver);
    }
    
 

    public boolean isLoginButtonDisplayed() {
        return isDisplayed(logBtn);
    }
    
    public String getLoginValidationText() {
    	return getText(text);
    }
}

