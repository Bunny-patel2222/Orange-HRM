package Utils;

import org.openqa.selenium.WebDriver;

public class DriverManager {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    public static WebDriver getDriver() { return driver.get(); }
    public static void setDriver(WebDriver driverParam) { driver.set(driverParam);}
    public static void removeDriver() {
        driver.remove();
    }
}
