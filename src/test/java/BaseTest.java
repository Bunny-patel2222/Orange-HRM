import Utils.ConfigReader;
import Utils.DriverManager;
import Utils.ScreenshotUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    public WebDriver driver;
    public static WebDriverWait wait;
    private static final Logger logger = LogManager.getLogger(BaseTest.class);

    public void BrowserSetup() throws IOException {


        String Browser = ConfigReader.getProperty("Browser");

        logger.info("Setting up browser: {}", Browser);

        switch (Browser) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + Browser);
        }

        DriverManager.setDriver(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

    }



    @BeforeClass
    public void LaunchApp() throws IOException {
        logger.info("Launching application...");
        BrowserSetup();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        String URL = ConfigReader.getProperty("URL");
        System.out.println(URL);
        driver.get(URL);
       // wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("html head title"))));
        //ScreenshotUtil.takeScreenshot(driver, "BaseTest_LaunchApp");


    }

    @AfterClass
    public void TearDown(){

        if (driver != null){
            driver.quit();
            DriverManager.removeDriver();
        }
        logger.info("Browser closed successfully.");
    }
}
