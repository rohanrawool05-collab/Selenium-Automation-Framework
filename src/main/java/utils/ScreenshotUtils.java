package utils;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

public class ScreenshotUtils {

    public static String captureScreenshot(WebDriver driver, String testName) {

        File src = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.FILE);

        String path = System.getProperty("user.dir")
                + "/reports/" + testName + ".png";

        try {
            FileUtils.copyFile(src, new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return path;
    }
}
