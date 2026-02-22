package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.WaitUtils;

public class BasePage {

    protected WebDriver driver;
    protected WaitUtils wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver);
    }

    protected void click(By locator) {
        wait.waitForVisibility(locator);
        driver.findElement(locator).click();
    }

    protected void type(By locator, String text) {
        wait.waitForVisibility(locator);
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
    }

    protected String getText(By locator) {
        wait.waitForVisibility(locator);
        return driver.findElement(locator).getText();
    }
}

