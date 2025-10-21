package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddUserPage {
    protected WebDriver driver;
    protected  WebDriverWait wait;
    public AddUserPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[.='Admin']")
    WebElement adminPage;

    @FindBy(xpath = "//button[.=' Add ']")
    WebElement createButton;

    @FindBy(xpath = "//label[contains(text(), 'User Role')]/parent::div/following-sibling::div//div[contains(@class,'oxd-select-text')]")
    WebElement userRoleDropdown;

    @FindBy(xpath = "//input[@placeholder='Type for hints...']")
    WebElement employeeNameField;

    @FindBy(xpath = "//label[contains(text(), 'Status')]/parent::div/following-sibling::div//div[contains(@class,'oxd-select-text')]")
    WebElement statusDropdown;

    // Select statusDropdown1 = new Select(driver.findElement(By.xpath("//label[contains(text(), 'Status')]/parent::div/following-sibling::div//div[contains(@class,'oxd-select-text')]")));


    @FindBy(xpath = "//label[contains(text(), 'Username')]/parent::div/following-sibling::div//input")
    WebElement usernameField;

    @FindBy(xpath = "//label[contains(text(), 'Password')]/parent::div/following-sibling::div//input")
    WebElement passwordField;

    @FindBy(xpath = "//label[contains(text(), 'Confirm Password')]/parent::div/following-sibling::div//input")
    WebElement confirmPasswordField;

    @FindBy(xpath = "//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message'][normalize-space()='Required']")
    WebElement requiredErrorMessage;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement saveButton;

    @FindBy(xpath = "//span[.='Passwords do not match']")
    WebElement passwordMismatchErrorMessage;

    @FindBy(xpath = "//p[contains(@class,'oxd-text--toast-message') and contains(text(),'Successfully')]")
    WebElement successMessage;

    @FindBy(xpath = "//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']")
    WebElement userAlreadyExistsErrorMessage;


    public void addUser(String userRole, String firstName, String status, String username, String password, String confirmPassword) {
        adminPage.click();
        createButton.click();
        userRoleDropdown.click();
        //    userRoleDropdown.findElement(By.xpath("//div[@role='listbox']//span[text()='ESS']")).click();
        wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='listbox']")));

        driver.findElement(By.xpath("//div[@role='listbox']//span[text()='" + userRole + "']")).click();


        employeeNameField.sendKeys(firstName);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='option']//span[contains(.,'"+ firstName +"')]")));

        driver.findElement(By.xpath("//div[@role='option']//span[contains(.,'"+ firstName +"')]")).click();


        statusDropdown.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='option']")));
         driver.findElement(By.xpath("//div[@role='option']//span[text()='" + status + "']")).click();


        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        confirmPasswordField.sendKeys(confirmPassword);

        saveButton.click();
    }

    public String getsucessMessage() {
       // wait.until(ExpectedConditions.visibilityOf(successMessage));
        return successMessage.getText();
    }

    public String getRequiredErrorMessage() {
        return requiredErrorMessage.getText();
    }
    public String getPasswordMismatchErrorMessage() {
        return passwordMismatchErrorMessage.getText();
    }
    public String getUserAlreadyExistsErrorMessage() {
        return userAlreadyExistsErrorMessage.getText();
    }
}
