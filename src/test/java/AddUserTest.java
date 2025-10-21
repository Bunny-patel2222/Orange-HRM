import Pages.AddEmployeePage;
import Pages.AddUserPage;
import Pages.LoginPage;
import Utils.ConfigReader;
import Utils.ExcelReader;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AddUserTest extends BaseTest {

    protected LoginPage loginPage;
    protected AddUserPage addUserPage;
    protected AddEmployeePage addEmployeePage;

    @DataProvider(name = "userData")
    public Object[][] getUserData() {
        return ExcelReader.readExcelData("TestData.xlsx", "EmployeeData");

    }

    @Test(priority = 1)
    public void testAdminLogin() {

        String AdminUsername = ConfigReader.getProperty("AdminUsername");
        String AdminPassword = ConfigReader.getProperty("AdminPassword");

        loginPage = new LoginPage(driver);
        loginPage.AdminLogin(AdminUsername, AdminPassword);


    }

    @Test(dataProvider = "userData", priority = 2)
    public void testAddUser(String testcase, String firstName, String middleName, String lastName, String employeeId, String username, String Password, String confirmPassword, String expectedResult, String userrole, String status) {
        addUserPage = new AddUserPage(driver);
        addUserPage.addUser(userrole, firstName, status, username, Password, confirmPassword);


        if (expectedResult.trim().equalsIgnoreCase("Success")) {

            String successMessage = addUserPage.getsucessMessage();
            System.out.println("success" + expectedResult+ testcase+ successMessage);
            Assert.assertEquals(successMessage, "Success", "Test case '" + testcase + "': Expected successful user creation");


        } else if (expectedResult.trim().equalsIgnoreCase("Required_username")) {
            String requiredUsernameMessage = addUserPage.getRequiredErrorMessage();
            System.out.println("Required_username" + expectedResult+ testcase+ requiredUsernameMessage);
            Assert.assertEquals(requiredUsernameMessage, "Required", "Test case '" + testcase + "': Expected required username validation");

        } else if (expectedResult.trim().equalsIgnoreCase("Required_confirm")) {
            String requiredConfirmMessage = addUserPage.getRequiredErrorMessage();
            System.out.println("Required_confirm" + expectedResult+ testcase+ requiredConfirmMessage);

            Assert.assertEquals(requiredConfirmMessage, "Required", "Test case '" + testcase + "': Expected required confirm password validation");

        } else if (expectedResult.trim().equalsIgnoreCase("Password_do_not_match")) {
            String passwordMismatchMessage = addUserPage.getPasswordMismatchErrorMessage();
            System.out.println("Password do not match" + expectedResult+ testcase+ passwordMismatchMessage);
            Assert.assertEquals(passwordMismatchMessage, "Passwords do not match", "Test case '" + testcase + "': Expected password mismatch validation");

        } else if (expectedResult.trim().equalsIgnoreCase("Required_password")) {
            String  requiredPasswordMessage = addUserPage.getRequiredErrorMessage();
            System.out.println("Required message" + expectedResult+ testcase+ requiredPasswordMessage);
            Assert.assertEquals(requiredPasswordMessage, "Required", "Test case '" + testcase + "': Expected required password validation");
        } else if (expectedResult.trim().equalsIgnoreCase("User_already_exists")) {
            String userAlreadyExistsMessage = addUserPage.getUserAlreadyExistsErrorMessage();
            Assert.assertEquals(userAlreadyExistsMessage, "Already exists", "Test case '" + testcase + "': Expected user already exists validation");
        }
    }


}