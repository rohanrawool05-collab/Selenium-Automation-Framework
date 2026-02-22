package base;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import utils.PropertyUtils;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public WebDriver getDriver() {
        return driver.get();
    }

    @BeforeMethod
    public void setup() {

        String browser = PropertyUtils.getProperty("browser");
        String url = PropertyUtils.getProperty("url");
        boolean headless = Boolean.parseBoolean(PropertyUtils.getProperty("headless"));

        

        if (browser == null || browser.isEmpty()) {
            browser = "chrome";
        }

        if (browser.equalsIgnoreCase("chrome")) {
        	
        	WebDriverManager.chromedriver().setup();

        	ChromeOptions options = new ChromeOptions();
        	
        	boolean headless1 = Boolean.parseBoolean(PropertyUtils.getProperty("headless"));
        	if(headless1) {
        		options.addArguments("--headless=new");
        	}
        	
        	options.addArguments("--guest");
        	options.addArguments("--disable-notifications");
        	options.addArguments("--disable-infobars");
        	options.addArguments("--disable-save-password-bubble");

        	driver.set(new ChromeDriver(options));


        } else if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver.set(new EdgeDriver());

        } else {
            WebDriverManager.chromedriver().setup();
            driver.set(new ChromeDriver());
        }

        getDriver().manage().window().maximize();
        getDriver().get(url);
    }

    @AfterMethod
    public void tearDown() {

        if (getDriver() != null) {
            getDriver().quit();
            driver.remove(); // Clean memory
        }
    }
    
}


