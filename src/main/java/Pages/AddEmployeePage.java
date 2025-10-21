package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddEmployeePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    public AddEmployeePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    @FindBy(xpath = "//span[contains(.,'Admin')]")
    WebElement adminPage;

    @FindBy(xpath = "//button[.=' Add ']")
    WebElement createButton;

    @FindBy(xpath = "//span[normalize-space()='PIM']")
    WebElement pimPage;

    @FindBy(xpath = "//input[@placeholder='First Name']")
    WebElement firstNameField;

    @FindBy(xpath = "//input[@placeholder='Middle Name']")
    WebElement middleNameField;

    @FindBy(xpath = "//input[@placeholder='Last Name']")
    WebElement lastNameField;

//    @FindBy(xpath = "//span[@class='oxd-switch-input oxd-switch-input--active --label-right']")
//    WebElement loginCredentialSwitch;

    @FindBy(xpath = "(//input[@class='oxd-input oxd-input--active'])[3])")
    WebElement usernameField;

    @FindBy(xpath = "//input[@type='password'])[1]")
    WebElement passwordField;

    @FindBy(xpath = "//input[@type='password'])[2]")
    WebElement confirmPasswordField;

    @FindBy(css = "button[type='submit']")
    WebElement saveButton;

//    @FindBy(xpath = "//h6[contains(.,'" + firstName +"'))]")
//    WebElement employeeNameValidation;



    public void AddEmployee(String firstName, String middleName, String lastName, String username, String password,String confirmPassword ) throws InterruptedException {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOf(pimPage));
        pimPage.click();
        createButton.click();
        firstNameField.sendKeys(firstName);
        middleNameField.sendKeys(middleName);
        lastNameField.sendKeys(lastName);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("oxd-form-loader")));

        saveButton.click();

//       Actions actions = new Actions(driver);
//       actions.keyDown(Keys.ENTER).perform();

       Thread.sleep(1000); // Wait for the page to load

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h6[contains(.,'" + firstName + "')]"))));
        pimPage.click();
    }



}
