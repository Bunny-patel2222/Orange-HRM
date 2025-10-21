import Pages.LoginPage;
import Utils.ExcelReader;
import org.openqa.selenium.WebDriver;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginTest extends BaseTest {
    // Initialize the LoginPage object
    public static final Logger logger = LogManager.getLogger(LoginTest.class.getName());

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        return ExcelReader.readExcelData("TestData.xlsx", "LoginData");
    }

    @Test(dataProvider = "loginData")
    public void testLogin(String testCase, String username, String password, String expectedResult) {
        LoginPage loginpage = new LoginPage(driver);

        loginpage.login(username, password);
        logger.info("Executing test case: " + testCase + " with username: " + username + " and password: " + password);
        if (expectedResult.equals("Success")) {
            // Positive test: Verify URL after login

            String PageTitle = driver.getTitle();

            Assert.assertTrue(PageTitle.contains("OrangeHRM"), "Test case '" + testCase + "': Expected successful login");
            logger.info("Test case '" + testCase + "': Login successful with username: " + username);

        } else if (expectedResult.equals("Blank")) {
            //check for blank field validation
            String requiredFieldMessage = loginpage.getRequiredFieldMessage();
            Assert.assertEquals(requiredFieldMessage, "Required", "Test case '" + testCase + "': Expected required field validation");
            logger.info("Test case '" + testCase + "': Blank field validation successful for username: " + username);

        } else {
            // Negative test: Verify error message
            String errorMessage = loginpage.getErrorMessage();
            Assert.assertEquals(errorMessage, "Invalid credentials");
           logger.info("Test case '" + testCase + "': Expected invalid credentials error for username: " + username);
        }

        // Refresh page to clear fields for the next test
        driver.navigate().refresh();
    }


}
