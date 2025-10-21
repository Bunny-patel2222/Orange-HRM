package Utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotUtil {


    private static final String SCREENSHOT_FOLDER = System.getProperty("user.dir") + File.separator + "screenshots";

    public static File takeScreenshot(WebDriver driver, String methodName) throws IOException {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String fileName = methodName + "_" + timestamp + ".png";
        String fullPath = SCREENSHOT_FOLDER + File.separator + fileName;

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destinationFile = new File(fullPath);

        FileUtils.copyFile(screenshot, destinationFile);
        return destinationFile;
    }

    public static byte[] ScreenshotAsBytes(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
