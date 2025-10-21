package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    protected WebDriver driver;
    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        // Initialize elements with PageFactory
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "input[name='username']")
    WebElement usernameField;

    @FindBy(css = "input[type='password']")
    WebElement passwordField;

    @FindBy(css = "button[type='submit']")
    WebElement submit;

    @FindBy(xpath = "//p[contains(normalize-space(),'Forgot your password?')]")
    WebElement forgotPassword;

    @FindBy(xpath = "//p[contains(normalize-space(),'Invalid')]")
    WebElement errormsg;

    @FindBy(xpath = "//span[contains(.,'Required')]")
    WebElement requiredField;


    public void login(String adminusername, String adminpassword) {
        usernameField.sendKeys(adminusername);
        passwordField.sendKeys(adminpassword);
        submit.click();
    }

    public void AdminLogin(String username, String password) {
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        submit.click();
    }
    public String getRequiredFieldMessage() {
        return requiredField.getText();
    }
    public String getErrorMessage() {
        return errormsg.getText();
    }

}
