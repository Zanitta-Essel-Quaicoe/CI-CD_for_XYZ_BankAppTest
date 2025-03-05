package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By bankManagerLoginButton = By.xpath("//button[contains(text(),'Bank Manager Login')]");
    private By customerLoginButton = By.xpath("//button[contains(text(),'Customer Login')]");
    private By loginPageHeader = By.xpath("//strong[contains(text(),'XYZ Bank')]");
    private By customerDropdown = By.id("userSelect"); // Dropdown for customer names
    private By loginButton = By.xpath("//button[contains(text(),'Login')]");

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Click "Bank Manager Login"
    public void clickBankManagerLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(bankManagerLoginButton)).click();
    }

    //  NEW: Click "Customer Login"
    public void clickCustomerLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(customerLoginButton)).click();
    }

    // Select a customer from dropdown
    public void selectCustomer(String customerName) {
        wait.until(ExpectedConditions.elementToBeClickable(customerDropdown)).click();
        driver.findElement(By.xpath("//option[contains(text(),'" + customerName + "')]")).click();
    }

    // Wait for customer dashboard to load after clicking login
    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(@class,'fontBig')]"))); // Wait for customer name label
    }


    //  Verify if login page is displayed (used for logout validation)
    public boolean isLoginPageDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(loginPageHeader)).isDisplayed();
    }
}
