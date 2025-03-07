package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.github.javafaker.Faker;
import java.time.Duration;

public class AddCustomerPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor jsExecutor;
    private Faker faker;

    // Locators
    private By firstNameInput = By.xpath("//input[@placeholder='First Name']");
    private By lastNameInput = By.xpath("//input[@placeholder='Last Name']");
    private By postCodeInput = By.xpath("//input[@placeholder='Post Code']");
    private By addCustomerButton = By.xpath("//button[text()='Add Customer']");

    // Constructor
    public AddCustomerPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.jsExecutor = (JavascriptExecutor) driver;
        this.faker = new Faker();
    }

    // Fill customer details
    public void enterCustomerDetails(String firstName, String lastName, String postCode) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput)).sendKeys(firstName);
        driver.findElement(lastNameInput).sendKeys(lastName);
        driver.findElement(postCodeInput).sendKeys(postCode);
    }

    // Click "Add Customer" button without auto-accepting the alert
    public void clickAddCustomer() {
        jsExecutor.executeScript("arguments[0].click();", driver.findElement(addCustomerButton));
        try {
            wait.until(ExpectedConditions.alertIsPresent());
        } catch (TimeoutException e) {
            System.out.println("No alert appeared after clicking 'Add Customer'.");
        }
    }

    // Generate a random customer (for testing)
    public String[] generateRandomCustomer() {
        return new String[]{faker.name().firstName(), faker.name().lastName(), faker.address().zipCode()};
    }

    // Get the alert text for validation (handles timeout gracefully)
    public String getAlertText() {
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            return driver.switchTo().alert().getText();
        } catch (TimeoutException e) {
            System.out.println("No alert appeared — returning null.");
            return null; // No alert was present
        }
    }

    // Accept alert if it's present
    public void acceptAlertIfPresent() {
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept();
        } catch (TimeoutException e) {
            System.out.println("No alert to accept.");
        }
    }

    // Explicit method for forced alert acceptance (for tests expecting an alert)
    public void acceptAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }
}
