import Pages.AddEmployeePage;
import Pages.LoginPage;
import Utils.ConfigReader;
import Utils.ExcelReader;
import Utils.ScreenshotUtil;
import net.bytebuddy.build.Plugin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddEmployeeTest extends BaseTest {
    public static final Logger logger = LogManager.getLogger(AddEmployeeTest.class.getName());

    protected LoginPage loginPage;
    protected  AddEmployeePage addEmployeePage;
    @DataProvider(name = "employeeData")
    public Object[][] getEmployeeData() {return ExcelReader.readExcelData("TestData.xlsx", "EmployeeData");}

    @Test(priority = 1)
    public void testAdminLogin(){

        String AdminUsername = ConfigReader.getProperty("AdminUsername");
        String AdminPassword = ConfigReader.getProperty("AdminPassword");

         loginPage = new LoginPage(driver);
         loginPage.AdminLogin(AdminUsername, AdminPassword);


    }

    @Test(dataProvider = "employeeData", priority = 2)
    public void testAddEmployee(String testcase, String firstName, String middleName, String lastName, String employeeId, String username, String password, String confirmPassword, String expectedResult, String userRole, String status) throws InterruptedException, IOException {
        addEmployeePage = new AddEmployeePage(driver);

        addEmployeePage.AddEmployee(firstName, middleName, lastName, username, password, confirmPassword);

        ScreenshotUtil.takeScreenshot(driver, "AddEmployeeTest_" + testcase);
        logger.info("Executing test case: {} with first name: {}{}{} and username: {} and password: {}", testcase, firstName, middleName, lastName, username, password);


    }
}
